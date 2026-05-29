package br.com.serratec.trabalhofinalapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinalapi.model.FotoPeca;
import br.com.serratec.trabalhofinalapi.model.Peca;

public interface FotoPecaRepository extends JpaRepository<FotoPeca, Long> {

    Optional<FotoPeca> findByPeca(Peca peca);
}