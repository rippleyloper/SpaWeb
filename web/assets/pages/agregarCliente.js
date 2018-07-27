$(document).ready(function(){
	botones();
});
function botones(){
	obtenerGenero();
	obtenerDepartamento();
	$("#departamento").change(function(evt){
		if(!(this.value==0)){
			obtenerBarrio(this.value);
		}else{
			var barrio=$("#barrio");
			barrio.empty();
			barrio.append('<option value="0">Seleccione un departamento</option>');
		}
	});
	$('#btnAgregar').click(function(evt){
		document.getElementById('form').reset();
		$('#departamento').val("").change();
		evt.preventDefault();
		var su=$('input[type="submit"]');
		su.val("Agregar");
		su.show();
		$('input[name="dni"]').attr("disabled",false);
		$('#myModal').modal("show");
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
function obtenerGenero(){
	$.ajax({
		url:"ServletCliente",
		data:{data:5},
		type:"GET",
		dataType : "json",
        contentType: "application/json; charset=utf-8",
		success :function(data){	
			var genero=$("#genero");
			$.each(data,function(i,val){
				genero.append('<option value="'+val.id+'">'+val.nombre+'</option>');
			});
		}
	});
}
function obtenerDepartamento(){
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
			var barrio=$("#barrio");
			barrio.empty();
			$.each(data,function(i,val){
				barrio.append('<option value="'+val.id+'">'+val.nombre+'</option>');
			});
		}
	});
}
function detalle(dni){
	document.getElementById('form').reset();
	$('#departamento').val(0).change();
	$.ajax({
		url:"ServletCliente",
		type:"GET",
		dataType:"JSON",
		data:{data:3,dni:dni},
		success:function(data){
			if(data!=null){
				$('input[name="dni"]').val(data.dni);
				$('input[name="dni"]').attr("disabled",true);
				$('input[name="nombre"]').val(data.nombre);
				$('input[name="apellido"]').val(data.apellido);
				$('select[name="genero"]').val(data.idGenero);
				$('input[name="numero"]').val(data.telefono);
				$('input[name="correo"]').val(data.correo);
				$('input[name="fecha"]').val(data.fecha);
				$('input[name="direccion"]').val(data.direccion);
				$('select[name="departamento"]').val(data.departamento).change();
				$('select[name="barrio"]').val(data.barrio).change();
				$('input[type="submit"]').hide();
				$("#myModal").modal("show");
				
			}
		}
	});
}
function modificar(dni){
	document.getElementById('form').reset();
	$('#departamento').val(0).change();
	$.ajax({
		url:"ServletCliente",
		type:"GET",
		dataType:"JSON",
		data:{data:3,dni:dni},
		success:function(data){
			if(data!=null){
				$('input[name="dni"]').val(data.dni);
				$('input[name="dni"]').attr("disabled",true);
				$('input[name="nombre"]').val(data.nombre);
				$('input[name="apellido"]').val(data.apellido);
				$('select[name="genero"]').val(data.idGenero);
				$('input[name="numero"]').val(data.telefono);
				$('input[name="correo"]').val(data.correo);
				$('input[name="fecha"]').val(data.fecha);
				$('input[name="direccion"]').val(data.direccion);
				$('select[name="departamento"]').val(data.departamento).change();
				$('select[name="barrio"]').val(data.barrio).change();
				var su=$('input[type="submit"]');
				su.val("Modificar");
				su.show();
				$("#myModal").modal("show");
				
			}
		}
	});
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
		window.location.href='ServletCliente?data=2&dni='+dni;
	});
}