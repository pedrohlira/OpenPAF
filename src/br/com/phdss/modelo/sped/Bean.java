package br.com.phdss.modelo.sped;

import java.io.FileWriter;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

public abstract class Bean implements Bloco {

    protected String bean;
    protected String padrao;
    protected String reg;

    public Bean(String reg) {
        this.reg = reg;
        this.bean = "/" + getClass().getName().replace("Dados", "Bean").replace('.', '/') + ".xml";
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(getClass().getResourceAsStream(bean));
        BeanWriter bw = factory.createWriter("EFD", fw);
        bw.write(this);
        bw.flush();
    }

    public String getPadrao() {
        return padrao;
    }

    public void setPadrao(String padrao) {
        this.padrao = padrao;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }
}
