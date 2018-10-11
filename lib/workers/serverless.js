'user strict';

const Worker = require('potter-api').Worker;

class ServerlessWorker extends Worker {
    doWork() {
        this.invoke('new-project');

        /* Set process to project path */
        this.goTo(this.context.metadata.project.name);

        this.invoke('configuration', this.entities);

        this.invokeLoop('make-crud-serverless', this.entities);
    }
}

module.exports.Worker = ServerlessWorker;