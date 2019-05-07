var ws;

function connect() {
   ws = new WebSocket("ws://localhost:8080/GruppInluppWebService/tempsensor");
   
    ws.onmessage = function(event) {
        
        alert('Msg recieved');
        var message = JSON.parse(event.data);
        var tempText = document.getElementById("txttemperature"); 
        
        if (message.hasOwnProperty("temperature")){     
            tempText.textContent = message.temperature;
        }
    };
}

function send() {
    var temperatureFromDb = "28";
    
    var json = JSON.stringify({
        "temperature":temperatureFromDb
    });
    ws.send(json);
    
}

function printLatestTemp(){
    var tempText = document.getElementById("txttemperature");
    tempText.textContent = "25";
}