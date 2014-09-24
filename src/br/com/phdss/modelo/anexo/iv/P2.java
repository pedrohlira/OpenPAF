package br.com.phdss.modelo.anexo.iv;

import br.com.phdss.modelo.anexo.Bean;

/**
 * Classe que representa o modelo P2 do anexo V.
 *
 * @author Pedro H. Lira
 */
public class P2 extends Bean {

    private String cnpj;
    private String codigo;
    private String descricao;
    private String unidade;
    private char iat;
    private char ippt;
    private char tributacao;
    private Double aliquota;
    private Double valor;

    public P2() {
        this.padrao = "P2";
    }

    public Double getAliquota() {
        return aliquota * 100; // precisa informar os decimais sem separador
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

    public char getIat() {
        return iat;
    }

    public void setIat(char iat) {
        this.iat = iat;
    }

    public char getIppt() {
        return ippt;
    }

    public void setIppt(char ippt) {
        this.ippt = ippt;
    }

    public char getTributacao() {
        return tributacao;
    }

    public void setTributacao(char tributacao) {
        this.tributacao = tributacao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getValor() {
        return valor * 100; // precisa informar os decimais sem separador
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
