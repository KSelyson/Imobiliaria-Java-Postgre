-- == TRIGGERS ==

-- 1. Criação de triggers para automatização de logs na tabela parcela
CREATE OR REPLACE FUNCTION fn_log_op_parcela()
RETURNS TRIGGER AS $$
BEGIN
	IF(TG_OP = 'INSERT') THEN
		INSERT INTO log_imobiliaria(data_hora, usuario, tabela_afetada, acao, descricao) VALUES (now(), current_user, 'PARCELA', 'INSERT', 'Parcela adicionada');

	ELSIF(TG_OP = 'UPDATE') THEN
		INSERT INTO log_imobiliaria(data_hora, usuario, tabela_afetada, acao, descricao) VALUES (now(), current_user, 'PARCELA', 'UPDATE', 'Parcela ajustada');

	ELSIF  (TG_OP = 'DELETE')	THEN
		INSERT INTO log_imobiliaria(data_hora, usuario, tabela_afetada, acao, descricao) VALUES (now(), current_user, 'PARCELA', 'DELETE', 'Parcela deletada');
		
	END IF;
RETURN NULL;		
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_log_op_parcela
AFTER INSERT OR UPDATE OR DELETE on parcela
FOR EACH ROW
EXECUTE FUNCTION fn_log_op_parcela();

-- TERMINAR TRIGGERS DE LOGS

-- 2. Criação de trigger para automatização de logs na tabela manutencao
CREATE OR REPLACE FUNCTION fn_log_op_manutencao()
RETURNS TRIGGER AS $$
BEGIN
	IF(TG_OP = 'INSERT') THEN
		INSERT INTO log_imobiliaria(data_hora, usuario, tabela_afetada, acao, descricao) VALUES (now(), current_user, 'MANUTENCAO', 'INSERT', 'Manutenção marcada.');

	ELSIF(TG_OP = 'UPDATE') THEN
		INSERT INTO log_imobiliaria(data_hora, usuario, tabela_afetada, acao, descricao) VALUES (now(), current_user, 'MANUTENCAO', 'UPDATE', 'Manutenção alterada.');

	ELSIF  (TG_OP = 'DELETE')	THEN
		INSERT INTO log_imobiliaria(data_hora, usuario, tabela_afetada, acao, descricao) VALUES (now(), current_user, 'MANUTENCAO', 'DELETE', 'Manutenção cancelada.');
		
	END IF;
RETURN NULL;		
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_log_op_manutencao
AFTER INSERT OR UPDATE OR DELETE on parcela
FOR EACH ROW
EXECUTE FUNCTION fn_log_op_parcela();

-- 2. REVER ESSA FUNÇÃO/TRIGGER (Obejtivo é fazer uma validação no inquilino antes de realizar um contrato)
CREATE OR REPLACE FUNCTION fn_validar_novo_contrato()
RETURNS TRIGGER AS $$
DECLARE
	media_atraso NUMERIC(10,2);
	limite_atraso CONSTANT NUMERIC(10,2) := 8;
BEGIN
	SELECT fn_media_atraso_inquilino(NEW.fk_inquilino)
	INTO media_atraso;

	IF media_atraso >= limite_atraso THEN
		RAISE EXCEPTION 'LIMITE DE ATRASO ATINGIDO! o inquilino não pode prosseguir. 
		a media de atraso de % dias excedeu o limite de % dias de atraso.',
				media_atraso,
				limite_atraso;
	ELSE
		RETURN NEW;
	END IF;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_validar_risco_inquilino
BEFORE INSERT OR UPDATE ON contrato
FOR EACH ROW
EXECUTE FUNCTION fn_validar_novo_contrato();
