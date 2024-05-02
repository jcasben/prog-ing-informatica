-- CONSULTAS TABLA VOLS

-- 1. Mostrar la llista de companyies aèries d’Espanya

SELECT nomCo
FROM companyia
WHERE pais='Espanya';

-- 2. Mostrar la llista de companyies aèries que tenen algun vol amb sortida de l aeroport de Son Sant Joan 

SELECT DISTINCT nomCo
FROM vol
WHERE codiIATAorigen = (SELECT codiIATA
                        FROM aeroport
                        WHERE nomAeroport='Son Sant Joan');

-- 3. Mostrar la llista de vols de la companyia Bristish Airways

SELECT numVol
FROM vol
WHERE nomCo = 'British Airways';

-- 4. Mostrar la llista de vols que tenen arribada a un aeroport del Regne Unit

SELECT v.numVol
FROM vol v, aeroport a
WHERE v.codiIATAdesti = a.codiIATA
    AND a.pais = 'Regne Unit';

-- 5. Mostrar la llista de vols que tenen sortida o arribada a un aeroport del Regne Unit

SELECT v.numVol
FROM vol v, aeroport a
WHERE (v.codiIATAdesti = a.codiIATA OR codiIATAorigen = a.codiIATA)
    AND a.pais = 'Regne Unit';

-- JOIN

-- 1.

SELECT v.numVol
FROM vol v
	LEFT JOIN aeroport a
    ON a.codiIATA = v.codiIATAorigen
WHERE a.ciutat = 'Londres'
ORDER BY v.nomCo;

-- 2.

SELECT v.capacitat - SUM(r.places) AS places_buides111
FROM vol v
    JOIN reserva r
    ON v.numVol = r.numVol
    AND v.numVol = '111'