package br.com.phdss.modelo.anexo.iv;

import java.util.List;

/**
 * Classe que representa o modelo do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class AnexoIV {

    private E1 e1;
    private List<E2> listaE2;
    private E9 e9;

    public AnexoIV() {
    }

    public AnexoIV(E1 e1, List<E2> listaE2, E9 e9) {
        this.e1 = e1;
        this.listaE2 = listaE2;
        this.e9 = e9;
    }

    public E1 getE1() {
        return e1;
    }

    public void setE1(E1 e1) {
        this.e1 = e1;
    }

    public E9 getE9() {
        return e9;
    }

    public void setE9(E9 e9) {
        this.e9 = e9;
    }

    public List<E2> getListaE2() {
        return listaE2;
    }

    public void setListaE2(List<E2> listaE2) {
        this.listaE2 = listaE2;
    }
}
