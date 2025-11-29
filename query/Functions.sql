-- == FUNCTIONS ==

-- Função para calcular o juros de atraso do pagamento
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

-- Função a ser refeita 
CREATE OR REPLACE FUNCTION fn_media_atraso_inquilino (IN id_inquilino_inserido INT)
RETURNS NUMERIC(10,2) AS $$
DECLARE
	media_atraso numeric(10,2);
BEGIN
	SELECT 
	COALESCE( 
		AVG( 
		CASE WHEN p.data_pagamento > p.data_vencimento THEN 
			(p.data_pagamento - p.data_vencimento)
		ELSE
			0
		END
		), 0.00)
	INTO media_atraso
	FROM parcela p 
	INNER JOIN contrato c ON p.fk_contrato = c.id_contrato
	WHERE c.fk_inquilino = id_inquilino_inserido
	AND status='pago';

RETURN media_atraso;

END;
$$ LANGUAGE plpgsql;
