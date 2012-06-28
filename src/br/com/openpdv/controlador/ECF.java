package br.com.openpdv.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import org.apache.log4j.Logger;

/**
 * Classe que representa o ECF no sistema e todas suas funcionalidiades.
 *
 * @author Pedro H. Lira
 */
public class ECF {

    private static ECF ecf;
    private static Logger log;
    private Socket acbr;
    private PrintWriter saida = null;
    private BufferedReader entrada = null;
    private AsyncCallback<String[]> async;
    public static final String linhaSimples = "------------------------------------------------";
    public static final String linhaDupla = "================================================";
    public static final String OK = "OK";
    public static final String ERRO = "ERRO";
    
    /**
     * Construtor padrao.
     */
    public ECF() {
        log = Logger.getLogger(ECF.class);
    }

    /**
     * Metodo que retorna a instancia unica de ECF.
     *
     * @return o objeto ECF.
     */
    public static ECF getInstancia() {
        return getInstancia(null);
    }

    /**
     * Metodo que retorna a instancia unica de ECF.
     *
     * @param async informe a classe que vai responder ao metodo de resposta.
     * @return o objeto ECF.
     */
    public static ECF getInstancia(AsyncCallback<String[]> async) {
        if (ecf == null) {
            ecf = new ECF();
        }
        ecf.async = async;
        return ecf;
    }

    /**
     * Metodo que realiza a conexao com o ECF.
     *
     * @throws Exception dispara um excecao caso nao cosiga.
     */
    public void conectar(String servidor, int porta) throws Exception {
        try {
            InetAddress ip = InetAddress.getByName(servidor);
            SocketAddress url = new InetSocketAddress(ip, porta);
            acbr = new Socket();
            acbr.connect(url, 2000);
            saida = new PrintWriter(acbr.getOutputStream());
            entrada = new BufferedReader(new InputStreamReader(acbr.getInputStream()));

            Thread.sleep(500);
            lerDados();
        } catch (IOException | InterruptedException ex) {
            log.error("Nao foi possivel se conectar ao ACBrMonitor", ex);
            throw new Exception("Verifique se as configuraõçes estão corretas e se está ativo no sistema.");
        }
    }

    /**
     * Metodo que envia um comando ao ECF que repassa para a ECF.
     *
     * @param comando um EComandoACBr que representa um comando aceito.
     * @param parametros um sequencia de parametros, opcionais usado somente em
     * alguns comandos.
     */
    public String[] enviar(EComandoACBr comando, String... parametros) {
        return enviar(comando.toString(), parametros);
    }

    /**
     * Metodo que envia um comando ao ECF que repassa para a ECF.
     *
     * @param comando um EComandoECF que representa um comando aceito.
     * @param parametros um sequencia de parametros, opcionais usado somente em
     * alguns comandos.
     */
    public String[] enviar(EComandoECF comando, String... parametros) {
        return enviar(comando.toString(), parametros);
    }

    /**
     * Metodo que envia um comando ao ECF que repassa para a ECF.
     *
     * @param comando uma String que representa um comando aceito.
     * @param parametros um sequencia de parametros, opcionais usado somente em
     * alguns comandos.
     */
    private String[] enviar(final String comando, final String... parametros) {
        final String[] resp = new String[]{"", ""};

        Thread fluxo = new Thread(new Runnable() {

            @Override
            public void run() {
                StringBuilder acao = new StringBuilder(comando);
                if (parametros != null && parametros.length > 0) {
                    acao.append("(");
                    for (String param : parametros) {
                        acao.append(param).append(",");
                    }
                    acao.deleteCharAt(acao.length() - 1).append(")");
                }

                try {
                    saida.print(acao.toString() + '\r' + '\n' + "." + '\r' + '\n');
                    saida.flush();

                    if ("SAIR".equals(comando.toString())) {
                        saida.close();
                        entrada.close();
                        acbr.close();
                        resp[0] = OK;
                        resp[1] = "FIM";
                    } else {
                        String dados = lerDados();
                        if ("".equals(dados)) {
                            resp[0] = OK;
                        } else if (dados.contains(":")) {
                            String[] ret = dados.split(":", 2);
                            resp[0] = ret[0].trim();
                            resp[1] = ret[1].trim();
                        } else {
                            resp[0] = OK;
                            resp[1] = dados.trim();
                        }
                    }
                } catch (Exception ex) {
                    log.error("Nao foi possivel enviar ou receber comando do ACBrMonitor" + acao.toString(), ex);
                    resp[0] = ERRO;
                    resp[1] = "Nao foi possivel enviar ou receber comando do ACBrMonitor";
                } finally {
                    if (async != null) {
                        async.sucesso(resp);
                    }
                }
            }
        });

        // se nao passou o monitor chama o metodo fora da thread.
        if (async == null) {
            fluxo.run();
            return resp;
        } else {
            fluxo.start();
            return null;
        }
    }

    /**
     * Metodo que faz a leitura do retorno do ECF.
     *
     * @return uma String da resposta.
     * @throws IOException dispara uma excecao caso nao consiga ler.
     */
    private String lerDados() throws IOException {
        short b = -1;
        String retorno = "";
        while (b != 3) {
            b = (short) entrada.read();
            if (b != 3) {
                retorno += (char) b;
            }
        }
        return retorno;
    }
}
