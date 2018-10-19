var React = require('react');
var transparentBg = require('../styles').transparentBg;

function StandardOfLiving (){
    return (
        <div className="jumbotron col-sm-6 col-sm-offset-3 text-center" style={transparentBg}>
            <h2>Average standard of living</h2>
            <div class="checkbox">
                <label><input type="checkbox" value="1"/>Very low</label>
            </div>
            <div class="checkbox">
                <label><input type="checkbox" value="2"/>Low</label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="3"/> Medium </label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="4"/> High </label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="5"/> Very high </label>
            </div>
        </div>
    )
}

module.exports = StandardOfLiving;
