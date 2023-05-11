drop table if exists articles;

create table articles (
    id            int           AUTO_INCREMENT,
    external_id   varchar(15)   NOT NULL,
    name          varchar(100)  NOT NULL,
    unit          varchar(5)    NOT NULL,
    description   varchar(255)  NOT NULL,
    amount        int           NOT NULL,
    buying_price  int           NOT NULL,
    selling_price int           NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (external_id)
);
