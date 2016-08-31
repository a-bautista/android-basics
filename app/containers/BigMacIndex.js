/**
 * Created by abautista on 30/08/2016.
 */
var React = require('react');
var transparentBg = require('../styles').transparentBg;

function BigMacIndex (){
    return (
        <div className="jumbotron col-sm-6 col-sm-offset-3 text-center" style={transparentBg}>
            <h2>BigMac Index</h2>
            <div class="checkbox">
                <label><input type="checkbox" value="1"/>0 - 2.0$</label>
            </div>
            <div class="checkbox">
                <label><input type="checkbox" value="2"/>2.0 - 2.5$</label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="3"/>2.5 - 3.0$</label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="4"/> 3.0 - 3.5$ </label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="5"/> 3.5 - 4.0$ </label>
            </div>
        </div>
    )
}

module.exports = BigMacIndex;