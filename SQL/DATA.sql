
insert into symptome values ('migraine' ,'migraine') ,('lelo','lelo') ,('caca','caca') ,('fatigue','fatigue');

insert into diagnostic values (default ,'A' ,'migraine' ,4,8,10,20)
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
    ('D', 'D');


INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('migraine' ,'A',8) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('lelo' ,'A',2) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('caca' ,'A',2) ;

INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('migraine' ,'B',3) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('lelo'     ,'B',6) ;
INSERT INTO soinssymptome (  idsymptome  ,idmedicament ,efficacite ) values('caca'     ,'B',2) ;


