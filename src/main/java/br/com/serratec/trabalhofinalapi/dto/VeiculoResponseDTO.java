package br.com.serratec.trabalhofinalapi.dto;

public class VeiculoResponseDTO {
    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;
    private ProprietarioDTO proprietario; 

    public VeiculoResponseDTO() {}

    public VeiculoResponseDTO(Long id, String placa, String marca, String modelo, Integer ano, String cor, ProprietarioDTO proprietario) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.proprietario = proprietario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public ProprietarioDTO getProprietario() { return proprietario; }
    public void setProprietario(ProprietarioDTO proprietario) { this.proprietario = proprietario; }
}
