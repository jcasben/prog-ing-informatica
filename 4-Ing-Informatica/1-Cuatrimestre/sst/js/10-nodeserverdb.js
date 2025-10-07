const http = require('http');
const url = require('url');
const mysql = require('mysql2');

const server = http.createServer((req, res) => {
    const call = url.parse(req.url, true).query;

    const connection = mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: '',
        database: 'sst'
    });

    let query = `SELECT * FROM user`;

    connection.query(query, (err, result, fields) => {
        if (err) {
            res.statusCode = 404;
            res.setHeader('Content-Type', 'text/html');
            res.end('<html lang="en"><body><h1>Could not connect to DB</h1></body></html>');
        } else {
            let data = '';
            for (let i = 0; i < result.length; i++) {
                data += `<h1>${result[i]['id']} ${result[i]['firstname']} ${result[i]['lastname']}</h1>`;
            }
            res.statusCode = 200;
            res.setHeader('Content-Type', 'text/html');
            res.end(`
                <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                    </head>
                    <body>
                        ${data}
                    </body>
                </html>
            `);
        }
    });
});

server.listen(3000, () => {
    console.log('Server started on http://localhost:3000');
});