package br.com.phdss.modelo.cat52;

/**
 * Classe que representa o modelo de Bean para exporacao do cat52.
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
