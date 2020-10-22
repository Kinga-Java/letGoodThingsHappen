use charity_donation;

INSERT INTO institution(name, description) VALUES('Fundacja "Dbam o Zdrowie"', 'Cel i misja: Pomoc dzieciom z ubogich rodzin.');
INSERT INTO institution(name, description) VALUES('Fundacja "Dla dzieci', 'Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.');
INSERT INTO institution(name, description) VALUES('Fundacja "A kogo"','Cel i misja: Pomoc w wybudzaniu dzieci ze śpiączki.');
INSERT INTO institution(name, description) VALUES('Fundacja "Bez domu"', 'Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania');

INSERT INTO categories(name) VALUES('ubrania');
INSERT INTO categories(name) VALUES('zabawki');

INSERT INTO donations(quantity, street, city, zip_code, pick_up_date, pick_up_time, pick_up_comment,institution_id) VALUES('3','Kartuska','Gdańsk','87-887','2020-11-10','12:45','duże torby','2');
INSERT INTO donations(quantity, street, city, zip_code, pick_up_date, pick_up_time, pick_up_comment,institution_id) VALUES('6','Kartuska','Gdańsk','87-887','2020-11-18','10:45','proszę dzwonić','1');
