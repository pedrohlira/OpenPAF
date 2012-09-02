package br.com.openpdv.modelo.anexo.v;

import java.util.List;

/**
 * Classe que representa o modelo do anexo V.
 *
 * @author Pedro H. Lira
 */
public class AnexoV {

    private P1 p1;
    private List<P2> listaP2;
    private P9 p9;

    public AnexoV() {
    }

    public AnexoV(P1 p1, List<P2> listaP2, P9 p9) {
        this.p1 = p1;
        this.listaP2 = listaP2;
        this.p9 = p9;
    }

    public List<P2> getListaP2() {
        return listaP2;
    }

    public void setListaP2(List<P2> listaP2) {
        this.listaP2 = listaP2;
    }

    public P1 getP1() {
        return p1;
    }

    public void setP1(P1 p1) {
        this.p1 = p1;
    }

    public P9 getP9() {
        return p9;
    }

    public void setP9(P9 p9) {
        this.p9 = p9;
    }
}
