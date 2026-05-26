package br.com.serratec.trabalhofinalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.serratec.trabalhofinalapi.model.HistoricoManutencao;

@Repository
public interface HistoricoManutencaoRepository extends JpaRepository<HistoricoManutencao, Long> {

    @Query("SELECT AVG(h.valorTotal) FROM HistoricoManutencao h WHERE h.veiculo.id = :veiculoId")
    Double calcularMediaGastosPorVeiculo(Long veiculoId);

    @Query(value = "SELECT v.modelo FROM tb_historico_manutencao h " +"JOIN tb_veiculo v ON h.id_veiculo = v.id " + "GROUP BY v.modelo ORDER BY COUNT(h.id) DESC LIMIT 1",  nativeQuery = true)
    String buscarModeloComMaisDefeitos();   
}