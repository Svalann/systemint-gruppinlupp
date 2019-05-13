var ws;

function test(){
    alert("TEST");
}

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
            var created = new Date(message.created);
            createdText.textContent = created.toLocaleString();
        }
        
        var today = new Date();         
        updatedText.textContent = today.toLocaleTimeString();
    };
}
