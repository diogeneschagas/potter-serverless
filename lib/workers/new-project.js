'use strict';

const Worker = require('potter-api').Worker

class NewServerlessProjectWorker extends Worker {
    doWork() {
        this.mkdir(this.context.metadata.project.name)
    }
}

// export default NewServerlessProjectWorker;
module.exports.Worker = NewServerlessProjectWorker;