package br.com.phdss.modelo.anexo.iii;

import br.com.phdss.modelo.anexo.Bean;

/**
 * Classe que representa o modelo N2 do anexo III.
 *
 * @author Pedro H. Lira
 */
public class N2 extends Bean{

    private String laudo;
    private String nome;
    private String versao;

    public N2() {
        this.padrao = "N2";
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

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}
