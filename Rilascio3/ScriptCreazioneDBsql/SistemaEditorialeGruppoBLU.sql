create user GruppoBLU identified by gruppoBLU;
grant dba, unlimited tablespace to GruppoBLU;

CREATE TABLE "GRUPPOBLU"."FUNZIONALITA"
  (
    "SIGLAFUNZIONE" VARCHAR2(10 BYTE),
    "NOMEFUNZIONE"  VARCHAR2(20 BYTE) NOT NULL ENABLE,
    PRIMARY KEY ("SIGLAFUNZIONE") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM" ENABLE
  )
  SEGMENT CREATION IMMEDIATE PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT
  )
  TABLESPACE "SYSTEM" ;

CREATE TABLE "GRUPPOBLU"."GRUPPO"
  (
    "ID_G"       CHAR(1 BYTE),
    "NOMEGRUPPO" VARCHAR2(15 BYTE) NOT NULL ENABLE,
    PRIMARY KEY ("ID_G") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM" ENABLE
  )
  SEGMENT CREATION IMMEDIATE PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT
  )
  TABLESPACE "SYSTEM" ;
  
CREATE TABLE "GRUPPOBLU"."ACCOUNT_UTENTE"
  (
    "NOME"             VARCHAR2(20 BYTE) NOT NULL ENABLE,
    "COGNOME"          VARCHAR2(20 BYTE) NOT NULL ENABLE,
    "USERNAME"         VARCHAR2(55 BYTE),
    "PASSWORD_ACCOUNT" VARCHAR2(32 BYTE) NOT NULL ENABLE,
    "SIGLAREDAZIONE"   VARCHAR2(10 BYTE),
    "SIGLAGIORNALISTA" VARCHAR2(20 BYTE),
    "STATO"            CHAR(1 BYTE),
    PRIMARY KEY ("USERNAME") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM" ENABLE
  )
  SEGMENT CREATION IMMEDIATE PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT
  )
  TABLESPACE "SYSTEM" ;

CREATE TABLE "GRUPPOBLU"."NOTIZIA"
  (
    "ID_N"             NUMBER(*,0),
    "LOCK_N"           CHAR(1 BYTE) NOT NULL ENABLE,
    "STATO"            CHAR(1 BYTE) NOT NULL ENABLE,
    "TITOLO"           VARCHAR2(100 BYTE) NOT NULL ENABLE,
    "SOTTOTITOLO"      VARCHAR2(100 BYTE) NOT NULL ENABLE,
    "AUTORE"           VARCHAR2(55 BYTE) NOT NULL ENABLE,
    "ULTIMODIGITATORE" VARCHAR2(55 BYTE) NOT NULL ENABLE,
    "DATACREAZIONE" TIMESTAMP (6) NOT NULL ENABLE,
    "DATATRASMISSIONE" TIMESTAMP (6),
    "TESTO" CLOB NOT NULL ENABLE,
    "LUNGHEZZATESTO" NUMBER(*,0),
    "ULTIMO_ACCESSO" VARCHAR2(55 BYTE),
    PRIMARY KEY ("ID_N") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM" ENABLE
  )
  SEGMENT CREATION IMMEDIATE PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT
  )
  TABLESPACE "SYSTEM" LOB
  (
    "TESTO"
  )
  STORE AS BASICFILE
  (
    TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION NOCACHE LOGGING STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  ) ;
  
  CREATE TABLE "GRUPPOBLU"."ASS_ACCOUNT_GRUPPO"
  (
    "ID_ACCOUNT" VARCHAR2(55 BYTE),
    "ID_GRUPPO"  CHAR(1 BYTE),
    PRIMARY KEY ("ID_ACCOUNT", "ID_GRUPPO") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM" ENABLE,
    FOREIGN KEY ("ID_GRUPPO") REFERENCES "GRUPPOBLU"."GRUPPO" ("ID_G") ENABLE,
    FOREIGN KEY ("ID_ACCOUNT") REFERENCES "GRUPPOBLU"."ACCOUNT_UTENTE" ("USERNAME") ENABLE
  )
  SEGMENT CREATION IMMEDIATE PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT
  )
  TABLESPACE "SYSTEM" ;
  
  CREATE TABLE "GRUPPOBLU"."ASS_GRUPPO_FUNZIO"
  (
    "ID_G" CHAR(1 BYTE),
    "ID_F" VARCHAR2(10 BYTE),
    PRIMARY KEY ("ID_G", "ID_F") USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT) TABLESPACE "SYSTEM" ENABLE,
    FOREIGN KEY ("ID_G") REFERENCES "GRUPPOBLU"."GRUPPO" ("ID_G") ENABLE,
    FOREIGN KEY ("ID_F") REFERENCES "GRUPPOBLU"."FUNZIONALITA" ("SIGLAFUNZIONE") ENABLE
  )
  SEGMENT CREATION IMMEDIATE PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING STORAGE
  (
    INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT
  )
  TABLESPACE "SYSTEM" ;
  
  CREATE SEQUENCE "GRUPPOBLU"."SEQUENCE1" 
  MINVALUE 1 MAXVALUE 99999999999 
  INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER NOCYCLE ;
  
  create or replace
