const fs = require('fs');

const data = '<?xml version="1.0" encoding="UTF-8"?><File><Code>1234</Code><Article>5</Article></File>'

fs.writeFileSync('./data.xml', data);