package br.com.phdss.modelo.cat52;

/**
 * Classe que representa o modelo E00 do CAT52.
 *
 * @author Pedro H. Lira
 */
public class E00 extends CabecalhoE {

    private String tipoEcf;
    private String marcaEcf;
    private int coo;
    private int numAplicativo;
    private String cnpj_cpf;
    private String ie;
    private String im;
    private String razao;
    private String nomeAplicativo;
    private String versaoAplicativo;
    private String linha01;
    private String linha02;

    public E00() {
        padrao = "E00";
    }

    public String getTipoEcf() {
        return tipoEcf;
    }

    public void setTipoEcf(String tipoEcf) {
        this.tipoEcf = tipoEcf;
    }

    public String getMarcaEcf() {
        return marcaEcf;
    }

    public void setMarcaEcf(String marcaEcf) {
        this.marcaEcf = marcaEcf;
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public int getNumAplicativo() {
        return numAplicativo;
    }

    public void setNumAplicativo(int numAplicativo) {
        this.numAplicativo = numAplicativo;
    }

    public String getCnpj_cpf() {
        return cnpj_cpf;
    }

    public void setCnpj_cpf(String cnpj_cpf) {
        this.cnpj_cpf = cnpj_cpf;
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

    public String getNomeAplicativo() {
        return nomeAplicativo;
    }

    public void setNomeAplicativo(String nomeAplicativo) {
        this.nomeAplicativo = nomeAplicativo;
    }

    public String getVersaoAplicativo() {
        return versaoAplicativo;
    }

    public void setVersaoAplicativo(String versaoAplicativo) {
        this.versaoAplicativo = versaoAplicativo;
    }

    public String getLinha01() {
        return linha01;
    }

    public void setLinha01(String linha01) {
        this.linha01 = linha01;
    }

    public String getLinha02() {
        return linha02;
    }

    public void setLinha02(String linha02) {
        this.linha02 = linha02;
    }

}
