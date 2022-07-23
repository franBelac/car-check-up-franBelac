create table cars (
    id UUID PRIMARY KEY,
    date_added VARCHAR(50) NOT NULL ,
    production_year SMALLINT NOT NULL ,
    manufacturer VARCHAR(30) NOT NULL,
    model VARCHAR(30) NOT NULL ,
    vin VARCHAR(50) NOT NULL
);


create table carcheckups (
    id UUID PRIMARY KEY,
    performed_at VARCHAR(50) NOT NULL,
    worker_name VARCHAR(30) NOT NULL,
    price BIGINT NOT NULL,
    checked_car_id UUID CONSTRAINT car_fk REFERENCES cars(id)
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