package br.com.openpdv.controlador;

import br.com.openpdv.modelo.Pagamento;
import br.com.openpdv.modelo.anexo.iv.AnexoIV;
import br.com.openpdv.modelo.anexo.iv.E2;
import br.com.openpdv.modelo.anexo.v.AnexoV;
import br.com.openpdv.modelo.anexo.v.P2;
import br.com.openpdv.modelo.anexo.x.AnexoX;
import br.com.openpdv.modelo.anexo.x.N3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map.Entry;
import java.util.*;
import javax.xml.bind.DatatypeConverter;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.jasypt.util.digest.Digester;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * Classe que representa o PAF no sistema e todas suas funcionalidiades.
 *
 * @author Pedro H. Lira
 */
public class PAF {

    /*
     * Propriedades do arquivo auxiliar.
     */
    public static final Properties AUXILIAR = new Properties();

    /**
     * Metodo de acao externa usado somente para criptografar o arquivo
     * auxiliar.
     *
     * @param args um array sendo o primeiro o path completo do arquivo
     * auxiliar.properties
     */
    public static void main(String[] args) {
        File arquivo = new File(args[0]);
        try (FileInputStream fis = new FileInputStream(arquivo)) {
            AUXILIAR.load(fis);
            criptografarAuxiliar(args[0].replace("properties", "txt"));
        } catch (Exception ex) {
            System.out.println("Nao foi possivel ler ou gerar o arquivo auxiliar.");
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Metodo que criptografa o arquivo auxiliar do sistema.
     *
     * @param path local de geracao do arquivo, se null salva no padrao.
     * @throws Exception dispara caso nao consiga.
     */
    public static void criptografarAuxiliar(String path) throws Exception {
        // recuperando os valores
        StringBuilder sb = new StringBuilder();
        Enumeration nomes = AUXILIAR.propertyNames();
        for (; nomes.hasMoreElements();) {
            String nome = (String) nomes.nextElement();
            String valor = (String) AUXILIAR.getProperty(nome);
            sb.append(nome).append("=").append(valor).append("\n");
        }

        // encriptando
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(ChavePrivada.VALOR);
        String dados = encryptor.encrypt(sb.toString());

        // salva o arquivo
        if (path == null) {
            path = "conf" + System.getProperty("file.separator") + "auxiliar.txt";
        }
        try (FileWriter outArquivo = new FileWriter(path)) {
            outArquivo.write(dados);
            outArquivo.flush();
        }
    }

    /**
     * Metodo que descriptografa o arquivo auxiliar do sistema.
     *
     * @throws Exception dispara caso nao consiga.
     */
    public static void descriptografarAuxiliar() throws Exception {
        // lendo dados do arquivo para assinar
        byte[] bytes;
        try (FileInputStream inArquivo = new FileInputStream("conf" + System.getProperty("file.separator") + "auxiliar.txt")) {
            bytes = new byte[inArquivo.available()];
            inArquivo.read(bytes);
        }

        // descriptografando
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(ChavePrivada.VALOR);
        String dados = encryptor.decrypt(new String(bytes));

        // inserindo os valores
        AUXILIAR.clear();
        String[] props = dados.split("\n");
        for (String prop : props) {
            String[] chaveValor = prop.split("=");
            AUXILIAR.put(chaveValor[0], chaveValor[1]);
        }
    }

    /**
     * Metodo que informa o path dos arquivos e caso nao exista ja cria-o.
     *
     * @return uma String com o caminho do path ou null caso nao consiga criar.
     */
    public static String getPathArquivos() {
        StringBuilder path = new StringBuilder(System.getProperty("user.dir"));
        path.append(System.getProperty("file.separator"));
        path.append("arquivos");
        path.append(System.getProperty("file.separator"));

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
    public static void assinarArquivoEAD(String arquivo) throws Exception {
        // configurando a chave
        byte[] privateKeyBytes = DatatypeConverter.parseBase64Binary(ChavePrivada.VALOR);
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

        // adicionando a assinatura no arquivo
        String ead = "EAD" + new BigInteger(1, ass).toString(16).toUpperCase();
        try (FileWriter outArquivo = new FileWriter(arquivo, true)) {
            outArquivo.write(ead);
            outArquivo.flush();
        }
    }

    /**
     * Metodo que gera o MD5 de um arquivo informado.
     *
     * @param arquivo o path completo do arquivo.
     * @return o codigo MD5 do arquivo.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarMD5(String arquivo) throws Exception {
        // lendo dados do arquivo para assinar
        byte[] dados;
        try (FileInputStream inArquivo = new FileInputStream(arquivo)) {
            dados = new byte[inArquivo.available()];
            inArquivo.read(dados);
        }

        // gerando o MD5
        Digester md5 = new Digester("MD5");
        return new BigInteger(1, md5.digest(dados)).toString(16).toUpperCase();
    }

    /**
     * Metodo que gera o arquivo exigido no anexo X do (ER-PAF-ECF)
     *
     * @param anexoX o modelo de dados a ser gravado no arquivo.
     * @return o MD5 do arquivo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarArquivos(AnexoX anexoX) throws Exception {
        // gerar o arquivo
        String path = getPathArquivos() + "arquivos.txt";
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/openpdv/modelo/anexo/x/AnexoX.xml"));
        BeanWriter bw = factory.createWriter("AnexoX", fw);

        // escevendo no arquivo
        bw.write(anexoX.getN1());
        bw.write(anexoX.getN2());
        for (N3 n3 : anexoX.getListaN3()) {
            bw.write(n3);
            bw.flush();
        }
        bw.write(anexoX.getN9());
        bw.flush();

        // assinando o arquivo
        assinarArquivoEAD(path);
        return gerarMD5(path);
    }

    /**
     * Metodo que emite a leitura X.
     */
    public static void leituraX() throws Exception {
        // verifica o estado
        String[] resp = ECF.getInstancia().enviar(EComandoECF.ECF_Estado);
        if (resp[0].equals("OK") && !resp[1].equals("estLivre")) {
            ECF.getInstancia().enviar(EComandoECF.ECF_CorrigeEstadoErro);
        }

        resp = ECF.getInstancia().enviar(EComandoECF.ECF_LeituraX);
        if (resp[0].equals("ERRO")) {
            throw new Exception(resp[1]);
        }
    }

    /**
     * Metodo que gera o arquivo exigido no anexo V do (ER-PAF-ECF)
     *
     * @param anexoV o modelo de dados a ser gravado no arquivo.
     * @return o path do arquivo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarTabProdutos(AnexoV anexoV) throws Exception {
        // gerar o arquivo
        String path = getPathArquivos() + "TabelaProdutos.txt";
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/openpdv/modelo/anexo/v/AnexoV.xml"));
        BeanWriter bw = factory.createWriter("AnexoV", fw);

        // escevendo no arquivo
        bw.write(anexoV.getP1());
        for (P2 p2 : anexoV.getListaP2()) {
            bw.write(p2);
            bw.flush();
        }
        bw.write(anexoV.getP9());
        bw.flush();

        // assinando o arquivo
        assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que gera o arquivo exigido no anexo IV do (ER-PAF-ECF)
     *
     * @param anexoIV o modelo de dados a ser gravado no arquivo.
     * @return o path do arquivo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarEstoque(AnexoIV anexoIV) throws Exception {
        // gerar o arquivo
        String path = getPathArquivos() + "Estoque.txt";
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/openpdv/modelo/anexo/iv/AnexoIV.xml"));
        BeanWriter bw = factory.createWriter("AnexoIV", fw);

        // escevendo no arquivo
        bw.write(anexoIV.getE1());
        for (E2 e2 : anexoIV.getListaE2()) {
            bw.write(e2);
            bw.flush();
        }
        bw.write(anexoIV.getE9());
        bw.flush();

        // assinando o arquivo
        assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que emite o relatorio dos meios de pagamentos.
     *
     * @param inicio data de inicio do relatorio.
     * @param fim data de fim do relatorio.
     * @param pagamentos lista de pagamentos agrupados e ordenados pela data e
     * identificacao.
     * @throws Exception dispara uma exececao caso nao consiga.
     */
    public static void emitirMeiosPagamentos(String inicio, String fim, List<Pagamento> pagamentos) throws Exception {
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
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE><N>MEIOS DE PAGAMENTO</N></CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE><N>PERIODO DE " + inicio + " A " + fim + "</N></CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaDupla);
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");

        // dados por dia
        String aux = null;
        Double subTotal = 0.00;
        NumberFormat nf = DecimalFormat.getCurrencyInstance();
        Map<String, Double> total = new HashMap<>();
        for (Pagamento pag : pagamentos) {
            if (!pag.getData().equals(aux)) {
                if (aux != null) {
                    // fechando um dia
                    ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
                    ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, formataTexto("SUB-TOTAL", " ", 30, true) + nf.format(subTotal));
                    ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
                    subTotal = 0.00;
                }
                // abrindo um dia
                ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<N>DATA DE ACUMULACAO: </N> " + pag.getData());
                ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, formataTexto("IDENTIFICACAO", " ", 20, true)
                        + formataTexto("TIPO", " ", 10, true) + "VALOR");
                ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
                aux = pag.getData();
            }
            // dados do dia
            ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, formataTexto(pag.getIdentificacao().toUpperCase(), " ", 20, true)
                    + formataTexto(pag.getTipo(), " ", 10, true) + nf.format(pag.getValor()));
            subTotal += pag.getValor();
            double tot = total.containsKey(pag.getIdentificacao()) ? total.get(pag.getIdentificacao()) : 0.00;
            total.put(pag.getIdentificacao(), tot + pag.getValor());
        }
        // fechando o ultimo dia
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, formataTexto("SUB-TOTAL", " ", 30, true) + nf.format(subTotal));
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        subTotal = 0.00;

        // rodape
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>TOTAL GERAL</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE><N>PERIODO DE " + inicio + " A " + fim + "</N></CE>");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, formataTexto("IDENTIFICACAO", " ", 30, true) + "VALOR");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        for (Entry<String, Double> tot : total.entrySet()) {
            ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, formataTexto(tot.getKey(), " ", 30, true) + nf.format(tot.getValue()));
            subTotal += tot.getValue();
        }
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, formataTexto("TOTAL", " ", 30, true) + nf.format(subTotal));

        // fechando o relatorio
        resp = ecf.enviar(EComandoECF.ECF_FechaRelatorio);
        if (resp[0].equals("ERRO")) {
            ecf.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }
    }

    /**
     * Metodo que emite o relatorio de identificacao do PAF.
     *
     * @exception Exception dispara uma excecao caso nao consiga.
     */
    public static void emitirIdentificaoPAF() throws Exception {
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
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NUMERO LAUDO..: " + AUXILIAR.getProperty("out.laudo"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "CNPJ..........: " + AUXILIAR.getProperty("sh.cnpj"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "RAZAO SOCIAL..: " + AUXILIAR.getProperty("sh.razao"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "ENDERECO......: " + AUXILIAR.getProperty("sh.logradouro"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NUMERO........: " + AUXILIAR.getProperty("sh.numero"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "COMPLEMENTO...: " + AUXILIAR.getProperty("sh.complemento"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "BAIRRO........: " + AUXILIAR.getProperty("sh.bairro"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "CEP...........: " + AUXILIAR.getProperty("sh.cep"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "CIDADE........: " + AUXILIAR.getProperty("sh.cidade"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "UF............: " + AUXILIAR.getProperty("sh.uf"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TELEFONE......: " + AUXILIAR.getProperty("sh.fone"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "EMAIL.........: " + AUXILIAR.getProperty("sh.email"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "CONTATO.......: " + AUXILIAR.getProperty("sh.contato"));
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // identifica o paf
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>IDENTIFICACAO DO PAF-ECF</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NOME COMERCIAL.....: " + AUXILIAR.getProperty("paf.nome"));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "VERSAO DO PAF-ECF..: " + AUXILIAR.getProperty("paf.versao"));
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // principal exe
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>PRINCIPAL ARQUIVO EXECUTAVEL</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NOME....: OpenPDV.jar");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "MD5.....: " + AUXILIAR.getProperty("paf.md5"));
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // arquivo txt e versao er
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>ARQUIVO TEXTO</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NOME....: arquivos.txt");
        String pathArquivo = getPathArquivos() + "arquivos.txt";
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "MD5.....: " + gerarMD5(pathArquivo));
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "VERSAO ER PAF-ECF........: " + AUXILIAR.getProperty("paf.er"));
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // ecf autoriazidos
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>RELACAO DOS ECF AUTORIZADOS</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "SERIE....: " + AUXILIAR.getProperty("ecf.serie"));
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
    public static void emitirConfiguracao() throws Exception {
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
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TODAS AS PARAMETRIZACOES RELACIONADAS NESTE     ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "RELATORIO SAO DE CONFIGURACAO INACESSIVEL AO    ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "USUARIO DO PAF-ECF NAO E DOCUMENTO FISCAL.      ");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "ATIVACAO OU NAO DESTES PARAMETROS E DETERMINADA ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "PELA UNIDADE FEDERADA E SOMENTE PODE SER FEITA  ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "PELA INTERVENCAO DA EMPRESA DESENVOLVEDORA DO   ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "PAF-ECF.");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // funcionalidades
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>FUNCIONALIDADES</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TIPO DE FUNCIONAMENTO..........: PARAMETRIZAVEL ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TIPO DE DESENVOLVIMENTO........: COMERCIALIZAVEL");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "INTEGRACAO DO PAF-ECF..........: REST           ");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // nao concomitancia
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>PARAMETROS PARA NAO CONCOMITANCIA</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "PRE-VENDA..................................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "DAV POR ECF................................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "DAV IMPRESSORA NAO FISCAL..................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "DAV-OS.....................................: NAO");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        // aplicacoes especiais
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>APLICACOES ESPECIAIS</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TAB. INDICE TECNICO DE PRODUCAO............: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "POSTO REVENDEDOR DE COMBUSTIVEIS...........: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "BAR - RESTAURANTE - SIMILAR................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "FARMACIA DE MANIPULACAO....................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "OFICINA DE CONSERTO........................: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TRANSPORTE DE PASSAGEIROS..................: NAO");
        // unidade federada
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "<CE>A CRITERIO DA UNIDADE FEDERADA</CE>");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, ECF.linhaSimples);
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "REQUISITO XVIII - Tela Consulta de Preco.......:");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TOTALIZACAO DOS VALORES DA LISTA...........: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TRANSFORMACAO DAS INFORMACOES EM PRE-VENDA.: NAO");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "TRANSFORMACAO DAS INFORMACOES EM DAV.......: NAO");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "REQUISITO XXII - PAF-ECF Integrado ao ECF       ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "NAO COINCIDENCIA GT da ECF x ARQ. CRIPTOGRAFADO ");
        ecf.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, "RECOMPOE VALOR DO GT ARQUIVO CRIPTOGRAFADO.: SIM");
        ecf.enviar(EComandoECF.ECF_PulaLinhas, "1");

        // fechando o relatorio
        resp = ecf.enviar(EComandoECF.ECF_FechaRelatorio);
        if (resp[0].equals("ERRO")) {
            ecf.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }
    }

    /**
     * Metodo que formata o texto.
     *
     * @param texto o texto a ser formatado.
     * @param caracter o caracter que sera repetido.
     * @param tamanho o tamanho total do texto de resposta.
     * @param direita a direcao onde colocar os caracteres.
     * @return o texto formatado.
     */
    private static String formataTexto(String texto, String caracter, int tamanho, boolean direita) {
        StringBuilder sb = new StringBuilder();
        int fim = tamanho - texto.length();
        for (int i = 0; i < fim; i++) {
            sb.append(caracter);
        }
        return direita ? texto + sb.toString() : sb.toString() + texto;
    }
}
