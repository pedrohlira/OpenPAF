package br.com.phdss.modelo.sintegra;

import br.com.phdss.controlador.PAF;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que gera o arquivo do sintegra com os dados passados.
 *
 * @author Pedro H. Lira
 */
public class Sintegra implements Registro {

    private Dados10 dados10;
    private Dados11 dados11;
    private List<Dados50> dados50;
    private List<Dados54> dados54;
    private List<Dados60M> dados60M;
    private List<Dados60R> dados60R;
    private List<Dados61> dados61;
    private List<Dados61R> dados61R;
    private List<Dados74> dados74;
    private List<Dados75> dados75;
    private Dados90 dados90;

    public Sintegra() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if (dados10 != null) {
            dados10.gerar(fw);
        }
        if (dados11 != null) {
            dados11.gerar(fw);
        }
        if (dados50 != null) {
            for (Dados50 d : dados50) {
                d.gerar(fw);
            }
        }
        if (dados54 != null) {
            for (Dados54 d : dados54) {
                d.gerar(fw);
            }
        }
        if (dados60M != null) {
            for (Dados60M d : dados60M) {
                d.gerar(fw);
            }
        }
        if (dados60R != null) {
            for (Dados60R d : dados60R) {
                d.gerar(fw);
            }
        }
        if (dados61 != null) {
            for (Dados61 d : dados61) {
                d.gerar(fw);
            }
        }
        if (dados61R != null) {
            for (Dados61R d : dados61R) {
                d.gerar(fw);
            }
        }
        if (dados74 != null) {
            for (Dados74 d : dados74) {
                d.gerar(fw);
            }
        }
        if (dados75 != null) {
            for (Dados75 d : dados75) {
                d.gerar(fw);
            }
        }
        gerarDados90();
        if (dados90 != null) {
            dados90.gerar(fw);
        }
        fw.close();
    }

    private void gerarDados90() throws Exception {
        // Lendo as linhas do sintegra
        Map<String, Integer> regs = new LinkedHashMap<>();
        int total = 1;

        // lendo o arquivo
        BufferedReader br = new BufferedReader(new FileReader(PAF.getPathArquivos() + "sintegra.txt"));
        String linha, chave;
        while ((linha = br.readLine()) != null) {
            chave = linha.substring(0, 2);
            if (!chave.equals("10") && !chave.equals("11")) {
                Integer qtd = regs.get(chave);
                qtd = qtd == null ? 1 : qtd + 1;
                regs.put(chave, qtd);
            }
            total++;
        }

        // montando os totalizadores
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> reg : regs.entrySet()) {
            sb.append(reg.getKey());
            String valor = reg.getValue() + "";
            while (valor.length() < 8) {
                valor = "0" + valor;
            }
            sb.append(valor);
        }
        // total geral
        sb.append("99");
        String valor = total + "";
        while (valor.length() < 8) {
            valor = "0" + valor;
        }
        sb.append(valor);

        dados90 = new Dados90();
        dados90.setCnpj(dados10.getCnpj());
        dados90.setIe(dados10.getIe());
        dados90.setTotalizadores(sb.toString());
        dados90.setRegistro90(1);
    }

    public Dados10 getDados10() {
        return dados10;
    }

    public void setDados10(Dados10 dados10) {
        this.dados10 = dados10;
    }

    public Dados11 getDados11() {
        return dados11;
    }

    public void setDados11(Dados11 dados11) {
        this.dados11 = dados11;
    }

    public List<Dados50> getDados50() {
        return dados50;
    }

    public void setDados50(List<Dados50> dados50) {
        this.dados50 = dados50;
    }

    public List<Dados54> getDados54() {
        return dados54;
    }

    public void setDados54(List<Dados54> dados54) {
        this.dados54 = dados54;
    }

    public List<Dados60M> getDados60M() {
        return dados60M;
    }

    public void setDados60M(List<Dados60M> dados60M) {
        this.dados60M = dados60M;
    }

    public List<Dados60R> getDados60R() {
        return dados60R;
    }

    public void setDados60R(List<Dados60R> dados60R) {
        this.dados60R = dados60R;
    }

    public List<Dados61> getDados61() {
        return dados61;
    }

    public void setDados61(List<Dados61> dados61) {
        this.dados61 = dados61;
    }

    public List<Dados61R> getDados61R() {
        return dados61R;
    }

    public void setDados61R(List<Dados61R> dados61R) {
        this.dados61R = dados61R;
    }

    public List<Dados74> getDados74() {
        return dados74;
    }

    public void setDados74(List<Dados74> dados74) {
        this.dados74 = dados74;
    }

    public List<Dados75> getDados75() {
        return dados75;
    }

    public void setDados75(List<Dados75> dados75) {
        this.dados75 = dados75;
    }
}
