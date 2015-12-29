package br.com.phdss.modelo.anexo.iv;

import java.util.Date;

/**
 * Classe que representa o modelo R07 do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class R07 extends CabecalhoR {

    private int coo;
    private int ccf;
    private int gnf;
    private String meioPagamento;
    private Double valor;
    private Date data;
    private char estorno;
    private Double valorEstorno;
    
    public R07() {
        this.padrao = "R07";
    }

    public int getCcf() {
        return ccf;
    }

    public void setCcf(int ccf) {
        this.ccf = ccf;
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public char getEstorno() {
        return estorno;
    }

    public void setEstorno(char estorno) {
        this.estorno = estorno;
    }

    public int getGnf() {
        return gnf;
    }

    public void setGnf(int gnf) {
        this.gnf = gnf;
    }

    public String getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public Double getValor() {
        return valor * 100; // precisa informar os decimais sem separador
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorEstorno() {
        return valorEstorno == null ? 0.00 : valorEstorno * 100; // precisa informar os decimais sem separador
    }

    public void setValorEstorno(Double valorEstorno) {
        this.valorEstorno = valorEstorno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
