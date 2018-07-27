$(document).ready(function () {
    botones();
    cargarHorario();
});
function cargarHorario() {
    obtenerTrabajadores();
}
function cargarHorario2() {
    alert('cargandoPerro()');
    var perro = '<tr id="06:00:00" data-time="06:00:00">'
    perro = '<td class="fc-axis fc-time fc-widget-content" style="width: 58px;">';
    perro = '<span>perroloco</span></td>';
    perro = '<td>';
    perro = '</td></tr>';

    var trCuerpo = '';

    trCuerpo = '<tr><td class="fc-axis fc-widget-content" style="width: 58px;"><span>all-day</span></td><td class="fc-day fc-widget-content fc-sun fc-past" data-date="2018-07-22"></td><td class="fc-day fc-widget-content fc-mon fc-past" data-date="2018-07-23"></td><td class="fc-day fc-widget-content fc-tue fc-past" data-date="2018-07-24"></td><td class="fc-day fc-widget-content fc-wed fc-past" data-date="2018-07-25"></td><td class="fc-day fc-widget-content fc-thu fc-today " data-date="2018-07-26"></td><td class="fc-day fc-widget-content fc-fri fc-future" data-date="2018-07-27"></td><td class="fc-day fc-widget-content fc-sat fc-future" data-date="2018-07-28"></td></tr>';

    var cabecerath1 = '<th class="fc-axis fc-widget-header" style="width: 58px;"></th>';


    $("#cabecera").css({display: "block", overflow: "scroll"});
    var celda2 = '<th class="fc-day-header fc-widget-header fc-sun fc-past" data-date="2018-07-22"><a> thomas </a></th>'

    trCuerpo = '<tr style="position:fixed;top:0;background:#FFF;"><td class="fc-axis fc-widget-content" style="width: 58px;"><span>all-dayperro</span></td><td class="fc-day fc-widget-content fc-sun fc-past" data-date="2018-07-22"></td><td class="fc-day fc-widget-content fc-mon fc-past" data-date="2018-07-23"></td><td class="fc-day fc-widget-content fc-tue fc-past" data-date="2018-07-24"></td><td class="fc-day fc-widget-content fc-wed fc-past" data-date="2018-07-25"></td><td class="fc-day fc-widget-content fc-thu fc-today " data-date="2018-07-26"></td><td class="fc-day fc-widget-content fc-fri fc-future" data-date="2018-07-27"></td><td class="fc-day fc-widget-content fc-sat fc-future" data-date="2018-07-28"></td></tr>';

    $("#cuerpo2").html(trCuerpo);
    $("#cuerpo3").append(trCuerpo);
    $("#cuerpo3").append(trCuerpo);

}
function botones() {
    // $("#perro").click(function (evt) {


    $("#cargarHorario").click(function (evt) {

        obtenerTrabajadores();


    });
    $('#cargarP').click(function (evt) {
        obtenerTrabajadores();
    });
}

function obtenerTrabajadores() {
    $.ajax({
        url: "ServletTrabajador",
        type: "GET",
        data: {data: 10},
        dataType: "json",
        success: function (data) {
            var tamanoCelda = 'style="width: 130px!important; " ';
            var tamanoCelda2 = 'style="width: 105px!important;  height: 1px!important;" ';
            var tdcito = '';
            var espacioVacio = '<th class="fc-axis fc-widget-header" style="width: 58px;"></th>';
            var celda = '<td class="fc-day-header fc-widget-header fc-tue"><span>nuevacelda</span></td>';
            var i;
            var cabecerath = '<th  class="fc-axis fc-widget-header" style="width: 58px;"></th>';
            $('#tabla-horas > thead > tr  ').html(cabecerath);
            for (i = 0; i < data.length; i++) {
                var clasetd = 'fc-day fc-widget-content';
                var claseth = 'fc-day-header fc-widget-header fc-sun fc-past';
                var clasef = 'scope="col"';
                var fondo = 'style="background-color: #dddddd!important;"';
                celda = '<th ' + tamanoCelda + ' class="' + claseth + '" ' + fondo + ' data-date="2018-07-22"><a>' + data[i].nombre + ' ' + data[i].apellido + ' </a></th>'
                $('#tabla-horas > thead > tr  ').append(celda);
                $('#tabla-horas > tbody > tr').each(function () {
                    console.log(this.id);
                    var argumentoProfesional = "'" + data[i].nombre + " " + data[i].apellido + "'";

                    tdcito = '<td ' + tamanoCelda2 + ' onclick="torombolo(' + argumentoProfesional + ');" class="fc-day fc-widget-content " data-date="2018-07-27"></td>';
                    $(this).append(tdcito);
                    // $('#tabla-horas > tbody>  tr').append(tdcito)
                })
            }


            var largoTrabajadores = data.length;
            var recorrido = 0;
            recorrido = 7 - largoTrabajadores;
            for (i = 0; i < recorrido; i++) {
                celda = '<th class="fc-day-header fc-widget-header fc-sun fc-past" data-date="2018-07-22"><a>&nbsp</a></th>'
                $('#tabla-horas > thead > tr  ').append(celda);
                tdcito = '<td  ' + tamanoCelda + ' class="fc-day fc-widget-content " data-date="2018-07-27"></td>';
                $('#tabla-horas tr').append(tdcito);
            }
            console.log("ready!");
        },
        error: function (evt) {
            swal("Error", "Problemas de conexion, numero de error C025", "error");
        }

    });
}