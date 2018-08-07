$(document).ready(function () {
    botones();
    cargarHorario();
    botones2();
    });
    var dataReserva = 'hola';
    var dnitrabajador = 'cc';
    var horaAntigua ='';
    var horaInicial = '';
    
    
    /*
$('#tabla-horas tr ').onco(function () {
        var id = $(this).attr('id');
        //do something with id
        alert('id'+id);
})
*/


function myFunction(){
    alert('hola');
}
function pintarCelda(horaParm, dniParm){
    //alert('id='+horaParm+':'+dniParm);
   $("#tabla-horas > tbody > tr ").each(function(){
    var tr = $(this);
    var tds = tr.find("td[id='"+horaParm+":"+dniParm+"']").css('background-color','#337ab7');
   // tr.find("td[id='"+horaParm+":"+dniParm+"']").css('border','none');
    })
}

function pintarAgenda(dni, hora, duracion){
    dniTrabajador = dni;
    var cantidadCeldaAgenda = duracion/15;
    horaInicial = hora;
    horaAntigua = hora;
  
        for (var j = 0; j < cantidadCeldaAgenda; j++) {
            if(j!=0){
              pintarCelda(horaInicial+':00', dniTrabajador);  
              horaAntigua = sumarMinutos(horaInicial, 15);         
          }
          else {
      
            pintarCelda(horaInicial, dniTrabajador);  
            horaAntigua = sumarMinutos(horaInicial, 15);
          }
            horaInicial = horaAntigua;
    }
}

function recorrerTrabajador(){
    
    for (var i = 0; i < dataReserva.length; i++) {
       obtenerDniTrabajador(dataReserva[i].idTrabajador, dataReserva[i].hora, dataReserva[i].duracion);
    }
}



function cargarHorario() {
    obtenerTrabajadores();
    obtenerServicios(); 
}

function botones() {
    $("#cargarHorario").click(function (evt) {
    obtenerTrabajadores();
    });
    $('#cargarP').click(function (evt) {
        obtenerTrabajadores();
    });
}

function obtenerServicios(){
          var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1;
var yyyy = today.getFullYear();
var hh = today.getHours();
var ss = today.getSeconds();
var fecha = $("#fechaHoy").val();
    $.ajax({
        url: "ServletServicio",
        type: "GET",
        data: {data: 5, fecha: fecha},
        dataType: "json",
        success: function (data){
             dataReserva = data;
             recorrerTrabajador();
             for (i = 0; i < data.length; i++) {          
             var idTrabajador = data[i].idTrabajador;
             var horar = data[i].hora;
             $("#tabla-horas > tbody > tr ").each(function(){
        var tr = $(this);
        var tds = tr.find("td[id='"+horar+""+dnitrabajador+"']").css('background-color','#337ab7');
    })            
            }
              
            $("#tabla-horas > tbody > tr ").each(function(){
        var tr = $(this);
        var tds = tr.find("td[id='08:30:0:Thomas']").css('background-color','#337ab7');
        var tds2 = tr.find("td[id='08:45:0:Thomas']").css('background-color','#337ab7');
        var tds2 = tr.find("td[id='09:30:0:Thomas']").css('background-color','#337ab7');
    })       
        },
          error: function (evt) {
            swal("Error", "Problemas de conexion, numero de error C025", "error");
        }
    })
}

function obtenerDniTrabajador(idTrabajador, hora, duracion){
    $.ajax({
        url: "ServletTrabajador",
        type: "GET",
        data: {data: 11, idTrabajador: idTrabajador},
        dataType: "json",
        success: function (data){
             dnitrabajador = data["dni"];
             pintarAgenda(dnitrabajador, hora, duracion);
             
        }
        
    })
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
             
                celda = '<th ' + tamanoCelda + ' class="' + claseth + '" ' + fondo + ' data-date="2018-07-22"><a>' + cortarPrimeraPalabra(data[i].nombre) + ' ' + cortarPrimeraPalabra(data[i].apellido) + ' </a></th>'
                $('#tabla-horas > thead > tr  ').append(celda);
                 var horas = [];
                $('#tabla-horas > tbody > tr').each(function () {
                    console.log(this.id);
                    var auxHora = this.id;
                    var str = "h6:00:00";
                    var idHora = auxHora.substring(1, 9);
                    
                    var argumentoProfesional = "'" + cortarPrimeraPalabra(data[i].nombre) + " " + cortarPrimeraPalabra(data[i].apellido) + "' , '"+data[i].DNI+"','"+this.id+"' ";                  
                    //tdcito = '<td id="'+idHora+':'+data[i].DNI+'"  onclick="agendarServicio(' + argumentoProfesional + ');" class="fc-day fc-widget-content " data-date="2018-07-27"></td>';
                  // tdcito = '<td style="width: 58px"  class="fc-axis fc-time fc-widget-content" ></td>';
                tdcito = '<td  id="'+idHora+':'+data[i].DNI+'" data-asig="false" data-prof="'+cortarPrimeraPalabra(data[i].nombre)+" " + cortarPrimeraPalabra(data[i].apellido)+'" data-dni="'+data[i].DNI+'" data-hor="'+this.id+'" class="fc-axis fc-time fc-widget-content " style="width: 58px;"></td>';
                    $(this).append(tdcito);
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