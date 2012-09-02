package br.com.openpdv.modelo.anexo;

/**
 * Classe que representa o modelo de rodape de arquivos exportados.
 *
 * @author Pedro H. Lira
 */
public abstract class Rodape extends Bean {

    protected String cnpj;
    protected String ie;
    protected int total;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
