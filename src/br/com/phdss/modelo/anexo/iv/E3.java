package br.com.phdss.modelo.anexo.iv;

import br.com.phdss.modelo.anexo.Bean;
import java.util.Date;

/**
 * Classe que representa o modelo E3 do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class E3 extends Bean {

    private String serie;
    private String mfAdicional;
    private String tipoECF;
    private String marcaECF;
    private String modeloECF;
    private Date data;

    public E3() {
        this.padrao = "E3";
    }

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

    public String getTipoECF() {
        return tipoECF;
    }

    public void setTipoECF(String tipoECF) {
        this.tipoECF = tipoECF;
    }

    public String getMarcaECF() {
        return marcaECF;
    }

    public void setMarcaECF(String marcaECF) {
        this.marcaECF = marcaECF;
    }

    public String getModeloECF() {
        return modeloECF;
    }

    public void setModeloECF(String modeloECF) {
        this.modeloECF = modeloECF;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
