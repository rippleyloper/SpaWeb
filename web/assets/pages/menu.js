detalle=null;
cale=[];
dato=null;
user=null;
cli=null;
$(document).ready(function(){
   
});
var resultado = '';
var termino2 = '';
function agendarServicio(nombre, dni, idHora){
  
      
					$('#modalFecha').modal('show');
                                        $('#trabajadortexto').val(nombre);
                                        $('#trabajador').val(dni);
                                        var horaServicio = idHora;
                                         var resultadoinicio = horaServicio.substring(1, 9);
                                         resultado = horaServicio.substring(1, 6);
                                         termino2 = sumarMinutos(resultado, 30);
                                         
                                     //   alert(termino2);
}
                     
function botones2(){
	obtenerAgendas();
	servicios();
	calendario();
	bloquear(1);
	$("#tipo").change(function(){
		if(this.value>0)
			categoria(this.value)
	});
	$("#categoria").change(function(){
		if(this.value>0)
			trabajador(this.value);
	});
	$("#ids").change(function(){
		if(this.value>0){
			obtenerServicio(this.value);
		}
	});
	$("#servicio").change(function(){
      var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();
var hh = today.getHours();
var ss = today.getSeconds();
//var horaInicio = $("#inicio").val();
var fecha = dd+'/'+mm+'/'+yyyy+' '+resultado;

		if(this.value>0){
                   

 
			
			detalles(fecha,this.value);
                        
                  
		}
                $("#fecha").val(fecha);
//alert(fecha);
                 
	});
	$("#btnAgendar").click(function(evt){
		evt.preventDefault();
		if($("#tipo").val()==0){
			swal("Debe seleccionar un tipo de servicio");
			return false;
		}else if($("#ids").val()==0){
			swal("Debe seleccionar una categoria");
			return false;
		}else if($("#servicio").val()==0){
			swal("Debe seleccionar un servicio");
			return false;
		}
		var des=$("#descuento").val();
		console.log(des>0);
		if(!(des>=0 && des<=100)){
			swal("El descuento debe ser entre 0 y 100");
			return false;	
		}
		else if($("#categoria").val()==0){
			swal("Debe seleccionar una especialidad");
			return false;
		}else if($("#trabajador").val()==0){
			swal("Debe seleccionar un trabajador");
			return false;
		}
		else if($("#detalle").val().trim()==""){
			swal("Debe escribir un comentario");
			return false;
		}
		
		var total3=$("#total2").val();
		var pagado=$("#pagado").val();
		if(pagado==0 || pagado>total3){
			swal("El valor abonado debe ser entre 0 y "+total3);
			return false;
		}
		insertar();
	});
	$("#buscarDni").click(function(evt){
		evt.preventDefault();
		var dni=$("#dni").val();
		if(dni!=""){
			obtenerDni(dni);
		}else{
			swal("El campo DNI no puede estar vacio");
		}
	});
	$('#descuento').change(function(evt){
		evt.preventDefault();
		if(this.value==0){
			$("#total2").val($("#precio").val());
		}else{
			var precio=$("#precio").val();
			var des=parseInt($("#descuento").val());
			var por=des/100;
			var nPre=precio*por;
			$("#desApl").val(parseInt(nPre));
			$("#total2").val(parseInt(precio-nPre));
		}
		$("#pagado").attr("max",nPre);
	});
}
function obtenerAgendas(){
	$.ajax({
		url:"ServletReservas",
		type:"GET",
		data:{data:1},
		dataType:"JSON",
		success:function(data){
			detalle=null;
			detalle=data;
	//		armarCalendario();
		},
		error:function(error){
			swal("Error","Error de conexion, numero de error 3C025");
		}
	});
}
function servicios(){
	$.ajax({
		url:"ServletReservas",
		type:"GET",
		data:{data:2},
		dataType:"JSON",
		success:function(data){
			if(data!=null){
				$.each(data,function(x,y){
					if(y.servicio){
						$("#tipo").append($("<option>").val(y.servicio.id).html(y.servicio.nombre));
					}
					if(y.especialidad){
						$("#categoria").append($("<option>").val(y.especialidad.id).html(y.especialidad.nombre));	
					}
				});
			}
		},
		error:function(error){
			swal("Error","Error de conexion, numero de error 4C025");	
		}
	});
}
function obtenerServicio(id){
	$.ajax({
		url:"ServletReservas",
		type:"GET",
		data:{data:6,id:id},
		dataType:"JSON",
		success:function(data){
			
			if(data!=null){
				$("#servicio").empty();
				$("#servicio").append($("<option>").val(0).html("Seleccione"));
				$.each(data,function(x,y){
					$("#servicio").append($("<option>").val(y.id).html(y.nombre));	
				});
			}
		},
		error:function(error){
			swal("Error","Error de conexion, numero de error 5C025");
		}
	});	
}
function categoria(id){
	$.ajax({
		url:"ServletReservas",
		type:"GET",
		data:{data:3,id:id},
		dataType:"JSON",
		success:function(data){
			
			if(data!=null){
				$("#ids").empty();
				$("#ids").append($("<option>").val(0).html("Seleccione").attr("selected",true));
				$.each(data,function(x,y){
					$("#ids").append($("<option>").val(y.producto.id).html(y.producto.nombre));
				});
			}
		},
		error:function(error){
			swal("Error","Error de conexion, numero de error C025");
		}
	});
}
function trabajador(id){
	$.ajax({
		url:"ServletReservas",
		type:"GET",
		data:{data:4,id:id},
		dataType:"JSON",
		success:function(data){
			
			$("#trabajador").empty();
			$("#trabajador").append($("<option>").val(0).html("Seleccione"));
			if(data!=null){
				$.each(data,function(x,y){
					$("#trabajador").append($("<option>").val(y.dni).html(y.nombre+" "+y.apellido));
				});
			}
		},
		error:function(error){
			swal("Error","Error de conexion, numero de error 8C025");
		}
	});
}
function detalles(fecha,id, ){
	$.ajax({
		url:"ServletReservas",
		type:"GET",
		data:{data:5,fecha:fecha,id:id},
		dataType:"JSON",
		success:function(data){
			
			if(data!=null){
				$("#duracion").val(data.duracion);
				$("#inicio").val(resultado);
				$("#termino").val(termino2);
				$("#precio").val(data.precio);
				$("#total2").val(data.precio);
			}
		},
		error:function(error){
			swal("Error","Error de conexion, numero de error 9C025");
		}
	});
}
/*function armarCalendario(){
	cale=[];
	$.each(detalle,function(x,data){
		var totalD=data.curso.precio-(data.curso.precio*(data.curso.descuento/100));
		if(totalD==data.curso.pagado){
			cale.push(
				{
					id:data.curso.id,
					title:data.curso.Trabajadores[0].nombre+" "+data.curso.Trabajadores[0].apellido,
					start:data.curso.inicio,
					end:data.curso.termino,
					backgroundColor:'#08FF00'
				}
			);
		}else{
			cale.push(
				{
					id:data.curso.id,
					title:data.curso.Trabajadores[0].nombre+" "+data.curso.Trabajadores[0].apellido,
					start:data.curso.inicio,
					end:data.curso.termino,
					backgroundColor:'#FF0000'
				}
			);
		}	
	});
	//calendario();
	$("#calendar").fullCalendar('removeEvents');
	$("#calendar").fullCalendar('addEventSource',cale);
}
*/
function calendario(){
	$("#calendar").fullCalendar({
		header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			    },
		navLinks: true,
	    selectable:true,
	    selectHelper:true,
	    eventLimit:true,
		editable:false,
		weekends:true,
		events: cale,
		slotDuration: '00:15:00',
		dayClick:function(data){
                  
			moment.suppressDeprecationWarnings = true;
			var fecha=moment(data).format("DD/MM/YYYY HH:mm");
                        
			var yest=moment(moment().toDate()).format("DD/MM/YYYY HH:mm");
			if(!moment().subtract(2, 'day').isBefore(moment(data),'day'))
			     swal("Alerta","No puedes agendar días antes a hoy","error");
			else{
                       
				date=new Date(fecha);
				dato=fecha;
				var hora=fecha.split(' ');
				hora=hora[1].split(":");
				if(hora[0]>0){	
					document.getElementById("form").reset();
					bloquear(1);
                                        
					$('#modalFecha').modal('show');
				}
			}
		},
		eventClick:function(evt){
			buscarClie(evt.id);
		}
	});
}
function insertar(){
	$.ajax({
		url:"ServletReservas",
		type:"POST",
		data:{btnAgendar:'agendar',inicio:dato,servicio:$("#servicio").val(),dni:$("#dni").val(),detalle:$("#detalle").val(),trabajador:$("#trabajador").val(),descuento:$("#descuento").val(),pagado:$("#pagado").val(),fecha:$("#fecha").val()},
		dataType:"JSON",
		success:function(data){
			
			swal("Agregado",data.msj,"success");
			document.getElementById("form").reset();
			$('#modalFecha').modal('hide');
			bloquear(4);
			obtenerAgendas();
			load();
		},
		error:function(e){
			swal("Error","Error de conexion, numero de error 10C025");
		}
	});
}
function bloquear(id){
	if(id==1){
		$("#form input[type='text'],select, #btnAgendar,textarea").each(function(x){
			$(this).attr("disabled",true);
		});
		bloquear(4);
	}
	if(id==2){
		$("#form input[type='text'],select, #btnAgendar,textarea").each(function(x){
			$(this).removeAttr("disabled",true);
		});
		bloquear(4);
	}
	if(id==3){
		$("#dni").attr("disabled",true);
		$("#duracion").attr("disabled",true);
		$("#inicio").attr("disabled",true);
		$("#termino").attr("disabled",true);
		$("#precio").attr("disabled",true);
	}
	if(id==4){
		$("#dni").removeAttr("disabled");
	}
}
function obtenerDni(id){
	$.ajax({
		url:"ServletReservas",
		type:"GET",
		data:{data:7,dni:id},
		dataType:"JSON",
		success:function(data){
			
			if(data.persona==null)
				swal("Dni ingresado no es de un cliente activo");
			else{
				user=data.persona.dni;
				$("#nombre").val(data.persona.nombre+" "+data.persona.apellido);
				bloquear(2);
				bloquear(3);
			}
		},
		error:function(error){
			swal("Error","Error de conexion, numero de error 11C025");
		}
	});
}
function buscarClie(id){
	cli=null;
	$.each(detalle,function(x,y){
		if(id==y.curso.id){
			cli=y;
		}
	});
	if(cli!=null){
		console.log(cli);
		var totalD=cli.curso.precio*(cli.curso.descuento/100);
		$("#dniD").html(cli.curso.DNI);
		$("#nombreD").html(cli.curso.nombre+" "+cli.curso.apellido);
		$("#inicioD").html(moment(cli.curso.inicio).format("DD/MM/YYYY HH:mm"));
		$("#terminoD").html(moment(cli.curso.termino).format("DD/MM/YYYY HH:mm"));
		$("#tipoD").html(cli.curso.tipo);
		$("#categoriaD").html(cli.curso.categoria);
		$("#servicioD").html(cli.curso.servicio);
		$("#estadoD").html(cli.curso.asis);
		$("#valorD").html(cli.curso.precio);
		$("#descuentoD").html(cli.curso.descuento+ "%");
		$("#totalD").html(cli.curso.precio-totalD);
		$("#pagadoD").html(cli.curso.pagado);
		$("#trabajadorD").empty();
		$.each(cli.curso.Trabajadores,function(x,y){
			$("#trabajadorD").append($("<p>").html(y.nombre+" "+y.apellido+"</br>"));
		});
		$("#detalleD").html(cli.curso.detalle);
		$("#modalDetalle").modal("show");
	}
}
function action(det){
	console.log(cli);
	if(det=="Asistir"){
		var totalD=cli.curso.precio-(cli.curso.precio*(cli.curso.descuento/100));
		var totalFa=totalD-cli.curso.pagado;
		console.log(totalD);
		if(cli.curso.pagado<totalD){
			swal({
				title:"Para asistir, debe cumplir el pago completo",
				text:"Corrobore haber recibido $"+totalFa.toLocaleString("es-Ch")+" pesos",
				type:"warning",
				showCancelButton:!0,
				confirmButtonColor:"#DD6B55",
				confirmButtonText:"Si, lo Recibí",
				cancelButtonText:"No, cancelar",
				closeOnConfirm:!1,closeOnCancel:!1
			},
			function(e){
				e?action2(det,cli.curso.id,totalD):
				swal("Cancelado","No se realizó ninguna acción ","error")}
			);
		}else{
			action2(det,cli.curso.id,0);
		}
	}
	else if(det=="Cancelar"){
		action2(det,cli.curso.id,0);
	}
	
}
function action2(det,id,total){
	$.ajax({
		url:"ServletReservas",
		type:"POST",
		dataType:"JSON",
		data:{btnAgendar:det,id:id,total:total},
		success:function(data){
			if(data.msj){
				if(det=="Asistir"){
					swal("Asistido",data.msj,"success");
				}else if(det=="Cancelar"){
					swal("Cancelado",data.msj,"success");
				}
				$.each(detalle,function(x,y){
					if(y.curso.id===cli.curso.id){
						detalle.splice(x,1);
						return false;
					}
				});
				//armarCalendario();
				load();
				$("#modalDetalle").modal("hide");
				cli=null;
			}
		},
		error:function(e){
			swal("Error","Error de conexion, numero de error 2C025");
		}
	});
}
function load(){
	$.get("ServletEstadisticas",{data:2},function(data){
		$("#cantidadOrdenes2").html(data[0].serviciosH);
		$("#cantidadOrdenes").html("Hoy tiene "+data[0].serviciosH+" ordenes");
	},"json");
}