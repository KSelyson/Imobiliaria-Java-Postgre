-- == FUNCTIONS ==

-- 1. Função para calcular o juros de atraso do pagamento
CREATE OR REPLACE FUNCTION fn_calcula_juros (IN id_p INT, IN data_pagamento_inserido DATE)
RETURNS NUMERIC AS $$
DECLARE
	v_taxa_juros NUMERIC := 0.01;
	v_taxa_multa NUMERIC := 0.02;
	v_multa NUMERIC(10,2);
	v_juros NUMERIC(10,2);
	valor_original NUMERIC;
	data_vencimento DATE;
	dias_atraso INT;
BEGIN
	SELECT p.valor,p.data_vencimento 
	INTO valor_original,data_vencimento
	FROM parcela p
	WHERE p.id_parcela = id_p;

	IF valor_original IS NULL THEN
		RETURN 0;
	END IF;
	
	dias_atraso := data_pagamento_inserido - data_vencimento;
	
	IF dias_atraso <= 0 THEN
		RETURN 0;
	END IF;

	v_multa := valor_original * v_taxa_multa;

	v_juros := v_multa * dias_atraso;
	
	RETURN v_juros;
END;
$$ LANGUAGE PLPGSQL;


-- 2. Função para somar todo o valor ainda pendente no contrato
CREATE OR REPLACE FUNCTION fn_total_receber_contrato(
    p_id_contrato INT
)
RETURNS NUMERIC(12,2) AS $$
DECLARE
    v_total NUMERIC(12,2);
BEGIN
    SELECT
        COALESCE(SUM(valor), 0)
    INTO v_total
    FROM parcela
    WHERE fk_contrato = p_id_contrato
      AND status = 'pendente';

    RETURN v_total;
END;
$$ LANGUAGE plpgsql;
