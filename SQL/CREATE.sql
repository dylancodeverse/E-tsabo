create database etsabo;


create table symptome(
    idsymptome varchar(250) primary key,
    symptome varchar(250) unique
);

create table diagnostic(
    iddiagnostic serial primary key, 
    maladie varchar(250),
    idsymptome varchar(250) ,
    niveaumin integer default(0),
    niveaumax integer default(10),
    agepersonmin integer,
    agepersonmax integer,
    foreign key (idsymptome) references symptome(idsymptome)
);

create table medicament (
    idmedicament varchar(250) primary key,
    medicament varchar(250)
) ;

create table soinssymptome (
    idsoin serial primary key ,
    idsymptome varchar(250) ,
    idmedicament varchar(250),
    efficacite double precision default(0) ,
    foreign key (idsymptome) references symptome(idsymptome),
    foreign key (idmedicament) references medicament(idmedicament)    
);

create table prixUnitairemedicament(
    idprixUnitairemedicament serial primary key ,
    idmedicament varchar(250), 
    prix double precision default(0),
    foreign key (idmedicament) references medicament(idmedicament)
);


create view  v_soinssymptome as select soinssymptome.* , coalesce(prixUnitairemedicament.prix ,0) as prix
from soinssymptome 
left join prixUnitairemedicament on prixUnitairemedicament.idmedicament =soinssymptome.idmedicament ;