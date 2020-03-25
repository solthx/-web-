import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import TodoListHeader from './TodoListHeader';
import TodoList from './TodoList';


class App extends Component{
  render(){
    const todoList = [
      'React practise',
      'Game time'
    ];
    return (
      <div className="App">
        <TodoListHeader/>
        <TodoList/>
      </div>
    );
  }
}

// 写法二
// function App() {
//   const todoList = [
//     'React practise',
//     'Game time'
//   ];
//   return (
//     <div className="App">
//       <h2>Todo List</h2>
//       {
//         todoList.map(item=><p>{item}</p>)
//       }
//     </div>
//   );
// }

export default App;

// 1. 文件名.js = 组件名
// 2. 必须export default 组件名
// 3. 必须返回jsx的代码
