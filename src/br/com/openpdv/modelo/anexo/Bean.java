package br.com.openpdv.modelo.anexo;

/**
 * Classe que representa o modelo de Bean para exporacao de arquivos.
 *
 * @author Pedro H. Lira
 */
public abstract class Bean {

    protected String padrao;

    public String getPadrao() {
        return padrao;
    }

    public void setPadrao(String padrao) {
        this.padrao = padrao;
    }
}