trigger "GRUPPOBLU"."INS_ID_NOTIZIA"
before insert on "GRUPPOBLU"."NOTIZIA"
for each row
begin
  select SEQUENCE1.nextval
  into :new.id_n from dual;
end;
/
create or replace
trigger "GRUPPOBLU"."IMPO_MOD_IDFUNZIONALITA"
before update of siglafunzione on "GRUPPOBLU"."FUNZIONALITA" 
for each row
begin
 raise_application_error(-20500,'Impossibile modificare la chiave di funzionalità');
end;
/
create or replace
trigger "GRUPPOBLU"."IMPO_MOD_IDGRUPPO"
before update of id_g on "GRUPPOBLU"."GRUPPO" 
for each row
begin
 raise_application_error(-20500,'Impossibile modificare il campo id di gruppo');
end;
/
create or replace
trigger "GRUPPOBLU"."IMPO_MOD_USERNAME"
before update of username on "GRUPPOBLU"."ACCOUNT_UTENTE" 
for each row
begin
 raise_application_error(-20500,'Impossibile modificare username utente');
end;
/
Insert into "GRUPPOBLU"."ACCOUNT_UTENTE" (NOME,COGNOME,USERNAME,PASSWORD_ACCOUNT,SIGLAREDAZIONE,SIGLAGIORNALISTA,STATO) values ('Annamaria','Spera','annamaria.spera@hotmail.it','annamaria','FR_DI_ADM','FR_DI','A');
Insert into "GRUPPOBLU"."ACCOUNT_UTENTE" (NOME,COGNOME,USERNAME,PASSWORD_ACCOUNT,SIGLAREDAZIONE,SIGLAGIORNALISTA,STATO) values ('Antonio','Laurano','antonio.laurano@gmail.com','antonio','AN_LA_ADM','AN_LA','A');
Insert into "GRUPPOBLU"."ACCOUNT_UTENTE" (NOME,COGNOME,USERNAME,PASSWORD_ACCOUNT,SIGLAREDAZIONE,SIGLAGIORNALISTA,STATO) values ('Francesca','Di Miceli','francesca.dimiceli@gmail.com','francesca','FR_DI_ADM','FR_DI','A');
Insert into "GRUPPOBLU"."ACCOUNT_UTENTE" (NOME,COGNOME,USERNAME,PASSWORD_ACCOUNT,SIGLAREDAZIONE,SIGLAGIORNALISTA,STATO) values ('Francesco','Giancipoli','francesco.giancipoli@hotmail.it','francesco','FR_GI_ADM','FR_GI','A');
Insert into "GRUPPOBLU"."ACCOUNT_UTENTE" (NOME,COGNOME,USERNAME,PASSWORD_ACCOUNT,SIGLAREDAZIONE,SIGLAGIORNALISTA,STATO) values ('Eugenio','Stromei','eugenio.stromei@gmail.com','eugenio','EU_ST_ADM','EU_ST','A');

