CREATE TABLE ncm (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `numero` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE produto drop column ncm;
ALTER TABLE produto add column id_ncm BIGINT;
ALTER TABLE produto add CONSTRAINT `fk_produto_id_ncm` FOREIGN KEY (`id_ncm`) REFERENCES `ncm` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
 
