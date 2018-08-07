
   $('#myModal').modal({keyboard: true }) 
            $('#myModal').modal("hide");

function sumarMinutos(horas, minutos){
    const tiempoInicial = horas; //'12:30:00';
const minutosDuracion = minutos; //'120';
const tiempoFinal = moment(tiempoInicial, 'HH:mm:ss').add(minutosDuracion, 'minutes').format('HH:mm');
    console.log(tiempoFinal);
    return tiempoFinal;
    
}
window.onmousedown = function (e) {
    var el = e.target;
    if (el.tagName.toLowerCase() == 'option' && el.parentNode.hasAttribute('multiple')) {
        e.preventDefault();

        // toggle selection
        if (el.hasAttribute('selected')) el.removeAttribute('selected');
        else el.setAttribute('selected', '');

        // hack to correct buggy behavior
        var select = el.parentNode.cloneNode(true);
        el.parentNode.parentNode.replaceChild(select, el.parentNode);
    }
}
function inicialMayus(item) {
    var palabrasMinuscula = item.value.toLowerCase();
    var arregloPalabra = palabrasMinuscula.split(" "); //retorna un array
    var palabraConv = '';
    
    for (var i = 0; i < arregloPalabra.length; i++) {
       palabraConv += mayusPrimeraLetra(arregloPalabra[i])+' ';
    }
 
   $("#"+item.id).val(palabraConv);
    }

function cortarPrimeraPalabra(palabra)
{
    var arregloPalabra = palabra.split(" ");
    
    return arregloPalabra[0];
}    
  


function mayusPrimeraLetra(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}
function cargarDepartamento(){
     var departamentoSelect = $('#departamento option[value="10"]');
     load();
     
     departamentoSelect.attr('selected', true);
	function load(){
            
		if(!(this.value=="10"))
                   
			obtenerBarrio(10);
	
	}
        }
function darFormato() {

    formateaRut();
    function formateaRut() {
        var rut = "";
        var rutFormatear = $("#dni").val();
        $("#dni").val(rutFormatear);


        var rut = rutFormatear;
        var actual = rut.replace(/^0+/, "");
        if (actual != '' && actual.length > 1) {
            var sinPuntos = actual.replace(/\./g, "");
            var actualLimpio = sinPuntos.replace(/-/g, "");
            var inicio = actualLimpio.substring(0, actualLimpio.length - 1);
            var rutPuntos = "";
            var i = 0;
            var j = 1;
            for (i = inicio.length - 1; i >= 0; i--) {
                var letra = inicio.charAt(i);
                rutPuntos = letra + rutPuntos;
                if (j % 3 == 0 && j <= inicio.length - 1) {
                    rutPuntos = "." + rutPuntos;
                }
                j++;
            }
            var dv = actualLimpio.substring(actualLimpio.length - 1);
            rutPuntos = rutPuntos + "-" + dv;
        }
        // alert('rutformato'+rutPuntos);
        $('#dni').val(rutPuntos);
        return rutPuntos;


    }
}
function miles(parm)
{
    alert('m' + thousands_separators(1000));
    return thousands_separators(1000);
}
function thousands_separators(num)
{
    var num_parts = num.toString().split(".");
    num_parts[0] = num_parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return num_parts.join(".");
}
function confirmarModal(){
    
    window.location.reload();
    
}
