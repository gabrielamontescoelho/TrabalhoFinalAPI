package br.com.serratec.trabalhofinalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinalapi.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}