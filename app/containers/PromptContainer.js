/**
 * Created by abautista on 30/08/2016.
 */
var React = require('react');
var transparentBg = require('../styles').transparentBg;
var AirTempQuestion = require('../containers/AirTempQuestion');
var SeaTempQuestion = require('../containers/SeaTempQuestion');
var AverageRainfall = require('../containers/AverageRainfall');
var AverageSunshine = require('../containers/AverageSunshine');
var BigMacIndex = require('../containers/BigMacIndex');
var StandardOfLiving = require('../containers/StandardOfLiving');

var PromptContainer = React.createClass({
    getInitialState: function(){
        return {
            initialValue: ''
        }
    },

    render: function(){
        return (
            <div className="jumbotron col-sm-12 col-sm-offset-1 text-center" style={transparentBg}>

                <h1>Please, fill out some of the questions, so we can indicate your best destination.</h1>
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <AirTempQuestion></AirTempQuestion>
                        </td>
                        <td>
                            <SeaTempQuestion></SeaTempQuestion>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <AverageRainfall></AverageRainfall>
                        </td>
                        <td>
                            <AverageSunshine></AverageSunshine>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <BigMacIndex></BigMacIndex>
                        </td>
                        <td>
                            <StandardOfLiving></StandardOfLiving>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div className="form-group col-sm4 col-sm-offset-2">
                    <button className="btn btn-block btn-success"
                            type="submit">
                        Continue
                    </button>
                </div>
            </div>
        )
    }
});

module.exports = PromptContainer;