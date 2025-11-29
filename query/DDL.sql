-- == 1. Criação dos ENUMs ==
CREATE TYPE status_imovel AS ENUM ('disponivel', 'alugado', 'manutencao', 'reservado');
CREATE TYPE tipo_imovel AS ENUM ('casa', 'apartamento', 'kitnet');
CREATE TYPE status_contrato AS ENUM ('finalizado', 'em andamento');
CREATE TYPE tipo_enum_vistoria AS ENUM ('entrada', 'periodica', 'saida', 'extra');
CREATE TYPE tipo_enum_auditoria AS ENUM ('financeiro', 'operacional', 'qualidade');
CREATE TYPE status_parcela AS ENUM ('pago', 'pendente');

-- == 2. Criação das tabelas ==

-- Tabela Proprietario
CREATE TABLE Proprietario (
	id_proprietario INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	nome_completo VARCHAR(255) NOT NULL,
	cpf CHAR(11) NOT NULL UNIQUE,
	telefone VARCHAR(15) NOT NULL,
	email VARCHAR(50) NOT NULL,
	endereco VARCHAR(255),
	data_cadastro DATE DEFAULT CURRENT_DATE
);

-- Tabela Inquilino
CREATE TABLE Inquilino (
	id_inquilino INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	nome_completo VARCHAR(255) NOT NULL,
	cpf CHAR(11) NOT NULL UNIQUE,
	telefone VARCHAR(15) NOT NULL,
	email VARCHAR(50) NOT NULL,
	data_nascimento DATE NOT NULL,
	data_cadastro DATE DEFAULT CURRENT_DATE
);

-- Tabela Imovel
CREATE TABLE Imovel (
	id_imovel INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	tipo tipo_imovel DEFAULT 'casa',
	endereco VARCHAR(255) NOT NULL,
	area_total INT NOT NULL,
	qtd_quartos INT NOT NULL,
	valor_aluguel NUMERIC(10,2) NOT NULL,
	status status_imovel DEFAULT 'disponivel'
);

-- Tabela Pagamento
CREATE TABLE Pagamento (
	id_pagamento INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	data_pagamento DATE DEFAULT CURRENT_DATE,
	valor_pago NUMERIC(10,2),
	forma_pagamento VARCHAR(20) NOT NULL,
	comprovante VARCHAR(255)
);

-- Tabela Manutencao
CREATE TABLE Manutencao (
	id_manutencao INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	data_manutencao DATE DEFAULT CURRENT_DATE,
	custo NUMERIC(10,2) NOT NULL,
	responsavel VARCHAR(20) NOT NULL,
	fk_imovel INT NOT NULL
);

-- Tabela Contrato
CREATE TABLE Contrato (
	id_contrato INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	data_inicio DATE NOT NULL,
	data_fim DATE NOT NULL,
	status status_contrato NOT NULL,
	clausulas TEXT NOT NULL,
	fk_inquilino INT NOT NULL,
	fk_proprietario INT NOT NULL,
	fk_imovel INT NOT NULL
);

-- Tabela Vistoria
CREATE TABLE Vistoria (
	id_vistoria INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	data_vistoria DATE DEFAULT CURRENT_DATE,
	tipo_vistoria tipo_enum_vistoria DEFAULT 'entrada',
	fk_imovel INT NOT NULL,
	fk_contrato INT NOT NULL
);

-- Tabela AuditoriaContrato
CREATE TABLE Auditoria_contrato (
	id_auditoria INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	data_auditoria DATE DEFAULT CURRENT_DATE,
	tipo_auditoria tipo_enum_auditoria DEFAULT 'financeiro',
	responsavel_auditoria VARCHAR(20),
	fk_contrato INT NOT NULL
);

-- Tabela Parcela
CREATE TABLE Parcela (
	id_parcela INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	numero_parcela INT NOT NULL,
	valor NUMERIC(10,2) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	status status_parcela DEFAULT 'pendente',
	fk_pagamento INT,
	fk_contrato INT NOT NULL
);

-- == 3. Adição de FKs ==

-- Manutencao
ALTER TABLE Manutencao
ADD CONSTRAINT fk_manutencao_imovel
FOREIGN KEY (fk_imovel) REFERENCES Imovel(id_imovel);

-- Contrato
ALTER TABLE Contrato
ADD CONSTRAINT fk_contrato_inquilino
FOREIGN KEY (fk_inquilino) REFERENCES Inquilino(id_inquilino),
ADD CONSTRAINT fk_contrato_proprietario
FOREIGN KEY (fk_proprietario) REFERENCES Proprietario(id_proprietario),
ADD CONSTRAINT fk_contrato_imovel
FOREIGN KEY (fk_imovel) REFERENCES Imovel(id_imovel);

-- Vistoria
ALTER TABLE Vistoria
ADD CONSTRAINT fk_vistoria_imovel
FOREIGN KEY (fk_imovel) REFERENCES Imovel(id_imovel),
ADD CONSTRAINT fk_vistoria_contrato
FOREIGN KEY (fk_contrato) REFERENCES Contrato(id_contrato);

-- AuditoriaContrato
ALTER TABLE Auditoria_contrato
ADD CONSTRAINT fk_auditoria_contrato
FOREIGN KEY (fk_contrato) REFERENCES Contrato(id_contrato);

-- Parcela
ALTER TABLE Parcela
ADD CONSTRAINT fk_parcela_pagamento
FOREIGN KEY (fk_pagamento) REFERENCES Pagamento(id_pagamento),
ADD CONSTRAINT fk_parcela_contrato
FOREIGN KEY (fk_contrato) REFERENCES Contrato(id_contrato);

-- == 4. Log / Auditoria Automatica ==

CREATE TABLE Log_imobiliaria (
	id_log INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	data_hora TIMESTAMP,
	usuario VARCHAR(50),
	tabela_afetada VARCHAR(50),
	acao VARCHAR(50),
	descricao VARCHAR(50)
);
