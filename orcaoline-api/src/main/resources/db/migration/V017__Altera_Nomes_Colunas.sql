ALTER TABLE mapa_coleta  CHANGE COLUMN `dataEncerramento` `data_encerramento` DATE NULL DEFAULT NULL ;
ALTER TABLE mapa_coleta CHANGE COLUMN `dataRegistro` `data_registro` DATETIME NULL DEFAULT NULL ;
ALTER TABLE orcamento 
CHANGE COLUMN `dataRegistro` `data_registro` DATETIME NOT NULL ,
CHANGE COLUMN `dataEnvio` `data_envio` DATE NULL DEFAULT NULL ;


