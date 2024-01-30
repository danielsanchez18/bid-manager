const buttonInfo = document.getElementById("btnInfo");
controlCodigo = document.getElementById("txtCodigo");
controlNombre = document.getElementById("txtNombre");
controlEscuela = document.getElementById("txtEscuela");
controlMessages = document.getElementById("lblMessages");

buttonInfo.addEventListener("click", getDataFromServlet);

function getDataFromServlet() {

    // Create XMLHttprequest object
    const xhr = new XMLHttpRequest();
    var params = 'cod=' + controlCodigo.value;
    console.log(params);
    xhr.open("POST", "/ajax", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.getResponseHeader("Content-type", "application/json");

    xhr.onload = function() {
        const obj = JSON.parse(this.responseText);
        controlNombre.value = obj.nombre;
        controlEscuela.value = obj.escuela;
        controlMessages.innerHTML = obj.msg;
    }

    xhr.send(params);
}