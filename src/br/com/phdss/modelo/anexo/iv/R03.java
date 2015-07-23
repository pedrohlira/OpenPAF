package br.com.phdss.modelo.anexo.iv;

/**
 * Classe que representa o modelo R03 do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class R03 extends CabecalhoR {

    private int crz;
    private String totalizador;
    private Double valor;
    
    public R03() {
        this.padrao = "R03";
    }

    public int getCrz() {
        return crz;
    }

    public void setCrz(int crz) {
        this.crz = crz;
    }

    public String getTotalizador() {
        return totalizador;
    }

    public void setTotalizador(String totalizador) {
        this.totalizador = totalizador;
    }

    public Double getValor() {
        return valor * 100; // precisa informar os decimais sem separador
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
