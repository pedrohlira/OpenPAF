package br.com.phdss.controlador;

import br.com.phdss.ECF;
import br.com.phdss.EComando;
import br.com.phdss.IECF;
import br.com.phdss.Util;
import br.com.phdss.modelo.anexo.iv.AnexoIV;
import br.com.phdss.modelo.anexo.iv.E2;
import br.com.phdss.modelo.anexo.v.AnexoV;
import br.com.phdss.modelo.anexo.v.P2;
import br.com.phdss.modelo.anexo.vi.*;
import br.com.phdss.modelo.anexo.x.AnexoX;
import br.com.phdss.modelo.anexo.x.N1;
import br.com.phdss.modelo.anexo.x.N2;
import br.com.phdss.modelo.anexo.x.N3;
import br.com.phdss.modelo.anexo.x.N9;
import br.com.phdss.modelo.cat52.Cat52;
import br.com.phdss.modelo.cat52.E13;
import br.com.phdss.modelo.cat52.E14;
import br.com.phdss.modelo.cat52.E15;
import br.com.phdss.modelo.cat52.E16;
import br.com.phdss.modelo.cat52.E21;
import br.com.phdss.modelo.sintegra.Sintegra;
import br.com.phdss.modelo.sped.Sped;
import java.io.File;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Map.Entry;
import java.util.*;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

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
     * Gera o arquivo com os arquivos autenticados.
     *
     * @throws Exception dispara caso nao consiga.
     */
    public static void gerarArquivos() throws Exception {
        // cria o objeto modelo n1
        N1 n1 = new N1();
        n1.setCnpj(AUXILIAR.getProperty("sh.cnpj"));
        n1.setIe(AUXILIAR.getProperty("sh.ie"));
        n1.setIm(AUXILIAR.getProperty("sh.im"));
        n1.setRazao(AUXILIAR.getProperty("sh.razao"));
        // cria o objeto modelo n2
        N2 n2 = new N2();
        n2.setLaudo(AUXILIAR.getProperty("out.laudo"));
        n2.setNome(AUXILIAR.getProperty("paf.nome"));
        n2.setVersao(AUXILIAR.getProperty("paf.versao"));
        // binario principal
        N3 n3 = new N3();
        n3.setNome("OpenPDV.jar");
        StringBuilder principal = new StringBuilder(System.getProperty("user.dir"));
        principal.append(System.getProperty("file.separator")).append("OpenPDV.jar");
        n3.setMd5(Util.gerarMD5(principal.toString()));
        // cria a lista de n3
        List<N3> listaN3 = new ArrayList<>();
        listaN3.add(n3);
        // cria o objeto modelo n9
        N9 n9 = new N9();
        n9.setCnpj(AUXILIAR.getProperty("sh.cnpj"));
        n9.setIe(AUXILIAR.getProperty("sh.ie"));
        n9.setTotal(listaN3.size());
        // cria o modelo do anexo X
        AnexoX anexoX = new AnexoX(n1, n2, listaN3, n9);
        String md5Arquivo = gerarArquivos(anexoX);
        if (!AUXILIAR.isEmpty()) {
            AUXILIAR.setProperty("out.autenticado", md5Arquivo);
            Util.criptografar(null, PAF.AUXILIAR);
        }
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
        String path = Util.getPathArquivos() + "arquivoMD5.txt";
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/phdss/modelo/anexo/x/AnexoX.xml"));
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
        Util.assinarArquivoEAD(path);
        return Util.gerarMD5(path);
    }

    /**
     * Metodo que emite a leitura X.
     *
     * @throws Exception dispara caso nao consiga.
     */
    public static void leituraX() throws Exception {
        String[] resp = ECF.getInstancia().enviar(EComando.ECF_LeituraX);
        if (resp[0].equals("ERRO")) {
            throw new Exception(resp[1]);
        }
    }

    /**
     * Metodo que realiza a impressao/arquivo de memoria fiscal.
     *
     * @param comando o comando a ser executado modelo ACRB.
     * @param parametros a lista de parametros exigidos pelo comando
     * @return retorna os dados da emissao.
     */
    public static String[] leituraMF(EComando comando, String... parametros) {
        return ECF.getInstancia().enviar(comando, parametros);
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
        StringBuilder sb = new StringBuilder(Util.getPathArquivos());
        sb.append("TabelaProdutos_").append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).append(".txt");
        FileWriter fw = new FileWriter(sb.toString());

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/phdss/modelo/anexo/v/AnexoV.xml"));
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
        Util.assinarArquivoEAD(sb.toString());
        return sb.toString();
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
        StringBuilder sb = new StringBuilder(Util.getPathArquivos());
        sb.append("Estoque_").append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).append(".txt");
        FileWriter fw = new FileWriter(sb.toString());

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/phdss/modelo/anexo/iv/AnexoIV.xml"));
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
        Util.assinarArquivoEAD(sb.toString());
        return sb.toString();
    }

    /**
     * Metodo que gera o arquivo exigido no anexo VI do (ER-PAF-ECF)
     *
     * @param anexoVI o modelo de dados a ser gravado no arquivo.
     * @param arquivo o mome do arquivo a ser gerado.
     * @return o path do arquivo completo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarMovimentosECF(AnexoVI anexoVI, String arquivo) throws Exception {
        // gerar o arquivo
        String path = Util.getPathArquivos() + arquivo;
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/phdss/modelo/anexo/vi/AnexoVI.xml"));
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
        Util.assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que gera o arquivo exigido para NFA ou NFP.
     *
     * @param cat52 o modelo de dados a ser gravado no arquivo.
     * @param arquivo o nome do arquivo de acordo com o ECF.
     * @return o path do arquivo completo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarArquivoCat52(Cat52 cat52, String arquivo) throws Exception {
        char[] alpha = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        // recupera a data que sera usada na geracao do arquivo
        Calendar cal = Calendar.getInstance();
        cal.setTime(cat52.getE01().getDataIni());
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR) - 2000;

        // verifica se existe a pasta do ano e mes e gera o nome completo do arquivo
        StringBuilder sb = new StringBuilder(Util.getPathArquivos());
        sb.append("cat52").append(System.getProperty("file.separator"));
        sb.append(cal.get(Calendar.YEAR)).append(System.getProperty("file.separator"));
        if (mes < 9) {
            sb.append("0");
        }
        sb.append(mes);
        File dir = new File(sb.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        sb.append(System.getProperty("file.separator")).append(arquivo);
        sb.append(".").append(alpha[dia]).append(alpha[mes]).append(alpha[ano]);
        FileWriter fw = new FileWriter(sb.toString());

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/phdss/modelo/cat52/Cat52.xml"));
        BeanWriter bw = factory.createWriter("Cat52", fw);

        // escevendo no arquivo
        bw.write(cat52.getE00());
        bw.write(cat52.getE01());
        bw.write(cat52.getE02());
        bw.write(cat52.getE12());
        bw.flush();
        for (E13 e13 : cat52.getListaE13()) {
            bw.write(e13);
            bw.flush();
        }
        for (E14 e14 : cat52.getListaE14()) {
            bw.write(e14);
            bw.flush();
        }
        for (E15 e15 : cat52.getListaE15()) {
            bw.write(e15);
            bw.flush();
        }
        for (E16 e16 : cat52.getListaE16()) {
            bw.write(e16);
            bw.flush();
        }
        for (E21 e21 : cat52.getListaE21()) {
            bw.write(e21);
            bw.flush();
        }
        bw.close();

        // assinando o arquivo
        Util.assinarArquivoEAD(sb.toString());
        return sb.toString();
    }

    /**
     * Metodo que gera o arquivo SPED das vendas do periodo.
     *
     * @param sped oom os quatros blocos exigidos
     * @return o path do arquivo completo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarVendasPeriodo(Sped sped) throws Exception {
        // gerar o arquivo
        File tmp = new File(Util.getPathArquivos() + "sped.txt");
        try (FileWriter fw = new FileWriter(tmp)) {
            sped.gerar(fw);
        }

        // renomeando
        String path = Util.getPathArquivos() + AUXILIAR.getProperty("out.laudo") + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".txt";
        File novo = new File(path);
        tmp.renameTo(novo);

        // assinando o arquivo
        Util.assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que gera o arquivo SINTEGRA das vendas do periodo.
     *
     * @param sintegra oom os registros exigidos.
     * @return o path do arquivo completo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarVendasPeriodo(Sintegra sintegra) throws Exception {
        // gerar o arquivo
        File tmp = new File(Util.getPathArquivos() + "sintegra.txt");
        try (FileWriter fw = new FileWriter(tmp)) {
            sintegra.gerar(fw);
        }

        // renomeando
        String path = Util.getPathArquivos() + AUXILIAR.getProperty("out.laudo") + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".txt";
        File novo = new File(path);
        tmp.renameTo(novo);

        // assinando o arquivo
        Util.assinarArquivoEAD(path);
        return path;
    }

    /**
     * Metodo que emite o relatorio dos meios de pagamentos.
     *
     * @param inicio data de inicio do relatorio.
     * @param fim data de fim do relatorio.
     * @param pagamentos lista de pagamentos agrupados e ordenados pela data ASC
     * @param relatorio o codigo do relatorio de pagamentos cadastro no ECF
     * @throws Exception dispara uma exececao caso nao consiga.
     */
    public static void emitirMeiosPagamentos(String inicio, String fim, List<R07> pagamentos, String relatorio) throws Exception {
        StringBuilder sb = new StringBuilder();
        IECF ecf = ECF.getInstancia();

        // abrindo o relatorio
        String[] resp = ecf.enviar(EComando.ECF_AbreRelatorioGerencial, relatorio);
        if (resp[0].equals("ERRO")) {
            ecf.enviar(EComando.ECF_CorrigeEstadoErro);
        }

        // cabecalho
        sb.append(IECF.LD).append(IECF.SL);
        sb.append("<N>").append(Util.formataTexto("MEIOS DE PAGAMENTO", " ", IECF.COL, Util.EDirecao.AMBOS)).append("</N>").append(IECF.SL);
        sb.append(" PERIODO SOLICITADO DE ").append(inicio).append(" A ").append(fim).append(IECF.SL);
        sb.append(IECF.LD).append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, sb.toString());

        // dados por dia
        String aux = null;
        double subTotal = 0.00;
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        Map<String, Double> total = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (R07 pag : pagamentos) {
            String data = sdf.format(pag.getData());
            if (!data.equals(aux)) {
                if (aux != null) {
                    // fechando um dia
                    ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, IECF.LS);
                    sb = new StringBuilder("\"");
                    sb.append("SOMA DO DIA ").append(aux).append("  ");
                    sb.append(nf.format(subTotal));
                    sb.append("\"");
                    ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, sb.toString());
                    ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, IECF.LS);
                    subTotal = 0.00;
                }
                aux = data;
            }
            // dados do dia
            sb = new StringBuilder("\"");
            sb.append(formataTexto(sdf.format(pag.getData()), " ", 11, true));
            sb.append(formataTexto(pag.getMeioPagamento().toUpperCase(), " ", 15, true));
            sb.append(formataTexto(pag.getSerie(), " ", 12, true));
            sb.append(nf.format(pag.getValor() / 100));
            sb.append("\"");
            ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, sb.toString());
            // subtotal daquele dia de todos os meios
            subTotal += pag.getValor() / 100;
            // total geral daquele meio
            double tot = total.containsKey(pag.getMeioPagamento()) ? total.get(pag.getMeioPagamento()) : 0.00;
            total.put(pag.getMeioPagamento(), tot + pag.getValor() / 100);
        }

        // fechando o ultimo dia
        if (aux != null) {
            ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, IECF.LS);
            sb = new StringBuilder("\"");
            sb.append("SOMA DO DIA ").append(aux).append("  ");
            sb.append(nf.format(subTotal));
            sb.append("\"");
            ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, sb.toString());
            ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, IECF.LS);
        }
        subTotal = 0.00;

        // rodape
        ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, Util.formataTexto("TOTAL DO PERIODO SOLICITADO", " ", IECF.COL, Util.EDirecao.AMBOS));
        ecf.enviar(EComando.ECF_PulaLinhas, "1");
        ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, IECF.LS);
        ecf.enviar(EComando.ECF_PulaLinhas, "1");

        for (Entry<String, Double> tot : total.entrySet()) {
            sb = new StringBuilder("\"");
            sb.append(formataTexto(tot.getKey().toUpperCase(), " ", 35, true));
            sb.append(nf.format(tot.getValue()));
            sb.append("\"");
            ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, sb.toString());
            subTotal += tot.getValue();
        }

        ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, IECF.LS);
        sb = new StringBuilder("\"");
        sb.append("SOMA TOTAL  ");
        sb.append(nf.format(subTotal));
        sb.append("\"");
        ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, sb.toString());
        ecf.enviar(EComando.ECF_FechaRelatorio);
    }

    /**
     * Metodo que emite o relatorio de identificacao do PAF.
     *
     * @param relatorio o codigo do relatorio de identificacao do paf-ecf
     * cadastro no ECF
     * @exception Exception dispara uma excecao caso nao consiga.
     */
    public static void emitirIdentificaoPAF(String relatorio) throws Exception {
        StringBuilder sb = new StringBuilder();
        IECF ecf = ECF.getInstancia();

        // abrindo o relatorio
        String[] resp = ecf.enviar(EComando.ECF_AbreRelatorioGerencial, relatorio);
        if (resp[0].equals("ERRO")) {
            ecf.enviar(EComando.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }

        // cabecalho
        sb.append(IECF.LD).append(IECF.SL);
        sb.append("<N>").append(Util.formataTexto("IDENTIFICACAO DO PAF-ECF", " ", IECF.COL, Util.EDirecao.AMBOS)).append("</N>").append(IECF.SL);
        sb.append(IECF.LD).append(IECF.SL);
        // dados da sh
        sb.append("NUMERO LAUDO..: ").append(AUXILIAR.getProperty("out.laudo")).append(IECF.SL);
        sb.append("CNPJ..........: ").append(AUXILIAR.getProperty("sh.cnpj")).append(IECF.SL);
        sb.append("RAZAO SOCIAL..: ").append(AUXILIAR.getProperty("sh.razao")).append(IECF.SL);
        sb.append("ENDERECO......: ").append(AUXILIAR.getProperty("sh.logradouro")).append(IECF.SL);
        sb.append("NUMERO........: ").append(AUXILIAR.getProperty("sh.numero")).append(IECF.SL);
        sb.append("COMPLEMENTO...: ").append(AUXILIAR.getProperty("sh.complemento")).append(IECF.SL);
        sb.append("BAIRRO........: ").append(AUXILIAR.getProperty("sh.bairro")).append(IECF.SL);
        sb.append("CEP...........: ").append(AUXILIAR.getProperty("sh.cep")).append(IECF.SL);
        sb.append("CIDADE........: ").append(AUXILIAR.getProperty("sh.cidade")).append(IECF.SL);
        sb.append("UF............: ").append(AUXILIAR.getProperty("sh.uf")).append(IECF.SL);
        sb.append("TELEFONE......: ").append(AUXILIAR.getProperty("sh.fone")).append(IECF.SL);
        sb.append("EMAIL.........: ").append(AUXILIAR.getProperty("sh.email")).append(IECF.SL);
        sb.append("CONTATO.......: ").append(AUXILIAR.getProperty("sh.contato")).append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        // identifica o paf
        sb.append(IECF.LS).append(IECF.SL);
        sb.append(Util.formataTexto("IDENTIFICACAO DO PAF-ECF", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(IECF.LS).append(IECF.SL);
        sb.append("NOME COMERCIAL.....: ").append(AUXILIAR.getProperty("paf.nome")).append(IECF.SL);
        sb.append("VERSAO DO PAF-ECF..: ").append(AUXILIAR.getProperty("paf.versao")).append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        // principal exe
        sb.append(IECF.LS).append(IECF.SL);
        sb.append(Util.formataTexto("PRINCIPAL ARQUIVO EXECUTAVEL", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(IECF.LS).append(IECF.SL);
        sb.append("NOME....: OpenPDV.jar").append(IECF.SL);
        StringBuilder principal = new StringBuilder(System.getProperty("user.dir"));
        principal.append(System.getProperty("file.separator")).append("OpenPDV.jar");
        sb.append("MD5.....: ").append(Util.gerarMD5(principal.toString())).append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        // arquivo txt e versao er
        sb.append(IECF.LS).append(IECF.SL);
        sb.append(Util.formataTexto("ARQUIVO TEXTO", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(IECF.LS).append(IECF.SL);
        sb.append("NOME....: arquivoMD5.txt").append(IECF.SL);
        String path = Util.getPathArquivos() + "arquivoMD5.txt";
        sb.append("MD5.....: ").append(Util.gerarMD5(path)).append(IECF.SL);
        sb.append("VERSAO ER PAF-ECF........: ").append(AUXILIAR.getProperty("paf.er")).append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        // ecf autorizados
        sb.append(IECF.LS).append(IECF.SL);
        sb.append(Util.formataTexto("RELACAO DOS ECF AUTORIZADOS", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(IECF.LS).append(IECF.SL);
        for (String serie : AUXILIAR.getProperty("ecf.serie").split(";")) {
            sb.append("SERIE....: ").append(serie).append(IECF.SL);
        }
        sb.append(IECF.SL); // pula linha

        // envia o comando com todo o texto
        ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, sb.toString());
        if (resp[0].equals("ERRO")) {
            ecf.enviar(EComando.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        } else {
            ecf.enviar(EComando.ECF_FechaRelatorio);
        }
    }

    /**
     * Metodo que emite o relatorio de parametros de configuracao.
     *
     * @param relatorio o codigo do relatorio de configuracoes cadastro no ECF
     * @exception Exception dispara uma excecao caso nao consiga.
     */
    public static void emitirConfiguracao(String relatorio) throws Exception {
        StringBuilder sb = new StringBuilder();
        IECF ecf = ECF.getInstancia();

        // abrindo o relatorio
        String[] resp = ecf.enviar(EComando.ECF_AbreRelatorioGerencial, relatorio);
        if (resp[0].equals("ERRO")) {
            ecf.enviar(EComando.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }

        // cabecalho
        sb.append(IECF.LD).append(IECF.SL);
        sb.append("<N>").append(Util.formataTexto("PARAMETROS DE CONFIGURACAO", " ", IECF.COL, Util.EDirecao.AMBOS)).append("</N>").append(IECF.SL);
        sb.append(IECF.LD).append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        sb.append(Util.formataTexto("IDENTIFICACAO E CARACTERISTICAS", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(Util.formataTexto("DO PROGRAMA APLICATIVO FISCAL", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(IECF.SL).append(IECF.SL); // pula linha
        sb.append("TODAS AS PARAMETRIZACOES RELACIONADAS NESTE     ").append(IECF.SL);
        sb.append("RELATORIO SAO DE CONFIGURACAO INACESSIVEL AO    ").append(IECF.SL);
        sb.append("USUARIO DO PAF-ECF NAO E DOCUMENTO FISCAL.      ").append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        sb.append("ATIVACAO OU NAO DESTES PARAMETROS E DETERMINADA ").append(IECF.SL);
        sb.append("PELA UNIDADE FEDERADA E SOMENTE PODE SER FEITA  ").append(IECF.SL);
        sb.append("PELA INTERVENCAO DA EMPRESA DESENVOLVEDORA DO   ").append(IECF.SL);
        sb.append("PAF-ECF.").append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        // funcionalidades
        sb.append(IECF.LS).append(IECF.SL);
        sb.append(Util.formataTexto("FUNCIONALIDADES", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(IECF.LS).append(IECF.SL);
        sb.append("TIPO DE FUNCIONAMENTO..........: PARAMETRIZAVEL ").append(IECF.SL);
        sb.append("TIPO DE DESENVOLVIMENTO........: COMERCIALIZAVEL").append(IECF.SL);
        sb.append("INTEGRACAO DO PAF-ECF..........: NAO INTEGRADO  ").append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        // nao concomitancia
        sb.append(IECF.LS).append(IECF.SL);
        sb.append(Util.formataTexto("PARAMETROS PARA NAO CONCOMITANCIA", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(IECF.LS).append(IECF.SL);
        sb.append("PRE-VENDA..................................: NAO").append(IECF.SL);
        sb.append("DAV POR ECF................................: NAO").append(IECF.SL);
        sb.append("DAV IMPRESSORA NAO FISCAL..................: NAO").append(IECF.SL);
        sb.append("DAV-OS.....................................: NAO").append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        // aplicacoes especiais
        sb.append(IECF.LS).append(IECF.SL);
        sb.append(Util.formataTexto("APLICACOES ESPECIAIS", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(IECF.LS).append(IECF.SL);
        sb.append("TAB. INDICE TECNICO DE PRODUCAO............: NAO").append(IECF.SL);
        sb.append("POSTO REVENDEDOR DE COMBUSTIVEIS...........: NAO").append(IECF.SL);
        sb.append("BAR - RESTAURANTE - SIMILAR................: NAO").append(IECF.SL);
        sb.append("FARMACIA DE MANIPULACAO....................: NAO").append(IECF.SL);
        sb.append("OFICINA DE CONSERTO........................: NAO").append(IECF.SL);
        sb.append("TRANSPORTE DE PASSAGEIROS..................: NAO").append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        // unidade federada
        sb.append(IECF.LS).append(IECF.SL);
        sb.append(Util.formataTexto("A CRITERIO DA UNIDADE FEDERADA", " ", IECF.COL, Util.EDirecao.AMBOS)).append(IECF.SL);
        sb.append(IECF.LS).append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        sb.append("MINAS LEGAL................................: ").append(AUXILIAR.getProperty("paf.minas_legal")).append(IECF.SL);
        sb.append("CUPOM MANIAL...............................: ").append(AUXILIAR.getProperty("paf.cupom_mania")).append(IECF.SL);
        sb.append("CUPOM LEGAL................................: ").append(AUXILIAR.getProperty("paf.cupom_legal")).append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        sb.append("REQUISITO XVIII - Tela Consulta de Preco.......:").append(IECF.SL);
        sb.append("TOTALIZACAO DOS VALORES DA LISTA...........: NAO").append(IECF.SL);
        sb.append("TRANSFORMACAO DAS INFORMACOES EM PRE-VENDA.: NAO").append(IECF.SL);
        sb.append("TRANSFORMACAO DAS INFORMACOES EM DAV.......: NAO").append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        sb.append("REQUISITO XXII ITEM 8 - PAF-ECF Integrado ao ECF").append(IECF.SL);
        sb.append("NAO COINCIDENCIA GT da ECF x ARQ. CRIPTOGRAFADO ").append(IECF.SL);
        sb.append("RECOMPOE VALOR DO GT ARQUIVO CRIPTOGRAFADO.: SIM").append(IECF.SL);

        // envia o comando com todo o texto
        ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, sb.toString());
        if (resp[0].equals("ERRO")) {
            ecf.enviar(EComando.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        } else {
            ecf.enviar(EComando.ECF_FechaRelatorio);
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
