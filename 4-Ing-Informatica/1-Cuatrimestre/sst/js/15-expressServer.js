// paquete que nos permite manejar peticiones HTTP de manera más fácil
const express = require('express');
const mysql = require('mysql2');

// crear una nueva app de express
const app = express();
const PORT = 3000;

// abstraemos la configuracion de la BBDD por si tenemos que cambiar algo
const dbConfig = {
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'sst'
};

const dbConnection = mysql.createConnection(dbConfig);
dbConnection.connect(err => {
    if (err) console.error('Connection error: ', err);
});

// metodo que escucha peticiones HTTP GET en la ruta http://localhost:3000/user
// espera ciertos parámetros (o no) para hacer la query.

// En clase definimos los siguientes:
// - lastname (opcional)
// - email (opcional)
// -id (obligatorio)
app.get("/user", async function (req, res) {
    // De los parámetros que hemos pasado en la petición, selecciona los que tengan el mismo
    // nombre que las variables. Asigna automáticamente el valor.

    // EJERCICIO 1: Investigar si de esta manera, estamos haciendo que los parámetros que
    // son obligatorios/opcionales realmente lo son. Si no es así, cambiarlo.
    const {lastname, email, userId} = req.query;

    // EJERCICIO 2: Cambiar la siguiente query para que use AND en lugar de OR y que añada
    // las AND solo si el parámetro está presente.

    // await dbConnection.query("SELECT * FROM user WHERE lastname LIKE \'%?%\' OR email LIKE \'%?%\' OR id LIKE \'%?%\'", [lastname, email, id])


    // SOLUCIÓN EJERCICIO 2
    let sql = 'SELECT * FROM user WHERE 1=1';
    const params = []

    if (lastname) {
        sql += ' AND lastname LIKE ?';
        params.push(`%${lastname}%`);
    }

    if (email) {
        sql += ' AND email LIKE ?';
        params.push(`%${email}%`);
    }

    if (userId) {
        sql += ' AND userId = ?';
        params.push(`%${userId}%`);
    }

    try {
        const [rows] = await dbConnection.query(sql, params);
    } catch (error) {
        console.error('Error in the query: ', error);
    }
})