Insert into "GRUPPOBLU"."GRUPPO" (ID_G,NOMEGRUPPO) values ('a','amministratore');
Insert into "GRUPPOBLU"."GRUPPO" (ID_G,NOMEGRUPPO) values ('g','giornalista');

Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f1','creaAccount');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f2','cancellaAccount');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f3','modificaAccount');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f4','listaAccount');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f5','creazioneNotizia');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f6','modificaNotizia');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f7','registraNotizia');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f8','cancellazioneNotizia');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f9','trasmettiNotizia');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f10','visualizzaNotizia');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f11','listaNotizie');
Insert into "GRUPPOBLU"."FUNZIONALITA" (SIGLAFUNZIONE,NOMEFUNZIONE) values ('f12','annulla');

Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('a','f1');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('a','f2');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('a','f3');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('a','f4');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('g','f10');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('g','f11');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('g','f12');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('g','f5');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('g','f6');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('g','f7');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('g','f8');
Insert into "GRUPPOBLU"."ASS_GRUPPO_FUNZIO" (ID_G,ID_F) values ('g','f9');

Insert into "GRUPPOBLU"."ASS_ACCOUNT_GRUPPO" (ID_ACCOUNT,ID_GRUPPO) values ('annamaria.spera@hotmail.it','a');
Insert into "GRUPPOBLU"."ASS_ACCOUNT_GRUPPO" (ID_ACCOUNT,ID_GRUPPO) values ('antonio.laurano@gmail.com','a');
Insert into "GRUPPOBLU"."ASS_ACCOUNT_GRUPPO" (ID_ACCOUNT,ID_GRUPPO) values ('francesca.dimiceli@gmail.com','a');
Insert into "GRUPPOBLU"."ASS_ACCOUNT_GRUPPO" (ID_ACCOUNT,ID_GRUPPO) values ('francesco.giancipoli@hotmail.it','a');
Insert into "GRUPPOBLU"."ASS_ACCOUNT_GRUPPO" (ID_ACCOUNT,ID_GRUPPO) values ('eugenio.stromei@gmail.com','a');

CREATE OR REPLACE FORCE VIEW "GRUPPOBLU"."CODA_NOTIZIE_DA_TRASM" ("ID_N", "LOCK_N", "STATO", "TITOLO", "SOTTOTITOLO", "AUTORE", "ULTIMODIGITATORE", "DATACREAZIONE", "DATATRASMISSIONE", "TESTO", "LUNGHEZZATESTO", "ULTIMO_ACCESSO")
AS
  SELECT "ID_N",
    "LOCK_N",
    "STATO",
    "TITOLO",
    "SOTTOTITOLO",
    "AUTORE",
    "ULTIMODIGITATORE",
    "DATACREAZIONE",
    "DATATRASMISSIONE",
    "TESTO",
    "LUNGHEZZATESTO",
    "ULTIMO_ACCESSO"
  FROM NOTIZIA
  WHERE STATO='Q';

  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."ACC_FUNZIONI" (
o_cursor out SYS_REFCURSOR,
i_username in ass_account_gruppo.id_account% TYPE
)AS
BEGIN
  open o_cursor for
  select fun.nomefunzione from ass_account_gruppo ass, gruppo gr, ass_gruppo_funzio ass_fun, funzionalita fun where ass.id_gruppo= gr.id_g and gr.id_g= ass_fun.id_g
  and ass_fun.id_f= fun.siglafunzione and ass.id_account= i_username;
END ACC_FUNZIONI;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."ALL_ACCOUNT" (
o_cursor out SYS_REFCURSOR
) AS
BEGIN
  open o_cursor FOR
  select * FROM account_utente;
END ALL_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."ALL_NOTIZIA" (
o_cursor out SYS_REFCURSOR
) AS
BEGIN
  open o_cursor FOR
  select id_n,titolo,autore,datacreazione,datatrasmissione,stato,lock_n,ultimodigitatore,sottotitolo,testo,ultimo_accesso FROM notizia;
