package br.com.openpdv.modelo.sintegra;

import java.io.FileWriter;

public interface Registro {

    public void gerar(FileWriter fw) throws Exception;
}
