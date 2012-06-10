package br.com.openpdv.modelo;

/**
 * Classe que representa o modelo de produto.
 *
 * @author Pedro H. Lira
 */
public class Produto {

    private String padrao;
    private String cnpj;
    private String codigo;
    private String descricao;
    private String unidade;
    private String iat;
    private String ippt;
    private String tributacao;
    private Double aliquota;
    private Double valor;
    private Double estoque;
    private String mensuracao;

    //GETs e SETs
    public String getPadrao() {
        return padrao;
    }

    public void setPadrao(String padrao) {
        this.padrao = padrao;
    }

    public Double getAliquota() {
        return aliquota * 100;
    }

    public void setAliquota(Double aliquota) {
        this.aliquota = aliquota;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getEstoque() {
        return estoque * 1000;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getIppt() {
        return ippt;
    }

    public void setIppt(String ippt) {
        this.ippt = ippt;
    }

    public String getTributacao() {
        return tributacao;
    }

    public void setTributacao(String tributacao) {
        this.tributacao = tributacao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getValor() {
        return valor * 100;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMensuracao() {
        return Math.abs(estoque) == estoque ? "+" : "-";
    }

    public void setMensuracao(String mensuracao) {
        this.mensuracao = mensuracao;
    }
}
