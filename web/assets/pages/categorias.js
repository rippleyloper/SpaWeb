dato=null;
tipos=null;
idCa=null;
$(document).ready(function(){
	botones();
});
function botones(){
	$("#tipo").change(function(evt){
		evt.preventDefault();
		obtenerDatos(1,this.value);
	});
	$("#btnAgregar").click(function(evt){
		evt.preventDefault();
		document.getElementById("addCate").reset();
		$("#btnSubmit").val("Agregar");
		$("#modalAdd").modal("show");
	});
	$("#btnSubmit").click(function(evt){
		evt.preventDefault();
		if(!($("#addTipo").val()>0)){
			swal("Debe selecionar un tipo de servicio");
			$("#addTipo").focus();
			return false;
		}
		if(!($("#nombre").val().length>3)){
			swal("Nombre debe ser mayor a 4 caracteres");
			$("#nombre").focus();
			return false;
		}
		agregar($("#btnSubmit").val());
	});
	obtenerDatos(2,0);
}
function obtenerDatos(data,id){
	$.getJSON("ServletCategoria",{data:data,id:id},function(res){
		if(data==1){
			$("#tabla tbody").empty();	
			var tipo=$("#tipo").find("option:selected").text();
			dato=res;
			$.each(res,function(x,y){
			$("#tabla tbody").append($("<tr>").append($("<td>").html(y.id)).append($("<td>").html(y.nombre)).append($("<td>").html(tipo)).append($("<td>").append($("<button>").html("Modificar").addClass("btn btn-info").attr("id","btnMod").attr("onClick","accion("+y.id+",1);"))).append($("<td>").append($("<button>").html("Eliminar").addClass("btn btn-danger").attr("id","btnDel").attr("onClick","accion("+y.id+",2);"))));
		});
		}
		else if(data==2){
			$.each(res,function(x,y){
				$("#tipo").append($("<option>").val(y.id).html(y.nombre));
				$("#addTipo").append($("<option>").val(y.id).html(y.nombre));
			});
			tipos=res;
			$("#addTipo option").first().before($("<option>").val(0).html("Seleccione").attr("selected",true));
			$("#tipo").val($("#tipo option").first().val()).change();
		}
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
		});buscar.id;
		idCa=val;
		$("#addTipo").val($("#tipo").val());
		$("#nombre").val(buscar.nombre);
		$("#modalAdd").modal("show");
	}
	else if(id==2){
		idCa=val;
		eliminar("eliminar")
	}
}
function agregar(e){
	console.log({nombre:$("#nombre").val(),tipo:$("#addTipo").val(),btnAgregar:e,id:idCa});
	$.ajax({
		url:"ServletCategoria",
		type:"POST",
		data:{nombre:$("#nombre").val(),tipo:$("#addTipo").val(),btnAgregar:e,id:idCa},
		dataType:"json",
		success:function(data){
			console.log(data);
			if(data!=null){
				if(data.estado){
					if(e=="Agregar"){
						swal("Agregado",data.msj,"success");
					}
					else if(e=="Modificar"){
						swal("Modificado",data.msj,"success");
					}
					document.getElementById("addCate").reset();
					obtenerDatos(1,$("#tipo").val());
					$("#modalAdd").modal("hide");
					
				}else{
					swal("Error",data.msj,"error");
				}
			}
		},error:function(e){
			console.log(e);
			swal("Error","Error de conexion, numero de error C025");
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
				url:"ServletCategoria",
				type:"POST",
				data:{btnAgregar:data,id:idCa},
				dataType:"json",
				success:function(data){
					if(data!=null){
						if(data.estado){
							swal("Eliminado",data.msj,"success");
							document.getElementById("addCate").reset();
							$("#modalAdd").modal("hide");
							obtenerDatos(1,$("#tipo").val());
						}else{
							swal("Error",data.msj,"error");
						}
					}
				},error:function(e){
					swal("Error","Error de conexion, numero de error C025");
				}
			});
		});
}
