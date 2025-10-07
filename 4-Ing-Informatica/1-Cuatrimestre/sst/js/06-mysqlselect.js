const mysql = require('mysql2');

const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'sst'
});

let query = `SELECT * FROM user`;

connection.query(query, (err, res, fields) => {
    if (err) console.log(err.message);
    else console.log(res);
});

connection.end();
