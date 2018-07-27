var inicio=null;
var detalleServicio=null;
var sessiones=[];
$(document).ready(function(){
	botones();
});
function botones(){
	
	$("#wizard").smartWizard({
		selected:0,
		keyNavigation:true,
		cycleSteps:false,
		autoAdjustHeight:true,
		backButtonSupport:true,
		useURLhash:false,
		lang:{next:"Siguiente",
			  previous:"Atras"},
		toolbarSettings:{
			toolbarPosition:'bottom',
			toolbarButtonPosition:'right',
			showNextButton:true,
			showPreviousButton:true,
			toolbarExtraButtons:[
			$('<button></button>').text('Finalizar')
					      .addClass('btn btn-info')
					      .on('click', function(){ 
						alert('Finsih button click');                            
					      }),
			$('<button></button>').text('Cancelar')
					      .addClass('btn btn-danger')
					      .on('click', function(){ 
					         accion(2);                            
					      })
			]
		},
		theme: 'arrows',
		transitionEffect:'fade',
		transitionSpeed:'400',
		onShowStep:function(){
			$("#wizard").smartWizard("fixHeight");
		}
	});
	$("#wizard").on("leaveStep", function(e, anchorObject, stepNumber, stepDirection) {
		var form=$("#form-step-"+stepNumber);
		// console.log(form);
		// if(stepDirection==='forward' && form){
		// 	form.validator('validate');
		// 	var elmErr=form.children(".has-error");
		// 	if(elmErr && elmErr.length>0)
		// 		return false;
		// }
		// return true;
     });
	$("#wizard2").smartWizard({
		selected:0,
		keyNavigation:true,
		cycleSteps:false,
		autoAdjustHeight:true,
		backButtonSupport:true,
		showStepURLhash:false,
		lang:{next:"Siguiente",
			  previous:"Atras"},
		toolbarSettings:{
			toolbarPosition:'bottom',
			toolbarButtonPosition:'right',
			showNextButton:true,
			showPreviousButton:true,
			toolbarExtraButtons:[
			$('<button></button>').text('Finalizar')
					      .addClass('btn btn-info')
					      .on('click', function(){ 
					      	var form=$("#form-step"+2);
							form.validator('validate');
							var elmErr=form.children(".has-error");
							if(elmErr && elmErr.length>0){
								return false;
							}
							else{
								var fecha=$("#fechaI").val();
								if(fecha=="")
									alert("Seleccione una fecha");
								else
									armarSessiones(); 
							}                          
					      }),
			$('<button></button>').text('Cancelar')
					      .addClass('btn btn-danger')
					      .on('click', function(){ 
					        $("#modalAgregar").modal("hide");	                            
					      	$("#wizard2").smartWizard("reset");
					      })
			]
		},
		theme: 'arrows',
		transitionEffect:'fade',
		transitionSpeed:'400'
	});
	$("#wizard2").on("leaveStep", function(e, anchorObject, stepNumber, stepDirection) {
		var form=$("#form-step"+stepNumber);
		console.log(stepNumber);
		if(stepDirection==='forward' && stepNumber==1){
			$("#fechaI").val(inicio);
			obtenerTipoCategoria(4,$("#servicio").val());
		}
		if(stepDirection==='forward' && form){
			form.validator('validate');
			var elmErr=form.children(".has-error");
			if(elmErr && elmErr.length>0)
				return false;
		}
		return true;
     });
	$("#calendario").fullCalendar({
		header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			    },
		defaultDate: new Date(),
		navLinks: true,
	    selectable:true,
	    selectHelper:true,
	    eventLimit:true,
		editable:false,
		weekends:true,
		events: [],
		slotDuration: '00:15:00',
		dayClick:function(data){
			var fecha=moment(data).format("DD/MM/YYYY HH:mm");
			var hora=fecha.split(" ")[1];
			console.log(parseInt(hora.split(":")[0]));
			if(parseInt(hora.split(":")[0])==0){
				$('#calendario').fullCalendar('changeView', 'agendaDay',data);
			}
			if(parseInt(hora.split(":")[0])>0){
				inicio = fecha;
				$("#wizard2 .sw-btn-next").click();
			}
			
		}
	});
	//Eventos de elemento
	obtenerTipoCategoria(1,0);
	$("#tServicio").change(function(){
		if(!this.value==""){
			obtenerTipoCategoria(2,this.value);
			$("#servicio").val("");
		}
	});
	$("#categoria").change(function(){
		if(!this.value=="")
			obtenerTipoCategoria(3,this.value);
	});
	obtenerTipoCategoria(5,0);
	$("#categoriaT").change(function(){
		if(!this.value=="")
			obtenerTipoCategoria(6,this.value);
	});

}
function accion(a){
	if(a==2){
		$("#addCupo").modal('hide');
		swal({
			title:"¿Estas seguro?",
			text:"Perderá todo lo realizado y no podrá recuperarlo",
			type:"warning",
			showCancelButton:!0,
			confirmButtonColor:"#DD6B55",
			confirmButtonText:"Si, cancelar",
			cancelButtonText:"No, quiero seguir",
			closeOnConfirm:!1,closeOnCancel:!0
		},
		function(e){
			if(e){
				swal("Correcto","Se cancelo correctamente","success");
				$("#wizard").smartWizard("reset");
			}else{
				$("#addCupo").modal('show');
			}
			
		});
	}
}
class Session{

