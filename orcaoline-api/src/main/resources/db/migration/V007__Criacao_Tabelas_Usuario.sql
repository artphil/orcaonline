CREATE TABLE permissao (
  id bigint(20) PRIMARY KEY AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  descricao varchar(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tipo_usuario (
  id bigint(20) PRIMARY KEY AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  descricao varchar(200)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao_tipo_usuario (
  id_permissao bigint(20) NOT NULL,
  id_tipo_usuario bigint(20) NOT NULL,
  PRIMARY KEY (`id_permissao`, `id_tipo_usuario`),
  CONSTRAINT `fk_permissao` FOREIGN KEY(id_permissao) REFERENCES permissao(id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tipo_usuario` FOREIGN KEY(id_tipo_usuario) REFERENCES tipo_usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario (
  id bigint(20) PRIMARY KEY AUTO_INCREMENT,
  email varchar(150) NOT NULL, 
  senha varchar(300) NOT NULL,
  cnpj varchar(14),
  razao_social varchar(300),
  nome_fantasia varchar(300),
  id_tipo_usuario bigint(20),
  CONSTRAINT `fk_id_tipo_usuario` FOREIGN KEY(id_tipo_usuario) REFERENCES tipo_usuario(id) ON DELETE SET NULL ON UPDATE CASCADE	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





