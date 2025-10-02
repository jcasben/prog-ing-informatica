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

app.get("/user", async function (req, res) {
    const {lastname, email, userId} = req.query;

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