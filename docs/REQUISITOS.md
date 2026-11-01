# Documento de Projeto — HelpDesk TI

## 1. Apresentação
O HelpDesk TI é um sistema desktop destinado a auxiliar empresas no registro e acompanhamento de solicitações de suporte técnico. A aplicação organiza informações sobre usuários, equipamentos e chamados em um único ambiente.

## 2. Descrição do projeto
O sistema será desenvolvido em Java, com interface desktop e banco de dados MySQL. Permitirá cadastrar funcionários e técnicos, registrar equipamentos, abrir chamados, consultar solicitações e atualizar o andamento dos atendimentos.

## 3. Descrição dos usuários
- **Funcionário:** solicita suporte e acompanha os próprios chamados.
- **Técnico de TI:** consulta chamados, realiza atendimento, atualiza status e registra solução.
- **Administrador (evolução prevista):** poderá administrar cadastros e acompanhar indicadores.

## 4. Necessidades observadas e regras de negócio
### Necessidades
- Centralizar solicitações de suporte.
- Evitar perda de informações em pedidos feitos informalmente.
- Permitir acompanhamento do status.
- Manter histórico de problemas e soluções.

### Regras
1. Todo chamado deve estar associado a um usuário solicitante.
2. Todo chamado deve possuir título e descrição.
3. O status será Aberto, Em andamento ou Concluído.
4. Apenas perfil técnico poderá alterar o status na versão com autenticação/perfis.
5. Para concluir um chamado, deve ser informada a solução.
6. O patrimônio de cada equipamento deve ser único.
7. Um chamado pode não estar associado a um equipamento específico.

## 5. Requisitos funcionais
- **RF01:** cadastrar funcionários e técnicos.
- **RF02:** consultar usuários cadastrados.
- **RF03:** cadastrar equipamentos.
- **RF04:** consultar equipamentos.
- **RF05:** abrir chamados de suporte.
- **RF06:** listar chamados registrados.
- **RF07:** atualizar status do chamado.
- **RF08:** registrar a solução do atendimento.

## 6. Requisitos não funcionais
- **RNF01:** utilizar Java.
- **RNF02:** utilizar MySQL como banco de dados.
- **RNF03:** desenvolver no Apache NetBeans.
- **RNF04:** oferecer interface desktop simples e intuitiva.
- **RNF05:** organizar o código com orientação a objetos.
- **RNF06:** validar campos obrigatórios.
- **RNF07:** utilizar JDBC e comandos parametrizados para acesso a dados.
- **RNF08:** apresentar mensagens compreensíveis em caso de erro.

## 7. Classes previstas
- **Usuario:** id, nome, email, tipo.
- **Equipamento:** id, nome, patrimonio, tipo.
- **Chamado:** id, titulo, descricao, status, solucao, usuarioId, equipamentoId, dataAbertura.
- **ConexaoMySQL:** fornece conexão JDBC.
- **HelpDeskDAO:** operações de cadastro, consulta e atualização.
- **TelaPrincipal:** interface desktop inicial.