END ALL_NOTIZIA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."ALL_NOTIZIE_DA_TRASMETTERE" (
o_cursor out SYS_REFCURSOR
) AS
BEGIN
  open o_cursor FOR
  select id_n,titolo,autore,datacreazione,datatrasmissione,stato,lock_n,ultimodigitatore,sottotitolo,testo, ultimo_accesso FROM coda_notizie_da_trasm
  order by datacreazione DESC;
END ALL_NOTIZIE_DA_TRASMETTERE;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."VISUALIZZA_TESTO_NOTIZIA" (
o_cursor out SYS_REFCURSOR,
s_id in notizia.id_n%type
) AS
testonotizia VARCHAR2(4000);
BEGIN
  open o_cursor for
  select testo into testonotizia from notizia where id_n= s_id;
END VISUALIZZA_TESTO_NOTIZIA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."EXIST_ACC_AMM" (
o_cursor out SYS_REFCURSOR,
p_username IN account_utente.username%TYPE
) AS
BEGIN
  open o_cursor for
  select username FROM "GRUPPOBLU".account_utente,"GRUPPOBLU".ass_account_gruppo
  where account_utente.username=ass_account_gruppo.id_account and username= p_username and id_gruppo='a';
END EXIST_ACC_AMM;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_DATA_CREAZIONE" (
o_cursor out SYS_REFCURSOR,
i_id in notizia.id_n% TYPE
)AS
data_c date;
BEGIN
 open o_cursor for
 select datacreazione into data_c from notizia where id_n= i_id;
END GET_DATA_CREAZIONE;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."EXIST_ACC_CONTROL_PASS" (
o_cursor out SYS_REFCURSOR,
p_username IN account_utente.username% TYPE,
p_pass IN account_utente.password_account% TYPE
) AS
BEGIN
  open o_cursor for
  select password_account FROM "GRUPPOBLU".account_utente where username= p_username and password_account= p_pass;
END EXIST_ACC_CONTROL_PASS;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_STATO_NOTIZIA" (
o_cursor out SYS_REFCURSOR,
s_idn in notizia.id_n%type
)AS
BEGIN
  open o_cursor for
  select stato from notizia where id_n= s_idn;
END GET_STATO_NOTIZIA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."INSERT_NOTIZIA" (
titolo in VARCHAR2,
sottotit in VARCHAR2,
autore in VARCHAR2, 
ultimodigi in VARCHAR2,
testo in VARCHAR2,
lunghezza in NUMBER
) AS
BEGIN
  insert into "GRUPPOBLU".notizia (lock_n, stato, titolo, sottotitolo, autore, ultimodigitatore, datacreazione, datatrasmissione, testo, lunghezzatesto, ultimo_accesso)
  values('N','S', titolo, sottotit, autore, ultimodigi,sysdate,sysdate,testo, lunghezza, ultimodigi);
END INSERT_NOTIZIA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_DATA_TRASMISSIONE" 
(o_cursor out SYS_REFCURSOR,
id_i in notizia.id_n% TYPE)AS
datat date;
BEGIN
open o_cursor for
select datatrasmissione into datat FROM notizia where id_n= id_i;
END GET_DATA_TRASMISSIONE;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_STATO_ALL" (
o_cursor out SYS_REFCURSOR) AS
BEGIN
  open o_cursor for
  select * from notizia where stato= 'Q';
END GET_STATO_ALL;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."SEACH_NOTIZIA_STATO" (
o_cursor out SYS_REFCURSOR,
s_stato in VARCHAR2
) AS
BEGIN
  open o_cursor FOR
  select id_n,titolo,autore,datacreazione,datatrasmissione,stato,lock_n,ultimodigitatore,sottotitolo,testo FROM notizia where stato like '%'||s_stato||'%'
  order by datacreazione DESC;
END SEACH_NOTIZIA_STATO;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."INSERT_NOTIZIA_RICEVUTA" (
titolo in VARCHAR2,
sottotit in VARCHAR2,
autore in VARCHAR2, 
datacreaz in TIMESTAMP,
testo in VARCHAR2,
lunghezza in NUMBER 
) AS
BEGIN
  insert into "GRUPPOBLU".notizia (lock_n, stato, titolo, sottotitolo, autore, ultimodigitatore, datacreazione, testo, lunghezzatesto)
  values('N','S', titolo, sottotit, autore, 'RCV',datacreaz,testo, lunghezza);
