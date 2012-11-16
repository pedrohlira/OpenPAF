package br.com.phdss.modelo.anexo.x;

import java.util.List;

/**
 * Classe que representa o modelo do anexo X.
 *
 * @author Pedro H. Lira
 */
public class AnexoX {

    private N1 n1;
    private N2 n2;
    private List<N3> listaN3;
    private N9 n9;

    public AnexoX() {
    }

    public AnexoX(N1 n1, N2 n2, List<N3> listaN3, N9 n9) {
        this.n1 = n1;
        this.n2 = n2;
        this.listaN3 = listaN3;
        this.n9 = n9;
    }

    public List<N3> getListaN3() {
        return listaN3;
    }

    public void setListaN3(List<N3> listaN3) {
        this.listaN3 = listaN3;
    }

    public N1 getN1() {
        return n1;
    }

    public void setN1(N1 n1) {
        this.n1 = n1;
    }

    public N2 getN2() {
        return n2;
    }

    public void setN2(N2 n2) {
        this.n2 = n2;
    }

    public N9 getN9() {
        return n9;
    }

    public void setN9(N9 n9) {
        this.n9 = n9;
    }
}
