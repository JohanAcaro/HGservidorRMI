CREATE DATABASE juego_tronos;

USE juego_tronos;

CREATE TABLE casa (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Lema VARCHAR(100) NOT NULL,
    Escudo VARCHAR(100) NOT NULL
);

CREATE TABLE personaje (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Casa INT NOT NULL,
    Edad INT NOT NULL,
    Titulo VARCHAR(100) NOT NULL,
    FOREIGN KEY (Casa) REFERENCES casa(ID) 
);

INSERT INTO casa (Nombre, Lema, Escudo)
VALUES
    ('Stark', 'Se acerca el invierno', 'Lobo huargo en un campo de nieve'),
    ('Targaryen', 'Fuego y sangre', 'Dragones tres en un campo de rojo'),
    ('Lannister', '¡Oye mi Rugido!', 'León dorado en un campo de gules'),
    ('Snow', 'Nunca muera', 'Wolf blanco sobre fondo gris'),
    ('Baratheon', 'Nuestra es la Furia', 'León Rampante'),
    ('Greyjoy', 'Nosotros no sembramos', 'Kraken'),
    ('Mormont', 'Aquí Aguantamos', 'Oso'),
    ('Baelish', 'El Conocimiento es poder', 'Venado'),
    ('Seaworth', 'Honor, Deber, Disciplina', 'Barco');
    
    


INSERT INTO personaje (Nombre, Casa, Edad, Titulo)
VALUES
    ('Ned Stark', 1, 35, 'Señor de Invernalia'),
    ('Daenerys Targaryen', 2, 20, 'Madre de Dragones'),
    ('Tyrion Lannister', 3, 40, 'Mano del Rey'),
    ('Jon Snow', 4, 25, 'Rey en el Norte'),
    ('Arya Stark', 1, 17, 'Asesina'),
    ('Bran Stark', 1, 18, 'Brujo de la Cuarta'),
    ('Sansa Stark', 1, 18, 'Señora de Invernalia'),
    ('Jaime Lannister', 3, 41, 'Matarreyes'),
    ('Jorah Mormont', 7, 49, 'Caballero'),
    ('Theon Greyjoy', 6, 28, 'Príncipe de las Islas del Hierro'),
    ('Cersei Lannister', 3, 30, 'Reina'),
    ('Joffrey Baratheon', 5, 18, 'Rey Joffrey'),
    ('Petyr Baelish', 8, 40, 'Jardinero'),
    ('Stannis Baratheon', 5, 50, 'Reivindicador'),
    ('Davos Seaworth', 9, 45, 'Mano de Baratheon');
    
    
    
    
    
    

