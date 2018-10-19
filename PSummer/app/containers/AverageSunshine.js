var React = require('react');
var transparentBg = require('../styles').transparentBg;

function AverageSunshine (){
    return (
        <div className="jumbotron col-sm-6 col-sm-offset-3 text-center" style={transparentBg}>
            <h2>Sunlight hours</h2>
            <div class="checkbox">
                <label><input type="checkbox" value="1"/>0-3 h</label>
            </div>
            <div class="checkbox">
                <label><input type="checkbox" value="2"/>4-5 h</label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="3"/> 6-7h </label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="4"/> 8 -9h </label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="5"/> 10-12h </label>
            </div>
        </div>
    )
}

module.exports = AverageSunshine;
