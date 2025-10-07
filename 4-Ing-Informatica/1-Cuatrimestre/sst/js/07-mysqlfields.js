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
    else {
        console.log(fields);
        console.log(res);
        for (let i = 0; i < res.length; i++) {
            for (let j = 0; j < fields.length; j++) {
                console.log(res[i][fields[j].name]);
            }
        }
    }
});

connection.end();