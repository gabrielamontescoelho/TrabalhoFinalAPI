package br.com.serratec.trabalhofinalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinalapi.model.Peca;

public interface PecaRepository extends JpaRepository<Peca, Long> {
}