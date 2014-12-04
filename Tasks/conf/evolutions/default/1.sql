# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tarefa (
  id                        integer not null,
  prioridade                integer,
  nome                      varchar(255),
  descricao                 varchar(255),
  concluido                 boolean,
  semana                    integer,
  constraint ck_tarefa_prioridade check (prioridade in (0,1,2)),
  constraint pk_tarefa primary key (id))
;

create sequence tarefa_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists tarefa;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists tarefa_seq;

