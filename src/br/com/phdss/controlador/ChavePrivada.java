package br.com.phdss.controlador;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * Classe que contem a chave privada.
 *
 * @author Pedro H. Lira
 */
class ChavePrivada {

    private ChavePrivada() {
    }
    /**
     * Chave de 1024 bits RSA em base64.
     */
    static String VALOR;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("hRKPFqaYY46wLmchUU5oXFFa6uKWVpD58iLWJqZF0WuR6jf2wwaGbOMSxnjQpH4yUaRssE7q1NC4Y1cvbU4t29ShygeVBT1BtXTCBFBQ6tQSKhBVFnCvrh10DmL/v1dCsYOD6+KKgFQSqsyGs6FFcLHAEDHaP2XIuE9i9yOjnf3oD5KGYujitdKvP96OHHxhslE4qQ");
        sb.append("FORkhOSiCI0eSay0gtYai60cO3ARgRvcBYveDeWUSwXJjkaAhSF//Xl8tTdTreZWIpi64xWy/QleakiEkCDMq6Ckm+zTojojiU1h1q0COCddbqiZWR/jp58Uatwk35TV8z8/fzKcE3K3CAf42ihnI2cF9E2hzbC1Xx1H7AB/Ub5Gg2GFh8ipmgMF4Edh4CkQph90+9");
        sb.append("PIvmhaZpV3u2ETXyYxTVXpIMfQXHIXx4vUf+2PnWQdov0+2gxIdpfnlcGb4y79aof1bT61DBsuUeOxSdZRNvDlelXTgRIHZJLEpl66k9DPH2dseI6NBZSeHjoQCWmIacJgbRIyqyNcI/GlGeuuKgmvDLAWAONkIHcowvMf8S7UoZ1R9uEwGhayD2sl51Q4lc3r7cn7");
        sb.append("BiRvM9OAXEvo/Dzj4+Ezahvaz520sv9onP74wcU+464fo0coJOvMoEPEw7jNbOKbot/iWRXmsZ+db3t4G+cJvsUw8XJSJyRkzHbnLLpMIPzQ9NlmXn9c27D6JMIf0lc371S+ytYseD2dRMNefK72S2emZQqOG1AE394vVNo6UnUx/sceJd15G+R/myC9HGIRmskA66");
        sb.append("xLTrfrkBRzyv+SwXgoMPC1KtzjOvN6LI87mfjf/GbOXmh8szoaydgJ3k3CTzuXdQiDTeU4YHkega16K1r04nghsqt5GAXwHUpOxSARJcctA7427iytVM6EpTG2JJsho5xMS0lI20REoXVBftWYMakF7W6q9QCG9BN5ytpDFqgnnymgYpxZ8STC9VZbexx2tGmEfXDq");
        sb.append("/A/i9ImYXpZ4rzuEDHooDpTkGaul8AyFTzc9NlPIJv4k6SuP+QkmvFVfx3+TSANtg6THCphXGidS+tRF7RTSecVrcflJz7a6SEsHBkAkXgL/Yt3jm8cIwzDbYvnnyfD6Xgx8ktNWcL+wFqmkRdtXmCmwS7e7FIWDkJ");

        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("852A0B1988AE0DF88DA242C7277360FBF5639A8E");
        VALOR = encryptor.decrypt(sb.toString());
    }
}
