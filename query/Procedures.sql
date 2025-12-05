-- == PROCEDURE ==

CREATE OR REPLACE PROCEDURE prc_gerar_contrato_e_parcelas(
    p_fk_inquilino INT,
    p_fk_imovel INT,
    p_data_inicio DATE,
    p_duracao_meses INT,
    p_valor_mensal NUMERIC(10, 2),
    p_dia_vencimento INT
) AS $$
DECLARE
    v_id_contrato INT;
    v_contador INT := 1;
    v_data_venc DATE;
    v_data_fim DATE;
BEGIN
    v_data_fim := (p_data_inicio + (p_duracao_meses || ' months')::INTERVAL)::DATE;

    INSERT INTO contrato(
        fk_inquilino,
        fk_imovel,
        clausulas,
        status,
        data_inicio,
        data_fim
    )
    VALUES (
        p_fk_inquilino,
        p_fk_imovel,
        NULL,               
        'ativo',            
        p_data_inicio,
        v_data_fim
    )
    RETURNING id_contrato INTO v_id_contrato;

    WHILE v_contador <= p_duracao_meses LOOP
        
        v_data_venc :=
            DATE_TRUNC('month', p_data_inicio + ((v_contador - 1) || ' months')::INTERVAL)
            + ((p_dia_vencimento - 1) || ' days')::INTERVAL;

        INSERT INTO parcela(
            fk_contrato,
            numero_parcela,
            valor,
            data_vencimento,
            data_pagamento,
            status
        )
        VALUES (
            v_id_contrato,
            v_contador,
            p_valor_mensal,
            v_data_venc,
            NULL,          
            'pendente'
        );

        v_contador := v_contador + 1;
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE 'ERRO: %', SQLERRM;
        RAISE EXCEPTION 'Falha ao gerar contrato e parcelas.';
END;
$$ LANGUAGE plpgsql;
