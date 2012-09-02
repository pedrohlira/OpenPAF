package br.com.openpdv.controlador;

import br.com.openpdv.modelo.anexo.iv.AnexoIV;
import br.com.openpdv.modelo.anexo.iv.E2;
import br.com.openpdv.modelo.anexo.v.AnexoV;
import br.com.openpdv.modelo.anexo.v.P2;
import br.com.openpdv.modelo.anexo.vi.*;
import br.com.openpdv.modelo.anexo.x.AnexoX;
import br.com.openpdv.modelo.anexo.x.N3;
import br.com.openpdv.modelo.sintegra.Sintegra;
import br.com.openpdv.modelo.sped.Sped;
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
import java.text.SimpleDateFormat;
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
public final class PAF {

    /*
     * Propriedades do arquivo auxiliar.
     */
    public static final Properties AUXILIAR = new Properties();

    /**
     * Construtor padrao.
     */
    private PAF() {
    }

    /**
     * Metodo que criptografa o arquivo auxiliar do sistema.
     *
     * @param path local de geracao do arquivo, se null salva no padrao.
     * <p/>
     * @throws Exception dispara caso nao consiga.
     */
    public static void criptografarAuxiliar(String path) throws Exception {
        // recuperando os valores
        StringBuilder sb = new StringBuilder();
        for (String chave : AUXILIAR.stringPropertyNames()) {
            sb.append(chave).append("=").append(AUXILIAR.getProperty(chave)).append("\n");
        }

        // salva o arquivo
        if (path == null) {
            path = "conf" + System.getProperty("file.separator") + "auxiliar.txt";
        }
        try (FileWriter outArquivo = new FileWriter(path)) {
            String dados = encriptar(sb.toString());
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

        // inserindo os valores
        AUXILIAR.clear();
        String[] props = descriptar(new String(bytes)).split("\n");
        for (String prop : props) {
            String[] chaveValor = prop.split("=");
            AUXILIAR.put(chaveValor[0], chaveValor[1]);
        }
    }

    /**
     * Metodo que criptografa um texto passado usando a chave privada.
     *
     * @param texto valor a ser criptografado.
     * <p/>
     * @return o texto informado criptografado
     */
    public static String encriptar(String texto) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(ChavePrivada.VALOR);
        return encryptor.encrypt(texto);
    }

