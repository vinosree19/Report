CREATE DATABASE report;

--RENAME TABLE product TO product_t;

DROP TABLE product_t;

CREATE TABLE product_t
(
        prod_id VARCHAR(10) NOT NULL,
        prod_desc VARCHAR(250) NOT NULL,
        rate DECIMAL(10,2),
        PRIMARY KEY (prod_id) 
);

INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('ARIEL', 'Ariel', 25.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('BOUNTY', 'Bounty Paper Towel', 32.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('BOOST', 'Boost', 80.50);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('BOURNEVLE', 'Bournville', 75.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('CREST', 'Crest Tooth Paste', 40.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('DAIRYMILK', 'Dairy Milk', 100.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('GILLETTE', 'Gillette Razor', 70.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('LAMS', 'Lambs Foods', 180.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('OLAY', 'Olay', 90.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('PANTENE', 'Pantene', 10.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('TIDE', 'Tide Powder', 20.00);
INSERT INTO product_t (prod_id, prod_desc, rate) VALUES ('VICKS', 'Vicks', 25.00);


DROP TABLE user_t;

CREATE TABLE user_t
(
        user_id SMALLINT NOT NULL AUTO_INCREMENT,
        username VARCHAR(15) NOT NULL,
        password VARCHAR(100) NOT NULL,
        active CHAR(1),
        PRIMARY KEY (user_id)
) ENGINE=INNODB;

ALTER TABLE user_t AUTO_INCREMENT = 1001;