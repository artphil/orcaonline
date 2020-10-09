alter table usuario modify  cnpj varchar(14) not null unique key;
alter table tipo_usuario modify  nome varchar(100) not null unique key;
alter table permissao modify  nome varchar(100) not null unique key;
alter table status modify  nome varchar(150) not null unique key;
alter table segmento modify  nome varchar(150) not null unique key;
alter table produto modify  id_gtin_ean bigint not null unique key;
alter table ncm modify  numero varchar(8) not null unique key;
alter table gtin_ean modify  numero bigint not null unique key;
alter table familia modify  nome varchar(150) not null unique key;
alter table classe modify  nome varchar(150) not null unique key;
alter table brick modify  nome varchar(150) not null unique key;