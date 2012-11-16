package br.com.phdss.modelo.sped.blocoH;

import br.com.phdss.modelo.sped.Bloco;
import java.io.FileWriter;
import java.util.List;

public class BlocoH implements Bloco{

    private DadosH001 dH001;
    private DadosH005 dH005;
    private List<DadosH010> dH010;
    private DadosH990 dH990;

    public BlocoH() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if(dH001 != null){
            dH001.gerar(fw);
        }
        if(dH005 != null){
            dH005.gerar(fw);
        }
        if(dH010 != null){
            for(DadosH010 dados : dH010){
                dados.gerar(fw);
            }
        }
        if(dH990 != null){
            dH990.gerar(fw);
        }
    }
    
    public DadosH001 getdH001() {
        return dH001;
    }

    public void setdH001(DadosH001 dH001) {
        this.dH001 = dH001;
    }

    public DadosH005 getdH005() {
        return dH005;
    }

    public void setdH005(DadosH005 dH005) {
        this.dH005 = dH005;
    }

    public List<DadosH010> getdH010() {
        return dH010;
    }

    public void setdH010(List<DadosH010> dH010) {
        this.dH010 = dH010;
    }

    public DadosH990 getdH990() {
        return dH990;
    }

    public void setdH990(DadosH990 dH990) {
        this.dH990 = dH990;
    }
}
