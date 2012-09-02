package br.com.openpdv.modelo.anexo.vi;

import java.util.Date;

/**
 * Classe que representa o modelo R01 do anexo VI.
 *
 * @author Pedro H. Lira
 */
public class R01 extends CabecalhoR {

    private String tipoECF;
    private String marcaECF;
    private String versaoSB;
    private Date dataSB;
    private int numeroECF;
    private String empresaCNPJ;
    private String empresaIE;
    private String shCNPJ;
    private String shIE;
    private int shIM;
    private String shRazao;
    private String pafNome;
    private String pafVersao;
    private String pafMD5;
    private String pafER;
    private Date inicio;
    private Date fim;

    public R01() {
        padrao = "R01";
    }

    public String getEmpresaCNPJ() {
        return empresaCNPJ;
    }

    public void setEmpresaCNPJ(String empresaCNPJ) {
        this.empresaCNPJ = empresaCNPJ;
    }

    public String getEmpresaIE() {
        return empresaIE;
    }

    public void setEmpresaIE(String empresaIE) {
        this.empresaIE = empresaIE;
    }

    public Date getDataSB() {
        return dataSB;
    }

    public void setDataSB(Date dataSB) {
        this.dataSB = dataSB;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public String getMarcaECF() {
        return marcaECF;
    }

    public void setMarcaECF(String marcaECF) {
        this.marcaECF = marcaECF;
    }

    public int getNumeroECF() {
        return numeroECF;
    }

    public void setNumeroECF(int numeroECF) {
        this.numeroECF = numeroECF;
    }

    public String getPafER() {
        return pafER;
    }

    public void setPafER(String pafER) {
        this.pafER = pafER;
    }

    public String getPafMD5() {
        return pafMD5;
    }

    public void setPafMD5(String pafMD5) {
        this.pafMD5 = pafMD5;
    }

    public String getPafNome() {
        return pafNome;
    }

    public void setPafNome(String pafNome) {
        this.pafNome = pafNome;
    }

    public String getPafVersao() {
        return pafVersao;
    }

    public void setPafVersao(String pafVersao) {
        this.pafVersao = pafVersao;
    }

    public String getShCNPJ() {
        return shCNPJ;
    }

    public void setShCNPJ(String shCNPJ) {
        this.shCNPJ = shCNPJ;
    }

    public String getShIE() {
        return shIE;
    }

    public void setShIE(String shIE) {
        this.shIE = shIE;
    }

    public int getShIM() {
        return shIM;
    }

    public void setShIM(int shIM) {
        this.shIM = shIM;
    }

    public String getShRazao() {
        return shRazao;
    }

    public void setShRazao(String shRazao) {
        this.shRazao = shRazao;
    }

    public String getTipoECF() {
        return tipoECF;
    }

    public void setTipoECF(String tipoECF) {
        this.tipoECF = tipoECF;
    }

    public String getVersaoSB() {
        return versaoSB;
    }

    public void setVersaoSB(String versaoSB) {
        this.versaoSB = versaoSB;
    }
}
