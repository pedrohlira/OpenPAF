package br.com.phdss.modelo.anexo.iv;

import br.com.phdss.modelo.anexo.Bean;

/**
 * Classe que representa o modelo U1 do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class U1 extends Bean {

    private String cnpj;
    private String ie;
    private String im;
    private String razao;
    
    public U1() {
        this.padrao = "U1";
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }
    
}
