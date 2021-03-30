CREATE SEQUENCE currency_rates_seq;


create table currency_rates(
                               id INT NOT NULL DEFAULT NEXTVAL ('currency_rates_seq') , PRIMARY KEY (id),
                               currency_id INT NOT NULL,
                               date_time date NOT NULL,
                               rate  VARCHAR(100) NOT NULL);

CREATE SEQUENCE currency_rates_daywise_seq;


create table currency_rates_daywise(
                                       id INT NOT NULL DEFAULT NEXTVAL ('currency_rates_daywise_seq') , PRIMARY KEY (id),
                                       currency_id INT NOT NULL,
                                       date_time date NOT NULL,
                                       rate  VARCHAR(100) NOT null);