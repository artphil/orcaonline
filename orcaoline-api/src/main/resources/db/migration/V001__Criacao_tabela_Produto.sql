CREATE TABLE `segmento` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(150) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `familia` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(150) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `id_segmento` bigint DEFAULT NULL,
  CONSTRAINT `fk_familia_idSegmento` FOREIGN KEY (`id_segmento`) REFERENCES `segmento` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `classe` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(150) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `id_familia` bigint DEFAULT NULL,
  CONSTRAINT `fk_classe_idFamilia` FOREIGN KEY (`id_familia`) REFERENCES `familia` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `brick` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(150) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `id_classe` bigint DEFAULT NULL,
  CONSTRAINT `fk_brick_idClasse` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `gtin_ean` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `numero` bigint DEFAULT NULL,
  `id_brick` bigint DEFAULT NULL,
  CONSTRAINT `fk_gtin_ean_idBrick` FOREIGN KEY (`id_brick`) REFERENCES `brick` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `produto` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(150) DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `ncm` varchar(8) DEFAULT NULL,
  `id_gtin_ean` bigint DEFAULT NULL,
  CONSTRAINT `fk_produto_idGTIN` FOREIGN KEY (`id_gtin_ean`) REFERENCES `gtin_ean` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8


