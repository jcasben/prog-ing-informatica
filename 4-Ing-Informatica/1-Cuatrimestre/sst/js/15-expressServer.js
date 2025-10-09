const express = require('express');
const mysql = require('mysql2');

const app = express();
const PORT = 3000;

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


app.listen(PORT, () => console.log(`Server running at http:localhost:${PORT}`))
app.get("/user", async function (req, res) {
    const {lastname, email, id} = req.query;

    let sql = 'SELECT * FROM user WHERE 1=1';
    let fields = [];
    let values = [];

    if (lastname) {
        fields.push('lastname');
        values.push(`%${lastname}%`);
    }

    if (email) {
        fields.push('email');
        values.push(`%${email}%`);
    }

    if (id) {
        fields.push('id');
        values.push(`%${id}%`);
    }

    for(let i = 0; i < fields.length; i++) {
        sql = sql + ' AND ' + fields[i] + ' LIKE ?';
    }

    console.log(sql);
    console.log(values);
    
    dbConnection.query(sql, values, (err, rows) => {
        if (err) {
            console.log('Error in the query: ', err)
            return;
        }

        res.json(rows);
    });
    
})