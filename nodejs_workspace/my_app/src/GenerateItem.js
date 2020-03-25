import React, { Component } from "react";
import './GenerateItem.css'

class GenerateItem extends Component{

    constructor(props){
        super(props);
        this.state = {
            inputValue : ''
        }
    }

    changeStatusBtnClick = (event) => {
        this.setState({
            inputValue:event.target.value
        })
    }

    AddItemBtnClick = () => {
        this.props.addItem(this.state.inputValue)
        this.setState({
            inputValue:''
        })
    }

    render(){
        return (
            <div>
                <input id="input_text" value={this.state.inputValue} onChange={this.changeStatusBtnClick} />
                <button id="input_button" onClick={this.AddItemBtnClick}>添加</button>
            </div>
        )
    }
}

export default GenerateItem;