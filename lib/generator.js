'use strict';

const Generator = require('potter-api').Generator;

/**
 * The 'ServerlessGenerator' is a PotterJS code generator for Serverless applications.
 *
 * It generates components, services, repositories
 */
class ServerlessGenerator extends Generator {

    get generatorPath() {
        return __dirname;
    }

    getParams(context) {
        return { 
            "groupPath": context.metadata.project.group.split(".").join("/"),
            "projectName": context.metadata.project.name.toLowerCase()
        };
    }
}

module.exports = new ServerlessGenerator();