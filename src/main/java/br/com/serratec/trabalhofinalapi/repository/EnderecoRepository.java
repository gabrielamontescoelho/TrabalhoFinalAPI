package br.com.serratec.trabalhofinalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhofinalapi.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    public Endereco findByCep(String cep);
}
