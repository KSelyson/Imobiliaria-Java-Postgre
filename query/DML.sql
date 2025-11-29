-- # 1. INSERTS TABELA PROPRIETARIO

INSERT INTO Proprietario (nome_completo, cpf, telefone, email, endereco) VALUES
('Aerith Gainsborough', '12345678901', '(11)99988-7777', 'aerith.gains@gaia.com', 'Rua Setima Ceu, 42, Midgar'),
('Geralt de Rivia', '98765432109', '(22)11122-3333', 'geralt.rivia@kaermorhen.com', 'Castelo Kaer Morhen, Kaedwen'),
('Light Yagami', '13579246800', '(33)55544-3333', 'kira.l@shinigami.jp', 'Av. Central, 101, Tóquio');

-- # 2. INSERTS NA TABELA INQUILINO

INSERT INTO Inquilino (nome_completo, cpf, telefone, email, data_nascimento) VALUES
('Levi Ackerman', '11223344550', '(44)66655-4444', 'levi.a@scouts.org', '1980-12-25'),
('Michael Scott', '24680135790', '(55)77766-5555', 'michael.s@dundermifflin.com', '1965-03-15'),
('Cloud Strife', '50505050500', '(66)88877-6666', 'cloud.s@fenrir.net', '1990-08-19');

-- # 3. INSERTS NA TABELA IMOVEL

INSERT INTO Imovel (tipo, endereco, area_total, qtd_quartos, valor_aluguel, status) VALUES
('apartamento', 'Bloco D-5, Shiganshina', 80, 2, 2500.00, 'alugado'), -- Alugado pelo Levi Ackerman
('casa', 'Alameda da Dunder Mifflin, Scranton, PA', 150, 4, 3200.00, 'alugado'), -- Alugado pelo Michael Scott
('kitnet', 'Prédio da Universidade, Tóquio', 30, 1, 1200.00, 'disponivel'),
('casa', 'Rua dos Chocobos, 7, Kalm', 200, 3, 4000.00, 'manutencao'); -- Em manutenção

-- # 4. INSERTS NA TABELA CONTRATO

-- Contrato 1: Levi (Inquilino 1) aluga Apartamento Shiganshina (Imovel 1) da Aerith (Proprietario 1)
INSERT INTO Contrato (data_inicio, data_fim, status, clausulas, fk_inquilino, fk_proprietario, fk_imovel) VALUES
('2024-01-01', '2024-12-31', 'em andamento', 'Uso do gás tridimensional proibido dentro da propriedade.', 1, 1, 1);

-- Contrato 2: Michael Scott (Inquilino 2) aluga Casa Scranton (Imovel 2) do Geralt (Proprietario 2)
INSERT INTO Contrato (data_inicio, data_fim, status, clausulas, fk_inquilino, fk_proprietario, fk_imovel) VALUES
('2023-05-01', '2025-05-01', 'em andamento', 'O inquilino deve parar de dizer "That''s what she said".', 2, 2, 2);

-- # 5. INSERTS NA TABELA PAGAMENTO

INSERT INTO Pagamento (data_pagamento, valor_pago, forma_pagamento, comprovante) VALUES
('2024-09-05', 2500.00, 'PIX', 'comp-09-levi-2024'),   -- Pagamento 1
('2024-09-10', 3200.00, 'Boleto', 'comp-09-michael-2024'); -- Pagamento 2

-- # 6. INSERTS NA TABELA PARCELA
-- Tentando passar parcelas diferentes

-- Parcela Contrato 1 (Levi)
INSERT INTO Parcela (numero_parcela, valor, data_vencimento, data_pagamento, status, fk_pagamento, fk_contrato) VALUES
(9, 2500.00, '2024-09-05', '2024-09-05', 'pago', 1, 1), -- Parcela 9 Paga (ligada ao Pagamento 1)
(10, 2500.00, '2024-10-05', NULL, 'pendente', NULL, 1); -- Parcela 10 Pendente

-- Parcela Contrato 2 (Michael Scott)
INSERT INTO Parcela (numero_parcela, valor, data_vencimento, data_pagamento, status, fk_pagamento, fk_contrato) VALUES
(17, 3200.00, '2024-09-10', '2024-09-10', 'pago', 2, 2), -- Parcela 17 Paga (ligada ao Pagamento 2)
(18, 3200.00, '2024-10-10', NULL, 'pendente', NULL, 2); -- Parcela 18 Pendente

-- # 7. INSERTS NA TABELA MANUTENCAO

INSERT INTO Manutencao (custo, responsavel, fk_imovel) VALUES
(550.00, 'Yennefer Vengerberg', 4); -- Manutenção na Casa em Kalm (Imovel 4)

-- # 8. INSERTS NA TABELA VISTORIA

INSERT INTO Vistoria (data_vistoria, tipo_vistoria, fk_imovel, fk_contrato) VALUES
('2024-01-01', 'entrada', 1, 1), -- Vistoria de entrada Contrato 1
('2024-07-15', 'periodica', 1, 1),
('2023-05-01', 'entrada', 2, 2); -- Vistoria de entrada Contrato 2

-- # 9. INSERTS NA TABELA AUDITORIACCONTRATO

INSERT INTO Auditoria_contrato (data_auditoria, tipo_auditoria, responsavel_auditoria, fk_contrato) VALUES
('2024-09-20', 'financeiro', 'The Accountant', 1),
('2024-08-01', 'operacional', 'Dwight Schrute', 2);
