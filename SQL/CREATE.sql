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


 create view v_soinssymptome as with this as
(select idmedicament ,idsymptome ,efficacite  from soinssymptome union all  
select medicament.idmedicament , symptome.idsymptome , 0 as efficacite  from medicament,symptome
) ,
  thiss as ( select this.idmedicament , this.idsymptome , sum(efficacite) as efficacite  from this group by this.idmedicament , this.idsymptome  order by this.idmedicament , this.idsymptome)
select thiss.* , prix from thiss join prixunitairemedicament on thiss.idmedicament = prixunitairemedicament.idmedicament ;

