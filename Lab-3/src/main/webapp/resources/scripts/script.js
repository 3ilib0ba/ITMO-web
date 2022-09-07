
function buttonX(valueX) {
    var field = document.getElementById(`main_form:x_buttons`);
    console.log(field);
    field.setAttribute("value", valueX);// = valueX;
    //console.log(document.getElementById("x_buttons"));//.getAttribute("value"));
    return false;
}

function validateX() {
    // if (document.querySelector('input[name="j_idt6:coordinateX"]:checked') !== null) {
    //     //X = document.querySelector('input[name="coordinateX"]:checked').value;
    // } else {
    //     alert("X value must be selected;");
    //     return false;
    // }
    return true;
}

function changeR(){
    let r = document.getElementById(`main_form:rSpinner_input`);
    R = r.attributes.getNamedItem("aria-valuenow").value;
    drawAxes();
    drawPoints();
}

function validateY() {
    let y = document.getElementById(`main_form:yValue`);
    if (y.value.trim() === "") {
        alert("Y value must not be null;");
        return false;
    }
    y.value = y.value.replace(',', '.');
    if (!(y.value && !isNaN(y.value))) {
        alert("Y must be a number;");
        return false;
    }
    if (y.value < -5 || y.value > 3) {
        alert("Y must be in the following interval: (-5; 3);");
        return false;
    }

    return true;
}

function clickOnChart(canvas, event) {
    let pos = getMousePos(event);
    let xFromCanvas = (pos.x - (300 / 2)) / (29);
    let yFromCanvas = ((300 / 2) - pos.y) / (29);
    document.getElementById('form_hidden:x_hidden').value = xFromCanvas;
    document.getElementById('form_hidden:y_hidden').value = yFromCanvas;
    document.getElementById('form_hidden:r_hidden').value = R;
    document.getElementById('form_hidden:button_hidden').click();
}

