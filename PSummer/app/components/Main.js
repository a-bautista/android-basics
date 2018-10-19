/**
 * Created by abautista on 30/08/2016.
 */

var React = require('react');

var Main = React.createClass({
    render: function(){
        return (
            <div className='main-container'>
                {this.props.children}
            </div>

        )

    }
});

module.exports = Main;