'use strict';

const path = require('path');
const express = require('express');
const port = process.env.PORT || 5000;
const app = express();

app.use(express.static(__dirname + '/dist'));

app.get('/*', function(req, res) {
  res.sendFile(path.join(__dirname + '/dist/index.html'));
});

app.listen(port, () => {
  console.log(`Express server listening on port %d in %s mode ${port}`)
});

module.exports = app;
