
import React,{Component} from 'react';
import './ListItem.css';

class ListItem extends Component{

    constructor(props){
        super(props);
    }

    render(){
        if(this.props.item.done){
            return <p className="finished_item">{this.props.item.content}</p>
        }else{
            return <p className="item" onClick={this.onChangeItem}>{this.props.item.content}</p>
        }
    }
    

    onChangeItem = (event) => {
        event.target.className = "finished_item";
    }
}
export default ListItem;