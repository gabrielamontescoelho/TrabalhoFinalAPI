package br.com.serratec.trabalhofinalapi.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.config.MailConfig;
import br.com.serratec.trabalhofinalapi.dto.ClienteRequestDto;
import br.com.serratec.trabalhofinalapi.dto.ClienteResponseDto;
import br.com.serratec.trabalhofinalapi.dto.EnderecoResponseDto;
import br.com.serratec.trabalhofinalapi.model.Cliente;
import br.com.serratec.trabalhofinalapi.model.Endereco;
import br.com.serratec.trabalhofinalapi.repository.ClienteRepository;
import br.com.serratec.trabalhofinalapi.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MailConfig config;

    @Transactional
    public ClienteResponseDto inserir(ClienteRequestDto dto) {

        Cliente clienteBanco = repository.findByEmail(dto.email());

        if (clienteBanco != null) {
            throw new RuntimeException("Email já cadastrado");
        }

        Cliente clienteCpf = repository.findByCpf(dto.cpf());

        if (clienteCpf != null) {
            throw new RuntimeException("CPF já cadastrado");
        }

        EnderecoResponseDto enderecoDto = enderecoService.buscarCep(dto.cep());

        Endereco endereco = enderecoRepository.findByCep(
                enderecoDto.cep().replaceAll("-", "")
        );

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setCpf(dto.cpf());
        cliente.setEndereco(endereco);

        Cliente clienteSalvo = repository.save(cliente);

        config.sendEmail(
        clienteSalvo.getEmail(),
        "Cadastro realizado",
        "Cliente cadastrado com sucesso!\n" +
        "Nome: " + clienteSalvo.getNome() + "\n" +
        "Email: " + clienteSalvo.getEmail() + "\n" +
        "Telefone: " + clienteSalvo.getTelefone() + "\n" +
        "CPF: " + clienteSalvo.getCpf());

        return new ClienteResponseDto(clienteSalvo);

        
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ClienteResponseDto editar(Long id, ClienteRequestDto dto) {

        Cliente clienteBanco = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        EnderecoResponseDto enderecoDto = enderecoService.buscarCep(dto.cep());

        Endereco endereco = enderecoRepository.findByCep(
                enderecoDto.cep().replaceAll("-", "")
        );

        clienteBanco.setNome(dto.nome());
        clienteBanco.setEmail(dto.email());
        clienteBanco.setTelefone(dto.telefone());
        clienteBanco.setCpf(dto.cpf());
        clienteBanco.setEndereco(endereco);

        Cliente clienteAtualizado = repository.save(clienteBanco);

        config.sendEmail(
                clienteAtualizado.getEmail(),
                "Cadastro atualizado",
                "Cliente atualizado com sucesso!\n" +
                "Nome: " + clienteAtualizado.getNome() + "\n" +
                "Email: " + clienteAtualizado.getEmail()
        );

        return new ClienteResponseDto(clienteAtualizado);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Page<Cliente> listarPorPagina(Pageable pageable) {
        return repository.findAll(pageable);
    }
}