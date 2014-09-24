package br.com.phdss.modelo.anexo.iv;

import br.com.phdss.modelo.anexo.Bean;
import java.util.Date;

/**
 * Classe que representa o modelo A2 do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class A2 extends Bean {

    private Date data;
    private String meioPagamento;
    private String tipoPagamento;
    private Double valor;

    public A2() {
        this.padrao = "A2";
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Double getValor() {
        return valor * 100; // precisa informar os decimais sem separador
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
