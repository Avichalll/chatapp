// 'use strict'

// const usernamePage = document.querySelector('#username-page');
// const chatPage = document.querySelector('#chat-page');
// const usernameForm = document.querySelector('#usernameForm');
// const messageForm = document.querySelector('#messageForm');
// const messageInput = document.querySelector('#message');
// const connectingElement = document.querySelector('.connecting');
// const chatArea = document.querySelector('#chat-messages');
// const logout = document.querySelector('#logout');


// let stompClient= null;
// let nickname= null;
// let fullname= null;
// let selectedUser= null;

// function connect(event){
//     nickname= document.querySelector("#nickname");
//     fullname= document.querySelector("#fullname");

//     if(nickname && fullname){
//         usernamePage.classList.add("hidden");
//         chatPage.classList.remove("hiddden");

//         const socket = new Sockjs('/ws')
//         stompClient= Stomp.over(socket);
//         stompClient.connect({}, onConnected,onError)
//     }
    
//     event.preventDefault();

// }

// function onConnected(){

//     stompClient.subscribe(`/user/${nickname}/queue/message`, onMessageReceived);
//     stompClient.subscribe(`/user/public`,onMessageReceived);

//     stompClient.send(`app/user.addUser`,
//         {},
//         JSON.stringify({nickName:nickname, fullName:fullname, status:'ONLINE'})
//     )

//     async function findAndDisplayConnectedUsers(params) {
    
//         const connectedUsersResponse= await fetch('/users')
//         let connectedUsers= await connectedUsersResponse.filter(user=> user.nickName!=nickname);
//         const connectedUserList= document.getElementById("connectedUsers");


//         connectedUserList.innerHTML='';
//         connectedUsers.forEach(user =>{
//             appendUserElement(user,connectedUserList);
//             if(connectedUsers.index(user)< connectedUsers.length-1){
//                 const separator= document.createElement("li");
//                 separator.classList.add("separator");
//                 connectedUserList.appendChild(separator);
//             }
//         })

//     }


// }

// usernameForm.addEventListener("submit", onConnected)