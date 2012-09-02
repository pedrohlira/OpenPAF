package br.com.openpdv.modelo.sintegra;

public class Dados54 extends Tipo {

    private String cnpj;
    private int modelo;
    private String serie;
    private int numero;
    private int cfop;
    private String cst;
    private int item;
    private String codigo;
    private double qtd;
    private double valor;
    private double desconto;
    private double base_icms;
    private double base_icmsST;
    private double valor_ipi;
    private double aliq_icms;

    public Dados54() {
        super(54);
    }

    public double getAliq_icms() {
        return aliq_icms * 100;
    }

    public void setAliq_icms(double aliq_icms) {
        this.aliq_icms = aliq_icms;
    }

    public double getBase_icms() {
        return base_icms * 100;
    }

    public void setBase_icms(double base_icms) {
        this.base_icms = base_icms;
    }

    public double getBase_icmsST() {
        return base_icmsST * 100;
    }

    public void setBase_icmsST(double base_icmsST) {
        this.base_icmsST = base_icmsST;
    }

    public int getCfop() {
        return cfop;
    }

    public void setCfop(int cfop) {
        this.cfop = cfop;
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

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public double getDesconto() {
        return desconto * 100;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getQtd() {
        return qtd * 1000;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public double getValor() {
        return valor * 100;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor_ipi() {
        return valor_ipi * 100;
    }

    public void setValor_ipi(double valor_ipi) {
        this.valor_ipi = valor_ipi;
    }
}
