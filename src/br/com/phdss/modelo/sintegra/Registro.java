package br.com.phdss.modelo.sintegra;

import java.io.FileWriter;

/**
 * Interface que define como cada registro deve gerar o arquivo.
 *
 * @author Pedro H. Lira
 */
public interface Registro {

    /**
     * Metodo que recebe o arquivo a ser escrito e gera de acordo com os dados.
     *
     * @param fw instancia do arquivo a ser escrito.
     * @throws Exception dispara caso ocorra uma excecao.
     */
    public void gerar(FileWriter fw) throws Exception;
}