	constructor(idServicio,nombre,duracion,precio,fechaI,idTrabajador){
		this.idServicio=idServicio;
		this.nombre=nombre;
		this.duracion=duracion;
		this.precio=precio;
		this.fechaI=fechaI;
		this.idTrabajador=idTrabajador;
		this.fechaF=this.calcularFinal();
	}
	getIdServicio(){
		return this.idServicio;
	}
	getNombre(){
		return this.nombre;
	}
	getDuracion(){
		return this.duracion;
	}
	getPrecio(){
		return this.precio;
	}
	getFechaI(){
		return this.fechaI;
	}
	getIdTrabajador(){
		return this.idTrabajador;
	}
	getFechaF(){
		return this.fechaF;
	}
	calcularFinal(){
		var a =moment(this.fechaI,"DD/MM/YYYY HH:mm");
		a.add(this.duracion,'minutes');
		return a.format("DD/MM/YYYY HH:mm");
	}

}
function armarSessiones(){
	var a=moment($("#fechaI").val(),"DD/MM/YYYY HH:mm");
	for(var i=1;i<=parseInt($("#repetir").val());i++){
		sessiones.push(new Session(detalleServicio.id,detalleServicio.nombre,detalleServicio.duracion,detalleServicio.precio,a.format("DD/MM/YYYY HH:mm"),$("#trabajador").val()));
		a.add($("#descanse").val(),'days');
		if(a.day()==0){
			a.add($("#descanse").val()+1,'days');
		}
	}
	llenarTabla("tblSer");
}
function llenarTabla(id){
	if(id=="tblSer"){
		$("#"+id+">tbody").empty();
		$.each(sessiones,function(x,y){
			$("#"+id+">tbody").append($("<tr>").append($("<td>").html(y.getNombre())).append($("<td>").html(y.getFechaI())).append($("<td>").html(y.getDuracion())));
		});
	}
}

function obtenerTipoCategoria(e,a){
	if(e==1){
		$.get("ServletCuponera",{data:e},function(e){
			$.each(e,function(x,y){
				$("#tServicio").append($("<option>").val(y.id).html(y.nombre));
			});
		}).fail(function(e){
			console.log(e);
		});
	}
	else if(e==2){
		$.get("ServletCuponera",{data:e,idSer:a},function(data){
			$("#categoria").empty();
			$("#categoria").append($("<option>").val("").html("Seleccione una categoria"));
			$.each(data,function(x,y){
				$("#categoria").append($("<option>").val(y.id).html(y.nombre));
			});
		}).fail(function(e){
			console.log(e);
		});
	}
	else if(e==3){
		$.get("ServletCuponera",{data:e,idCat:a},function(data){
			$("#servicio").empty();
			$("#servicio").append($("<option>").val("").html("Seleccione un servicio"));
			$.each(data,function(x,y){
				$("#servicio").append($("<option>").val(y.id).html(y.nombre));
			});
		});
	}
	else if(e==4){
		$.get("ServletCuponera",{data:e,idCat:a},function(data){
			detalleServicio=data;
		});
	}
	else if(e==5){
		//categoriaT
		$.get("ServletCuponera",{data:e},function(data){
			$("#categoriaT").empty();
			$("#categoriaT").append($("<option>").val("").html("Seleccione una categoria"));
			$.each(data,function(x,y){
				$("#categoriaT").append($("<option>").val(y.id).html(y.nombre));
			});
		});
	}
	else if(e==6){
		//trabajador
		$.get("ServletCuponera",{data:e,idEs:a},function(data){
			$("#trabajador").empty();
			$("#trabajador").append($("<option>").val("").html("Seleccione un trabajador"));
			$.each(data,function(x,y){
				$("#trabajador").append($("<option>").val(y.id).html(y.nombre));
			});
		}).fail(function(e){
			console.log(e);
		});
	}
}