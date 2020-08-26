CREATE TABLE produto(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigo varchar(13),
	nome varchar(150),
	descricao varchar(200),
	familia varchar(100),
	segmento varchar(100),
	classe varchar(100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
