let graph;

lines = [5, 34, 63, 92, 121, 179, 208, 237, 266, 295];
values = [-5, -4, -3, -2, -1, 1, 2, 3, 4, 5];
var R = 1;

function drawPoints() {
    [...document.querySelectorAll('circle')].map(n => n && n.remove());
    const xValues = document.getElementsByClassName("iks");
    const yValues = document.getElementsByClassName("igr");
    const resValues = document.getElementsByClassName("resi");
    for (let i = 0; i < xValues.length; i++) {
        let x = parseFloat(xValues[i].textContent);
        let y = parseFloat(yValues[i].textContent);
        let res = resValues[i].textContent === 'yes';
        drawShoot(x, y, res);
    }
}

function drawAxes(){
    document.getElementById("mySVG").innerHTML='';
    graph = d3.select('svg');
    graph.append('line')
        .attr('x1', 0)
        .attr('y1', 150)
        .attr('x2', 300)
        .attr('y2', 150)
        .attr('stroke', '#000000')

    graph.append('line')
        .attr('x1', 150)
        .attr('y1', 0)
        .attr('x2', 150)
        .attr('y2', 300)
        .attr('stroke', '#000000')


    for (let i = 0; i < lines.length; i++) {
        graph.append('line')
            .attr('x1', 145)
            .attr('y1', lines[i])
            .attr('x2', 155)
            .attr('y2', lines[i])
            .attr('stroke', '#000000')

        graph.append('text')
            .attr('x', 156)
            .attr('y', lines[i])
            .attr('stroke', '#000000')
            .text(values[9-i])

        graph.append('line')
            .attr('x1', lines[i])
            .attr('y1', 145)
            .attr('x2', lines[i])
            .attr('y2', 155)
            .attr('stroke', '#000000')

        graph.append('text')
            .attr('x', lines[i])
            .attr('y', 145)
            .attr('stroke', '#000000')
            .text(values[i])
    }

    graph.append('polygon')
        .attr('points', "300,150 295,155 295,145")
        .attr('fill-opacity', 0.4)
        .attr('stroke', '#000000')
        .attr('fill', '#51b1e3')

    graph.append('polygon')
        .attr('points', "150,0 145,5 155,5")
        .attr('fill-opacity', 0.4)
        .attr('stroke', '#51b1e3')
        .attr('fill', '#51b1e3')

    graph.append('rect')
        .attr('x', 150)
        .attr('y', 150 - 29 * R / 2)
        .attr('width', 29 * R)
        .attr('height', 29 * R / 2)
        .attr('fill-opacity', 0.4)
        .attr('stroke', '#51b1e3')
        .attr('fill', '#51b1e3')

    graph.append('polygon')
        .attr('points', `150,150 150,${150 + R / 2 * 29} ${150 + R * 29},150`)
        .attr('fill-opacity', 0.4)
        .attr('stroke', '#51b1e3')
        .attr('fill', '#51b1e3')

    graph.append('path')
        .attr('d', `M ${150 - R / 2 * 29} 150
           A ${R / 2 * 29} ${R / 2 * 29}, 0, 0, 1, 150 ${150 - R / 2 * 29} L 150 150 Z`)
        .attr('fill-opacity', 0.4)
        .attr('stroke', '#51b1e3')
        .attr('fill', '#51b1e3')
}

function drawShoot(x, y, res) {
    graph = document.querySelector('svg');
    graph.insertAdjacentHTML('beforeend', `<circle r="3" cx="${translateX(x)}" cy="${translateY(y)}" fill="${res ? 'green' : 'red'}"/>`);
}

function drawLastShoot() {
    const xValues = document.getElementsByClassName("iks");
    const yValues = document.getElementsByClassName("igr");
    const resValues = document.getElementsByClassName("resi");
    let x = parseFloat(xValues[0].innerText.replace(",", "."));
    let y = parseFloat(yValues[0].innerText.replace(",", "."));
    let isHit = resValues[0].innerText === "yes";
    drawShoot(x, y, isHit);
}

function svgInit(){
    let canvas = document.querySelector('svg');
    canvas.addEventListener('mousedown', event => clickOnChart(canvas, event));
    drawAxes();
}

function getMousePos(evt) {
    const svg = document.getElementById('mySVG');
    let rect = svg.getBoundingClientRect();
    return {
        x: evt.clientX - rect.left - 5,
        y: evt.clientY - rect.top - 5
    };
}

function translateX(value){
    return 150 + (parseFloat(value) * 29);
}

function translateY(value){
    return 150 - (parseFloat(value) * 29);
}

