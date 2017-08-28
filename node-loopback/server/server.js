'use strict';

const loopback = require('loopback');
const boot = require('loopback-boot');
const app = module.exports = loopback();

const cluster = require('cluster');
const cps = require('os').cpus().length;

if (cluster.isMaster) {
    for (var i = 0; i < cps; i++) {
        cluster.fork();
    }

    cluster.on('exit', (worker, code, signal) => console.log('worker ' + worker.pid + ' died'));
} else {
	app.start = () => app.listen(() => app.emit('started'));

	boot(app, __dirname, (err) => {

	  if (err) throw err;

	  app.start();
	});
}
