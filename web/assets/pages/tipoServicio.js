dato=null;
idCa=null;
$(document).ready(function(){
	botones();
});
function botones(){
	$("#btnAgregar").click(function(evt){
		evt.preventDefault();
		document.getElementById("addCate").reset();
		$("#btnSubmit").val("Agregar");
		$("#modalAdd").modal("show");
	});
	$("#btnSubmit").click(function(evt){
		evt.preventDefault();
		if(!($("#nombre").val().length>3)){
			swal("Nombre debe ser mayor a 4 caracteres");
			$("#nombre").focus();
			return false;
		}
		agregar($("#btnSubmit").val());
	});
	obtenerDatos();
}
function obtenerDatos(){
	$.getJSON("ServletTipoServicio",{data:1},function(res){
		$("#tabla tbody").empty();
		dato=res;
		$.each(res,function(x,y){
			$("#tabla tbody").append($("<tr>").append($("<td>").html(y.id)).append($("<td>").html(y.nombre)).append($("<td>").append($("<button>").html("Modificar").addClass("btn btn-info").attr("id","btnMod").attr("onClick","accion("+y.id+",1);"))).append($("<td>").append($("<button>").html("Eliminar").addClass("btn btn-danger").attr("id","btnDel").attr("onClick","accion("+y.id+",2);"))));
		});
	});
}
function accion(val,id){
	if(id==1){
		$("#btnSubmit").val("Modificar");
		buscar=null;
		$.each(dato,function(x,y){
			if(y.id==val){
				buscar=y;
			}
		});
		idCa=buscar.id;
		$("#nombre").val(buscar.nombre);
		$("#modalAdd").modal("show");
	}
	else if(id==2){
		idCa=val;
		eliminar("eliminar");
	}
}
function agregar(e){
	$.ajax({
		url:"ServletTipoServicio",
		type:"POST",
		data:{nombre:$("#nombre").val(),btnAgregar:e,id:idCa},
		dataType:"json",
		success:function(data){
			if(data!=null){
				if(data.estado){
					if(e=="Agregar"){
						swal("Agregado",data.msj,"success");
					}
					else if(e=="Modificar"){
						swal("Modificado",data.msj,"success");
					}
					document.getElementById("addCate").reset();
					$("#modalAdd").modal("hide");
					obtenerDatos();
				}else{
					swal("Error",data.msj,"error");
				}
			}
		},error:function(e){
			swal("Error","Error en la conexion, numero de error C025","error");
		}
	});
}
function eliminar(data){
	swal({
		title:"¿Estas seguro?",
		text:"una vez eliminado no se puede recuperar",
		type:"warning",
		showCancelButton:!0,
		confirmButtonClass:"btn-warning",
		confirmButtonText:"Si, borralo!",
		closeOnConfirm:!1},
		function(){
			$.ajax({
				url:"ServletTipoServicio",
				type:"POST",
				data:{btnAgregar:data,id:idCa},
				dataType:"json",
				success:function(data){
					if(data!=null){
						if(data.estado){
							swal("Eliminado",data.msj,"success");
							document.getElementById("addCate").reset();
							$("#modalAdd").modal("hide");
							obtenerDatos();
						}else{
							swal("Error",data.msj,"error");
						}
					}
				},error:function(e){
					swal("Error","Error en la conexion, numero de error C025","error");
				}
			});
		});
}