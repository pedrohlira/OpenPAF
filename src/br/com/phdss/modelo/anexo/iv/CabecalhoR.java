package br.com.phdss.modelo.anexo.iv;

import br.com.phdss.modelo.anexo.Bean;

/**
 * Classe que representa o modelo de cabecalho dos registros tipo R.
 *
 * @author Pedro H. Lira
 */
public abstract class CabecalhoR extends Bean {

    protected String serie;
    protected String mfAdicional;
    protected String modeloECF;
    protected int usuario;

    public String getMfAdicional() {
        return mfAdicional;
    }

    public void setMfAdicional(String mfAdicional) {
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
