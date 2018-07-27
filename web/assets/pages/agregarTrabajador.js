$(document).ready(function(){
	botones();
	
});
function botones(){
    
  /*  $('#cargarP').click(function(evt){
        
        obtenerTrabajadores();
    });*/
            $("#hola").click(function(evt){
		alert('hola');
                 
	});
            $("#agregar").click(function(evt){
		document.getElementById('form').reset();
		obtenerEspecialidad();
		$('input[name="dni"]').removeAttr("disabled");
		$('#departamento').val("").change();
		evt.preventDefault();
		var su=$('input[type="submit"]');
		su.val("Agregar");
		su.show();
		$('#myModal').modal("show");
                 
	});
	obtenerGenero();
	obtenerDepa();
	obtenerEspecialidad();
	obtenerSucursal();
	$("#departamento").change(function(evt){
		if(!(this.value==""))
			obtenerBarrio(this.value);
		else{
			var barrio=$("#barrio2");
			barrio.empty();
			barrio.append('<option value="">Seleccione un departamento</option>');
		}
	});
	$("form").submit(function(){
		$('input[name="dni"]').removeAttr("disabled");
           
                 
	});
	mensajes();
}
function mensajes(){
	if (typeof msg !== 'undefined') {
		var me=msg[1].value;
		console.log(me);
		if(msg[0].value==1){
			swal("Correcto",me,"success");
		}else if(msg[0].value==2){
			swal("Error",me,"error");
		}
	}
}

function obtenerTrabajadores(){
	$.ajax({
		url:"ServletTrabajador",
		type:"GET",
		data:{data:10},
		dataType:"json",
		success:function(data){
                   // alert(JSON.stringify(data));;
                    /*
			var genero=$("#cabecera");
			genero.empty();
			genero.append('<option value="">Seleccione</option>');
			$.each(data,function(i,val){
				genero.append('<option value="'+val.id+'">'+val.nombre+'</option>');
			});*/
		},
		error:function(evt){
			swal("Error","Problemas de conexion, numero de error C025", "error");
		}

	});
}

function obtenerGenero(){
	$.ajax({
		url:"ServletTrabajador",
		type:"GET",
		data:{data:2},
		dataType:"json",
		success:function(data){
			var genero=$("#genero");
			genero.empty();
			genero.append('<option value="">Seleccione</option>');
			$.each(data,function(i,val){
				genero.append('<option value="'+val.id+'">'+val.nombre+'</option>');
			});
		},
		error:function(evt){
			swal("Error","Problemas de conexion, numero de error C025", "error");
		}

	});
}
function obtenerDepa(){
	$.ajax({
		url:"ServletCliente",
		data:{data:6},
		type:"GET",
		dataType : "json",
		success :function(data){	
			var depa=$("#departamento");
			$.each(data,function(i,val){
				depa.append('<option value="'+val.id+'">'+val.nombre+'</option>');
			});
		},
		error :function(error){
			swal("Error","Problemas de conexion, numero de error C025", "error");
		}
	});
}	
function obtenerBarrio(id){
	$.ajax({
		url:"ServletCliente",
		data:{data:7,idDepa:id},
		type:"GET",
		dataType : "json",
        contentType: "application/json; charset=utf-8",
		success :function(data){	
			var barrio=$("#barrio2");
			barrio.empty();
			$.each(data,function(i,val){
				barrio.append('<option value="'+val.id+'">'+val.nombre+'</option>');
			});
		},
		error :function(error){
			swal("Error","Problemas de conexion, numero de error C025", "error");
		}
	});
}
function obtenerEspecialidad(){
	$.ajax({
		url:"ServletTrabajador",
		type:"Get",
		data:{data:5},
		dataType:"JSON",
		success:function(data){
			var espe=$("#especialidad");
			espe.empty();
			espe.append('<option value="0">Seleccione</option>');
			$.each(data,function(i,val){
				espe.append('<option value="'+val.id+'">'+val.nombre+'</option>');
			});
		}
	});
}
function obtenerEspecialidad2(){
	$.ajax({
		url:"ServletTrabajador",
		type:"Get",
		data:{data:5},
		dataType:"JSON",
		success:function(data){
			var espe=$("#especialidad");
			espe.empty();
			$.each(data,function(i,val){
				espe.append('<option value="'+val.id+'">'+val.nombre+'</option>');
			});
		}
	});
}

