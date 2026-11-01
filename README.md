# HelpDesk TI

**Status:** Em desenvolvimento — Projeto Integrador SENAC (Etapas 1 a 5).

## Objetivo
Sistema desktop para registrar e acompanhar solicitações de suporte técnico em uma empresa, organizando usuários, equipamentos, chamados e soluções.

## Tecnologias
- Java 17
- Swing (interface desktop)
- MySQL
- JDBC / MySQL Connector/J
- Apache NetBeans
- Git e GitHub

## Funcionalidades previstas
- Cadastro de funcionários e técnicos.
- Cadastro de equipamentos.
- Abertura de chamados de suporte.
- Consulta de chamados.
- Atualização do status e registro da solução.

## Desenvolvedor
- Alexandre Oliveira Machado

## Estrutura
- `src/main/java`: código-fonte Java.
- `database/helpdesk_ti.sql`: criação das tabelas e dados iniciais.
- `docs`: documentação do projeto e plano das etapas.
- `wireframes`: protótipos visuais iniciais.
- `evidencias`: local para guardar capturas reais do GitHub e histórico de commits.

## Como abrir no NetBeans
1. Extraia o ZIP.
2. No NetBeans, escolha **File > Open Project**.
3. Selecione a pasta `HelpDeskTI_PI` (a pasta que contém `pom.xml`).
4. Aguarde o Maven baixar a dependência MySQL Connector/J.
5. Execute `br.com.helpdeskti.Main`.

## Configuração do MySQL
1. Abra o MySQL Workbench.
2. Abra e execute `database/helpdesk_ti.sql`.
3. Se necessário, configure as variáveis de ambiente:
   - `HELPDESK_DB_URL`
   - `HELPDESK_DB_USER`
   - `HELPDESK_DB_PASSWORD`
4. Por padrão, a aplicação usa usuário `root` e senha vazia. Altere a configuração conforme o seu MySQL.

## Etapas do Projeto Integrador
- **Etapa 1:** requisitos, classes, UML e protótipo inicial.
- **Etapa 2:** UX/UI, wireframes, cores e acessibilidade.
- **Etapa 3:** programação das interfaces e navegação.
- **Etapa 4:** integração MySQL, persistência, consultas e testes.
- **Etapa 5:** versionamento GitHub, commits e evidências.

> Importante: este repositório é uma base de trabalho evolutiva. As entregas de cada etapa devem ser revisadas, testadas e complementadas conforme as orientações do docente. As capturas de evidência da Etapa 5 precisam ser feitas na conta GitHub do aluno; não são geradas artificialmente.
