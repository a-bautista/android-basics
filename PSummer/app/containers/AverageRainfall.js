var React = require('react');
var transparentBg = require('../styles').transparentBg;

function AverageRainfall (){
    return (
        <div className="jumbotron col-sm-6 col-sm-offset-3 text-center" style={transparentBg}>
            <h2>Any favourite average rainfall?</h2>
            <div class="checkbox">
                <label><input type="checkbox" value="1"/>Nothing (0-5mm/month) </label>
            </div>
            <div class="checkbox">
                <label><input type="checkbox" value="2"/>Almost nothing (6-25mm/month) </label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="3"/>A little bit (26-50mm/month) </label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="4"/>Some rain (51 - 100mm)</label>
            </div>
            <div class="checkbox disabled">
                <label><input type="checkbox" value="5"/>Regular rain (101+mm) </label>
            </div>
     </div>
    )
}

module.exports = AverageRainfall;

    /*
    <div className="col-sm-12">
        <form>
            <div className="form-group">
                <input className="form-control"
                       placeholder="Nothing (1) Almost nothing (2) A little bit (3)
                               Some rain (4) Regular rain (5)"
                       type="text" />

            </div>
        </form>
    </div>
        */