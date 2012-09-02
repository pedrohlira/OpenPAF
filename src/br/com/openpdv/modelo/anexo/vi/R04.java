package br.com.openpdv.modelo.anexo.vi;

import java.util.Date;

/**
 * Classe que representa o modelo R04 do anexo VI.
 *
 * @author Pedro H. Lira
 */
public class R04 extends CabecalhoR {

    private int documento;
    private int coo;
    private Date data;
    private Double bruto;
    private Double desconto;
    private char tipoDesconto;
    private Double acrescimo;
    private char tipoAcrescimo;
    private Double liquido;
    private char cancelado;
    private Double canceladoAcrescimo;
    private char ordemAcresDesc;
    private String clienteNome;
    private String clienteCPF;
    
    public R04() {
        padrao = "R04";
    }

    public Double getAcrescimo() {
        return acrescimo * 100; // precisa informar os decimais sem separador
    }

    public void setAcrescimo(Double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public Double getBruto() {
        return bruto * 100; // precisa informar os decimais sem separador
    }

    public void setBruto(Double bruto) {
        this.bruto = bruto;
    }

    public char getCancelado() {
        return cancelado;
    }

    public void setCancelado(char cancelado) {
        this.cancelado = cancelado;
    }

    public Double getCanceladoAcrescimo() {
        return canceladoAcrescimo * 100; // precisa informar os decimais sem separador
    }

    public void setCanceladoAcrescimo(Double canceladoAcrescimo) {
        this.canceladoAcrescimo = canceladoAcrescimo;
    }

    public String getClienteCPF() {
        return clienteCPF;
    }

    public void setClienteCPF(String clienteCPF) {
        this.clienteCPF = clienteCPF;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getDesconto() {
        return desconto * 100; // precisa informar os decimais sem separador
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public Double getLiquido() {
        return liquido;
    }

    public void setLiquido(Double liquido) {
        this.liquido = liquido;
    }

    public char getOrdemAcresDesc() {
        return ordemAcresDesc;
    }

    public void setOrdemAcresDesc(char ordemAcresDesc) {
        this.ordemAcresDesc = ordemAcresDesc;
    }

    public char getTipoAcrescimo() {
        return tipoAcrescimo;
    }

    public void setTipoAcrescimo(char tipoAcrescimo) {
        this.tipoAcrescimo = tipoAcrescimo;
    }

    public char getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(char tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }
}
