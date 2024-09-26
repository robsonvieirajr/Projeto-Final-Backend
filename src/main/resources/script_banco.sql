--Script_Banco_Sistema_Projeto_Final Unipe

-- Criação da sequence para a tabela acude
CREATE SEQUENCE aesa.seq_id_acude
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Criação da sequence para a tabela acude
CREATE SEQUENCE aesa.seq_id_chuva
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE aesa.seq_id_cav
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE aesa.seq_id_vazao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE aesa.seq_id_evaporacao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE aesa.seq_id_usuariovo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Criação da tabela acude
CREATE TABLE aesa.acude (
    id_acude BIGINT NOT NULL DEFAULT nextval('aesa.seq_id_acude'),
    nome VARCHAR(255),
    vol_morto NUMERIC,
    vol_acumulado NUMERIC,
    area_drenagem NUMERIC,
    vol_maximo NUMERIC,
    coeficiente_tanque NUMERIC,
    PRIMARY KEY (id_acude)
);

-- Criação da tabela precipitacao = chuva
CREATE TABLE aesa.chuva (
    id_chuva BIGINT NOT NULL DEFAULT nextval('aesa.seq_id_chuva'),
    id_acude BIGINT,
    municipio VARCHAR(255),
    estacao VARCHAR(255),
    ano integer,
    mes VARCHAR(255),
    valor_chuva numeric,
    PRIMARY KEY (id_chuva),
    CONSTRAINT fk_id_acude FOREIGN KEY (id_acude) REFERENCES aesa.acude(id_acude)
);

-- Criação da tabela Cota x Area x Volume
CREATE TABLE aesa.cav (
    id_cav BIGINT NOT NULL DEFAULT nextval('aesa.seq_id_cav'),
    id_acude BIGINT,
    cota numeric,
    area numeric,
    volume numeric,
    PRIMARY KEY (id_cav),
    CONSTRAINT fk_id_acude FOREIGN KEY (id_acude) REFERENCES aesa.acude(id_acude)
);


-- Criação da tabela Vazões
CREATE TABLE aesa.vazao (
    id_vazao BIGINT NOT NULL DEFAULT nextval('aesa.seq_id_vazao'),
    id_acude BIGINT,
    ano_vazao integer,
    mes_vazao VARCHAR(255),
    valor_vazao numeric,
    PRIMARY KEY (id_vazao),
    CONSTRAINT fk_id_acude FOREIGN KEY (id_acude) REFERENCES aesa.acude(id_acude)
);

-- Criação da tabela Evaporação
CREATE TABLE aesa.evaporacao (
    id_evaporacao BIGINT NOT NULL DEFAULT nextval('aesa.seq_id_evaporacao'),
    id_acude BIGINT,
    ano_evaporacao integer,
    mes_evaporacao VARCHAR(255),
    valor_media_mes_evaporacao numeric,
    PRIMARY KEY (id_evaporacao),
    CONSTRAINT fk_id_acude FOREIGN KEY (id_acude) REFERENCES aesa.acude(id_acude)
);


CREATE TABLE aesa.usuariovo (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    token VARCHAR(255)
);

