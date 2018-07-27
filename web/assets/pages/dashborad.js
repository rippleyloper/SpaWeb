$(document).ready(function(){
	cargarDatos();
});
var dato=[];
function cargarDatos(){
	$.get("ServletEstadisticas",{data:1},function(data){
		
		$("#cliente").html(data[0].clienteA);
		$("#trabajador").html(data[8].trabajadorA);
		$("#ordene").html(data[3].totalA);
		$("#recaudado").html(data[10].calcularT.toLocaleString("es-Ch"));
		dato=data;
		crearDonut("donut",[
			{label:"Asistidos",value:data[4].totalAs},
			{label:"Cancelados",value:data[5].totalC},
			{label:"No asistidos",value:data[7].totalNoA},
			{label:"En espera",value:data[6].totalE}
			],["#20F30F",
					"#EC0B2A",
					"#B7E8D6",
					"#60F8F6"]);
		$("#agenda  li h4 b").html(data[3].totalA);
		//id,data,xkey,ykey,etiqueta,i
		var totalG=dato[10].calcularT-dato[12].calcularTD;
		var totalD=totalG-dato[11].calcularP;
		createBar("bar",
			[
				{y:"Montos",a:totalG,b:dato[11].calcularP,c:totalD}
			],
			"y",
			["a","b","c"],
			["Total final","Pagado","Deficit"],
			["#20F30F","#EC0B2A","#B7E8D6"]
			);
		$.each($("#recau  li h4 b"),function(x,y)
		{
			if(x==0)
				$(this).html(dato[10].calcularT.toLocaleString("es-Ch"));
			else if(x==1)
				$(this).html(dato[12].calcularTD.toLocaleString("es-Ch"));
			else if(x==2)
				$(this).html(totalG.toLocaleString("es-Ch"));
		});
		crearDonut("donut2",[
				{label:"Mensual",value:data[13].cumpleMes},
				{label:"Día",value:data[14].cumpleDia},
				{label:"Total",value:data[0].clienteA}
			],["#37CEFF","#94E5FF","#C5F1FF"]);
		$.each($("#cumple  li h4 b"),function(x,y){
			if(x==0)
				$(this).html(data[14].cumpleDia.toLocaleString("es-Ch"));
			else if(x==1)
				$(this).html(data[13].cumpleMes.toLocaleString("es-Ch"));
			else if(x==2)
				$(this).html(data[0].clienteA.toLocaleString("es-Ch"));
		});
	},"json")
}
function crearDonut(nombre,data,e){
	Morris.Donut(
		{element:nombre,
			data:data,
			resize:!0,
			colors:e
		});
}
function createBar(id,data,xkey,ykey,etiqueta,i){
	Morris.Bar(
		{element:id,
			data:data,
			xkey:xkey,
			ykeys:ykey,
			labels:etiqueta,
			gridLineColor:"#eee",
			barSizeRatio:.4,
			resize:!0,
			hideHover:"auto",
			barColors:i
		});
}
