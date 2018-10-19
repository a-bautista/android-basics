var React = require('react');
var transparentBg = require('../styles').transparentBg;

function SeaTempQuestion (){
    return (
        <div className="jumbotron col-sm-6 col-sm-offset-3 text-center" style={transparentBg}>
            <h2>What's your favourite sea temperature level</h2>
            <div className="col-sm-12">
                <form>
                    <div className="form-group">
                        <input className="form-control"
                               placeholder="0 - 40 degree C."
                               type="text" />
                    </div>
                </form>
            </div>
        </div>
    )
}

module.exports = SeaTempQuestion;