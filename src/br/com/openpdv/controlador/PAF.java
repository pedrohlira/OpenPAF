package br.com.openpdv.controlador;

import br.com.openpdv.modelo.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

/**
 * Classe que representa o PAF no sistema e todas suas funcionalidiades.
 *
 * @author Pedro H. Lira
 */
public class PAF {

    private static PAF paf;
    private static Logger log;
    private AsyncCallback<String[]> async;

    /**
     * Construtor padrao.
     */
    private PAF() {
        log = Logger.getLogger(PAF.class);
    }

    /**
     * Metodo que retorna a instancia unica do PAF.
     *
     * @return o objeto PAF.
     */
    public static PAF getInstancia() {
        return getInstancia(null);
    }

    /**
     * Metodo que retorna a instancia unica do PAF.
     *
     * @param monitor informe a classe que vai responder ao retorno.
     * @return o objeto PAF.
     */
    public static PAF getInstancia(AsyncCallback<String[]> async) {
        if (paf == null) {
            paf = new PAF();
        }

        paf.async = async;
        return paf;
    }

    /**
     * Metodo que informa o path dos arquivos e caso nao exista ja cria-o.
     *
     * @return uma String com o caminho do path ou null caso nao consiga criar.
     */
    public String getPathArquivos() {
        StringBuilder path = new StringBuilder(System.getProperty("user.dir"));
        path.append(System.getProperty("file.separator"));
        path.append("arquivos");

        File f = new File(path.toString());
        if (!f.exists()) {
            f.mkdir();
        }

        return path.toString();
    }

    /**
     * Metodo que adiciona a assinatura ao final do arquivo.
     *
     * @param arquivo path completo do arquivo a ser assinado.
     */
    public void assinarArquivoEAD(String arquivo) throws Exception {
        // configurando a chave
        byte[] privateKeyBytes = DatatypeConverter.parseBase64Binary(ChavePrivada.CHAVE);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        KeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // lendo dados do arquivo para assinar
        byte[] dados;
        try (FileInputStream inArquivo = new FileInputStream(arquivo)) {
            dados = new byte[inArquivo.available()];
            inArquivo.read(dados);
        }

        // recuperando assinatura do arquivo
        Signature sig = Signature.getInstance("MD5withRSA");
        sig.initSign(privateKey);
        sig.update(dados);
        byte[] ass = sig.sign();

        // transformando em hexadecimal
        StringBuilder hexa = new StringBuilder();
        for (int i = 0; i < ass.length; i++) {
            int parteAlta = ((ass[i] >> 4) & 0xf) << 4;
            int parteBaixa = ass[i] & 0xf;
            if (parteAlta == 0) {
                hexa.append('0');
            }
            hexa.append(Integer.toHexString(parteAlta | parteBaixa));
        }

        // adicionando a assinatura no arquivo
        String ead = "EAD" + hexa.toString().toUpperCase();
        try (FileWriter outArquivo = new FileWriter(arquivo, true)) {
            outArquivo.write(ead);
            outArquivo.flush();
        }
    }

    /**
     * Metodo que emite a leitura X.
     */
    public void leituraX() {
        // verifica o estado
        String[] resp = ECF.getInstancia().enviar(EComandoECF.ECF_Estado);
        if (resp[0].equals("OK") && !resp[1].equals("estLivre")) {
            ECF.getInstancia().enviar(EComandoECF.ECF_CorrigeEstadoErro);
        }

        ECF.getInstancia(async).enviar(EComandoECF.ECF_LeituraX);
    }

