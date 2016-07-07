/**
 * Autor: Estrada Mares Alejandro
 * Fecha: 11 de Abril de 2016
 * Programa: reporte
 * Descripcion: Genera las consultas sobre las tiendas y su historial
 * de reportes ejecutivos de 6 meses atras dependiendo de la fecha actual
 */
function carga_Sucursal() {
	
	var cCliente = leerCookies(document.cookie); 

	$.ajax({
		type : "GET",
		url : "BuscaSucursal",
		dataType : "json",
		contentType : "application/json",
		data : {
			cCliente : cCliente
		},
		success : function(data) {
			
			if(data != ""){
				$("#mytable > tbody").empty();
				for ( var item in data) {
					$('#mytable > tbody').append(
							'<tr ondblclick="abrir_VenReporte('+data[item].cSucursal+');">' + 
								'<td>' + data[item].cSucursal + '</td>' + 
								'<td>' + data[item].cNombre   +   '</td>' + 
									 /*'<td><nobr>'

									+ '<a class="pure-button pure-button-primary"'
									+ 'onclick="return confirm('
									+ "'¿Desea Eliminar el usuario selecionado?'"
									+ ');" ' + 'href="javascript:remove_sysMenu('
									+ data[item].cSucursal + ' )"> <i'
									+ '	class="fa fa-times"></i>Eliminar' + '</a>'

									+ '</nobr></td>' + */'</tr>');
					}
				}else{
					swal("No Existen Registros");
				}
		},
		error : function(data,status,error) {
			sweetAlert("Oops...", "Algo salio mal intenta mas tarde o contacta a sistemas", "error");
		}

	});

}

function abrir_VenReporte(sucursal){
	
	var tmpSucursal = sucursal;
	window.open('ejecutivo?sucursal='+tmpSucursal/*+'&nombre='+tmpNombre*/);

}

function leerCookies(galleta){
	var lista = galleta.split("=");
    return lista[1];
}

$(document).ready(function(){
    carga_Sucursal();
});
