package br.com.phdss.modelo.anexo.iii;

import br.com.phdss.modelo.anexo.Bean;

/**
 * Classe que representa o modelo N3 do anexo III.
 *
 * @author Pedro H. Lira
 */
public class N3 extends Bean{

    private String nome;
    private String md5;

    public N3() {
        this.padrao = "N3";
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

}