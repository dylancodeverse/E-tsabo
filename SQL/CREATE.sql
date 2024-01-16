create database etsabo;


create table symptome(
    idsymptome varchar(250) primary key,
    symptome varchar(250) unique
);

create table diagnostic(
    iddiagnostic serial primary key, 
    idsymptome varchar(250) ,
    niveaumin integer default(0),
    niveaumax integer default(10),
    ageperson integer,
    foreign key (idsymptome) references symptome(idsymptome)
);

