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
            createdText.textContent = message.created;
        }
        
        var today = new Date(); 
        var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
        
        updatedText.textContent = time;
    };
}



function oldGraph(){
    alert("In Old");
    var listOfData = [];
    var minValue = document.getElementById("minValue"); 
    var maxValue = document.getElementById("maxValue"); 
    var url = "http://localhost:8080/GruppInluppWebService/rest/SensorDataService/TempSensor/" +minValue + "/" + maxValue;
    
    $.getJSON(url, function(data) {
        $.each(data, function(i, message){
            var temp = message.temperature;
            var humid = message.humidity;
            var created = message.created;
            listOfData.push(message); 
            
            $(".divTest").append(temp + " ");
        });
    });
}

