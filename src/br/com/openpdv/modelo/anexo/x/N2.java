package br.com.openpdv.modelo.anexo.x;

/**
 * Classe que representa o modelo N2 do anexo X.
 *
 * @author Pedro H. Lira
 */
public class N2 {

    private String padrao;
    private String laudo;
    private String nome;
    private String versao;

    public N2() {
        padrao = "N2";
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
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

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}
