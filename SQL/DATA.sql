
-- insert into symptome values ('migraine' ,'migraine') ,
--                             ('lelo','lelo') ,
--                             ('caca','caca') ,
--                             ('fatigue','fatigue');

insert into symptome values ('kibo','kibo'),
                            ('caca','caca'),
                            ('andoha','andoha'),
                            ('temperature','temperature'),
                            ('fatigue','fatigue'),
                            ('lelo','lelo') ;


-- insert into diagnostic values (default ,'A'  ,'migraine' ,4,8,10,20)
--                               ,(default ,'A' ,'lelo' ,4,8,10,20) 
--                               ,(default ,'A' ,'caca' ,4,8,10,20) 
--                               ,(default ,'A' ,'fatigue' ,4,8,10,20) 
--                               ,(default ,'B' ,'migraine' ,2,3,10,20)
--                               ,(default ,'B' ,'lelo' ,4,8,10,20) 
--                               ,(default ,'B' ,'caca' ,4,8,10,20) 
--                               ,(default ,'B' ,'fatigue' ,4,8,10,20) ;


insert into diagnostic values (default ,'grippe' ,'temperature',3,5,0,100),
                              (default , 'grippe','lelo',5,8,0,100),
                              (default,'grippe','andoha',5,7,0,100),
                              (default,'vavony','kibo',5,7,0,100),
                              (default,'vavony','temperature',3,6,0,100),
                              (default,'indigestion','kibo',5,8,0,100),
                              (default,'indigestion','caca',6,8,0,100),
                              (default,'indigestion','fatigue',3,7,0,100);
-- INSERT INTO medicament (idmedicament, medicament) VALUES 
--     ('A', 'A'),
--     ('B', 'B'),
--     ('C', 'C'),
--     ('D', 'D'),
--     ('E', 'E'),
--     ('F', 'F'),
--     ('G', 'G'),
--     ('H', 'H'),
--     ('I', 'I'),
--     ('J', 'J'),
--     ('K', 'K'),
--     ('L', 'L'),
--     ('M', 'M'),
--     ('N', 'N'),
--     ('O', 'O'),
--     ('P', 'P'),
--     ('Q', 'Q'),
--     ('R', 'R'),
--     ('S', 'S'),
--     ('T', 'T'),
--     ('U', 'U'),
--     ('V', 'V');
insert into medicament (idmedicament,medicament) values
    ('f1','f1'),
    ('f2','f2'),
    ('f3','f3');


-- INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('lelo'     ,'A',-1) ;
-- INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('caca'     ,'B',1) ;
-- INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('fatigue'  ,'D',1) ;


-- INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('lelo'     ,'C',-4) ;
-- INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('caca'     ,'C',8) ;
-- INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('fatigue'  ,'C',2) ;

INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('temperature'  ,'f1',-2) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('lelo'  ,'f1',-3) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('andoha'  ,'f1',-3) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('kibo'  ,'f1',-1) ;

INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('kibo'  ,'f2',-3) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('andoha'  ,'f2',-1) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('caca'  ,'f2',-2) ;

INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('fatigue'  ,'f3',-2) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('lelo'  ,'f3',-2) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('andoha'  ,'f3',-3) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('temperature'  ,'f3',-2) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('kibo'  ,'f3',-2) ;
-- insert into prixUnitairemedicament values (default , 'A' , 234);
-- insert into prixUnitairemedicament values (default , 'B' , 234);
-- insert into prixUnitairemedicament values (default , 'D' , 234);
-- insert into prixUnitairemedicament values (default , 'C' , 5);

-- insert into prixUnitairemedicament values (default , 'E' , 25);

insert into prixUnitairemedicament values (default , 'f1' , 20000);
insert into prixUnitairemedicament values (default , 'f2' , 15000);
insert into prixUnitairemedicament values (default , 'f3' , 40000);


insert into client values ('dylan',15);
-- insert into client values ('doda',18);
-- insert into client values ('dida',13);

