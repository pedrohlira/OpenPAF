package br.com.phdss.modelo.anexo.iii;

import br.com.phdss.modelo.anexo.Cabecalho;

/**
 * Classe que representa o modelo N9 do anexo III.
 *
 * @author Pedro H. Lira
 */
public class N9 extends Cabecalho {

    private int total;
    
    public N9() {
        this.padrao = "N9";
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
