CREATE TABLE status (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(150) NOT NULL
);

CREATE TABLE mapaColeta (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  dataRegistro DATETIME NULL,
  id_comprador BIGINT NOT NULL,
  id_status INT NOT NULL,
  CONSTRAINT `fk_mapaColeta_usuario` FOREIGN KEY (`id_comprador`) REFERENCES usuario (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_mapaColeta_status` FOREIGN KEY (`id_status`) REFERENCES `status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
 
CREATE TABLE itemMapa (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  quantidade DOUBLE NULL DEFAULT NULL,
  unidade VARCHAR(100) NOT NULL,
  marca VARCHAR (100),
  id_mapa BIGINT NOT NULL,
  id_brick BIGINT,
  id_produto BIGINT,
  CONSTRAINT `fk_itemMapa_mapa` FOREIGN KEY (id_mapa) REFERENCES mapaColeta (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_itemMapa_brick` FOREIGN KEY (id_brick) REFERENCES brick (id) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_itemMapa_produto` FOREIGN KEY (id_produto) REFERENCES produto (id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE orcamento(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  dataRegistro DATETIME NOT NULL,
  id_fornecedor BIGINT NOT NULL,
  id_status INT NOT NULL,
  id_mapa BIGINT NOT NULL,
  CONSTRAINT `fk_orcamento_fornecedor` FOREIGN KEY (id_fornecedor) REFERENCES usuario(id) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_orcamento_status` FOREIGN KEY (id_status) REFERENCES status (id) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_orcamento_mapa` FOREIGN KEY (id_mapa) REFERENCES mapaColeta (id) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE itemOrcamento(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  valorUnitario DOUBLE NOT NULL,
  id_orcamento BIGINT NOT NULL,
  id_item_mapa BIGINT NOT NULL,
  id_produto BIGINT NOT NULL,
  CONSTRAINT fk_itemOrcamento_orcamento FOREIGN KEY (id_orcamento) REFERENCES orcamento(id) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT fk_itemOrcamento_itemMapa FOREIGN KEY (id_item_mapa) REFERENCES itemMapa(id) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT fk_itemOrcamento_produto FOREIGN KEY (id_produto) REFERENCES produto (id) ON DELETE NO ACTION ON UPDATE CASCADE	
);

