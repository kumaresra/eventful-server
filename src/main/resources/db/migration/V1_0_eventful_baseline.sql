CREATE TABLE IF NOT EXISTS CATEGORY
(
    category_surrogate_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    category_name varchar(25) DEFAULT NULL
) ENGINE=INNODB AUTO_INCREMENT=100;

CREATE TABLE IF NOT EXISTS VENUE
(
    venue_surrogate_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    venue_name varchar(100) DEFAULT NULL,
    location varchar(25) DEFAULT NULL,
    coordinate POINT NOT NULL,
    SPATIAL INDEX `SPATIAL` (`coordinate`)
) ENGINE=INNODB AUTO_INCREMENT=10;

CREATE TABLE IF NOT EXISTS EVENT (
     event_surrogate_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
     event_name varchar(100) DEFAULT NULL,
     short_description varchar(300) DEFAULT NULL,
     category_surrogate_id int(11) NOT NULL,
     venue_surrogate_id int(11) NOT NULL,
     from_datetime DATETIME,
     to_datetime DATETIME,
     photo_url varchar(255) DEFAULT NULL,
     FOREIGN KEY (category_surrogate_id) REFERENCES category (category_surrogate_id) ON DELETE CASCADE ON UPDATE RESTRICT,
     FOREIGN KEY (venue_surrogate_id) REFERENCES VENUE (venue_surrogate_id) ON DELETE CASCADE ON UPDATE RESTRICT

) ENGINE=INNODB AUTO_INCREMENT=1000;

ALTER TABLE EVENT
    ADD CONSTRAINT FOREIGN KEY (category_surrogate_id) REFERENCES CATEGORY (category_surrogate_id);
ALTER TABLE EVENT
    ADD CONSTRAINT FOREIGN KEY (venue_surrogate_id) REFERENCES VENUE (venue_surrogate_id);
