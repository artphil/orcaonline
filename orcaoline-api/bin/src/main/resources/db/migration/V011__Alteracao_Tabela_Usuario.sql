ALTER TABLE `orcaoline`.`usuario` 
DROP FOREIGN KEY `fk_id_tipo_usuario`;
ALTER TABLE `orcaoline`.`usuario` 
CHANGE COLUMN `id_tipo_usuario` `id_tipo_usuario` BIGINT NOT NULL ;
ALTER TABLE `orcaoline`.`usuario` 
ADD CONSTRAINT `fk_id_tipo_usuario`
  FOREIGN KEY (`id_tipo_usuario`)
  REFERENCES `orcaoline`.`tipo_usuario` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE;
  
  
 ALTER TABLE `orcaoline`.`permissao` 
CHANGE COLUMN `modulo` `modulo` VARCHAR(20) NOT NULL ;

