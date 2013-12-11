package br.com.phdss.modelo.cat52;

import java.util.Date;

/**
 * Classe que representa o modelo E02 do CAT52.
 *
 * @author Pedro H. Lira
 */
public class E02 extends CabecalhoE {

    private String cnpj;
    private String ie;
    private String razao;
    private String endereco;
    private Date cadastro;
    private int cro;
    private double gt;

    public E02() {
        padrao = "E02";
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

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public int getCro() {
        return cro;
    }

    public void setCro(int cro) {
        this.cro = cro;
    }

    public double getGt() {
        return gt;
    }

    public void setGt(double gt) {
        this.gt = gt;
    }

}
