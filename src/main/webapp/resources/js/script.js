function ClearGraphic() {
    canvasContext.clearRect(0, 0, graphicCanvas.width, graphicCanvas.height);
    figuresContext.clearRect(0, 0, graphicCanvas.width, graphicCanvas.height);
    pointsContext.clearRect(0, 0, graphicCanvas.width, graphicCanvas.height);
    graphic.removeEventListener("mousemove", ShowCoordinates);
    graphic.removeEventListener("mouseout", HideCoordinates);
}
function ClearFigures() {
    figuresContext.clearRect(0, 0, graphicCanvas.width, graphicCanvas.height);
}
function ClearPoints() {
    pointsContext.clearRect(0, 0, graphicCanvas.width, graphicCanvas.height);
}

function DrawGraphic() {
    let origin = 0;
    let border = graphicCanvas.width;
    let center = (origin + border) / 2;
    let arrow = border / 50;
    canvasContext.clearRect(0, 0, graphicCanvas.width, graphicCanvas.height);
    canvasContext.fillStyle = "black";
    canvasContext.strokeStyle = 'black';
    canvasContext.lineWidth = 4.0;
    canvasContext.beginPath();
    canvasContext.moveTo(origin, origin);
    canvasContext.lineTo(origin, border);
    canvasContext.lineTo(border, border);
    canvasContext.lineTo(border, origin);
    canvasContext.lineTo(origin, origin);
    canvasContext.lineWidth = 2.0;
    canvasContext.moveTo(center, border);
    canvasContext.lineTo(center, origin);
    canvasContext.lineTo(center + arrow, origin + arrow);
    canvasContext.moveTo(center, origin);
    canvasContext.lineTo(center - arrow, origin + arrow);// Ось Y

    canvasContext.moveTo(origin, center);
    canvasContext.lineTo(border, center);
    canvasContext.lineTo(border - arrow, center - arrow);
    canvasContext.moveTo(border, center);
    canvasContext.lineTo(border - arrow, center + arrow);// Ось X
    canvasContext.stroke();
}
function DrawFigures(radius){
    let origin = 0;
    let border = graphicCanvas.width;
    let center = (origin + border) / 2;
    radius *= (2 * border / 25);
    figuresContext.beginPath();
    figuresContext.moveTo(center, center);
    figuresContext.lineTo(center - radius/2, center);
    figuresContext.lineTo(center, center - radius);
    figuresContext.lineTo(center, center);
    figuresContext.fillStyle = '#1ADBD5';
    figuresContext.fill();
    figuresContext.stroke(); // triangle

    figuresContext.beginPath();
    figuresContext.moveTo(center, center);
    figuresContext.lineTo(center, center - radius/2);
    figuresContext.lineTo(center + radius, center - radius/2);
    figuresContext.lineTo(center+radius, center);
    figuresContext.lineTo(center, center);
    figuresContext.fillStyle = '#1ADBD5';
    figuresContext.fill();
    figuresContext.stroke(); // rectangle

    figuresContext.beginPath();
    figuresContext.moveTo(center, center);
    // figuresContext.lineTo(center, center + radius);
    // figuresContext.quadraticCurveTo(center + radius, center + radius, center + radius, center);
    figuresContext.arc(center,center,radius,0,0.5*Math.PI,false);
    figuresContext.lineTo(center, center);
    figuresContext.fillStyle = '#1ADBD5';
    figuresContext.fill();
    figuresContext.stroke(); // half-cycle
}
function DrawPoints(){
    let coordinate = document.querySelectorAll("table tbody tr td");
    let radius = parseFloat(form.elements["pointsForm:radius"].value);
    for (let i = 0; i < coordinate.length / 6; i++) {
        if (radius===parseFloat(coordinate[i * 6 + 4].innerText))
        DrawPoint(
            graphicCanvas.width / 2 + parseFloat(coordinate[i * 6 + 2].innerText) / 6.25 * (graphicCanvas.width / 2),
            graphicCanvas.height / 2 - parseFloat(coordinate[i * 6 + 3].innerText) / 6.25 * (graphicCanvas.height / 2),
            coordinate[i * 6 + 5].innerText==="true"
        );
    } // drawing every points on the graph
}
function DrawPoint(x, y, result=true) {
    pointsContext.beginPath();
    pointsContext.arc(x, y, 3, 0, 2 * Math.PI, false);
    let color = 'black';
    if (result===true)
        color='red';
    pointsContext.fillStyle = color;
    pointsContext.strokeStyle = color;
    pointsContext.fill();
    pointsContext.lineWidth = 1;
    pointsContext.stroke();
}

