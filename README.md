# homeAnimals

<details>
  <summary>MySql script</summary>


```sql
create database IF NOT EXISTS domestic_animal_db;

use domestic_animal_db;

create table type_pet(
	id INT NOT NULL AUTO_INCREMENT,
    type_pet VARCHAR(20) NOT NULL,
    CONSTRAINT  type_pet_uq UNIQUE(type_pet),
    CONSTRAINT type_pet_id_pk PRIMARY KEY (id)
);

insert domestic_animal_db.type_pet(type_pet) 
values
	('Собака'), 
	('Кошка'), 
	('Хомяк'), 
	('Черепаха');

create table pet(
	id INT NOT NULL AUTO_INCREMENT,
    type_pet_id INT NOT NULL,
    weight DECIMAL(5,2) NOT NULL,
    color varchar(25) not null,
    nickname varchar(25) not null,
    CONSTRAINT pet_id_pk PRIMARY KEY (id),
    FOREIGN KEY (type_pet_id)  REFERENCES domestic_animal_db.type_pet(id)
);

insert pet(type_pet_id, weight, color, nickname) 
values 
	(1, 20.5, 'чёрный', 'артём'),
	(2, 15.2, 'белый', 'Цицерон'),
	(2, 7.1, 'чёрный', 'Шенди'),
	(3, 2.3, 'розовый', 'Рамштайн'),
	(1, 7.6, 'чёрный', 'Шарфей'),
	(4, 4.5, 'красный', 'Каштан'),
	(1, 8.5, 'чёрный', 'Роджер'),
	(4, 10.3, 'голубой', 'Чиф');


create table person(
	id INT NOT NULL AUTO_INCREMENT,
    name varchar(25) NOT NULL,
    surname varchar(25) not null,
    lastname varchar(25),
    CONSTRAINT person_id_pk PRIMARY KEY (id)
);

insert domestic_animal_db.person(name, surname, lastname) 
values
	('Иванов', 'Иван', 'Иванович'), 
	('Петров', 'Константин', 'Юрьевич'), 
	('Заболотный', 'Сергей', 'Викторович'), 
	('Иванов', 'Лидия', 'Ивановна'),
	('Спиридонов', 'Александр', 'Ильич'),
	('Попов', 'Владимир', 'Алиевич'),
	('Пономарев', 'Мирон', 'Даниилович'),
	('Гусева', 'Кристина', 'Егоровна'),
	('Медведев', 'Александр', 'Львович');


create table person_pet(
	pet_id int not null,
    person_id int not null,
    data DATE not null,
    primary key (pet_id, person_id),
    foreign key(pet_id) references domestic_animal_db.pet(id),
	foreign key(person_id) references domestic_animal_db.person(id)
);
``` 
    
</details>
