package br.com.phdss.modelo.anexo.iv;

import java.util.List;

/**
 * Classe que representa o modelo do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class AnexoIV {

    private U1 u1;
    private List<A2> listaA2;
    private List<P2> listaP2;
    private List<E2> listaE2;
    private E3 e3;
    private R01 r01;
    private List<R02> listaR02;
    private List<R03> listaR03;
    private List<R04> listaR04;
    private List<R05> listaR05;
    private List<R06> listaR06;
    private List<R07> listaR07;

    public AnexoIV() {
    }

    public AnexoIV(U1 u1, List<A2> listaA2, List<P2> listaP2, List<E2> listaE2, E3 e3, R01 r01, List<R02> listaR02, List<R03> listaR03, List<R04> listaR04, List<R05> listaR05, List<R06> listaR06, List<R07> listaR07) {
        this.u1 = u1;
        this.listaA2 = listaA2;
        this.listaP2 = listaP2;
        this.listaE2 = listaE2;
        this.e3 = e3;
        this.r01 = r01;
        this.listaR02 = listaR02;
        this.listaR03 = listaR03;
        this.listaR04 = listaR04;
        this.listaR05 = listaR05;
        this.listaR06 = listaR06;
        this.listaR07 = listaR07;
    }
    
    public U1 getU1() {
        return u1;
    }

    public void setU1(U1 u1) {
        this.u1 = u1;
    }

    public List<A2> getListaA2() {
        return listaA2;
    }

    public void setListaA2(List<A2> listaA2) {
        this.listaA2 = listaA2;
    }

    public List<P2> getListaP2() {
        return listaP2;
    }

    public void setListaP2(List<P2> listaP2) {
        this.listaP2 = listaP2;
    }

    public List<E2> getListaE2() {
        return listaE2;
    }

    public void setListaE2(List<E2> listaE2) {
        this.listaE2 = listaE2;
    }

    public E3 getE3() {
        return e3;
    }

    public void setE3(E3 e3) {
        this.e3 = e3;
    }

    public R01 getR01() {
        return r01;
    }

    public void setR01(R01 r01) {
        this.r01 = r01;
    }

    public List<R02> getListaR02() {
        return listaR02;
    }

    public void setListaR02(List<R02> listaR02) {
        this.listaR02 = listaR02;
    }

    public List<R03> getListaR03() {
        return listaR03;
    }

    public void setListaR03(List<R03> listaR03) {
        this.listaR03 = listaR03;
    }

    public List<R04> getListaR04() {
        return listaR04;
    }

    public void setListaR04(List<R04> listaR04) {
        this.listaR04 = listaR04;
    }

    public List<R05> getListaR05() {
        return listaR05;
    }

    public void setListaR05(List<R05> listaR05) {
        this.listaR05 = listaR05;
    }

    public List<R06> getListaR06() {
        return listaR06;
    }

    public void setListaR06(List<R06> listaR06) {
        this.listaR06 = listaR06;
    }

    public List<R07> getListaR07() {
        return listaR07;
    }

    public void setListaR07(List<R07> listaR07) {
        this.listaR07 = listaR07;
    }
}
