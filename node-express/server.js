var cluster = require('cluster')
    , numCPUs = require('os').cpus().length
    , express = require('express');

if (cluster.isMaster) {
    // Fork workers.
    for (var i = 0; i < numCPUs; i++) {
        cluster.fork();
    }

    cluster.on('exit', function(worker, code, signal) {
        console.log('worker ' + worker.pid + ' died');
    });
} else {
    var app = module.exports = express();

    // Set headers for all routes
    app.use(function(req, res, next) {
        res.setHeader("Server", "Express");
        return next();
    });

    // Routes

    app.get('/', function(req, res) {
        res.header('Content-Type', 'text/plain').send('Hello, World!');
    });

    app.listen(8080);
}