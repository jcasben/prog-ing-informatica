const fs = require('fs');

const data = {
    code: 12345,
    article: 5
}

fs.writeFileSync('./data.json', JSON.stringify(data));