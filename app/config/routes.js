/**
 * Created by abautista on 30/08/2016.
 */
var React = require('react');
var ReactRouter = require('react-router');
var Router = ReactRouter.Router;
var Route = ReactRouter.Route;
var IndexRoute = ReactRouter.IndexRoute;
var hashHistory = ReactRouter.hashHistory;

var Main = require('../components/Main');
var Home = require('../components/Home');
var PromptContainer = require('../containers/PromptContainer');

var routes = (
    <Router history={hashHistory}>
        <Route path='/' component={Main}>
            <IndexRoute component={Home}/>
            <Route path='desiredAirTemp' header='What is your desired air temperature' component={PromptContainer} />
            <Route path='desiredSeaTemp/:desiredAirTemp' header='What is your desired sea temperature' component={PromptContainer} />
            <Route path='desiredRainfall/:desiredSeaTemp' header='What is your desired average rainfall' component={PromptContainer} />

        </Route>
    </Router>
);

module.exports = routes;


