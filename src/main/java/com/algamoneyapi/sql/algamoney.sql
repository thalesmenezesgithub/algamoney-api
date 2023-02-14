CREATE TABLE CATEGORIA
(
    ID BIGSERIAL PRIMARY KEY  NOT NULL,
    NOME VARCHAR(150) NOT NULL
)

CREATE TABLE PESSOA
(
    ID          BIGSERIAL PRIMARY KEY NOT NULL,
    NOME        VARCHAR(150) NOT NULL,
    LOGRADOURO  VARCHAR(200),
    NUMERO      VARCHAR(30),
    COMPLEMENTO VARCHAR(100),
    BAIRRO      VARCHAR(150),
    CEP         VARCHAR(10),
    CIDADE      VARCHAR(150)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   NOT NULL,
    ESTADO      VARCHAR(150),
    ATIVO       SMALLINT
)

CREATE TABLE LANCAMENTO
(
    ID BIGSERIAL PRIMARY KEY NOT NULL,
    DESCRICAO VARCHAR(200),
    DATA_VENCIMENTO DATE,
    DATA_PAGAMENTO DATE,
    VALOR DECIMAL(10,2),
    OBSERVACAO VARCHAR(150),
    TIPO VARCHAR(20),
    ID_CATEGORIA BIGINT,
    ID_PESSOA BIGINT
)

ALTER TABLE LANCAMENTO ADD CONSTRAINT FK_CATEGORIA_1 FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIA(ID)
ALTER TABLE LANCAMENTO ADD CONSTRAINT FK_PESSOA_1    FOREIGN KEY (ID_PESSOA)    REFERENCES PESSOA(ID)

CREATE TABLE CONTATO
(
    ID bigserial PRIMARY KEY NOT NULL,
    ID_PESSOA BIGINT NOT NULL,
    NOME VARCHAR(150) NOT NULL,
    EMAIL VARCHAR(150) NOT NULL,
    FIXO VARCHAR(20),
    CELULAR VARCHAR(20),
    FOREIGN KEY (ID_PESSOA) REFERENCES PESSOA(ID)
)

CREATE TABLE CIDADE
(
    ID BIGSERIAL PRIMARY KEY NOT NULL,
    NOME VARCHAR(150) NOT NULL,
    ID_ESTADO BIGINT NOT NULL,
    FOREIGN KEY (ID_ESTADO) REFERENCES ESTADO(id)
)

CREATE TABLE USUARIO (
    ID bigserial PRIMARY KEY NOT NULL,
    NOME VARCHAR(150) NOT NULL,
    EMAIL VARCHAR(150) NOT NULL,
    SENHA VARCHAR(200) NOT NULL
)

INSERT INTO public.estado (id, nome) VALUES(5, "Bahia");
INSERT INTO public.estado (id, nome) VALUES(6, "Ceará");
INSERT INTO public.estado (id, nome) VALUES(7, "Distrito Federal");
INSERT INTO public.estado (id, nome) VALUES(8, "Espírito Santo");
INSERT INTO public.estado (id, nome) VALUES(9, "Goiás");
INSERT INTO public.estado (id, nome) VALUES(10, "Maranhão");
INSERT INTO public.estado (id, nome) VALUES(11, "Mato Grosso");
INSERT INTO public.estado (id, nome) VALUES(12, "Mato Grosso do Sul");
INSERT INTO public.estado (id, nome) VALUES(13, "Minas Gerais");
INSERT INTO public.estado (id, nome) VALUES(14, "Pará");
INSERT INTO public.estado (id, nome) VALUES(15, "Paraíba");
INSERT INTO public.estado (id, nome) VALUES(16, "Paraná");
INSERT INTO public.estado (id, nome) VALUES(17, "Pernambuco");
INSERT INTO public.estado (id, nome) VALUES(18, "Piauí");
INSERT INTO public.estado (id, nome) VALUES(19, "Rio de Janeiro");
INSERT INTO public.estado (id, nome) VALUES(20, "Rio Grande do Norte");
INSERT INTO public.estado (id, nome) VALUES(21, "Rio Grande do Sul");
INSERT INTO public.estado (id, nome) VALUES(22, "Rondônia");
INSERT INTO public.estado (id, nome) VALUES(23, "Roraima");
INSERT INTO public.estado (id, nome) VALUES(24, "Santa Catarina");
INSERT INTO public.estado (id, nome) VALUES(25, "São Paulo");
INSERT INTO public.estado (id, nome) VALUES(26, "Sergipe");
INSERT INTO public.estado (id, nome) VALUES(27, "Tocantins");


INSERT INTO cidade (id, nome, id_estado) VALUES (1, 'Rio Branco', 1);
INSERT INTO cidade (id, nome, id_estado) VALUES (2, 'Maceió', 2);
INSERT INTO cidade (id, nome, id_estado) VALUES (3, 'Macapá', 3);
INSERT INTO cidade (id, nome, id_estado) VALUES (4, 'Manaus', 4);
INSERT INTO cidade (id, nome, id_estado) VALUES (5, 'Salvador', 5);
INSERT INTO cidade (id, nome, id_estado) VALUES (6, 'Fortaleza', 6);
INSERT INTO cidade (id, nome, id_estado) VALUES (7, 'Brasília', 7);
INSERT INTO cidade (id, nome, id_estado) VALUES (8, 'Vitória', 8);
INSERT INTO cidade (id, nome, id_estado) VALUES (9, 'Goiânia', 9);
INSERT INTO cidade (id, nome, id_estado) VALUES (10, 'São Luís', 10);
INSERT INTO cidade (id, nome, id_estado) VALUES (11, 'Cuiabá', 11);
INSERT INTO cidade (id, nome, id_estado) VALUES (12, 'Campo Grande', 12);
INSERT INTO cidade (id, nome, id_estado) VALUES (13, 'Belo Horizonte', 13);
INSERT INTO cidade (id, nome, id_estado) VALUES (14, 'Belém', 14);
INSERT INTO cidade (id, nome, id_estado) VALUES (15, 'João Pessoa', 15);
INSERT INTO cidade (id, nome, id_estado) VALUES (16, 'Curitiba', 16);
INSERT INTO cidade (id, nome, id_estado) VALUES (17, 'Recife', 17);
INSERT INTO cidade (id, nome, id_estado) VALUES (18, 'Teresina', 18);
INSERT INTO cidade (id, nome, id_estado) VALUES (19, 'Rio de Janeiro', 19);
INSERT INTO cidade (id, nome, id_estado) VALUES (20, 'Natal', 20);
INSERT INTO cidade (id, nome, id_estado) VALUES (21, 'Porto Alegre', 21);
INSERT INTO cidade (id, nome, id_estado) VALUES (22, 'Porto Velho', 22);
INSERT INTO cidade (id, nome, id_estado) VALUES (23, 'Boa Vista', 23);
INSERT INTO cidade (id, nome, id_estado) VALUES (24, 'Florianópolis', 24);
INSERT INTO cidade (id, nome, id_estado) VALUES (25, 'São Paulo', 25);
INSERT INTO cidade (id, nome, id_estado) VALUES (26, 'Aracaju', 26);
INSERT INTO cidade (id, nome, id_estado) VALUES (27, 'Palmas', 27);