END INSERT_NOTIZIA_RICEVUTA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."INSERT_FUNZIONALITA" (
id_fun in VARCHAR2,
nome_fun in VARCHAR2
) AS
BEGIN
  insert into "GRUPPOBLU".funzionalita( siglafunzione, nomefunzione) values (id_fun,nome_fun);
END INSERT_FUNZIONALITA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."RETURN_GRUPPO" (
id_ac in ass_account_gruppo.id_account% TYPE 
) AS
gruppo VARCHAR2(10);
BEGIN
  select gr.nomegruppo into gruppo from ass_account_gruppo ass, gruppo gr where ass.id_gruppo=gr.id_g and ass.id_account=id_ac;
END RETURN_GRUPPO;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."EXIST_ACC_GIORN" (
o_cursor out SYS_REFCURSOR,
p_username IN account_utente.username%TYPE
) AS
BEGIN
  open o_cursor for
  select username FROM "GRUPPOBLU".account_utente,"GRUPPOBLU".ass_account_gruppo
  where account_utente.username=ass_account_gruppo.id_account and username= p_username and id_gruppo='g';
END EXIST_ACC_GIORN;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."NOME_NOTIZIA_TX" (
o_cursor out SYS_REFCURSOR,
i_id in notizia.id_n% TYPE
)AS
data_c date;
BEGIN
 open o_cursor for
 select datacreazione into data_c from notizia where id_n= i_id;
END NOME_NOTIZIA_TX;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."EXIST_CONTRO_ACCOUNT" (
o_cursor out SYS_REFCURSOR,
p_username IN account_utente.username% TYPE,
p_pass IN account_utente.password_account% TYPE
) AS
BEGIN
  open o_cursor for
  select username FROM "GRUPPOBLU".account_utente where username= p_username and password_account= p_pass;
END EXIST_CONTRO_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."DELETE_NOTIZIA" (
d_id in notizia.id_n% TYPE
) AS
BEGIN
 update notizia set stato='C' where id_n=d_id;
END DELETE_NOTIZIA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."FILTRO_ALL_NOTIZIA" (
o_cursor out SYS_REFCURSOR,
P_MIN IN INTEGER,
P_MAX IN INTEGER
) AS
BEGIN
  open o_cursor FOR
  select * FROM 
  (SELECT e.*, ROWNUM rnum
  FROM
  (
  SELECT id_n,titolo,autore,datacreazione,datatrasmissione,stato,lock_n,ultimodigitatore,sottotitolo,testo,ultimo_accesso
  FROM notizia
  ORDER BY NOTIZIA.DATACREAZIONE DESC
  ) e
  WHERE ROWNUM <=P_MAX)
  WHERE rnum >=P_MIN;
END FILTRO_ALL_NOTIZIA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."SELECT_ACCOUNT" (
o_cursor out SYS_REFCURSOR
) AS
BEGIN
  open o_cursor for
  select username FROM "GRUPPOBLU".account_utente;
END SELECT_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."SEACH_NOTIZIA_TITOLO" (
o_cursor out SYS_REFCURSOR,
s_titolo in VARCHAR2
) AS
BEGIN
  open o_cursor FOR
  select id_n,titolo,autore,datacreazione,datatrasmissione,stato,lock_n,ultimodigitatore,sottotitolo,testo FROM notizia where titolo like lower('%'||s_titolo||'%')
  order by datacreazione DESC;
END SEACH_NOTIZIA_TITOLO;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_GRUPPO" (
o_cursor out SYS_REFCURSOR,
id_ac in ass_account_gruppo.id_account% TYPE 
) AS
BEGIN
  open o_cursor for
  select gr.nomegruppo from ass_account_gruppo ass, gruppo gr where ass.id_gruppo=gr.id_g and ass.id_account=id_ac;
