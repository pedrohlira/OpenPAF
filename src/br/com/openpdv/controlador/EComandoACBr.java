package br.com.openpdv.controlador;

/**
 * Enumerador que define os comando do ACBrMonitor.
 * <code>Link para as definicoes http://acbr.sourceforge.net/drupal/?q=node/24</code>
 *
 * @author Pedro H. Lira
 */
public enum EComandoACBr {
    /**
     * Encerra a comunicação TCP/IP com o ACBrMonitor, ou seja, desconecta-se do
     * ACBrMonitor.
     */
    Bye,
    /**
     * Encerra a comunicação TCP/IP com o ACBrMonitor, ou seja, desconecta-se do
     * ACBrMonitor.
     */
    Exit,
    /**
     * Encerra a comunicação TCP/IP com o ACBrMonitor, ou seja, desconecta-se do
     * ACBrMonitor.
     */
    Sair,
    /**
     * Encerra a comunicação TCP/IP com o ACBrMonitor, ou seja, desconecta-se do
     * ACBrMonitor.
     */
    Fim,
    /**
     * Executa comandos na máquina em que o ACBrMonitor está rodando, útil para
     * executar comandos no terminal Remoto, quando usando modo TCP/IP.<br><br>
     *
     * ACBr.Run(cComando, [ cParams, bAguarda, nWindowState, bAltTab ]
     * );<br><br>
     *
     * <b>Parâmetros:</b><br>
     *
     * <b>cComando</b> – Linha de comando a ser executada pelo ACBrMonitor.<br>
     *
     * <b>cParams</b> – (opcional) Parâmetros complementares informados a Linha
     * de Comando.<br>
     *
     * <b>bAguarda</b> – Opcional, se for omitido assume “False”. Quando
     * informado “True”, aguarda o termino da execução do programa informado em
     * cComando, antes de continuar com o ACBrMonitor (cuidado, esse parâmetro
     * em “True” pode fazer o ACBrMonitor para de responder enquanto o Programa
     * executado não seja finalizado)<br>
     *
     * <b>nWindowState</b> – (Opcional) Numérico informando o Estado daJanela:
     * Utilize: 0 = Escondido; 1 = Normal (default); 2 = Minimizado; 3 =
     * Maximizado (Disponível apenas no Windows).<br>
     *
     * <b>bAltTab</b> – Opcional, se for omitido assume “False”. Quando
     * informado “True”, envia ALT+TAB para o teclado do Windows assim que a
     * execução do programa terminar. Útil para restaurar o foco da aplicação
     * controladora do ACBrMonitor (Disponível apenas no Windows).<br><br>
     */
    ACBr_Run,
    /**
     * Exibe a tela do ACBrMonitor.
     */
    ACBr_Restaurar,
    /**
     * Esconde o ACBrMonitor no Systray.
     */
    ACBr_Ocultar,
    /**
     * Encerra, finaliza o ACBrMonitor.
     */
    ACBr_EncerrarMonitor,
    /**
     * Nao especidicado.
     */
    ACBr_SaveTofile,
    /**
     * Nao especidicado.
     */
    ACBr_LoadFromFile,
    /**
     * Nao especidicado.
     */
    ACBr_DeleteFiles;
    
    @Override
    public String toString() {
        return super.toString().replace('_', '.');
    }
}
