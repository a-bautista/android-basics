/**
 * Created by alexbr on 7/2/16.
 */
var React    = require('react');
var ReactDOM = require('react-dom');
var routes   = require('../app/config/routes.js');


ReactDOM.render(
    routes,
    document.querySelector('.container')
);