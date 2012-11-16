package br.com.phdss.modelo.sintegra;

import java.io.FileWriter;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

/**
 * Classe abstrata que define o tipo de dados do sintegra, padronizando algumas
 * informacoes.
 *
 * @author Pedro H. Lira
 */
public abstract class Tipo implements Registro {

    protected String bean;
    protected int tipo;

    /**
     * Construtor passando o tipo usado dos registro Sintegra.
     *
     * @param tipo o inteiro do tipo.
     */
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
