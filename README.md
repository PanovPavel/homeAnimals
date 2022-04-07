# homeAnimals

<details>
  <summary>Задание</summary>
	1)Spring, реляционная БД, запросы писать самому
	2)Должны быть несколько типов(видов) питомцев(собаки, кошки, и т.д)
	3)CRUD для питомцев, людец и видов питомцев
	4)Должна фиксироваться дата добавления питомца к человеку, и соответственно метод который позволит за период времени увидеть добавленных питомцев к конкретному человеку
	5)должно быть ограничение на кол-во питомцев одного вида у одного человека
</details>


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
    data DATETIME not null,
    primary key (pet_id, person_id),
    foreign key(pet_id) references domestic_animal_db.pet(id),
	foreign key(person_id) references domestic_animal_db.person(id)
);
insert domestic_animal_db.person_pet(pet_id, person_id, data)
values
    (3, 1, '2022-04-07 08:39:36'),
    (1, 1, '2022-04-08 00:00:00'),
    (8, 1, '2022-04-06 00:00:00'),
    (6, 2, '2022-04-09 00:00:00'),
    (6, 4, '2022-04-07 00:00:00'),
    (7, 5, '2022-04-05 00:00:00');
``` 
    
</details>

<details>
  <summary>Postman collection</summary>
	1)https://www.getpostman.com/collections/405a68088f400cff9d35
	2)https://www.getpostman.com/collections/56d3cc6741eae738818b
	3)https://www.getpostman.com/collections/f202855c7f8e7bb387ef
	
