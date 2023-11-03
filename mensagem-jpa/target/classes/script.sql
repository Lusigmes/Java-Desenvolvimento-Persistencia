select * from mensagem

drop table mensagem;

create table mensagem(
	id serial constraint pk_id_mensagem primary key,
	nome varchar(100) not null,
	codigo varchar(11) not null,
	descricao varchar(255) not null,
	constraint unique_codigo_mensagem unique(codigo)
	
);

ALTER TABLE mensagem
ADD data_entrada timestamp DEFAULT current_timestamp;
