const http = require('http');

const xml = '<?xml version="1.0" encoding="UTF-8" ?>\n' +
    '<Email>\n' +
    '    <Date>\n' +
    '        <Sent>2025/09/25 19:39:09</Sent>\n' +
    '        <Received>2025/09/25 19:39:20</Received>\n' +
    '    </Date>\n' +
    '    <From>jcasben@email.com</From>\n' +
    '    <Destinataries>\n' +
    '        <To>\n' +
    '            <Destinatary>\n' +
    '                <User>jesus</User>\n' +
    '                <Domain>uib.eu</Domain>\n' +
    '            </Destinatary>\n' +
    '        </To>\n' +
    '        <CC>\n' +
    '            <Destinatary>\n' +
    '                <User>pere</User>\n' +
    '                <Domain>uib.eu</Domain>\n' +
    '            </Destinatary>\n' +
    '        </CC>\n' +
    '        <CCO>\n' +
    '            <Destinatary>\n' +
    '                <User>toni</User>\n' +
    '                <Domain>uib.eu</Domain>\n' +
    '            </Destinatary>\n' +
    '        </CCO>\n' +
    '    </Destinataries>\n' +
    '    <Subject>TFG</Subject>\n' +
    '    <Body>Lorem Ipsum</Body>\n' +
    '    <Attachments>\n' +
    '        <Attachment>\n' +
    '            <Name>file</Name>\n' +
    '            <Extension>txt</Extension>\n' +
    '            <Size>7482473298472398472374</Size>\n' +
    '            <Content>xd</Content>\n' +
    '        </Attachment>\n' +
    '    </Attachments>\n' +
    '</Email>';

const server = http.createServer((req, res) => {
    res.statusCode = 200;
    res.setHeader('Content-Type', 'application/xml');
    res.end(xml);
});

server.listen(3000, () => {
    console.log('Server started on http://localhost:3000');
});