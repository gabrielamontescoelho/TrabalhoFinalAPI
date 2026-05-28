package br.com.serratec.trabalhofinalapi.dto;

public class RelatorioOficinaDTO {
    private String modeloMaisProblematico;
    private Double mediaGastosGerais;

    public RelatorioOficinaDTO() {}

    public RelatorioOficinaDTO(String modeloMaisProblematico, Double mediaGastosGerais) {
        this.modeloMaisProblematico = modeloMaisProblematico;
        this.mediaGastosGerais = mediaGastosGerais;
    }

    public String getModeloMaisProblematico() { return modeloMaisProblematico; }
    public void setModeloMaisProblematico(String modeloMaisProblematico) { this.modeloMaisProblematico = modeloMaisProblematico; }

    public Double getMediaGastosGerais() { return mediaGastosGerais; }
    public void setMediaGastosGerais(Double mediaGastosGerais) { this.mediaGastosGerais = mediaGastosGerais; }
}
