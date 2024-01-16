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

-- requete maka an'ny diagnostic:

with listemaladie as
(select maladie from diagnostic where niveaumin <= ? and ?< niveaumax and agepersonmin<= ? and ? < agepersonmax
where idsymptome = ?)

countMaladie as
(select count(maladie) , maladie from listemaladie group by maladie)


