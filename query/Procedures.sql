-- == PROCEDURE ==

CREATE OR REPLACE PROCEDURE prc_gerar_contrato_e_parcelas(
    p_fk_inquilino INT,
    p_fk_imovel INT,
    p_data_inicio DATE,
    p_duracao_meses INT,
    p_valor_mensal NUMERIC(10, 2),
    p_data_vencimento_dia INT
) AS $$
DECLARE
    v_id_contrato INT;
    v_contador INT := 1;
    v_data_vencimento DATE;
BEGIN
    -- 1. Inserir o novo Contrato (APENAS colunas obrigatórias ou existentes)
    INSERT INTO Contrato (
        fk_inquilino, fk_imovel, data_inicio, status
    )
    VALUES (
        p_fk_inquilino, p_fk_imovel, p_data_inicio, 'ativo'
    )
    RETURNING id_contrato INTO v_id_contrato; -- Captura o ID gerado

    -- 2. Loop para gerar as Parcelas (USANDO os parâmetros não armazenados no Contrato)
    WHILE v_contador <= p_duracao_meses LOOP
        
        -- Cálculo da data de vencimento
        v_data_vencimento := (p_data_inicio + (v_contador || ' months')::INTERVAL);
        v_data_vencimento := DATE_TRUNC('month', v_data_vencimento) + (p_data_vencimento_dia - 1 || ' days')::INTERVAL;
        
        -- Inserir a Parcela
        INSERT INTO Parcela (
            fk_contrato, numero_parcela, valor, data_vencimento, status
        )
        VALUES (
            v_id_contrato, 
            v_contador, 
            p_valor_mensal, -- Usa o parâmetro aqui!
            v_data_vencimento, 
            'pendente'
        );

        v_contador := v_contador + 1;
    END LOOP;

EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE 'ERRO: Falha ao gerar contrato e parcelas. Detalhe: %', SQLERRM;
        ROLLBACK;
        RAISE EXCEPTION 'PRC_ERROR: Processo de geração de contrato abortado.';

END;
$$ LANGUAGE plpgsql;