const form = document.querySelector("#pointsForm");
const hiddenForm = document.querySelector("#hiddenPointsForm");

let graphicCanvas = document.querySelector("#GraphicCanvas");
let graphicFigures = document.querySelector("#GraphicFigures");
let graphicPoints = document.querySelector("#GraphicPoints");
let graphic = document.querySelector("#graphic");

let canvasContext = graphicCanvas.getContext('2d');
let figuresContext = graphicFigures.getContext('2d');
let pointsContext = graphicPoints.getContext('2d');

function CheckPoint(e){
    hiddenForm.elements["hiddenPointsForm:hiddenX"].value
        = ((2 * (e.pageX - e.target.offsetLeft) - graphicCanvas.width) / (graphicCanvas.width) * 25 / 4).toFixed(3);
    hiddenForm.elements["hiddenPointsForm:hiddenY"].value
        = ((graphicCanvas.height - 2 * (e.pageY - e.target.offsetTop)) / (graphicCanvas.height) * 25 / 4).toFixed(3);
    hiddenForm.elements["hiddenPointsForm:hiddenR"].value = form.elements["pointsForm:radius"].value;
    hiddenForm.elements["hiddenPointsForm:hiddenCommandCheckPoint"].click();
    hiddenForm.elements["hiddenPointsForm:hiddenX"].value=null;
    hiddenForm.elements["hiddenPointsForm:hiddenY"].value=null;
}
let coordinates = document.querySelector("#coordinates");

function ShowCoordinates(e) {
    let x = e.pageX - e.target.offsetLeft;
    let y = e.pageY - e.target.offsetTop;
    coordinates.innerHTML = "Coordinates: ("
        + ((2 * x - graphicCanvas.width) / (graphicCanvas.width) / 4 * 25).toFixed(3) + ","
        + ((graphicCanvas.height - 2 * y) / (graphicCanvas.height) / 4 * 25).toFixed(3) + ")";
}

function HideCoordinates() {
    coordinates.innerHTML = "";
}

function ChangedRadius(elem,radius){
    ClearFigures();
    ClearPoints();
    graphic.removeEventListener("click",CheckPoint);
    form.elements["pointsForm:radius"].value = 0;
    hiddenForm.elements["hiddenPointsForm:hiddenR"].value=0;
    if (elem.checked===true) {
        document.querySelectorAll('#radiusCheckBox input[type=checkbox]').forEach(checkbox => checkbox.checked = false);
        elem.checked=true;
        form.elements["pointsForm:radius"].value = radius;
        hiddenForm.elements["hiddenPointsForm:hiddenR"].value=radius;
        DrawFigures(parseFloat(radius));
        graphic.addEventListener("click",CheckPoint);
        DrawPoints();
    }
}

document.addEventListener('DOMContentLoaded', () => {
    DrawGraphic();
    graphic.addEventListener("mousemove", ShowCoordinates);
    graphic.addEventListener("mouseout", HideCoordinates);
    //default value
    form.elements["pointsForm:radius"].value = "0.0";
    hiddenForm.elements["hiddenPointsForm:hiddenR"].value="0.0";
    document.querySelector("#resultMessage").innerText=null;
});