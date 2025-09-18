const prompt = require('prompt-sync')();
const mysql = require('mysql2');

const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'sst'
});

function filterDB(name) {
    let query = `SELECT * FROM user`;

    if (name) {
        query += ` WHERE firstname = '${name}';`;
    }

    connection.query(query, (err, res) => {
        if (err) console.log(err.message);
        else {
            console.log(res);
        }
    });

    connection.end();
}

let name = prompt("Introduce un nombre para filtrar la BBDD: ");
filterDB(name);

