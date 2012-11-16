package br.com.phdss.modelo.sped.bloco0;

import br.com.phdss.modelo.sped.Bean;

public class Dados0220 extends Bean {

    private String unid_conv;
    private int fat_conv;

    public Dados0220() {
        super("0220");
    }

    public String getUnid_conv() {
        return unid_conv;
    }

    public void setUnid_conv(String unid_conv) {
        this.unid_conv = unid_conv;
    }

    public int getFat_conv() {
        return fat_conv;
    }

    public void setFat_conv(int fat_conv) {
        this.fat_conv = fat_conv;
    }
}
