ALTER TABLE `orcaoline`.`produto` 
DROP FOREIGN KEY `fk_produto_idGTIN`;
ALTER TABLE `orcaoline`.`produto` 
ADD CONSTRAINT `fk_produto_idGTIN`
  FOREIGN KEY (`id_gtin_ean`)
  REFERENCES `orcaoline`.`gtin_ean` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
