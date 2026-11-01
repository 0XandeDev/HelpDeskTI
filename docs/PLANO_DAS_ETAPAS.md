# Plano de continuidade — Projeto Integrador SENAC

O HelpDesk TI será mantido como um único projeto durante as cinco etapas. A cada etapa, a versão anterior será ampliada, sem trocar de tema ou recomeçar do zero.

## Etapa 1 — Projeto, requisitos e classes
- Documento de apresentação, descrição, usuários, necessidades, regras e requisitos.
- Classes iniciais: Usuario, Equipamento e Chamado.
- Diagrama UML.
- Protótipo de execução pela classe Main.

## Etapa 2 — UX, UI e acessibilidade
- Wireframes das telas: painel inicial, usuários, equipamentos e chamados.
- Paleta: azul-marinho para navegação, azul para ações primárias, fundo claro e cinza para áreas neutras.
- Acessibilidade: contraste adequado, textos legíveis, navegação por teclado, rótulos claros, mensagens de erro objetivas e não depender apenas de cores para indicar status.
- Validar o desenho com o professor/usuários antes de consolidar as telas.

## Etapa 3 — Interface gráfica
- Implementar e revisar telas Swing.
- Garantir navegação entre telas e validação de campos.
- Testar todas as interações sem depender de conexão com banco, usando dados simulados se necessário.
- Remover simulações quando a persistência real for concluída na etapa 4.

## Etapa 4 — Banco de dados
- Criar o banco com o script database/helpdesk_ti.sql no MySQL Workbench.
- Configurar conexão JDBC.
- Testar cadastros, consultas e atualização de chamados.
- Incluir dados iniciais para teste.
- Remover dados provisórios e validar integridade e mensagens de erro.

## Etapa 5 — Git e GitHub
- Criar repositório remoto com o nome HelpDeskTI.
- Fazer commits pequenos e descritivos ao concluir cada parte.
- Executar pull antes de integrar mudanças e push para publicar commits.
- README.md incluído na raiz.
- Guardar prints reais do repositório e do histórico em `evidencias/`.
- Gerar PDF/DOCX com evidências reais para a entrega.

## Controle de versão sugerido
1. `chore: cria estrutura inicial do HelpDeskTI`
2. `docs: adiciona requisitos e diagrama UML`
3. `ui: adiciona wireframes e prototipo de interface`
4. `feat: implementa telas e navegacao`
5. `feat: integra persistencia com MySQL`
6. `docs: atualiza README e evidencias do GitHub`
