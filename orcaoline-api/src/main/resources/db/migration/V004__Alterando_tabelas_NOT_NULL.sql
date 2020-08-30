ALTER TABLE `orcaoline`.`brick` 
DROP FOREIGN KEY `fk_brick_idClasse`;
ALTER TABLE `orcaoline`.`brick` 
CHANGE COLUMN `id_classe` `id_classe` BIGINT NOT NULL ;
ALTER TABLE `orcaoline`.`brick` 
ADD CONSTRAINT `fk_brick_idClasse`
  FOREIGN KEY (`id_classe`)
  REFERENCES `orcaoline`.`classe` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `orcaoline`.`classe` 
DROP FOREIGN KEY `fk_classe_idFamilia`;
ALTER TABLE `orcaoline`.`classe` 
CHANGE COLUMN `id_familia` `id_familia` BIGINT NOT NULL ;
ALTER TABLE `orcaoline`.`classe` 
ADD CONSTRAINT `fk_classe_idFamilia`
  FOREIGN KEY (`id_familia`)
  REFERENCES `orcaoline`.`familia` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `orcaoline`.`familia` 
CHANGE COLUMN `id_segmento` `id_segmento` BIGINT NOT NULL ;
ALTER TABLE `orcaoline`.`familia` 
ADD CONSTRAINT `fk_familia_idSegmento`
  FOREIGN KEY (`id_segmento`)
  REFERENCES `orcaoline`.`segmento` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `orcaoline`.`gtin_ean` 
DROP FOREIGN KEY `fk_gtin_ean_idBrick`;
ALTER TABLE `orcaoline`.`gtin_ean` 
CHANGE COLUMN `id_brick` `id_brick` BIGINT NOT NULL ;
ALTER TABLE `orcaoline`.`gtin_ean` 
ADD CONSTRAINT `fk_gtin_ean_idBrick`
  FOREIGN KEY (`id_brick`)
  REFERENCES `orcaoline`.`brick` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE `orcaoline`.`produto` 
DROP FOREIGN KEY `fk_produto_idGTIN`;
ALTER TABLE `orcaoline`.`produto` 
CHANGE COLUMN `id_gtin_ean` `id_gtin_ean` BIGINT NOT NULL ;
ALTER TABLE `orcaoline`.`produto` 
ADD CONSTRAINT `fk_produto_idGTIN`
  FOREIGN KEY (`id_gtin_ean`)
  REFERENCES `orcaoline`.`gtin_ean` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  

