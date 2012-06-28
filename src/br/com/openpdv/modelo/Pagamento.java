package br.com.openpdv.modelo;

/**
 * Classe que representa os dados do modelo para geracao do Pagamento.
 *
 * @author Pedro H. Lira
 */
public class Pagamento {
    
    private String identificacao;
    private String tipo;
    private Double valor;
    private String data;
    
    //GETs e SETs
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
