package br.com.phdss.modelo.sped;

import br.com.phdss.controlador.PAF;
import br.com.phdss.modelo.sped.bloco0.Bloco0;
import br.com.phdss.modelo.sped.bloco1.Bloco1;
import br.com.phdss.modelo.sped.bloco9.Bloco9;
import br.com.phdss.modelo.sped.bloco9.Dados9001;
import br.com.phdss.modelo.sped.bloco9.Dados9900;
import br.com.phdss.modelo.sped.bloco9.Dados9990;
import br.com.phdss.modelo.sped.bloco9.Dados9999;
import br.com.phdss.modelo.sped.blocoC.BlocoC;
import br.com.phdss.modelo.sped.blocoD.BlocoD;
import br.com.phdss.modelo.sped.blocoE.BlocoE;
import br.com.phdss.modelo.sped.blocoG.BlocoG;
import br.com.phdss.modelo.sped.blocoH.BlocoH;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sped implements Bloco {

    private Bloco0 bloco0;
    private BlocoC blocoC;
    private BlocoD blocoD;
    private BlocoE blocoE;
    private BlocoG blocoG;
    private BlocoH blocoH;
    private Bloco1 bloco1;
    private Bloco9 bloco9;

    public Sped() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if (bloco0 != null) {
            bloco0.gerar(fw);
        }
        if (blocoC != null) {
            blocoC.gerar(fw);
        }
        if (blocoD != null) {
            blocoD.gerar(fw);
        }
        if (blocoE != null) {
            blocoE.gerar(fw);
        }
        if (blocoG != null) {
            blocoG.gerar(fw);
        }
        if (blocoH != null) {
            blocoH.gerar(fw);
        }
        if (bloco1 != null) {
            bloco1.gerar(fw);
        }
        gerarBloco9();
        if (bloco9 != null) {
            bloco9.gerar(fw);
        }
        fw.close();
    }

    private void gerarBloco9() throws Exception {
        bloco9 = new Bloco9();
        int linhas = 0;
        int total = 0;

        // Registro 9001
        Dados9001 _1 = new Dados9001();
        _1.setInd_mov(0);
        bloco9.setD9001(_1);
        linhas++;
        total++;

        // Lendo as linhas do sped
        Map<String, Integer> regs = new LinkedHashMap<>();
        // lendo o arquivo
        BufferedReader br = new BufferedReader(new FileReader(PAF.getPathArquivos() + "sped.txt"));
        String linha, chave;
        while ((linha = br.readLine()) != null) {
            chave = linha.substring(1, 5);
            Integer qtd = regs.get(chave);
            qtd = qtd == null ? 1 : qtd + 1;
            regs.put(chave, qtd);
            total++;
        }

        // inputando os ultimos
        regs.put("9001", 1);
        regs.put("9900", regs.size() + 4);
        regs.put("9990", 1);
        regs.put("9999", 1);

        // Registro 9900
        List<Dados9900> l9900 = new ArrayList<>();
        for (Map.Entry<String, Integer> reg : regs.entrySet()) {
            Dados9900 _9900 = new Dados9900();
            _9900.setReg_bcl(reg.getKey());
            _9900.setQtd_reg_bcl(reg.getValue());
            l9900.add(_9900);
            linhas++;
            total++;
        }
        bloco9.setD9900(l9900);

        // Registro 9990
        Dados9990 _9990 = new Dados9990();
        _9990.setQtd_lin(linhas + 2);
        bloco9.setD9990(_9990);
        total++;

        // Registro 9999
        Dados9999 _9999 = new Dados9999();
        _9999.setQtd_lin(total + 1);
        bloco9.setD9999(_9999);
    }

    public Bloco0 getBloco0() {
        return bloco0;
    }

    public void setBloco0(Bloco0 bloco0) {
        this.bloco0 = bloco0;
    }

    public BlocoC getBlocoC() {
        return blocoC;
    }

    public void setBlocoC(BlocoC blocoC) {
        this.blocoC = blocoC;
    }

    public BlocoD getBlocoD() {
        return blocoD;
    }

    public void setBlocoD(BlocoD blocoD) {
        this.blocoD = blocoD;
    }

    public BlocoE getBlocoE() {
        return blocoE;
    }

    public void setBlocoE(BlocoE blocoE) {
        this.blocoE = blocoE;
    }

    public BlocoG getBlocoG() {
        return blocoG;
    }

    public void setBlocoG(BlocoG blocoG) {
        this.blocoG = blocoG;
    }

    public BlocoH getBlocoH() {
        return blocoH;
    }

    public void setBlocoH(BlocoH blocoH) {
        this.blocoH = blocoH;
    }

    public Bloco1 getBloco1() {
        return bloco1;
    }

    public void setBloco1(Bloco1 bloco1) {
        this.bloco1 = bloco1;
    }
}