function detalle(dni){
	document.getElementById("form").reset();
	$('#departamento').val(0).change();
	$.ajax({
		url:"ServletTrabajador",
		type:"GET",
		data:{data:7,dni:dni},
		dataType:"JSON",
		success:function(data){
			if(data!=null){

				$('select[name="departamento"]').val(data.departamento).change();
				$('select[name="barrio2"]').val(data.barrioo).change();
				$('input[name="dni"]').val(data.dni);
				$('input[name="dni"]').attr("disabled",true);
				$('input[name="nombre"]').val(data.nombre);
				$('input[name="apellido"]').val(data.apellido);
				$('select[name="genero"]').val(data.genero);
				$('input[name="numero"]').val(data.telefono);
				$('input[name="correo"]').val(data.correo);
				$('input[name="fecha"]').val(data.fecha);
				$('input[name="direccion"]').val(data.direccion);
				var espe=$("#especialidad");
				
				espe.empty();
				$.each(data.especialidad,function(x,val){
					espe.append('<option value="'+val.id+'">'+val.nombre+'</option>');
				});
				$('select[name="especialidad"]').val(data.especialidad).change();
				$('select[name="sucursal"]').val(data.sucursal).change();
				$('input[type="submit"]').hide();
				setTimeout(function(){
				    asignarBarrio(data.barrioo);
				}, 1000);
				$("#myModal").modal("show");
			}
		},error:function(e){
			swal("Error","Problemas de conexion, numero de error C025","error");
		}
	});
}
function modificar(dni){
	document.getElementById("form").reset();
	$('#departamento').val(0).change();
	$.ajax({
		url:"ServletTrabajador",
		type:"GET",
		data:{data:7,dni:dni},
		dataType:"JSON",
		success:function(data){
			if(data!=null){
				$('select[name="departamento"]').val(data.departamento).change();
				$('select[name="barrio2"]').val(data.barrioo).change();
				$('input[name="dni"]').val(data.dni);
				$('input[name="dni"]').attr("disabled",true);
				$('input[name="nombre"]').val(data.nombre);
				$('input[name="apellido"]').val(data.apellido);
				$('select[name="genero"]').val(data.genero);
				$('input[name="numero"]').val(data.telefono);
				$('input[name="correo"]').val(data.correo);
				$('input[name="fecha"]').val(data.fecha);
				$('input[name="direccion"]').val(data.direccion);
				obtenerEspecialidad2();
				$("#especialidad option[value='0']").remove();
				$('select[name="sucursal"]').val(data.sucursal).change();
				setTimeout(function(){
				    asignarBarrio(data.barrioo);
				}, 1000);
				var im=$('input[type="submit"]');
				im.val("Modificar");
				im.show();
				$("#myModal").modal("show");
			}
		},error:function(e){
			swal("Error","Problemas de conexion, numero de error C025","error");
		}
	});
}
function asignarBarrio(id){
	$("#barrio2").val(id);
}
function eliminar(dni){
	swal({
		title:"¿Estas seguro?",
		text:"una vez eliminado lo puedes activar mas tarde",
		type:"warning",
		showCancelButton:!0,
		confirmButtonClass:"btn-warning",
		confirmButtonText:"Si, borralo!",
		closeOnConfirm:!1},function(){
		window.location.href='ServletTrabajador?data=8&dni='+dni;
	});
}
function obtenerSucursal(){
	$.ajax({
		url:"ServletTrabajador",
		type:"GET",
		data:{data:6},
		dataType:"JSON",
		success:function(data){
                //    alert('data'+data);
                    
                    /*
                   			var sucu=$("#sucursal");
			sucu.empty();
			sucu.append('<option value="">Seleccione</option>');
			$.each(data,function(i,val){
				sucu.append('<option value="'+val.id+'">'+val.nombre+'</option>');
			});*/
		}
	});
}