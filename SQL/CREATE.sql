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

insert into symptome values ('migraine' ,'migraine') ,('lelo','lelo') ,('caca','caca') ,('fatigue','fatigue');

insert into diagnostic values (default ,'A' ,'migraine' ,4,8,10,20)
                              ,(default ,'A' ,'lelo' ,4,8,10,20) 
                              ,(default ,'A' ,'caca' ,4,8,10,20) 
                              ,(default ,'A' ,'fatigue' ,4,8,10,20) 
                              ,(default ,'B' ,'migraine' ,2,3,10,20)
                              ,(default ,'B' ,'lelo' ,4,8,10,20) 
                              ,(default ,'B' ,'caca' ,4,8,10,20) 
                              ,(default ,'B' ,'fatigue' ,4,8,10,20) ;


with listemaladie as ( select maladie from diagnostic where niveaumin <= 5 and 5< niveaumax and agepersonmin<= 15 and 15 < agepersonmax and idsymptome = 'lelo' UNION ALL select maladie from diagnostic where niveaumin <= 5 and 5< niveaumax and agepersonmin<= 15 and 15 < agepersonmax and idsymptome = 'migraine' UNION ALL select maladie from diagnostic where niveaumin <= 5 and 5< niveaumax and agepersonmin<= 15 and 15 < agepersonmax and idsymptome = 'caca' UNION ALL select maladie from diagnostic where niveaumin <= 5 and 5< niveaumax and agepersonmin<= 15 and 15 < agepersonmax and idsymptome = 'fatigue'), countMaladie as ( select count(maladie) as proba , maladie from listemaladie group by maladie)  select proba ,maladie from countMaladie order by proba desc