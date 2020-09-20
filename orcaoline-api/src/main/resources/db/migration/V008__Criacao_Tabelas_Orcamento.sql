-- -----------------------------------------------------
-- Table `orcaoline`.`unidadeMedida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`unidadeMedida` (
  `idunidadeMedida` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`idunidadeMedida`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orcaoline`.`tipo_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`tipo_usuario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 116
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`usuario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(150) NOT NULL,
  `senha` VARCHAR(300) NOT NULL,
  `cnpj` VARCHAR(14) NULL DEFAULT NULL,
  `razao_social` VARCHAR(300) NULL DEFAULT NULL,
  `nome_fantasia` VARCHAR(300) NULL DEFAULT NULL,
  `id_tipo_usuario` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_id_tipo_usuario` (`id_tipo_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_id_tipo_usuario`
    FOREIGN KEY (`id_tipo_usuario`)
    REFERENCES `orcaoline`.`tipo_usuario` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`status` (
  `idstatus` INT NOT NULL,
  `nome` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`idstatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orcaoline`.`mapaColeta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`mapaColeta` (
  `idmapaColeta` INT NOT NULL AUTO_INCREMENT,
  `dataRegistro` DATETIME NULL,
  `comprador` BIGINT NOT NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`idmapaColeta`),
  INDEX `fk_mapaColeta_usuario1_idx` (`comprador` ASC) VISIBLE,
  INDEX `fk_mapaColeta_status1_idx` (`status` ASC) VISIBLE,
  CONSTRAINT `fk_mapaColeta_usuario1`
    FOREIGN KEY (`comprador`)
    REFERENCES `orcaoline`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_mapaColeta_status1`
    FOREIGN KEY (`status`)
    REFERENCES `orcaoline`.`status` (`idstatus`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orcaoline`.`itemMapa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`itemMapa` (
  `iditemMapa` INT NOT NULL AUTO_INCREMENT,
  `quantidade` DOUBLE NULL DEFAULT NULL,
  `unidade` INT NOT NULL,
  `mapa` INT NOT NULL,
  PRIMARY KEY (`iditemMapa`),
  INDEX `fk_itemMapa_unidadeMedida_idx` (`unidade` ASC) VISIBLE,
  INDEX `fk_itemMapa_mapaColeta1_idx` (`mapa` ASC) VISIBLE,
  CONSTRAINT `fk_itemMapa_unidadeMedida`
    FOREIGN KEY (`unidade`)
    REFERENCES `orcaoline`.`unidadeMedida` (`idunidadeMedida`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_itemMapa_mapaColeta`
    FOREIGN KEY (`mapa`)
    REFERENCES `orcaoline`.`mapaColeta` (`idmapaColeta`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orcaoline`.`orcamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`orcamento` (
  `idorcamento` INT NOT NULL AUTO_INCREMENT,
  `dataRegistro` DATETIME NOT NULL,
  `fornecedor` BIGINT NOT NULL,
  `status` INT NOT NULL,
  `mapaColeta` INT NOT NULL,
  PRIMARY KEY (`idorcamento`),
  INDEX `fk_orcamento_usuario1_idx` (`fornecedor` ASC) VISIBLE,
  INDEX `fk_orcamento_status1_idx` (`status` ASC) VISIBLE,
  INDEX `fk_orcamento_mapaColeta1_idx` (`mapaColeta` ASC) VISIBLE,
  CONSTRAINT `fk_orcamento_usuario`
    FOREIGN KEY (`fornecedor`)
    REFERENCES `orcaoline`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orcamento_status1`
    FOREIGN KEY (`status`)
    REFERENCES `orcaoline`.`status` (`idstatus`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orcamento_mapaColeta1`
    FOREIGN KEY (`mapaColeta`)
    REFERENCES `orcaoline`.`mapaColeta` (`idmapaColeta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orcaoline`.`itemOrcamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`itemOrcamento` (
  `iditemOrcamento` INT NOT NULL AUTO_INCREMENT,
  `valorUnitario` DOUBLE NOT NULL,
  `itemMapa` INT NOT NULL,
  `orcamento` INT NOT NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`iditemOrcamento`),
  INDEX `fk_itemOrcamento_itemMapa1_idx` (`itemMapa` ASC) VISIBLE,
  INDEX `fk_itemOrcamento_orcamento1_idx` (`orcamento` ASC) VISIBLE,
  INDEX `fk_itemOrcamento_status1_idx` (`status` ASC) VISIBLE,
  CONSTRAINT `fk_itemOrcamento_itemMapa1`
    FOREIGN KEY (`itemMapa`)
    REFERENCES `orcaoline`.`itemMapa` (`iditemMapa`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_itemOrcamento_orcamento1`
    FOREIGN KEY (`orcamento`)
    REFERENCES `orcaoline`.`orcamento` (`idorcamento`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_itemOrcamento_status1`
    FOREIGN KEY (`status`)
    REFERENCES `orcaoline`.`status` (`idstatus`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `orcaoline` ;

-- -----------------------------------------------------
-- Table `orcaoline`.`segmento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`segmento` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NULL DEFAULT NULL,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 207
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`familia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`familia` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NULL DEFAULT NULL,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  `id_segmento` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_familia_idSegmento` (`id_segmento` ASC) VISIBLE,
  CONSTRAINT `fk_familia_idSegmento`
    FOREIGN KEY (`id_segmento`)
    REFERENCES `orcaoline`.`segmento` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 147
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`classe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`classe` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NULL DEFAULT NULL,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  `id_familia` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_classe_idFamilia` (`id_familia` ASC) VISIBLE,
  CONSTRAINT `fk_classe_idFamilia`
    FOREIGN KEY (`id_familia`)
    REFERENCES `orcaoline`.`familia` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 97
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`brick`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`brick` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NULL DEFAULT NULL,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  `id_classe` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_brick_idClasse` (`id_classe` ASC) VISIBLE,
  CONSTRAINT `fk_brick_idClasse`
    FOREIGN KEY (`id_classe`)
    REFERENCES `orcaoline`.`classe` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 61
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`flyway_schema_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`flyway_schema_history` (
  `installed_rank` INT NOT NULL,
  `version` VARCHAR(50) NULL DEFAULT NULL,
  `description` VARCHAR(200) NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `script` VARCHAR(1000) NOT NULL,
  `checksum` INT NULL DEFAULT NULL,
  `installed_by` VARCHAR(100) NOT NULL,
  `installed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` INT NOT NULL,
  `success` TINYINT(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  INDEX `flyway_schema_history_s_idx` (`success` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `orcaoline`.`gtin_ean`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`gtin_ean` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `numero` BIGINT NULL DEFAULT NULL,
  `id_brick` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_gtin_ean_idBrick` (`id_brick` ASC) VISIBLE,
  CONSTRAINT `fk_gtin_ean_idBrick`
    FOREIGN KEY (`id_brick`)
    REFERENCES `orcaoline`.`brick` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`ncm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`ncm` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(8) NOT NULL,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 50
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`permissao` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 144
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`permissao_tipo_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`permissao_tipo_usuario` (
  `id_permissao` BIGINT NOT NULL,
  `id_tipo_usuario` BIGINT NOT NULL,
  PRIMARY KEY (`id_permissao`, `id_tipo_usuario`),
  INDEX `fk_tipo_usuario` (`id_tipo_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_permissao`
    FOREIGN KEY (`id_permissao`)
    REFERENCES `orcaoline`.`permissao` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tipo_usuario`
    FOREIGN KEY (`id_tipo_usuario`)
    REFERENCES `orcaoline`.`tipo_usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orcaoline`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orcaoline`.`produto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NULL DEFAULT NULL,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  `id_gtin_ean` BIGINT NOT NULL,
  `id_ncm` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_id_ncm` (`id_ncm` ASC) VISIBLE,
  INDEX `fk_produto_idGTIN` (`id_gtin_ean` ASC) VISIBLE,
  CONSTRAINT `fk_produto_id_ncm`
    FOREIGN KEY (`id_ncm`)
    REFERENCES `orcaoline`.`ncm` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_produto_idGTIN`
    FOREIGN KEY (`id_gtin_ean`)
    REFERENCES `orcaoline`.`gtin_ean` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;
