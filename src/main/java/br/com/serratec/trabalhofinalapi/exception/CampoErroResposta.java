package br.com.serratec.trabalhofinalapi.exception;

public class CampoErroResposta {

    private String campo;
    private String mensagem;
    
    public CampoErroResposta(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    }
