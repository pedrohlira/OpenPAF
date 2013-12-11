package br.com.phdss.modelo.cat52;

/**
 * Classe que representa o modelo de cabecalho do cat52.
 *
 * @author Pedro H. Lira
 */
public abstract class CabecalhoE extends Bean {

    protected String serie;
    protected String mfAdicional;
    protected String modelo;
    protected int usuario;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMfAdicional() {
        return mfAdicional;
    }

    public void setMfAdicional(String mfAdicional) {
        this.mfAdicional = mfAdicional;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

}
