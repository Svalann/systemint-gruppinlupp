
function PrintGraph(){
    var svg = d3.select("#my_dataviz");
    svg.selectAll("*").remove(); // clear previous charts. Used when taking new values
    var minValue = document.getElementById("minValue").value; 
    var maxValue = document.getElementById("maxValue").value;
    
    if(minValue === "Min" || minValue === "min" || minValue === "") {
        minValue = 0;
    }
    if(maxValue === "Max" || maxValue === "max"|| maxValue === "") {
        maxValue = 10000000;
    }
    
    if(isNaN(minValue) || isNaN(maxValue)){
        alert("numbers only");
        return;
    }
        
    
    var url = "http://localhost:8080/GruppInluppWebService/rest/SensorDataService/TempSensor/" + minValue + "/" + maxValue;
    
    // set the dimensions and margins of the graph
    var margin = {top: 10, right: 100, bottom: 30, left: 30},
        width =  1200 - margin.left - margin.right,
        height = 350 - margin.top - margin.bottom;

    // append the svg object to the body of the page
    var svg = d3.select("#my_dataviz")
      .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
      .append("g")
        .attr("transform",
              "translate(" + margin.left + "," + margin.top + ")");

    //Read the data
    d3.json(url, function(data) {

        // List of groups (here I have one group per column)
        var allGroup = ["temperature", "humidity"];

        // Reformat the data: we need an array of arrays of {x, y} tuples
        var dataReady = allGroup.map( function(grpName) { // .map allows to do something for each element of the list
          return {
            name: grpName,
            values: data.map(function(d) {
              return {created: d.created, value: +d[grpName], id: d.id};
            })
          };
        });
       
        //console.log(dataReady);

        // A color scale: one color for each group
        var myColor = d3.scaleOrdinal()
          .domain(allGroup)
          .range(d3.schemeSet2);

        function getDate(d) {
            return new Date(d.created);
        }

        var minDate = getDate(data[0]);
        var maxDate = getDate(data[data.length-1]);

        //var x = d3.time.scale().domain([minDate, maxDate]).range([0, width]);

        // Add X axis --> it is a date format
        var x = d3.scaleTime()
          .domain([minDate,maxDate])
          .range([ 0, width ]);
        svg.append("g")
          .attr("transform", "translate(0," + height + ")")
          .call(d3.axisBottom(x));

        // Add Y axis
        
        var y = d3.scaleLinear()
          .domain([0, 100])
          .range([ height, 0 ]);
        svg.append("g")
          .call(d3.axisLeft(y));



        // Add the lines
        var line = d3.line()
          .x(function(d) { return x(+getDate(d)) })
          .y(function(d) { return y(+d.value) })
        svg.selectAll("myLines")
          .data(dataReady)
          .enter()
          .append("path")
            .attr("class", function(d){ return d.name; })
            .attr("d", function(d){ return line(d.values); } )
            .attr("stroke", function(d){ return myColor(d.name); })
            .style("stroke-width", 4)
            .style("fill", "none");

     // create a tooltip
        var Tooltip = d3.select("#my_dataviz")
          .append("div")
          .style("opacity", 0)
          .attr("class", "tooltip")
          .style("background-color", "white")
          .style("border", "solid")
          .style("border-width", "2px")
          .style("border-radius", "5px")
          .style("padding", "5px");

     // Three function that change the tooltip when user hover / move / leave a cell
          var mouseover = function(d) {
            Tooltip
              .style("opacity", 1);
          };
          var mousemove = function(d) {
            Tooltip
              .html(d.value + " Id: " + d.id)
              .style("left", (d3.mouse(this)[0]+70) + "px")
              .style("top", (d3.mouse(this)[1]) + "px");
          };
          var mouseleave = function(d) {
            Tooltip
              .style("opacity", 0);
          };

        // Add the points
        svg
          // First we need to enter in a group
          .selectAll("myDots")
          .data(dataReady)
          .enter()
            .append('g')
            .style("fill", function(d){ return myColor(d.name); })
          // Second we need to enter in the 'values' part of this group
          .selectAll("myPoints")
          .data(function(d){ return d.values; })
          .enter()
          .append("circle")
            .attr("cx", function(d) { return x(getDate(d)); } )
            .attr("cy", function(d) { return y(d.value); } )
            .attr("r", 2)
            .attr("stroke", "white")
            .style("opacity", 0)
            .on("mouseover", mouseover)
            .on("mousemove", mousemove)
            .on("mouseleave", mouseleave);


        // Add a legend (interactive)
        svg
          .selectAll("myLegend")
          .data(dataReady)
          .enter()
            .append('g')
            .append("text")
              .attr('x', function(d,i){ return 30 + i*120})
              .attr('y', 30)
              .text(function(d) { return d.name; })
              .style("fill", function(d){ return myColor(d.name) })
              .style("font-size", 15)
            .on("click", function(d){
              // is the element currently visible ?
              currentOpacity = d3.selectAll("." + d.name).style("opacity")
              // Change the opacity: from 0 to 1 or from 1 to 0
              d3.selectAll("." + d.name).transition().style("opacity", currentOpacity == 1 ? 0:1)

            })  
    })

}

function oldGraph(){
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


