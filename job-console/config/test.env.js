'use strict'
const merge = require('webpack-merge')
const devEnv = require('./dev.env')

module.exports = merge(devEnv, {
  NODE_ENV: '"testing"',
  EVN_CONFIG: '"test"',
  jobService: '"http://127.0.0.1:8199"'
})