END GET_GRUPPO;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_INFO_ACCOUNT" (
o_cursor out SYS_REFCURSOR,
s_username in account_utente.username% TYPE,
s_password in account_utente.password_account%type
)AS
BEGIN
  open o_cursor for
  select * from account_utente acc where  username= s_username and password_account=s_password;
END GET_INFO_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."FILTRO_ALL_ACCOUNT" (
o_cursor out SYS_REFCURSOR,
P_MIN IN INTEGER,
P_MAX IN INTEGER
) AS
BEGIN
  open o_cursor FOR
  select * FROM 
  (SELECT e.*, ROWNUM rnum
  FROM
  (
  select nome,cognome,username,password_account,siglaredazione,siglagiornalista,stato  
  FROM account_utente
  ) e
  WHERE ROWNUM <=P_MAX)
  WHERE rnum >=P_MIN; 
END FILTRO_ALL_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."INSERT_GRUPPO" (
id_gru in VARCHAR2,
nome in VARCHAR2
) AS
BEGIN
  insert into "GRUPPOBLU".gruppo(id_g, nomegruppo) values (id_gru,nome);
END INSERT_GRUPPO;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_COUNT_NOTIZIE" (
o_cursor out SYS_REFCURSOR
) AS
BEGIN
  open o_cursor FOR
  select count(*) FROM notizia;
END GET_COUNT_NOTIZIE;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."FILTRO_SEACH_NOTIZIA_TITOLO" (
o_cursor out SYS_REFCURSOR,
P_MIN IN INTEGER,
P_MAX IN INTEGER,
s_titolo in VARCHAR2
) AS
BEGIN
OPEN o_cursor FOR
SELECT * FROM
(SELECT e.*, ROWNUM rnum
FROM
(
  select id_n,titolo,autore,datacreazione,datatrasmissione,stato,lock_n,ultimodigitatore,sottotitolo,testo 
  FROM notizia where lower(titolo) like lower('%'||s_titolo||'%')
  order by datacreazione DESC
  ) e
  WHERE ROWNUM <=P_MAX)
  WHERE rnum >=P_MIN; 
END FILTRO_SEACH_NOTIZIA_TITOLO;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_COUNT_ACCOUNT" (
o_cursor out SYS_REFCURSOR
) AS
BEGIN
  open o_cursor FOR
  select count(*) FROM account_utente;
END GET_COUNT_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."FILTRO_SEACH_NOTIZIA_AUTORE" (
o_cursor out SYS_REFCURSOR,
P_MIN IN INTEGER,
P_MAX IN INTEGER,
s_autore in VARCHAR2
) AS
BEGIN
open o_cursor FOR
select * FROM 
(SELECT e.*, ROWNUM rnum
FROM
(
select id_n,titolo,autore,datacreazione,datatrasmissione,stato,lock_n,ultimodigitatore,sottotitolo,testo 
FROM notizia where lower(autore) like lower('%'||s_autore||'%')   
order by datacreazione DESC
) e
WHERE ROWNUM <=P_MAX)
WHERE rnum >=P_MIN; 
END FILTRO_SEACH_NOTIZIA_AUTORE;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."INSERT_ACCOUNT" (
nome in VARCHAR2,
cognome in VARCHAR2,
username in VARCHAR2,
pass_acc in VARCHAR2,
siglar in VARCHAR2,
siglag in VARCHAR2
)
AS
BEGIN
  insert into "GRUPPOBLU".account_utente (nome, cognome, username, password_account, siglaredazione, siglagiornalista, stato) 
  values (nome, cognome, username, pass_acc, siglar, siglag, 'A');
END INSERT_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_NOTIZIE_ULTIMO_DIGI" (
o_cursor out SYS_REFCURSOR,
s_ultimo in notizia.ultimodigitatore% TYPE
) AS
BEGIN
  open o_cursor for
  select * from notizia where ultimodigitatore= s_ultimo;
END GET_NOTIZIE_ULTIMO_DIGI;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."INSERT_ASS_ACC_GRUP" (
id_acc in VARCHAR2,
id_gru in VARCHAR2
) AS
BEGIN
  insert into "GRUPPOBLU".ass_account_gruppo(id_account, id_gruppo) values (id_acc,id_gru);
