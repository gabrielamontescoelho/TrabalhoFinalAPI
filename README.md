# 🛠️ API OFICINA MECÂNICA — PROJETO FINAL 🚀

API desenvolvida como projeto final de residência para digitalizar, gerenciar e otimizar todo o fluxo de trabalho de uma oficina mecânica moderna. A aplicação cobre desde o controle de clientes e frotas até a emissão de ordens de serviço complexas e análises inteligentes de dados.

Construída sobre o ecossistema Java 17 e Spring Boot 3.x, a aplicação adota uma arquitetura em camadas (Controller, Service, Repository, Model/DTO) para garantir isolamento de responsabilidades, alta escalabilidade, segurança rigorosa e facilidade de manutenção.

------------------------------------------------------------------------
👥 INTEGRANTES DO GRUPO
------------------------------------------------------------------------
* Gabriela Montes Gomes Coelho — @gabrielamontescoelho 👩‍💻
* João Paulo Cordebello — @jpcordebello 👨‍💻
* Lucas Branco Lira — @lc-lira 👨‍💻
* Nathália de Queiroz Antunes — @nathaliaa-qa 👩‍💻
* Pedro Lucas da Costa Teixeira — @pedroteixeira5 👨‍💻

------------------------------------------------------------------------
🛠️ TECNOLOGIAS E FERRAMENTAS UTILIZADAS
------------------------------------------------------------------------
* Core: Java 17 & Spring Boot 3.x
* Persistência & Dados: Spring Data JPA, Hibernate, PostgreSQL (Produção) e H2 Database (Desenvolvimento)
* Segurança: Spring Security, JWT (JSON Web Tokens) e BCryptPasswordEncoder
* Comunicação & Integração: JavaMail Sender (Notificações por e-mail) e API Externa ViaCEP
* Documentação: Swagger UI e SpringDoc OpenAPI
* Gerenciamento de Dependências: Maven

------------------------------------------------------------------------
📋 FUNCIONALIDADES PRINCIPAIS (ESCOPO DO GRUPO)
------------------------------------------------------------------------

👤 CLIENTES E ENDEREÇOS
- Cadastro Automatizado: Inserção e atualização de clientes com validação estrita de dados obrigatórios (Nome, Telefone, E-mail e CPF).
- Integração ViaCEP: Consumo da API externa do ViaCEP para preenchimento automático do endereço.
- Notificações por E-mail: Sistema assíncrono que dispara e-mails automáticos para o cliente confirmando a realização de novos cadastros ou alterações.

🚗 VEÍCULOS E PROPRIEDADE
- Controle de Frotas: Registro detalhado de veículos (marca, modelo, ano, cor e placa).
- Vínculo Comercial: Todo veículo é obrigatoriamente associado ao ID de um cliente proprietário.

🔧 SERVIÇOS E ORDENS DE SERVIÇO (OS)
- Catálogo de Serviços: Registro prévio de procedimentos operacionais com valores base e tempo estimado.
- Fluxo de OS: Abertura e edição de Ordens de Serviço vinculando cliente e veículo.
- Controle de Estado: Ciclo de vida gerido através dos estados: ABERTA, EM_ANDAMENTO, FINALIZADA e CANCELADA.
- Tabela Intermediária (N:N): Modelagem de múltiplos serviços em uma única OS, com cálculos dinâmicos de subtotais e totais.

🔒 SEGURANÇA E INFRAESTRUTURA
- Autenticação JWT: Bloqueio de rotas protegidas com Spring Security.
- Criptografia: Senhas salvas de forma segura utilizando BCryptPasswordEncoder.
- Tratamento de Erros Global: Centralização de exceções retornando respostas padronizadas e limpas.
- Documentação Interativa: Interface Swagger UI para teste em tempo real de todas as rotas.

------------------------------------------------------------------------
📊 FUNCIONALIDADE EXTRA (PARTE INDIVIDUAL)
------------------------------------------------------------------------
📂 HISTÓRICO DE MANUTENÇÃO E INTELIGÊNCIA DE DADOS
Desenvolvedora: Nathália de Queiroz Antunes — @nathaliaa-qa 👩‍💻

Extensão robusta focada no armazenamento histórico de intervenções mecânicas por veículo e na inteligência de dados aplicada ao negócio.

📌 Endpoints do CRUD (/historicos)
- POST /historicos : Registra uma nova manutenção detalhada atrelada a um veículo existente.
- GET /historicos : Lista todos os históricos de manutenção.
- PUT /historicos/{id} : Atualiza dados específicos de um registro de manutenção existente.
- DELETE /historicos/{id} : Remove um registro do histórico pelo seu ID.

📈 Análise de Dados e Consultas Customizadas (/historicos/relatorio/{veiculoId})

1. Média de Gastos por Veículo:
   Cálculo do valor médio despendido em manutenções por um veículo específico.
   
   @Query("SELECT AVG(h.valorTotal) FROM HistoricoManutencao h WHERE h.veiculo.id = :veiculoId")
    Double calcularMediaGastosPorVeiculo(Long veiculoId);

2. Veículo com Mais Gastos / Entradas por Defeito:
   Identifica qual modelo de carro gerou o maior volume de ordens de manutenção e gastos.
   
    @Query(value = "SELECT v.modelo FROM tb_historico_manutencao h " +"JOIN tb_veiculo v ON h.id_veiculo = v.id " + 
"GROUP BY v.modelo ORDER BY COUNT(h.id) DESC LIMIT 1",  nativeQuery = true) String buscarModeloComMaisDefeitos();   

------------------------------------------------------------------------
📁 COMO EXECUTAR O PROJETO
------------------------------------------------------------------------

1. Clonar o Repositório
   No terminal, execute:
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   cd nome-do-repositorio

2. Configurar as Propriedades da Aplicação
   No arquivo src/main/resources/application.properties:
   - Certifique-se de que o perfil 'developer' está ativo para usar o H2 Database.
   - Altere as propriedades de Mail Sender com as suas credenciais de e-mail:
     spring.mail.username=seu-email@gmail.com
     spring.mail.password=sua-senha-de-aplicativo

3. Executar a Aplicação
   Execute via IDE ou pelo terminal:
   mvn spring-boot:run

4. Acessar a Documentação (Swagger UI)
   No navegador, acesse:
   http://localhost:8080/swagger-ui/index.html

5. Incluir Funcionalidades Individuais
   
   Para testar a branch da Nathália:
   git checkout parte-individual-nathalia

   Para testar a branch da Gabriela:
   git checkout parte-individual-gabriela

   Para testar a branch do João:
   git checkout joao-parte-ind

   Para testar a branch do Lucas:
   git checkout lucasindividual

   Para testar a branch do Pedro:
   git checkout parte-individual-pedro



		
