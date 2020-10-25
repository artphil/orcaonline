alter table tipo_usuario add column modalidade varchar(30) default 'INTERNO' not null;
alter table usuario modify  email varchar(300) not null unique key;
