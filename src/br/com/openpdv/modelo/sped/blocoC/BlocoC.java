package br.com.openpdv.modelo.sped.blocoC;

import br.com.openpdv.modelo.sped.Bloco;
import java.io.FileWriter;
import java.util.List;

public class BlocoC implements Bloco {

    private DadosC001 dC001;
    private List<DadosC100> dC100;
    private List<DadosC300> dC300;
    private List<DadosC350> dC350;
    private List<DadosC400> dC400;
    private DadosC990 dC990;

    public BlocoC() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if(dC001 != null){
            dC001.gerar(fw);
        }
        if(dC100 != null){
            for(DadosC100 dados : dC100){
                dados.gerar(fw);
            }
        }
        if(dC300 != null){
            for(DadosC300 dados : dC300){
                dados.gerar(fw);
            }
        }
        if(dC350 != null){
            for(DadosC350 dados : dC350){
                dados.gerar(fw);
            }
        }
        if(dC400 != null){
            for(DadosC400 dados : dC400){
                dados.gerar(fw);
            }
        }
        if(dC990 != null){
            dC990.gerar(fw);
        }
    }
    
    public DadosC001 getdC001() {
        return dC001;
    }

    public void setdC001(DadosC001 dC001) {
        this.dC001 = dC001;
    }

    public List<DadosC100> getdC100() {
        return dC100;
    }

    public void setdC100(List<DadosC100> dC100) {
        this.dC100 = dC100;
    }

    public List<DadosC300> getdC300() {
        return dC300;
    }

    public void setdC300(List<DadosC300> dC300) {
        this.dC300 = dC300;
    }

    public List<DadosC350> getdC350() {
        return dC350;
    }

    public void setdC350(List<DadosC350> dC350) {
        this.dC350 = dC350;
    }

    public List<DadosC400> getdC400() {
        return dC400;
    }

    public void setdC400(List<DadosC400> dC400) {
        this.dC400 = dC400;
    }

    public DadosC990 getdC990() {
        return dC990;
    }

    public void setdC990(DadosC990 dC990) {
        this.dC990 = dC990;
    }
}
