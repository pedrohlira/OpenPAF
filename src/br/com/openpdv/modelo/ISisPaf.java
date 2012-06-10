package br.com.openpdv.modelo;

/**
 * Interface de acesso aos dados do PAF.
 *
 * @author Pedro H. Lira
 */
public interface ISisPaf {

    public String getSisPafEr();

    public String getSisPafExe();

    public Integer getSisPafId();

    public String getSisPafLaudo();

    public String getSisPafMd5();

    public String getSisPafNome();

    public String getSisPafVersao();

    public void setSisPafEr(String sisPafEr);

    public void setSisPafExe(String sisPafExe);

    public void setSisPafId(Integer sisPafId);

    public void setSisPafLaudo(String sisPafLaudo);

    public void setSisPafMd5(String sisPafMd5);

    public void setSisPafNome(String sisPafNome);

    public void setSisPafVersao(String sisPafVersao);
}
