
insert into symptome values ('migraine' ,'migraine') ,
                            ('lelo','lelo') ,
                            ('caca','caca') ,
                            ('fatigue','fatigue');

insert into diagnostic values (default ,'A'  ,'migraine' ,4,8,10,20)
                              ,(default ,'A' ,'lelo' ,4,8,10,20) 
                              ,(default ,'A' ,'caca' ,4,8,10,20) 
                              ,(default ,'A' ,'fatigue' ,4,8,10,20) 
                              ,(default ,'B' ,'migraine' ,2,3,10,20)
                              ,(default ,'B' ,'lelo' ,4,8,10,20) 
                              ,(default ,'B' ,'caca' ,4,8,10,20) 
                              ,(default ,'B' ,'fatigue' ,4,8,10,20) ;


create table medicament (
    idmedicament varchar(250) primary key,
    medicament varchar(250)
) ;

INSERT INTO medicament (idmedicament, medicament) VALUES 
    ('A', 'A'),
    ('B', 'B'),
    ('C', 'C'),
    ('D', 'D'),
    ('E', 'E'),
    ('F', 'F'),
    ('G', 'G'),
    ('H', 'H'),
    ('I', 'I'),
    ('J', 'J'),
    ('K', 'K'),
    ('L', 'L'),
    ('M', 'M'),
    ('N', 'N'),
    ('O', 'O'),
    ('P', 'P'),
    ('Q', 'Q'),
    ('R', 'R'),
    ('S', 'S'),
    ('T', 'T'),
    ('U', 'U'),
    ('V', 'V');


INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('lelo'     ,'A',-1) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('caca'     ,'B',1) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('fatigue'  ,'D',1) ;


INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('lelo'     ,'C',-4) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('caca'     ,'C',8) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('fatigue'  ,'C',2) ;


insert into prixUnitairemedicament values (default , 'A' , 234);
insert into prixUnitairemedicament values (default , 'B' , 234);
insert into prixUnitairemedicament values (default , 'D' , 234);
insert into prixUnitairemedicament values (default , 'C' , 5);


