package br.com.openpdv.modelo.anexo.x;

/**
 * Classe que representa o modelo N3 do anexo X.
 *
 * @author Pedro H. Lira
 */
public class N3 {

    private String padrao;
    private String nome;
    private String md5;

    public N3() {
        padrao = "N3";
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPadrao() {
        return padrao;
    }

    public void setPadrao(String padrao) {
        this.padrao = padrao;
    }
}