package br.com.openpdv.modelo;

/**
 * Interface de acesso aos dados da Software House.
 *
 * @author Pedro H. Lira
 */
public interface ISisSh {

    public String getSisShBairro();

    public String getSisShCep();

    public String getSisShCidade();

    public String getSisShCnpj();

    public String getSisShComplemento();

    public String getSisShEmail();

    public String getSisShFantasia();

    public String getSisShFone();

    public Integer getSisShId();

    public String getSisShIe();

    public String getSisShIm();

    public String getSisShLogradouro();

    public int getSisShNumero();

    public String getSisShRazao();

    public String getSisShResponsavel();

    public String getSisShUf();

    public void setSisShBairro(String sisShBairro);

    public void setSisShCep(String sisShCep);

    public void setSisShCidade(String sisShCidade);

    public void setSisShCnpj(String sisShCnpj);

    public void setSisShComplemento(String sisShComplemento);

    public void setSisShEmail(String sisShEmail);

    public void setSisShFantasia(String sisShFantasia);

    public void setSisShFone(String sisShFone);

    public void setSisShId(Integer sisShId);

    public void setSisShIe(String sisShIe);

    public void setSisShIm(String sisShIm);

    public void setSisShLogradouro(String sisShLogradouro);

    public void setSisShNumero(int sisShNumero);

    public void setSisShRazao(String sisShRazao);

    public void setSisShResponsavel(String sisShResponsavel);

    public void setSisShUf(String sisShUf);
}
