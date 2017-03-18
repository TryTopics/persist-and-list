const express = require('express');
const app = express();

app.get('/topics', require('./topics/list-json'));

app.get('/topics/:topic', function (req, res) {
  console.log('Got request topic', req.params);
});

app.listen(80, () => console.log('Listening on port 80'));
