const express = require('express');
const http = require('http');
const webSock = require('ws');
const port = 3000;
const server = http.createServer(express);
const webServ = new webSock.Server({server});

webServ.on('connection',function connection(ws){
ws.on('message',function incoming(data){
  console.log(data.toString())
  webServ.clients.forEach(function each(client){
            if(client != ws){
                 client.send(data.toString());
            }
        })
   })
});

server.listen(port);