package br.com.serratec.trabalhofinalapi.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErroResposta {

    private LocalDateTime timestamp;
    private Integer status;
    private String erro;
    private String mensagem;
    private String path;
    private List<CampoErroResposta> campos;
    
    
    public ErroResposta(Integer status, String erro, String mensagem, String path) {
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.path = path;
    }


    public ErroResposta(Integer status, String erro, String mensagem, String path, List<CampoErroResposta> campos) {
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.path = path;
        this.campos = campos;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    public Integer getStatus() {
        return status;
    }


    public String getErro() {
        return erro;
    }


    public String getMensagem() {
        return mensagem;
    }


    public String getPath() {
        return path;
    }


    public List<CampoErroResposta> getCampos() {
        return campos;
    }

    

    
}