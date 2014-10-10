package br.com.phdss.modelo.cat52;

import java.util.List;

/**
 * Classe que representa o modelo do CAT52.
 *
 * @author Pedro H. Lira
 */
public class Cat52 {

    private E00 e00;
    private E01 e01;
    private E02 e02;
    private E12 e12;
    private List<E13> listaE13;
    private List<E14> listaE14;
    private List<E15> listaE15;
    private List<E16> listaE16;
    private List<E21> listaE21;

    public Cat52() {
    }

    public Cat52(E00 e00, E01 e01, E02 e02, E12 e12, List<E13> listaE13, List<E14> listaE14, List<E15> listaE15, List<E16> listaE16, List<E21> listaE21) {
        this.e00 = e00;
        this.e01 = e01;
        this.e02 = e02;
        this.e12 = e12;
        this.listaE13 = listaE13;
        this.listaE14 = listaE14;
        this.listaE15 = listaE15;
        this.listaE16 = listaE16;
        this.listaE21 = listaE21;
    }

    public E00 getE00() {
        return e00;
    }

    public void setE00(E00 e00) {
        this.e00 = e00;
    }

    public E01 getE01() {
        return e01;
    }

    public void setE01(E01 e01) {
        this.e01 = e01;
    }

    public E02 getE02() {
        return e02;
    }

    public void setE02(E02 e02) {
        this.e02 = e02;
    }

    public E12 getE12() {
        return e12;
    }

    public void setE12(E12 e12) {
        this.e12 = e12;
    }

    public List<E13> getListaE13() {
        return listaE13;
    }

    public void setListaE13(List<E13> listaE13) {
        this.listaE13 = listaE13;
    }

    public List<E14> getListaE14() {
        return listaE14;
    }

    public void setListaE14(List<E14> listaE14) {
        this.listaE14 = listaE14;
    }

    public List<E15> getListaE15() {
        return listaE15;
    }

    public void setListaE15(List<E15> listaE15) {
        this.listaE15 = listaE15;
    }

    public List<E16> getListaE16() {
        return listaE16;
    }

    public void setListaE16(List<E16> listaE16) {
        this.listaE16 = listaE16;
    }

    public List<E21> getListaE21() {
        return listaE21;
    }

    public void setListaE21(List<E21> listaE21) {
        this.listaE21 = listaE21;
    }

}
