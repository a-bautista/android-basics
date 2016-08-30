/**
 * Created by abautista on 30/08/2016.
 */
var React = require('react');
var transparentBg = require('../styles').transparentBg;

var PromptContainer = React.createClass({
    render: function(){
        console.log(this);
        return (
            <div className="jumbotron col-sm-6 col-sm-offset-3 text-center" style={transparentBg}>
            <h1>{this.props.route.header}</h1>
                <div className = "col-sm-12">
                    <form>
                        <div className="form-group">
                            <input
                                className="form-group"
                                placeholder="Values range from 0 - 50 degrees celsius"
                                type="text"/>
                        </div>
                        <div className="form-group col-sm-4 col-sm-offset-4">
                            <button
                                className="btn btn-block btn-success"
                                type="submit">
                                Continue
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
});

module.exports = PromptContainer;