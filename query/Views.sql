-- == VIEWS ==

-- View que mostra os imoveis com os seus poprietarios
CREATE VIEW vw_Proprietario_Imovel AS
SELECT 
	i.tipo,
	i.endereco,
	p.nome_completo

	FROM imovel i
	INNER JOIN contrato c ON c.fk_imovel = i.id_imovel
	INNER JOIN proprietario p ON c.fk_proprietario = p.id_proprietario
