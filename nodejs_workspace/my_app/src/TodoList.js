import React,{Component} from 'react';
import ListItem from './ListItem';
import GenerateItem from './GenerateItem';

class TodoList extends Component{

    constructor(props){
        super(props);
        this.state = {
            todoList : [
                {content:'task1',done:false},
                {content:'task2',done:true}
            ]
        }
    }

    generateItem = (GenerateItemContent) => {
        const newItemList = [...this.state.todoList,{content:GenerateItemContent,done:false}];
        this.setState({
            todoList: newItemList
        })
    }

    render(){
        return(
          <div>
            <GenerateItem addItem={this.generateItem}/>
            {
              this.state.todoList.map(item=><ListItem item = {item}/>)
            }
          </div>
        );
    }
}

export default TodoList;