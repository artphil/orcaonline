ALTER TABLE item_orcamento 
CHANGE COLUMN `valorUnitario` `valor_unitario` DOUBLE NOT NULL ,
CHANGE COLUMN `valorUnitarioPrazo` `valor_unitario_prazo` DECIMAL(10,2) NULL DEFAULT NULL ;

