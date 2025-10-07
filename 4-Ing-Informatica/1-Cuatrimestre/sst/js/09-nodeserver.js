const http = require('http');
const url = require('url');
const mysql = require('mysql2');

const server = http.createServer((req, res) => {
    const call = url.parse(req.url, true).query;

    res.statusCode = 200;
    res.setHeader('Content-Type', 'text/html');
    res.end('Hello World!');
});

server.listen(3000, () => {
    console.log('Server started on http://localhost:3000');
});