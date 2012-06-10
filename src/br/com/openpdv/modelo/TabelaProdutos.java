package br.com.openpdv.modelo;

/**
 * Classe que representa os dados de modelo para geracao do arquivo Tab.
 * Produtos.
 *
 * @author Pedro H. Lira
 */
public class TabelaProdutos {
    
    private String padrao;
    private String cnpj;
    private String ie;
    private String im;
    private String razao;
    private int total;

    //GETs e SETs
    public String getPadrao() {
        return padrao;
    }

    public void setPadrao(String padrao) {
        this.padrao = padrao;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