END INSERT_ASS_ACC_GRUP;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."DELETE_ACCOUNT" (
u_username VARCHAR2
) AS
cont INTEGER:=0;
BEGIN
  BEGIN
    delete from ass_account_gruppo where id_account=u_username and id_gruppo !='a';
  EXCEPTION
  WHEN OTHERS THEN
  ROLLBACK;
  RETURN;
  END;
  BEGIN
    select count(*) into cont from ass_account_gruppo where id_account= u_username;
  EXCEPTION
  WHEN OTHERS THEN
  ROLLBACK;
  RETURN;
  END;
  if(cont=0) then
     BEGIN
      update account_utente set stato='C' where username=u_username;
    EXCEPTION
    WHEN OTHERS THEN
    ROLLBACK;
    RETURN;
    END;
  end if;  
END DELETE_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."UPDATE_ACCOUNT" (
u_nome in account_utente.nome% TYPE,
u_cognome in account_utente.cognome% TYPE,
u_username VARCHAR2,
u_pass in account_utente.password_account% TYPE,
u_siglared IN account_utente.siglaredazione% TYPE, 
u_siglagio in account_utente.siglagiornalista% TYPE
) AS
BEGIN
  UPDATE "GRUPPOBLU".account_utente set nome=u_nome,cognome=u_cognome,password_account=u_pass, siglaredazione=u_siglared,
  siglagiornalista=u_siglagio where username= u_username;
END UPDATE_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_INFO_NOTIZIA" (
o_cursor out SYS_REFCURSOR,
s_id in notizia.id_n% TYPE
) AS
BEGIN
  open o_cursor for
  select * from notizia where id_n= s_id;
END GET_INFO_NOTIZIA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."TIPO_ACCOUNT" (
o_cursor out SYS_REFCURSOR,
i_username in ass_account_gruppo.id_account% TYPE
)AS
BEGIN
  open o_cursor for
  select gr.nomegruppo from ass_account_gruppo ass, gruppo gr where ass.id_gruppo=gr.id_g and id_account= i_username;
END TIPO_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."REGISTRA_NOTIZIA" (
u_id in notizia.id_n%type,
u_titolo in notizia.titolo% TYPE,
u_sotto in notizia.sottotitolo% TYPE, 
u_ultimod in notizia.ultimodigitatore% TYPE, 
u_testo in notizia.testo% TYPE,
u_lungh in notizia.lunghezzatesto% TYPE
) AS
BEGIN
  UPDATE notizia set id_n=u_id,titolo= u_titolo,sottotitolo= u_sotto,ultimodigitatore= u_ultimod,testo= u_testo,lunghezzatesto= u_lungh,lock_n='N',stato='S'
  where id_n=u_id;
END REGISTRA_NOTIZIA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."SET_ULTIMO_ACCESSO_E_LOCK" (
s_id in notizia.id_n% TYPE,
s_ult_acc in notizia.ultimo_accesso% TYPE)
AS
BEGIN
  UPDATE notizia set lock_n='Y', ultimo_accesso= s_ult_acc where id_n= s_id; 
END SET_ULTIMO_ACCESSO_E_LOCK;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."FILTRO_SEACH_NOTIZIA_STATO" (
o_cursor out SYS_REFCURSOR,
P_MIN IN INTEGER,
P_MAX IN INTEGER,
s_stato in VARCHAR2
) AS
BEGIN
  open o_cursor FOR
  select * FROM 
  (SELECT e.*, ROWNUM rnum
  FROM
  (
  select id_n,titolo,autore,datacreazione,datatrasmissione,stato,lock_n,ultimodigitatore,sottotitolo,testo,ultimo_accesso
  FROM notizia where lower(stato) like lower('%'||s_stato||'%')
  order by datacreazione DESC
  ) e
  WHERE ROWNUM <=P_MAX)
  WHERE rnum >=P_MIN; 