    /**
     * Metodo que descriptografa um texto passado usando a chave privada.
     *
     * @param texto valor a ser descriptografado.
     * <p/>
     * @return o texto informado descriptografado
     */
    public static String descriptar(String texto) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(ChavePrivada.VALOR);
        return encryptor.decrypt(texto);
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
        String ead = "EAD" + new BigInteger(1, ass).toString(16);
        try (FileWriter outArquivo = new FileWriter(arquivo, true)) {
            outArquivo.write(ead);
            outArquivo.write("\r\n");
            outArquivo.flush();
        }
    }

    /**
     * Metodo que gera o MD5 de um arquivo informado.
     *
     * @param arquivo o path completo do arquivo.
     * <p/>
     * @return o codigo MD5 do arquivo.
     * <p/>
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
        return new BigInteger(1, md5.digest(dados)).toString(16);
    }

    /**
     * Metodo que gera o arquivo exigido no anexo X do (ER-PAF-ECF)
     *
     * @param anexoX o modelo de dados a ser gravado no arquivo.
     * <p/>
     * @return o MD5 do arquivo gerado.
     * <p/>
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
        bw.close();

        // assinando o arquivo
        assinarArquivoEAD(path);
        return gerarMD5(path);
    }

    /**
     * Metodo que emite a leitura X.
     *
     * @throws Exception dispara caso nao consiga.
     */
    public static void leituraX() throws Exception {
        String[] resp = ECF.enviar(EComandoECF.ECF_LeituraX);
        if (resp[0].equals("ERRO")) {
            throw new Exception(resp[1]);
        }
    }

    /**
     * Metodo que realiza a impressao/arquivo de memoria fiscal.
     *
     * @param comando    o comando a ser executado modelo ACRB.
     * @param parametros a lista de parametros exigidos pelo comando
     * <p/>
     * @return retorna os dados da emissao.
     */
    public static String[] leituraMF(EComandoECF comando, String... parametros) {
        return ECF.enviar(comando, parametros);
    }

    /**
     * Metodo que gera o arquivo exigido no anexo V do (ER-PAF-ECF)
     *
     * @param anexoV o modelo de dados a ser gravado no arquivo.
     * <p/>
     * @return o path do arquivo gerado.
     * <p/>
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarTabProdutos(AnexoV anexoV) throws Exception {
        // gerar o arquivo
        String path = getPathArquivos() + "TabelaProdutos_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt";;
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
        bw.close();

        // assinando o arquivo
        assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que gera o arquivo exigido no anexo IV do (ER-PAF-ECF)
     *
     * @param anexoIV o modelo de dados a ser gravado no arquivo.
     * <p/>
     * @return o path do arquivo gerado.
     * <p/>
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarEstoque(AnexoIV anexoIV) throws Exception {
        // gerar o arquivo
        String path = getPathArquivos() + "Estoque_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".txt";;
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
        bw.close();

        // assinando o arquivo
        assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que gera o arquivo exigido no anexo VI do (ER-PAF-ECF)
     *
     * @param anexoVI o modelo de dados a ser gravado no arquivo.
     * @param arquivo o mome do arquivo a ser gerado.
     * <p/>
     * @return o path do arquivo completo gerado.
     * <p/>
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarMovimentosECF(AnexoVI anexoVI, String arquivo) throws Exception {
        // gerar o arquivo
        String path = getPathArquivos() + arquivo;
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/openpdv/modelo/anexo/vi/AnexoVI.xml"));
        BeanWriter bw = factory.createWriter("AnexoVI", fw);

        // escevendo no arquivo
        bw.write(anexoVI.getR01());
        for (R02 r02 : anexoVI.getListaR02()) {
            bw.write(r02);
            bw.flush();
        }
        for (R03 r03 : anexoVI.getListaR03()) {
            bw.write(r03);
            bw.flush();
        }
        for (R04 r04 : anexoVI.getListaR04()) {
            bw.write(r04);
            bw.flush();
        }
        for (R05 r05 : anexoVI.getListaR05()) {
            bw.write(r05);
            bw.flush();
        }
        for (R06 r06 : anexoVI.getListaR06()) {
            bw.write(r06);
            bw.flush();
        }
        for (R07 r07 : anexoVI.getListaR07()) {
            bw.write(r07);
            bw.flush();
        }
        bw.close();

        // assinando o arquivo
        assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que gera o arquivo SPED das vendas do periodo.
     *
     * @param sped oom os quatros blocos exigidos
     * <p/>
     * @return o path do arquivo completo gerado.
     * <p/>
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarVendasPeriodo(Sped sped) throws Exception {
        // gerar o arquivo
        File tmp = new File(getPathArquivos() + "sped.txt");
        try (FileWriter fw = new FileWriter(tmp)) {
            sped.gerar(fw);
        }

        // renomeando
        String path = getPathArquivos() + AUXILIAR.getProperty("out.laudo") + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".txt";
        File novo = new File(path);
        tmp.renameTo(novo);

        // assinando o arquivo
        assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que gera o arquivo SINTEGRA das vendas do periodo.
     *
     * @param sintegra oom os registros exigidos.
     * <p/>
     * @return o path do arquivo completo gerado.
     * <p/>
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarVendasPeriodo(Sintegra sintegra) throws Exception {
        // gerar o arquivo
        File tmp = new File(getPathArquivos() + "sintegra.txt");
        try (FileWriter fw = new FileWriter(tmp)) {
            sintegra.gerar(fw);
        }

        // renomeando
        String path = getPathArquivos() + AUXILIAR.getProperty("out.laudo") + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".txt";
        File novo = new File(path);
        tmp.renameTo(novo);

        // assinando o arquivo
        assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que emite o relatorio dos meios de pagamentos.
     *
     * @param inicio     data de inicio do relatorio.
     * @param fim        data de fim do relatorio.
     * @param pagamentos lista de pagamentos agrupados e ordenados pela data ASC
     * @param relatorio  o codigo do relatorio de pagamentos cadastro no ECF
     * <p/>
     * @throws Exception dispara uma exececao caso nao consiga.
     */
    public static void emitirMeiosPagamentos(String inicio, String fim, List<R07> pagamentos, String relatorio) throws Exception {
        StringBuilder sb = new StringBuilder();

        // abrindo o relatorio
        String[] resp = ECF.enviar(EComandoECF.ECF_AbreRelatorioGerencial, relatorio);
        if (resp[0].equals("ERRO")) {
            ECF.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }

        // cabecalho
        sb.append(ECF.LD).append(ECF.SL);
        sb.append("<CE><N>MEIOS DE PAGAMENTO</N></CE>").append(ECF.SL);
        sb.append("<CE><N>PERIODO DE ").append(inicio).append(" A ").append(fim).append("</N></CE>").append(ECF.SL);
        sb.append(ECF.LD).append(ECF.SL);
        sb.append(ECF.SL); // pula linha

        // dados por dia
        String aux = null;
        Double subTotal = 0.00;
        NumberFormat nf = DecimalFormat.getCurrencyInstance();
        Map<String, Double> totalDia = new HashMap<>();
        Map<String, Double> total = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (R07 pag : pagamentos) {
            String data = sdf.format(pag.getData());
            if (!data.equals(aux)) {
                if (aux != null) {
                    // dados do dia
                    for (Entry<String, Double> totDia : totalDia.entrySet()) {
                        sb.append(formataTexto(totDia.getKey().toUpperCase(), " ", 20, true));
                        sb.append(formataTexto("FISCAL", " ", 10, true));
                        sb.append(nf.format(totDia.getValue()));
                        sb.append(ECF.SL);
                    }

                    // fechando um dia
                    sb.append(ECF.LS).append(ECF.SL);
                    sb.append(formataTexto("SUB-TOTAL", " ", 30, true)).append(nf.format(subTotal)).append(ECF.SL);
                    sb.append(ECF.SL); // pula linha
                    subTotal = 0.00;
                }
                // abrindo um dia
                sb.append("<N>DATA DE ACUMULACAO: </N> ").append(data).append(ECF.SL);
                sb.append(formataTexto("IDENTIFICACAO", " ", 20, true)).append(formataTexto("TIPO", " ", 10, true)).append("VALOR").append(ECF.SL);
                sb.append(ECF.LS).append(ECF.SL);
                aux = data;
            }
            // total daquele dia daquele meio
            double totDia = totalDia.containsKey(pag.getMeioPagamento()) ? totalDia.get(pag.getMeioPagamento()) : 0.00;
            totalDia.put(pag.getMeioPagamento(), totDia + pag.getValor());
            // subtotal daquele dia de todos os meios
            subTotal += pag.getValor();
            // total geral daquele meio
            double tot = total.containsKey(pag.getMeioPagamento()) ? total.get(pag.getMeioPagamento()) : 0.00;
            total.put(pag.getMeioPagamento(), tot + pag.getValor());
        }

        // dados do ultimo dia
        for (Entry<String, Double> totDia : totalDia.entrySet()) {
            sb.append(formataTexto(totDia.getKey().toUpperCase(), " ", 20, true));
            sb.append(formataTexto("FISCAL", " ", 10, true));
            sb.append(nf.format(totDia.getValue()));
            sb.append(ECF.SL);
        }
        // fechando o ultimo dia
        sb.append(ECF.LS).append(ECF.SL);
        sb.append(formataTexto("SUB-TOTAL", " ", 30, true)).append(nf.format(subTotal)).append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        subTotal = 0.00;

        // rodape
        sb.append("<CE>TOTAL GERAL</CE>").append(ECF.SL);
        sb.append("<CE><N>PERIODO DE ").append(inicio).append(" A ").append(fim).append("</N></CE>").append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        sb.append(formataTexto("IDENTIFICACAO", " ", 30, true)).append("VALOR").append(ECF.SL);
        sb.append(ECF.LS).append(ECF.SL);

        for (Entry<String, Double> tot : total.entrySet()) {
            sb.append(formataTexto(tot.getKey().toUpperCase(), " ", 30, true)).append(nf.format(tot.getValue())).append(ECF.SL);
            subTotal += tot.getValue();
        }
        sb.append(ECF.LS).append(ECF.SL);
        sb.append(formataTexto("TOTAL", " ", 30, true)).append(nf.format(subTotal));

        // envia o comando com todo o texto
        ECF.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, sb.toString());
        if (resp[0].equals("ERRO")) {
            ECF.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        } else {
            ECF.enviar(EComandoECF.ECF_FechaRelatorio);
        }
    }

    /**
     * Metodo que emite o relatorio de identificacao do PAF.
     *
     * @param relatorio o codigo do relatorio de identificacao do paf-ecf
     * cadastro no ECF
     * <p/>
     * @exception Exception dispara uma excecao caso nao consiga.
     */
    public static void emitirIdentificaoPAF(String relatorio) throws Exception {
        StringBuilder sb = new StringBuilder();

        // abrindo o relatorio
        String[] resp = ECF.enviar(EComandoECF.ECF_AbreRelatorioGerencial, relatorio);
        if (resp[0].equals("ERRO")) {
            ECF.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }

        // cabecalho
        sb.append(ECF.LD).append(ECF.SL);
        sb.append("<CE><N>IDENTIFICACAO DO PAF-ECF</N></CE>").append(ECF.SL);
        sb.append(ECF.LD).append(ECF.SL);
        // dados da sh
        sb.append("NUMERO LAUDO..: ").append(AUXILIAR.getProperty("out.laudo")).append(ECF.SL);
        sb.append("CNPJ..........: ").append(AUXILIAR.getProperty("sh.cnpj")).append(ECF.SL);
        sb.append("RAZAO SOCIAL..: ").append(AUXILIAR.getProperty("sh.razao")).append(ECF.SL);
        sb.append("ENDERECO......: ").append(AUXILIAR.getProperty("sh.logradouro")).append(ECF.SL);
        sb.append("NUMERO........: ").append(AUXILIAR.getProperty("sh.numero")).append(ECF.SL);
        sb.append("COMPLEMENTO...: ").append(AUXILIAR.getProperty("sh.complemento")).append(ECF.SL);
        sb.append("BAIRRO........: ").append(AUXILIAR.getProperty("sh.bairro")).append(ECF.SL);
        sb.append("CEP...........: ").append(AUXILIAR.getProperty("sh.cep")).append(ECF.SL);
        sb.append("CIDADE........: ").append(AUXILIAR.getProperty("sh.cidade")).append(ECF.SL);
        sb.append("UF............: ").append(AUXILIAR.getProperty("sh.uf")).append(ECF.SL);
        sb.append("TELEFONE......: ").append(AUXILIAR.getProperty("sh.fone")).append(ECF.SL);
        sb.append("EMAIL.........: ").append(AUXILIAR.getProperty("sh.email")).append(ECF.SL);
        sb.append("CONTATO.......: ").append(AUXILIAR.getProperty("sh.contato")).append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        // identifica o paf
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("<CE>IDENTIFICACAO DO PAF-ECF</CE>").append(ECF.SL);
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("NOME COMERCIAL.....: ").append(AUXILIAR.getProperty("paf.nome")).append(ECF.SL);
        sb.append("VERSAO DO PAF-ECF..: ").append(AUXILIAR.getProperty("paf.versao")).append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        // principal exe
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("<CE>PRINCIPAL ARQUIVO EXECUTAVEL</CE>").append(ECF.SL);
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("NOME....: OpenPDV.jar").append(ECF.SL);
        sb.append("MD5.....: ").append(AUXILIAR.getProperty("paf.md5")).append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        // arquivo txt e versao er
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("<CE>ARQUIVO TEXTO</CE>").append(ECF.SL);
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("NOME....: arquivos.txt").append(ECF.SL);
        String path = getPathArquivos() + "arquivos.txt";
        sb.append("MD5.....: ").append(gerarMD5(path)).append(ECF.SL);
        sb.append("VERSAO ER PAF-ECF........: ").append(AUXILIAR.getProperty("paf.er")).append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        // ecf autorizados
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("<CE>RELACAO DOS ECF AUTORIZADOS</CE>").append(ECF.SL);
        sb.append(ECF.LS).append(ECF.SL);
        for (String serie : AUXILIAR.getProperty("ecf.serie").split(";")) {
            sb.append("SERIE....: ").append(serie).append(ECF.SL);
        }
        sb.append(ECF.SL); // pula linha

        // envia o comando com todo o texto
        ECF.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, sb.toString());
        if (resp[0].equals("ERRO")) {
            ECF.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        } else {
            ECF.enviar(EComandoECF.ECF_FechaRelatorio);
        }
    }

    /**
     * Metodo que emite o relatorio de parametros de configuracao.
     *
     * @param relatorio o codigo do relatorio de configuracoes cadastro no ECF
     * <p/>
     * @exception Exception dispara uma excecao caso nao consiga.
     */
    public static void emitirConfiguracao(String relatorio) throws Exception {
        StringBuilder sb = new StringBuilder();

        // abrindo o relatorio
        String[] resp = ECF.enviar(EComandoECF.ECF_AbreRelatorioGerencial, relatorio);
        if (resp[0].equals("ERRO")) {
            ECF.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }

        // cabecalho
        sb.append(ECF.LD).append(ECF.SL);
        sb.append("<CE><N>PARAMETROS DE CONFIGURACAO</N></CE>").append(ECF.SL);
        sb.append(ECF.LD).append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        sb.append("<CE>IDENTIFICACAO E CARACTERISTICAS</CE>").append(ECF.SL);
        sb.append("<CE>DO PROGRAMA APLICATIVO FISCAL</CE>").append(ECF.SL);
        sb.append(ECF.SL).append(ECF.SL); // pula linha
        sb.append("TODAS AS PARAMETRIZACOES RELACIONADAS NESTE     ").append(ECF.SL);
        sb.append("RELATORIO SAO DE CONFIGURACAO INACESSIVEL AO    ").append(ECF.SL);
        sb.append("USUARIO DO PAF-ECF NAO E DOCUMENTO FISCAL.      ").append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        sb.append("ATIVACAO OU NAO DESTES PARAMETROS E DETERMINADA ").append(ECF.SL);
        sb.append("PELA UNIDADE FEDERADA E SOMENTE PODE SER FEITA  ").append(ECF.SL);
        sb.append("PELA INTERVENCAO DA EMPRESA DESENVOLVEDORA DO   ").append(ECF.SL);
        sb.append("PAF-ECF.").append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        // funcionalidades
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("<CE>FUNCIONALIDADES</CE>").append(ECF.SL);
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("TIPO DE FUNCIONAMENTO..........: PARAMETRIZAVEL ").append(ECF.SL);
        sb.append("TIPO DE DESENVOLVIMENTO........: COMERCIALIZAVEL").append(ECF.SL);
        sb.append("INTEGRACAO DO PAF-ECF..........: RESTful        ").append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        // nao concomitancia
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("<CE>PARAMETROS PARA NAO CONCOMITANCIA</CE>").append(ECF.SL);
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("PRE-VENDA..................................: NAO").append(ECF.SL);
        sb.append("DAV POR ECF................................: NAO").append(ECF.SL);
        sb.append("DAV IMPRESSORA NAO FISCAL..................: NAO").append(ECF.SL);
        sb.append("DAV-OS.....................................: NAO").append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        // aplicacoes especiais
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("<CE>APLICACOES ESPECIAIS</CE>").append(ECF.SL);
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("TAB. INDICE TECNICO DE PRODUCAO............: NAO").append(ECF.SL);
        sb.append("POSTO REVENDEDOR DE COMBUSTIVEIS...........: NAO").append(ECF.SL);
        sb.append("BAR - RESTAURANTE - SIMILAR................: NAO").append(ECF.SL);
        sb.append("FARMACIA DE MANIPULACAO....................: NAO").append(ECF.SL);
        sb.append("OFICINA DE CONSERTO........................: NAO").append(ECF.SL);
        sb.append("TRANSPORTE DE PASSAGEIROS..................: NAO").append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        // unidade federada
        sb.append(ECF.LS).append(ECF.SL);
        sb.append("<CE>A CRITERIO DA UNIDADE FEDERADA</CE>").append(ECF.SL);
        sb.append(ECF.LS).append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        sb.append("REQUISITO XVIII - Tela Consulta de Preco.......:").append(ECF.SL);
        sb.append("TOTALIZACAO DOS VALORES DA LISTA...........: NAO").append(ECF.SL);
        sb.append("TRANSFORMACAO DAS INFORMACOES EM PRE-VENDA.: NAO").append(ECF.SL);
        sb.append("TRANSFORMACAO DAS INFORMACOES EM DAV.......: NAO").append(ECF.SL);
        sb.append(ECF.SL); // pula linha
        sb.append("REQUISITO XXII - PAF-ECF Integrado ao ECF       ").append(ECF.SL);
        sb.append("NAO COINCIDENCIA GT da ECF x ARQ. CRIPTOGRAFADO ").append(ECF.SL);
        sb.append("RECOMPOE VALOR DO GT ARQUIVO CRIPTOGRAFADO.: NAO").append(ECF.SL);

        // envia o comando com todo o texto
        ECF.enviar(EComandoECF.ECF_LinhaRelatorioGerencial, sb.toString());
        if (resp[0].equals("ERRO")) {
            ECF.enviar(EComandoECF.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        } else {
            ECF.enviar(EComandoECF.ECF_FechaRelatorio);
        }
    }

    /**
     * Metodo que formata o texto.
     *
     * @param texto    o texto a ser formatado.
     * @param caracter o caracter que sera repetido.
     * @param tamanho  o tamanho total do texto de resposta.
     * @param direita  a direcao onde colocar os caracteres.
     * <p/>
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
