CREATE DATABASE VOLS;

CREATE TABLE COMPANYIA(
	nomCO VARCHAR(30) PRIMARY KEY,
    websiteURL VARCHAR(30),
    cif VARCHAR(9) NOT NULL,
    pais VARCHAR(30) NOT NULL
);

ALTER TABLE companyia MODIFY websiteURL VARCHAR(128);

CREATE TABLE AEROPORT( 
    codiIATA VARCHAR(3) PRIMARY KEY, 
    nomAeroport VARCHAR(30) NOT NULL, 
    ciutat VARCHAR(30) NOT NULL, 
    pais VARCHAR(30) NOT NULL 
);

CREATE TABLE PASSATGER(
	id VARCHAR(9) PRIMARY KEY,
    tipudId VARCHAR(10) NOT NULL,
    nomPass VARCHAR(30) NOT NULL,
    cognoms VARCHAR(60) NOT NULL,
    email VARCHAR(256),
    dataNaixement VARCHAR(10) NOT NULL,
	sexe ENUM('home', 'dona', 'nodefinit') NOT NULL
);

ALTER TABLE passatger MODIFY dataNaixement DATE NOT NULL;

CREATE TABLE VOL(
	numVol INTEGER PRIMARY KEY,
    data VARCHAR(10) NOT NULL,
    capacitat INTEGER NOT NULL,
    nomCo VARCHAR(30) NOT NULL,
    codiIATAorigen VARCHAR(3) NOT NULL,
    codiIATAdesti VARCHAR(3) NOT NULL,
    CONSTRAINT fk_vol_companyia FOREIGN KEY (nomCo) REFERENCES COMPANYIA(nomCo),
    CONSTRAINT fk_vol_IATAorigen FOREIGN KEY (codiIATAorigen) REFERENCES AEROPORT(codiIATA),
    CONSTRAINT fk_vol_IATAdesti FOREIGN KEY (codiIATAdesti) REFERENCES AEROPORT(codiIATA)
);

CREATE TABLE RESERVA(
    numVol INTEGER NOT NULL,
    id VARCHAR(9) NOT NULL,
    data DATETIME NOT NULL,
    places INTEGER NOT NULL,
    CONSTRAINT fk_reserva_vol FOREIGN KEY (numVol) REFERENCES VOL(numVol),
    CONSTRAINT fk_reserva_passatger FOREIGN KEY (id) REFERENCES PASSATGER(id)
);

ALTER TABLE reserva ADD PRIMARY KEY(numVol, id);

INSERT INTO aeroport VALUES
('BCN', 'El Prat', 'Barcelona', 'Espanya'),
('LEJ', 'Halle-Leipzig', 'Leipzig', 'Alemanya'),
('LGW', 'London Gatwick', 'Londres', 'Regne Unit'),
('LHR', 'London Heathrow', 'London', 'Regne Unit'),
('PMI', 'Son Sant Joan', 'Palma', 'Espanya');

INSERT INTO companyia VALUES
('Air Europa', 'https://www.aireuropa.com/', '555555555', 'Espanya'),
('Air France', 'https://wwws.airfrance.es/', '333333333', 'França'),
('British Airways', 'https://www.britishairways.com/', '222222222', 'Regne Unit'),
('Eurowings', 'https://www.eurowings.com/', '444444444', 'Alemanya'),
('Iberia', 'https://www.iberia.com/', '111111111', 'Espanya');

INSERT INTO passatger VALUES 
('12121212A', ' NIF', 'Miquela', 'Santacana', 'miquela.santacana@gmail.com', '1990-01-01', 'dona'),
('34567439C', 'NIF', 'Pep', 'Pérez', 'pep.perez@gmail.com', '1985-05-15', 'home'),
('Z23456K', 'PAS', 'Eugeni', 'Ballester', 'eugeni@gmail.com', NULL, nodefinit);

INSERT INTO vol 
(numVol, data, hora, capacitat, nomCo, codiIATAorigen, codiIATAdesti) 
VALUES
(111, '2024-03-31', '12:40:00', 100, 'British Airways', 'PMI', 'LGW'),
(222, '2024-04-04', '06:30:00', 130, 'Iberia', 'BCN', 'LHR'), 
(333, '2024-05-15', '10:00:00', 220, 'Iberia', 'LHR','BCN'),
(444, '2024-03-20', '21:20:00', 100, 'British Airways', 'LGW', 'PMI'), 
(555, '2024-03-22', '07:30:00', 145, 'Iberia', 'BCN', 'LGW'),
(666, '2024-03-31', '09:00:00', 206, 'Eurowings', 'LEJ', 'LHR'),
(777, '2024-03-26', '06:20:00', 145, 'Eurowings', 'PMI', 'LEJ'),
(888, '2024-03-15', '12:00:00', 123, 'Eurowings', 'LEJ', 'PMI'),
(999, '2024-04-30', '09:00:00', 99, 'Air Europa', 'BCN', 'PMI');

INSERT INTO reserva
(numVol, id, data, places)
VALUES
(111, '12121212A', '2024-03-12', 3), 
(222, '12121212A', '2024-03-16', 1), 
(333, '34567439C', '2024-03-01', 2), 
(777, 'Z23456K', '2024-03-26', 1), 
(999, '34567439C', '2024-03-15', 1);