END FILTRO_SEACH_NOTIZIA_STATO;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_INFO_USER" (
o_cursor out SYS_REFCURSOR,
s_username in account_utente.username% TYPE
)AS
BEGIN
  open o_cursor for
  select * from account_utente acc where  username= s_username;
END GET_INFO_USER;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_ULTIMO_DIG" (
o_cursor out SYS_REFCURSOR,
s_id in notizia.id_n%type
) AS
udig VARCHAR2(55);
BEGIN
  open o_cursor for
  select ultimodigitatore into udig from notizia where id_n= s_id;
END GET_ULTIMO_DIG;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."UPDATE_NOTIZIA_TRASMESSA" (
t_id in notizia.id_n% TYPE
) AS
BEGIN
  update coda_notizie_da_trasm set stato='T' where id_n= t_id;
END UPDATE_NOTIZIA_TRASMESSA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."EXIST_AMM_E_GIORN" (
o_cursor out SYS_REFCURSOR,
p_username IN account_utente.username%TYPE
) AS
BEGIN
  declare contatore INT default 0;
  begin
  open o_cursor for
  select count(*) into contatore FROM "GRUPPOBLU".ass_account_gruppo
  where id_account= p_username;
  end;
END EXIST_AMM_E_GIORN;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."RILASCIA_ALL_NOTIZIE" (
s_ultimoaccesso in notizia.ultimo_accesso% TYPE
)
AS
BEGIN
  update notizia set stato='S', lock_n='N' where ultimo_accesso=s_ultimoaccesso;
END RILASCIA_ALL_NOTIZIE;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."INSERT_ASS_GRU_FUN" (
id_gru in VARCHAR2,
id_fun in VARCHAR2
) AS
BEGIN
  insert into "GRUPPOBLU".ass_gruppo_funzio(id_g, id_f) values (id_gru, id_fun);
END INSERT_ASS_GRU_FUN;
/

  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."ANNULLA_MODIFICA" (
i_id in notizia.id_n% TYPE)
AS
BEGIN
  update notizia set stato='S', lock_n='N' where id_n= i_id;
END ANNULLA_MODIFICA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."SEACH_NOTIZIA_AUTORE" (
o_cursor out SYS_REFCURSOR,
s_autore in VARCHAR2
) AS
BEGIN
  open o_cursor FOR
  select id_n,titolo,autore,datacreazione,datatrasmissione,stato,lock_n,ultimodigitatore,sottotitolo,testo FROM notizia where autore like '%'||s_autore||'%'   
  order by datacreazione DESC;
END SEACH_NOTIZIA_AUTORE;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."UPDATE_NOTIZIA_IN_CODA_TX" (
t_id in notizia.id_n%TYPE,
i_id in notizia.ultimodigitatore% TYPE
) AS
BEGIN
  update notizia set stato='Q', ultimodigitatore=i_id, datatrasmissione= sysdate, ultimo_accesso=i_id
  where id_n= t_id;
END UPDATE_NOTIZIA_IN_CODA_TX;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."EXIST_ACCOUNT" (
p_username IN account_utente.username% TYPE,
o_cursor out SYS_REFCURSOR
) AS
BEGIN
  open o_cursor for
  select username FROM "GRUPPOBLU".account_utente where username= p_username;
END EXIST_ACCOUNT;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_LOCK_NOTIZIA" (
o_cursor out SYS_REFCURSOR,
s_idN in notizia.id_n%type
)AS
BEGIN
  open o_cursor for
  select lock_n from notizia where id_n= s_idn;
END GET_LOCK_NOTIZIA;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."GET_ULTIMO_ACCESSO" (
o_cursor out SYS_REFCURSOR,
s_id in notizia.id_n%type
) AS
udig VARCHAR2(55);
BEGIN
  open o_cursor for
  select ultimo_accesso into udig from notizia where id_n= s_id;
END GET_ULTIMO_ACCESSO;
/
 
  CREATE OR REPLACE PROCEDURE "GRUPPOBLU"."ALL_STATO_Q" (
o_cursor out SYS_REFCURSOR
) AS
BEGIN
  open o_cursor for
  select * from notizia where stato='q';
END ALL_STATO_Q;
/
 

