var ws;

function connect() {
    ws = new WebSocket("ws://localhost:8080/GruppInluppWebService/tempsensor/connect");
   
    ws.onmessage = function(event) {
        
        var message = JSON.parse(event.data);
        
        var tempText = document.getElementById("txtTemperature"); 
        var humidText = document.getElementById("txtHumidity"); 
        var createdText = document.getElementById("txtCreated"); 
        var updatedText = document.getElementById("txtLastUpdate"); 
        
        
        if (message.hasOwnProperty("temperature")){     
            tempText.textContent = message.temperature;
        }
        if (message.hasOwnProperty("humidity")){     
            humidText.textContent = message.humidity;
        }
        if (message.hasOwnProperty("created")){     
            createdText.textContent = message.created;
        }
        var today = new Date(); 
        var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
        
        updatedText.textContent = time;
    };
}

function getAllData(){
    var array = arrayFromDB;
    
    graf.DataPoints = array;
    
    
    const request = new XMLHttpRequest();
 
    request.open('GET', '/GruppInluppWebService/rest/SensorDataService/TempSensor/getAllData');
    request.send(); 
 
    request.onload = () => {
      if (request.status === 200) {
        console.log("Success"); // So extract data from json and create table

        //Extracting data
        var jokeid = JSON.parse(request.response).value.id;
        var joke = JSON.parse(request.response).value.joke;

        //Creating table
        var table="<table>";
            table+="<tr><td>Joke ID</td><td>Joke</td></tr>"; 
            table+="<tr><td>"+jokeid+"</td><td>"+joke+"</td></tr>";
            table+="</table>";

        //Showing the table inside table
        document.getElementById("mydiv").innerHTML = table;   
        } 
      };

      request.onerror = () => {
        console.log("error")
      };
}