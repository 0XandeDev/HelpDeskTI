CREATE DATABASE IF NOT EXISTS helpdesk_ti
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE helpdesk_ti;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    tipo ENUM('FUNCIONARIO','TECNICO') NOT NULL
);

CREATE TABLE IF NOT EXISTS equipamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    patrimonio VARCHAR(50) NOT NULL UNIQUE,
    tipo VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS chamados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    status ENUM('Aberto','Em andamento','Concluído') NOT NULL DEFAULT 'Aberto',
    solucao TEXT NULL,
    usuario_id INT NOT NULL,
    equipamento_id INT NULL,
    data_abertura TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_chamado_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_chamado_equipamento FOREIGN KEY (equipamento_id) REFERENCES equipamentos(id)
);

-- Dados iniciais para teste (podem ser apagados antes da entrega final).
INSERT IGNORE INTO usuarios (nome,email,tipo) VALUES
('Ana Souza','ana.souza@empresa.com','FUNCIONARIO'),
('Carlos Lima','carlos.lima@empresa.com','TECNICO');
INSERT IGNORE INTO equipamentos (nome,patrimonio,tipo) VALUES
('Computador do Financeiro','PAT-001','Computador'),
('Impressora Recepção','PAT-002','Impressora');
