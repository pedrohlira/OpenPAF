package br.com.phdss.controlador;

import br.com.phdss.ECF;
import br.com.phdss.EComando;
import br.com.phdss.IECF;
import br.com.phdss.Util;
import br.com.phdss.modelo.anexo.iv.AnexoIV;
import br.com.phdss.modelo.anexo.iv.E2;
import br.com.phdss.modelo.anexo.iii.AnexoIII;
import br.com.phdss.modelo.anexo.iii.N1;
import br.com.phdss.modelo.anexo.iii.N2;
import br.com.phdss.modelo.anexo.iii.N3;
import br.com.phdss.modelo.anexo.iii.N9;
import br.com.phdss.modelo.anexo.iv.A2;
import br.com.phdss.modelo.anexo.iv.P2;
import br.com.phdss.modelo.anexo.iv.R02;
import br.com.phdss.modelo.anexo.iv.R03;
import br.com.phdss.modelo.anexo.iv.R04;
import br.com.phdss.modelo.anexo.iv.R05;
import br.com.phdss.modelo.anexo.iv.R06;
import br.com.phdss.modelo.anexo.iv.R07;
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
import java.text.SimpleDateFormat;
import java.util.*;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

/**
 * Classe que representa o PAF no sistema e todas suas funcionalidades.
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
        // cria o modelo do anexo III
        AnexoIII anexoIII = new AnexoIII(n1, n2, listaN3, n9);
        String md5Arquivo = gerarArquivos(anexoIII);
        if (!AUXILIAR.isEmpty()) {
            AUXILIAR.setProperty("out.autenticado", md5Arquivo);
            Util.criptografar(null, PAF.AUXILIAR);
        }
    }

    /**
     * Metodo que gera o arquivo exigido no anexo X do (ER-PAF-ECF)
     *
     * @param anexoIII o modelo de dados a ser gravado no arquivo.
     * @return o MD5 do arquivo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarArquivos(AnexoIII anexoIII) throws Exception {
        // gerar o arquivo
        String path = Util.getPathArquivos() + "arquivoMD5.txt";
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/phdss/modelo/anexo/iii/AnexoIII.xml"));
        BeanWriter bw = factory.createWriter("AnexoIII", fw);

        // escevendo no arquivo
        bw.write(anexoIII.getN1());
        bw.write(anexoIII.getN2());
        for (N3 n3 : anexoIII.getListaN3()) {
            bw.write(n3);
            bw.flush();
        }
        bw.write(anexoIII.getN9());
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
        if (resp[0].equals(IECF.ERRO)) {
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
     * Metodo que gera o arquivo binario e seu txt assinado.
     *
     * @return o path do arquivo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String[] gerarArqMF() throws Exception {
        String serie = ECF.getInstancia().enviar(EComando.ECF_NumSerie)[1];
        String data = Util.formataData(new Date(), "yyyyMMdd_HHmmss");
        String pathBin = Util.getPathArquivos() + serie + "_" + data + ".MF";
        String pathTxt = Util.getPathArquivos() + "MF" + serie + "_" + data + ".TXT";
        String[] resp = ECF.getInstancia().enviar(EComando.ECF_PafMF_ArqMF, pathBin);

        if (resp[0].equals(IECF.OK)) {
            String ead = Util.gerarEAD(pathBin);
            try (FileWriter outArquivo = new FileWriter(pathTxt, false)) {
                outArquivo.write(ead);
                outArquivo.write("\r\n");
                outArquivo.flush();
            }
        } else {
            throw new Exception(resp[1]);
        }

        // deletando o txt gerado pelo ACBR
        new File(pathBin.replace(".MF", ".TXT")).delete();
        return new String[] {pathBin, pathTxt};
    }

    /**
     * Metodo que gera o arquivo binario e seu txt assinado.
     *
     * @return o path do arquivo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String[] gerarArqMFD() throws Exception {
        String serie = ECF.getInstancia().enviar(EComando.ECF_NumSerie)[1];
        String data = Util.formataData(new Date(), "yyyyMMdd_HHmmss");
        String pathBin = Util.getPathArquivos() + serie + "_" + data + ".MFD";
        String pathTxt = Util.getPathArquivos() + "MFD" + serie + "_" + data + ".TXT";
        String[] resp = ECF.getInstancia().enviar(EComando.ECF_PafMF_ArqMFD, pathBin);

        if (resp[0].equals(IECF.OK)) {
            String ead = Util.gerarEAD(pathBin);
            try (FileWriter outArquivo = new FileWriter(pathTxt, false)) {
                outArquivo.write(ead);
                outArquivo.write("\r\n");
                outArquivo.flush();
            }
        } else {
            throw new Exception(resp[1]);
        }

        // deletando o txt gerado pelo ACBR
        new File(pathBin.replace(".MFD", ".TXT")).delete();
        return new String[] {pathBin, pathTxt};
    }

    /**
     * Metodo que gera o arquivo exigido no anexo IV do (ER-PAF-ECF)
     *
     * @param anexoIV o modelo de dados a ser gravado no arquivo.
     * @param arquivo o nome do arquivo gerado.
     * @return o path do arquivo gerado.
     * @throws Exception dispara caso nao consiga.
     */
    public static String gerarRegistros(AnexoIV anexoIV, String arquivo) throws Exception {
        // gerar o arquivo
        String path = Util.getPathArquivos() + arquivo;
        FileWriter fw = new FileWriter(path);

        // compila no formato
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(PAF.class.getClass().getResourceAsStream("/br/com/phdss/modelo/anexo/iv/AnexoIV.xml"));
        BeanWriter bw = factory.createWriter("AnexoIV", fw);

        // escevendo no arquivo
        bw.write(anexoIV.getU1());
        for (A2 a2 : anexoIV.getListaA2()) {
            bw.write(a2);
            bw.flush();
        }
        for (P2 p2 : anexoIV.getListaP2()) {
            bw.write(p2);
            bw.flush();
        }
        for (E2 e2 : anexoIV.getListaE2()) {
            bw.write(e2);
            bw.flush();
        }
        bw.write(anexoIV.getE3());
        bw.write(anexoIV.getR01());
        for (R02 r02 : anexoIV.getListaR02()) {
            bw.write(r02);
            bw.flush();
        }
        for (R03 r03 : anexoIV.getListaR03()) {
            bw.write(r03);
            bw.flush();
        }
        for (R04 r04 : anexoIV.getListaR04()) {
            bw.write(r04);
            bw.flush();
        }
        for (R05 r05 : anexoIV.getListaR05()) {
            bw.write(r05);
            bw.flush();
        }
        for (R06 r06 : anexoIV.getListaR06()) {
            bw.write(r06);
            bw.flush();
        }
        for (R07 r07 : anexoIV.getListaR07()) {
            bw.write(r07);
            bw.flush();
        }
        bw.flush();
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
        if (resp[0].equals(IECF.ERRO)) {
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
        if (resp[0].equals(IECF.ERRO)) {
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
        if (resp[0].equals(IECF.ERRO)) {
            ecf.enviar(EComando.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        }

        // cabecalho
        sb.append(IECF.LD).append(IECF.SL);
        sb.append("<N>").append(Util.formataTexto("PARAMETROS DE CONFIGURACAO", " ", IECF.COL, Util.EDirecao.AMBOS)).append("</N>").append(IECF.SL);
        sb.append(IECF.LD).append(IECF.SL);
        sb.append(IECF.SL); // pula linha
        sb.append("UF: ").append(AUXILIAR.getProperty("cli.estado")).append(IECF.SL);
        sb.append("PERFIL: ").append(AUXILIAR.getProperty("cli.perfil")).append(IECF.SL);

        // envia o comando com todo o texto
        ecf.enviar(EComando.ECF_LinhaRelatorioGerencial, sb.toString());
        if (resp[0].equals(IECF.ERRO)) {
            ecf.enviar(EComando.ECF_CorrigeEstadoErro);
            throw new Exception(resp[1]);
        } else {
            ecf.enviar(EComando.ECF_FechaRelatorio);
        }
    }
    
    /**
     * Metodo que altera a quantidade de registros validos para o PAF.
     *
     * @param dado o objeto que esta sendo adicionado ou removido.
     * @param qtd a quantidade positiva ou negativa.
     */
    public static void validarPAF(Object dado, int qtd) {
        if (dado.getClass().getSimpleName().startsWith("Ecf") || dado.getClass().getSimpleName().equals("ProdProduto")) {
            long registros = Long.valueOf(AUXILIAR.getProperty("paf.registros"));
            registros += qtd;
            AUXILIAR.setProperty("paf.registros", registros + "");
            try {
                Util.criptografar(null, AUXILIAR);
            } catch (Exception ex) {
                // nada
            }
        }
    }
}
