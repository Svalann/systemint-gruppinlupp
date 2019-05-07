var ws;

function connect() {
   ws = new WebSocket("ws://localhost:8080/GruppInluppWebService/");

    ws.onmessage = function(event) {
        
        var message = JSON.parse(event.data);
        var tempText = document.getElementById("txttemperature");        
        
        if (message.hasOwnProperty("temperature")){     
            tempText.textContent = message.temperature;
        }
    };
}

function send() {
    
}


function printLatestTemp(){
    var tempText = document.getElementById("txttemperature");
    tempText.textContent = "25";
}