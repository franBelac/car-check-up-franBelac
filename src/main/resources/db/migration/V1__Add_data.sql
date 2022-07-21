create table cars (
    id bigserial primary key ,
    dateAdded varchar(50),
    productionYear smallint,
    manufacturer varchar(30),
    model varchar(30),
    vin varchar(50)
);


create table carcheckups (
    id bigserial primary key ,
    performedAt varchar(50),
    workerName varchar(30),
    price bigint,
    carId bigint
);

-- insert into cars(productionYear,manufacturer,model,vin) values  (2000,'Porsche','Taycan','GJHL30');
-- insert into cars(productionYear,manufacturer,model,vin) values (2000,'Skoda','Fabia','QIUXA0');
-- insert into cars(productionYear,manufacturer,model,vin) values (2000,'Ford','Focus','HUSHME214');
--
--
-- insert into carcheckups(performedAt,workerName,price,carId) values ('2019-01-21T05:47:08.644','Pablo','300','1');
-- insert into carcheckups(performedAt,workerName,price,carId) values ('2020-01-21T05:47:08.644','Logan','100','2');
-- insert into carcheckups(performedAt,workerName,price,carId) values ('2014-01-21T05:47:08.644','Tony','250','3');
-- insert into carcheckups(performedAt,workerName,price,carId) values ('2022-01-21T05:47:08.644','Tommy','470','3');