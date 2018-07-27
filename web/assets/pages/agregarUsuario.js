$(document).ready(function(){
	botones();
});
var ret=false;
function botones(){
	$("#btnAccion2 input").last().attr("disabled",true);
	$("#trabajadores tr").click(function(evt){
		evt.preventDefault();
		$("#DNI").val($.trim($(this).children().html()));
		cambiarEstado(1);
		cambiarEstado(4);
		$("#btnAccion2 input").last().attr("disabled",true);
	});
	$("#usuarios tr").click(function(evt){
		evt.preventDefault();
		$("#DNI").val($.trim($(this).children().html()));
		cambiarEstado(2);
		cambiarEstado(4);
		obtenerDetalle($.trim($(this).children().html()));
		$("#btnAccion2 input").last().attr("disabled",false);
	});
	obtenerRol();
	$("#DNI").on("keypress",function(evt){
		if(isNaN(String.fromCharCode(evt.which))){
			evt.stopPropagation();
			return false;
		}
	});
	$("#DNI").on("keyup",function(evt){
		if($(this).val().length==0){
			$("#validar").attr("disabled",true);
		}else{
			$("#validar").attr("disabled",false);
		}
	});
	$("#validar").click(function(evt){
		evt.preventDefault();
		obtenerDetalle($("#DNI").val());
	});
	$("#btnAccion2").submit(function(e){
		if($(document.activeElement).val()=="Eliminar"){
			swal({
				title:"¿Estas seguro?",
				text:"una vez eliminado lo puedes recuperar mas tarde",
				type:"warning",
				showCancelButton:!0,
				confirmButtonClass:"btn-warning",
				confirmButtonText:"Si, borralo!",
				closeOnConfirm:!1},function(e){
					if(e){
						ret=true;
						$("#btnAccion2 input").last().click();
					}else{
						ret=false;
					}
			});
		}else if($(document.activeElement).val()=="Agregar"){
			ret=true;
		}else if($(document.activeElement).val()=="Modificar"){
			ret=true;
		}
		return ret;
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
function obtenerRol(){
	$.ajax({
		url:"ServletUsuario",
		type:"GET",
		dataType:"JSON",
		data:{data:2},
		success:function(data){
			if(data!=null){
				var rol=$("#rol");
				$.each(data,function(i,data){
					rol.append($("<option>").val(data.id).html(data.nombre));
				});
			}
		}
	});
}
function cambiarEstado(id){
	if(id==1)
		$('#btnAccion').val("Agregar");
	else if(id==2)
		$('#btnAccion').val("Modificar");
	else if(id==3)
		$('#btnAccion').attr("disabled",true);
	else if(id==4)
		$('#btnAccion').attr("disabled",false);
}
function obtenerDetalle(dni){
	$.ajax({
		url:"ServletUsuario",
		type:"GET",
		dataType:"JSON",
		data:{data:3,dni:dni},
		success:function(data){
			if(data!=null){
				if(data.rol!=null)
				{
					cambiarEstado(2);
					cambiarEstado(4)
					$("#rol").val(data.rol);
				}else if(data.rol==null){
					cambiarEstado(3);
					swal("Error","DNI ingresado no es de un usuario","error");
				}

			}
		},
		error:function(e){
			swal("Error","DNI ingresado no es de un usuario","error");
		}
	});
}