    /**
     * Metodo que gera o arquivo com os dados dos produtos.
     *
     * @param tabprod um objeto contendo os dados.
     * @param produtos a lista de dados dos produtos.
     * @return o path do arquivo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public String gerarTabProdutos(TabelaProdutos tabprod, List<Produto> produtos) throws Exception {
        // gerar o arquivo
        String path = getPathArquivos() + System.getProperty("file.separator") + "TabelaProdutos.txt";
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(getClass().getResourceAsStream("/br/com/openpdv/modelo/TabelaProdutos.xml"));
        BeanWriter bw = factory.createWriter("TabelaProdutos", fw);

        // escevendo na memoria
        bw.write("P1", tabprod);
        for (Produto prod : produtos) {
            bw.write("P2", prod);
            bw.flush();
        }
        bw.write("P9", tabprod);
        bw.flush();

        // assinando o arquivo
        assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que gera o arquivo com os dados do estoque.
     *
     * @param estoque um objeto contendo os dados.
     * @param produtos a lista de dados dos produtos.
     * @return o path do arquivo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public String gerarEstoque(Estoque estoque, List<Produto> produtos) throws Exception {
        // gerar o arquivo
        String path = getPathArquivos() + System.getProperty("file.separator") + "Estoque.txt";
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(getClass().getResourceAsStream("/br/com/openpdv/modelo/Estoque.xml"));
        BeanWriter bw = factory.createWriter("Estoque", fw);

        // escevendo na memoria
        bw.write("E1", estoque);
        for (Produto prod : produtos) {
            bw.write("E2", prod);
            bw.flush();
        }
        bw.write("E9", estoque);
        bw.flush();

        // assinando o arquivo
        assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que emite o relatorio de identificacao do PAF.
     *
     * @exception Exception dispara uma excecao caso nao consiga.
     */
    public void emitirIdentificaoPAF(ISisSh sh, ISisPaf paf) throws Exception {
        ECF ecf = ECF.getInstancia();

        // verifica o estado
        String[] resp = ecf.enviar(EComandoECF.ECF_Estado);
        if (resp[0].equals("OK") && resp[1].equals("estRelatorio")) {
            ecf.enviar(EComandoECF.ECF_CorrigeEstadoErro);
        }

        // abrindo o relatorio
        resp = ecf.enviar(EComandoECF.ECF_AbreRelatorioGerencial);
        if (resp[0].equals("ERRO")) {
            throw new Exception(resp[1]);
        }

        // cabecalho
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaDupla);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE><N>IDENTIFICACAO DO PAF-ECF</N></CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaDupla);
        // dados da sh
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NUMERO LAUDO..: " + paf.getSisPafLaudo());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "CNPJ..........: " + sh.getSisShCnpj());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "RAZAO SOCIAL..: " + sh.getSisShRazao());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "ENDERECO......: " + sh.getSisShLogradouro());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NUMERO........: " + sh.getSisShNumero());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "COMPLEMENTO...: " + sh.getSisShComplemento());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "BAIRRO........: " + sh.getSisShBairro());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "CEP...........: " + sh.getSisShCep());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "CIDADE........: " + sh.getSisShCidade());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "UF............: " + sh.getSisShUf());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TELEFONE......: " + sh.getSisShFone());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "CONTATO.......: " + sh.getSisShResponsavel());
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // identifica o paf
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>IDENTIFICACAO DO PAF-ECF</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NOME COMERCIAL............: " + paf.getSisPafNome());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "VERSAO DO PAF-ECF.........: " + paf.getSisPafVersao());
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // principal exe
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>PRINCIPAL ARQUIVO EXECUTAVEL</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NOME....: " + paf.getSisPafExe());
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "MD5.....: " + paf.getSisPafMd5());
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // arquivo txt e versao er
        // TODO definir o nome do arquivo e o MD5
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>ARQUIVO TEXTO</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NOME....: ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "MD5.....: ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "VERSAO ER PAF-ECF.........: " + paf.getSisPafEr());
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // ecf autoriazidos
        // TODO definir as ecfs autorizadas
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>RELACAO DOS ECF AUTORIZADOS</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "SERIE....: ");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");

        // fechando o relatorio
        resp = ecf.enviar(EComandoECF.ECF_FechaRelatorio);
        if (resp[0].equals("ERRO")) {
            ecf.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }
    }

    /**
     * Metodo que emite o relatorio de parametros de configuracao.
     *
     * @exception Exception dispara uma excecao caso nao consiga.
     */
    public void emitirConfiguracao() throws Exception {
        ECF ecf = ECF.getInstancia();
        // verifica o estado
        String[] resp = ecf.enviar(EComandoECF.ECF_Estado);
        if (resp[0].equals("OK") && resp[1].equals("estRelatorio")) {
            ecf.enviar(EComandoECF.ECF_CorrigeEstadoErro);
        }

        // abrindo o relatorio
        resp = ecf.enviar(EComandoECF.ECF_AbreRelatorioGerencial);
        if (resp[0].equals("ERRO")) {
            throw new Exception(resp[1]);
        }

        // cabecalho
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaDupla);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE><N>PARAMETROS DE CONFIGURACAO</N></CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaDupla);
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>IDENTIFICACAO E CARACTERISTICAS</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>DO PROGRAMA APLICATIVO FISCAL</CE>");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TODAS AS PARAMETRIZACOES RELACIONADAS NESTE");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "RELATORIO SAO DE CONFIGURACAO INACESSIVEL AO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "USUARIO DO PAF-ECF NAO E DOCUMENTO FISCAL.");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "A ATIVACAO OU NAO DESTES PARAMETROS E DETERMINADA PELA UNIDADE FEDERADA E SOMENTE PODE SER FEITA");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "PELA INTERVENCAO DA EMPRESA DESENVOLVEDORA DO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "PAF-ECF");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // funcionalidades
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>FUNCIONALIDADES</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TIPO DE FUNCIONAMENTO..........: PARAMETRIZAVEL");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TIPO DE DESENVOLVIMENTO........: COMERCIALIZAVEL");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "INTEGRACAO DO PAF-ECF..........: REST-FULL");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // nao concomitancia
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>PARAMETROS PARA NAO CONCOMITANCIA</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "PRE-VENDA......................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "DAV POR ECF....................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "DAV IMPRESSORA NAO FISCAL......: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "DAV-OS.........................: NAO");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // aplicacoes especiais
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>APLICACOES ESPECIAIS</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TAB. INDICE TECNICO DE PRODUCAO...........: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "POSTO REVENDEDOR DE COMBUSTIVEIS..........: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "BAR, RESTAURANTE E SIMILAR................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "ARMACIA DE MANIPULACAO....................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "OFICINA DE CONSERTO.......................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TRANSPORTE DE PASSAGEIROS.................: NAO");
        // unidade federada
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>A CRITERIO DA UNIDADE FEDERADA</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "REQUISITO XVIII - Tela Consulta de Preco:");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TOTALIZACAO DOS VALORES DA LISTA: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TRANSFORMACAO DAS INFORMACOES EM PRE-VENDA: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TRANSFORMACAO DAS INFORMACOES EM DAV: NAO");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "REQUISITO XXII - PAF-ECF Integrado ao ECF");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NNAO COINCIDENCIA GT(ECF) x ARQUIVO CRIPTOGRAFADO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "RECOMPOE VALOR DO GT ARQUIVO CRIPTOGRAFADO: NAO");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");

        // fechando o relatorio
        resp = ecf.enviar(EComandoECF.ECF_FechaRelatorio);
        if (resp[0].equals("ERRO")) {
            ecf.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }
    }
}
