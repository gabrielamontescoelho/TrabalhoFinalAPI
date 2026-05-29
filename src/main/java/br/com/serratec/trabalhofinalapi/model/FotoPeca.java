package br.com.serratec.trabalhofinalapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class FotoPeca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] dados;

    private String tipo;
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_peca")
    private Peca peca;

    public FotoPeca() {
    }

    public FotoPeca(Long id, byte[] dados, String tipo, String nome, Peca peca) {
        this.id = id;
        this.dados = dados;
        this.tipo = tipo;
        this.nome = nome;
        this.peca = peca;
    }

    public Long getId() {
        return id;
    }

    public byte[] getDados() {
        return dados;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
}