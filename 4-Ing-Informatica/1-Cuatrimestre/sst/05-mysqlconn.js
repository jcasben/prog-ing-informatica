const mysql = require('mysql2');

const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'sst'
});

connection.connect(err => {
    if (err) console.log('Error de conexión');
    else console.log('Connected successfully');
});

connection.end();