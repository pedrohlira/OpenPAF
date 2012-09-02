package br.com.openpdv.modelo.anexo.vi;

import br.com.openpdv.modelo.anexo.Bean;

/**
 * Classe que representa o modelo de cabecalho dos registros tipo R.
 *
 * @author Pedro H. Lira
 */
public abstract class CabecalhoR extends Bean {

    protected String serie;
    protected char mfAdicional;
    protected String modeloECF;
    protected int usuario;

    public char getMfAdicional() {
        return mfAdicional;
    }

    public void setMfAdicional(char mfAdicional) {
        this.mfAdicional = mfAdicional;
    }

    public String getModeloECF() {
        return modeloECF;
    }

    public void setModeloECF(String modeloECF) {
        this.modeloECF = modeloECF;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
}
