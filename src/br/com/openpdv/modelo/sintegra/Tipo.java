package br.com.openpdv.modelo.sintegra;

import java.io.FileWriter;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

public abstract class Tipo implements Registro {

    protected String bean;
    protected int tipo;

    public Tipo(int tipo) {
        this.tipo = tipo;
        this.bean = "/" + getClass().getName().replace("Dados", "Bean").replace('.', '/') + ".xml";
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        StreamFactory factory = StreamFactory.newInstance();
        factory.load(getClass().getResourceAsStream(bean));
        BeanWriter bw = factory.createWriter("SINTEGRA", fw);
        bw.write(this);
        bw.flush();
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int reg) {
        this.tipo = reg;
    }
}
