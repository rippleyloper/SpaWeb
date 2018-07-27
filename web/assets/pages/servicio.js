dato=null;
idCa=null;
servicios=null;
$(document).ready(function(){
	botones();
});
function botones(){
	$("#btnAgregar").click(function(evt){
		evt.preventDefault();
		if($("#tipo").val()>0 && $("#categoria").val()>0){
			document.getElementById("addCate").reset();
			$("#op").hide("FAST");
			$("#btnSubmit").val("Agregar");
			$("#modalAdd").modal("show");
		}else{
			swal("Debe seleccionar un tipo de servicio y su categoria.");
		}
	});
	$("#categoria").change(function(evt){
		evt.preventDefault();
		obtenerServicios(this.value);
	});
	$("#tipo").change(function(evt){
		evt.preventDefault();
		obtenerDatos(1,this.value);
	});
	$("#addTipo").change(function(evt){
		evt.preventDefault();
		obtenerDatos1(1,this.value);
	});
	$("#btnSubmit").click(function(evt){
		evt.preventDefault();
		if(!($("#nombre").val().length>3)){
			swal("Nombre debe ser mayor a 4 caracteres");
			$("#nombre").focus();
			return false;
		}
		if(!($("#duracion").val()>0 && $("#duracion").val()<=180)){
			swal("Duracion debe ser entre 0 y 180 minutos");
			$("#duracion").focus();
			return false;
		}
		if(!($("#precio").val()>0 && $("#precio").val()<=99999)){
			swal("Precio debe ser entre 0 y 999999");
			$("#precio").focus();
			return false;
		}
		
		if(!($("#detalle").val().length<=500 && $("#detalle").val().length>3)){
			swal("detalle debe ser mayor a 3 y menor a 500 caracteres");
			$("#detalle").focus();
			return false;
		}
		agregar($("#btnSubmit").val());
	});
	obtenerDatos(2,0);
	obtenerDatos1(2,0);
}
function obtenerDatos(data,id){
	$.getJSON("ServletCategoria",{data:data,id:id},function(res){
		if(data==1){
			$("#categoria").empty();	
			var tipo=$("#tipo").find("option:selected").text();
			dato=res;
			$.each(res,function(x,y){
				$("#categoria").append($("<option>").val(y.id).html(y.nombre));
			});
			$("#categoria option").first().before($("<option>").val(0).html("Seleccione").attr("selected",true));
			//$("#categoria").val($("#categoria option").first().val()).change();
		}
		else if(data==2){
			$.each(res,function(x,y){
				$("#tipo").append($("<option>").val(y.id).html(y.nombre));
			});
			tipos=res;
			$("#tipo").val($("#tipo option").first().val()).change();
		}
	});
}
function obtenerDatos1(data,id){
	$.getJSON("ServletCategoria",{data:data,id:id},function(res){
		if(data==1){
			$("#addCategoria").empty();	
			var tipo=$("#tipo").find("option:selected").text();
			dato=res;
			$.each(res,function(x,y){
				$("#addCategoria").append($("<option>").val(y.id).html(y.nombre));
			});
			$("#addCategoria option").first().before($("<option>").val(0).html("Seleccione").attr("selected",true));
			//$("#categoria").val($("#categoria option").first().val()).change();
		}
		else if(data==2){
			$.each(res,function(x,y){
				$("#addTipo").append($("<option>").val(y.id).html(y.nombre));
			});
			tipos=res;
			$("#addTipo option").first().before($("<option>").val(0).html("Seleccione").attr("selected",true));
		}
	});
}
function obtenerServicios(valor){
	//ServletServicio
	$("#tabla tbody").empty();
	$.getJSON("ServletServicio",{id:valor,data:1},function(res){
		if(res!=null){
			servicios=res;
			$.each(res,function(x,y){
				$("#tabla tbody").append($("<tr>")
					.append($("<td>").html(y.servicio.idServicio))
					.append($("<td>").html(y.servicio.nombre))
					.append($("<td>").html(y.servicio.duracion))
					.append($("<td>").html(y.servicio.precio))
					.append($("<td>").append($("<button>").html("Detalles").addClass("btn btn-info").attr("id","btnMod").attr("onClick","accion("+y.servicio.idServicio+",1);")))
					.append($("<td>").append($("<button>").html("Modificar").addClass("btn btn-primary").attr("id","btnMod").attr("onClick","accion("+y.servicio.idServicio+",3);")))
					.append($("<td>").append($("<button>").html("Eliminar").addClass("btn btn-danger").attr("id","btnDel").attr("onClick","accion("+y.servicio.idServicio+",2);")))
				);

			});
		}
	});
}
function accion(val,id){
	if(id==1){
		buscar=null;
		$.each(servicios,function(x,y){
			if(y.servicio.idServicio==val){
				buscar=y;
			}
		});
		idCa=buscar.servicio.idServicio;
		var tipo=$("#categoria").find("option:selected").text();
		$("#nombdreDt").text(buscar.servicio.nombre);
		$("#duracionDt").text(buscar.servicio.duracion);
		$("#precioDt").text(buscar.servicio.precio);
		$("#categoriaDt").text(tipo);
                var p=$("<p>").text(buscar.servicio.detalle)
		$("#detalleDt").append(p);
		$("#modalDetalle").modal("show");

	}
	else if(id==2){
		idCa=val;
		eliminar("eliminar");
	}else if(id==3){
		$("#op").show("FAST");
		$("#btnSubmit").val("Modificar");
		buscar=null;
		$.each(servicios,function(x,y){
			if(y.servicio.idServicio==val){
				buscar=y;
			}
		});
		var cate=$("#categoria").val();
		var t=$("#tipo").val();
		console.log(buscar);
		idCa=buscar.servicio.idServicio;
		$("#nombre").val(buscar.servicio.nombre);
		$("#duracion").val(buscar.servicio.duracion);
		$("#precio").val(buscar.servicio.precio);
		$("#addTipo").val(t);
		$("#addCategoria").val(cate).change();
		$("#detalle").val(buscar.servicio.detalle);
		$("#modalAdd").modal("show");
	}
}
function agregar(e){
	var serialise=[];
	serialise.push({name:"btnAgregar",value:e});
	serialise.push({name:"id",value:idCa});
	if(e=="Agregar"){
		serialise.push({name:"addCategoria",value:$("#categoria").val()});
	}else if(e=="Modificar")
	{
		serialise.push({name:"addCategoria",value:$("#addCategoria").val()});
	}
	serialise.push({name:"nombre",value:$("#nombre").val()});
	serialise.push({name:"duracion",value:$("#duracion").val()});
	serialise.push({name:"precio",value:$("#precio").val()});
	serialise.push({name:"detalle",value:$("#detalle").val()});
	$.ajax({
		url:"ServletServicio",
		type:"POST",
		data:$.param(serialise),
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
					obtenerServicios($("#categoria").val());
					$("#modalAdd").modal("hide");
				}else{
					swal("Error",data.msj,"error");
				}
			}
		},error:function(e){
			console.log(e);
			swal("Error","Se produjo un error de conexion, error numero C025","error");
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
				url:"ServletServicio",
				type:"POST",
				data:{btnAgregar:data,id:idCa},
				dataType:"json",
				success:function(data){
					if(data!=null){
						if(data.estado){
							swal("Borrado",data.msj,"success");
							obtenerServicios($("#categoria").val());
						}else{
							swal("Error",data.msj,"error");
						}
					}
				},error:function(e){
					swal("Error","Se produjo problemas de conexion, error numero C025","error");
				}
			});
		});
}