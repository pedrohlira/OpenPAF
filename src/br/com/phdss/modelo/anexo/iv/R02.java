package br.com.phdss.modelo.anexo.iv;

import java.util.Date;

/**
 * Classe que representa o modelo R02 do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class R02 extends CabecalhoR {

    private int crz;
    private int coo;
    private int cro;
    private Date movimento;
    private Date emissao;
    private Double bruto;
    private char issqn;
    
    public R02() {
        this.padrao = "R02";
    }

    public Double getBruto() {
        return bruto * 100; // precisa informar os decimais sem separador
    }

    public void setBruto(Double bruto) {
        this.bruto = bruto;
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public int getCro() {
        return cro;
    }

    public void setCro(int cro) {
        this.cro = cro;
    }

    public int getCrz() {
        return crz;
    }

    public void setCrz(int crz) {
        this.crz = crz;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public char getIssqn() {
        return issqn;
    }

    public void setIssqn(char issqn) {
        this.issqn = issqn;
    }

    public Date getMovimento() {
        return movimento;
    }

    public void setMovimento(Date movimento) {
        this.movimento = movimento;
    }
}
