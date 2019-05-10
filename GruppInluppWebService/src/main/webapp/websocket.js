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
    var listOfData = [];
    var url = "http://localhost:8080/GruppInluppWebService/rest/SensorDataService/TempSensor/getAllData";
    
    $.getJSON(url, function(data) {
        $.each(data, function(i, message){
            var temp = message.temperature;
            var humid = message.humidity;
            var created = message.created;
            listOfData.push(message); 
            
            $(".divTest").append(temp + " ");
        });
        
        //var text = `Temperature: ${data.temperature}
        //            Humidity: ${data.humidity}`
                    
        //var chart = document.getElementById("chartcontainer");
        //var test = document.getElementById("test"); 
        //test.textContent = text;
        
        //$(".test").html(text);
        //return listOfData;
    });
    
}

function printChart(){
    var chart = new CanvasJS.Chart("chartContainer",
            {
              title:{
                text: ""
            },
            axisX:{
                title: "",
                gridThickness: 2
            },
            axisY: {
                title: "Temperatures"
            },
            data: [
            {        
                type: "area",
                dataPoints: [
                { x: new Date(2019, 04, 1), y: 50},
                { x: new Date(2019, 04, 2), y: 38},
                { x: new Date(2019, 04, 3), y: 43},
                { x: new Date(2019, 04, 4), y: 29},
                { x: new Date(2019, 04, 5), y: 41},
                { x: new Date(2019, 04, 6), y: 54},
                { x: new Date(2019, 04, 7), y: 66},
                { x: new Date(2019, 04, 8), y: 60},
                { x: new Date(2019, 04, 9), y: 53},
                { x: new Date(2019, 04, 10), y: 60}
                ]
            }
            ]
        });

    chart.render();
}

