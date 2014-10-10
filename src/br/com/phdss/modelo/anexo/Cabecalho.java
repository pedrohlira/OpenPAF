package br.com.phdss.modelo.anexo;

/**
 * Classe que representa o modelo de cabecalho de arquivos exportados.
 *
 * @author Pedro H. Lira
 */
public abstract class Cabecalho extends Bean {

    protected String cnpj;
    protected String ie;
    protected String im;
    protected String razao;

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
