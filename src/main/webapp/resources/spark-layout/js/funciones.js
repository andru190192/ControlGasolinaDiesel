PrimeFaces.locales['es'] = {
	closeText : 'Cerrar',
	prevText : 'Anterior',
	nextText : 'Siguiente',
	monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
			'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
			'Diciembre' ],
	monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago',
			'Sep', 'Oct', 'Nov', 'Dic' ],
	dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves',
			'Viernes', 'Sábado' ],
	dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab' ],
	dayNamesMin : [ 'D', 'L', 'M', 'X', 'J', 'V', 'S' ],
	weekHeader : 'Semana',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Sólo hora',
	timeText : 'Tiempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	currentText : 'Fecha actual',
	ampm : false,
	month : 'Mes',
	week : 'Semana',
	day : 'Día',
	allDayText : 'Todo el día'
};

function estiloFila(id, idTabla) {
	var vec = id.split(':');
	var fila = obtenerNumero(vec);
	var tblData = idTabla + '_data';
	document.getElementById(tblData).getElementsByTagName('tr')[fila].classList
			.add("class", 'ui-state-highlight');
}

function sinEstiloFila(id, idTabla) {
	var vec = id.split(':');
	var fila = obtenerNumero(vec);
	var tblData = idTabla + '_data';
	document.getElementById(tblData).getElementsByTagName('tr')[fila].classList
			.remove('ui-state-highlight');
}

function obtenerNumero(vec) {
	for (i = 0; i < vec.length; i++) {
		var a = Number.parseInt(vec[i]);
		if (a.toString() != 'NaN') {
			return a;
		}
	}
	return -1;
}

function teclaAbajoArriba(keyCode, idComponente, idTabla) {
	var tabla = idTabla;
	var vectorIdComponente = idComponente.split(":");
	var idComponenteMitad = vectorIdComponente[0] + ":" + vectorIdComponente[1];
	var idComponenteUltimo = vectorIdComponente[3];
	console.log("tabla - " + tabla);
	console.log("vectorIdComponente - " + vectorIdComponente);
	console.log("idComponenteMitad - " + idComponenteMitad);
	console.log("idComponenteUltimo - " + idComponenteUltimo);

	var posSplit = idComponente.split(":");
	var p = parseInt(posSplit[2]);
	var n = document.getElementById(tabla).childNodes.length;
	if (keyCode == 38) {
		if (p == 0)
			p = n - 1;
		else
			p = p - 1;
		document.getElementById(
				idComponenteMitad + ':' + p + ':' + idComponenteUltimo).focus();
		return false;
	} else if (keyCode == 40) {
		if ((p + 1) == n)
			p = 0;
		else
			p = p + 1;
		document.getElementById(
				idComponenteMitad + ':' + p + ':' + idComponenteUltimo).focus();
		return false;
	}
}
function clickIngresar() {
	$('#btnSubmit').click();
}

function clickCerrar() {
	$('#btnCerrarBusqueda').click();
}

function mostarPanelMostrar(id) {
	$panelPrincipal = $('#' + id + 'panelPrincipal');
	$panelInsertar = $('#' + id + 'panelMostrar');
	$panelPrincipal.slideToggle();
	$panelInsertar.slideToggle();
	return false;
}

function mostarPanelInsertar(id) {
	$panelPrincipal = $('#' + id + 'panelPrincipal');
	$panelInsertar = $('#' + id + 'panelInsertar');
	$panelPrincipal.slideToggle();
	$panelInsertar.slideToggle();
	return false;
}

function mostarPanelImportar(id) {
	$panelPrincipal = $('#' + id + 'panelPrincipal');
	$panelImportar = $('#' + id + 'panelImportar');
	$panelPrincipal.slideToggle();
	$panelImportar.slideToggle();
	return false;
}

function mostarPanelEditar(id) {
	$panelPrincipal = $('#' + id + 'panelPrincipal');
	$panelEditar = $('#' + id + 'panelEditar');
	$panelPrincipal.slideToggle();
	$panelEditar.slideToggle();
	return false;
}

function mostarPanelEliminar(id) {
	$panelPrincipal = $('#' + id + 'panelPrincipal');
	$panelEliminar = $('#' + id + 'panelEliminar');
	$panelPrincipal.slideToggle();
	$panelEliminar.slideToggle();
	return false;
}
function mostarPanelBusqueda(id) {
	$panelBusqueda = $('#' + id + 'panelBusqueda');
	$panelBusqueda.slideToggle();
	return false;
}

function mostarPanel1(id) {
	$panelPrincipal = $('#panelPrincipal');
	$panel = $('#' + id);
	$panelPrincipal.slideToggle();
	$panel.slideToggle();
	return false;
}

function mostarPanel(idPanelPrincipal, idPanel) {
	$panelPrincipal = $('#' + idPanelPrincipal);
	$panel = $('#' + idPanel);
	$panelPrincipal.slideToggle();
	$panel.slideToggle();
	return false;
}

function mostrarPanel(idPanel, idFocus) {
	$panel = $('#' + idPanel);
	$panel.slideDown('slow');
	if (idFocus != '') {
		$fo = $('#' + idFocus);
		$fo.focus();
		setTimeout(function() {
			$fo = $('#' + idFocus);
			$fo.focus();
		}, 300);
	}
	return false;
}

function ocultarPanel(idPanel, idFocus) {
	$panel = $('#' + idPanel);
	$panel.hide();
	if (idFocus != '') {
		$fo = $('#' + idFocus);
		$fo.focus();
		setTimeout(function() {
			$fo = $('#' + idFocus);
			$fo.focus();
		}, 300);
	}
	return false;
}

function comprobarInsertar(xhr, status, args, id) {
	if (!args.validationFailed && args.cerrar) {
		mostarPanelInsertar(id);
	}
}

function comprobarEditar(xhr, status, args, id) {
	if (!args.validationFailed && args.cerrar) {
		mostarPanelEditar(id);
	}
}

function comprobarDialogoPago(xhr, status, args, id) {
	if (!args.error) {
		mostarPanel1(id);
	}
}

function concatenarNuevo() {
	var apellidos = $('#formNuevo\\:apellidos').val();
	var nombres = $('#formNuevo\\:nombres').val();
	$('#formNuevo\\:nombreComercial').val(apellidos + " " + nombres);
}

function concatenarEditar() {
	var apellidos = $('#formEditar\\:apellidos').val();
	var nombres = $('#formEditar\\:nombres').val();
	$('#formEditar\\:nombreComercial').val(apellidos + " " + nombres);
}

function abrirDialogoPago() {
	var tipoPagoVar = $('#txtTipoPago').val();
	if (tipoPagoVar == 1) {
		ocultarPanel('panelTarjeta', '');
		ocultarPanel('panelCheque', '');
		mostrarPanel('panelEfectivo', 'formEfectivo\\:txtCuotaEfectivo');
		$('#formEfectivo\\:txtCuotaEfectivo').val('');
	} else if (tipoPagoVar == 2) {
		ocultarPanel('panelEfectivo', '');
		ocultarPanel('panelCheque', '');
		mostrarPanel('panelTarjeta', 'formTarjeta\\:txtValorTarjeta');
		$('#formTarjeta\\:txtTarjetaCredito').val('');
		$('#formTarjeta\\:txtVaucherCredito').val('');
		$('#formTarjeta\\:txtCuotaCredito').val('');
		PF('somBancoCreditoWV').selectValue(0);
	} else if (tipoPagoVar == 3) {
		ocultarPanel('panelTarjeta', '');
		ocultarPanel('panelEfectivo', '');
		mostrarPanel('panelCheque', 'formCheque\\:txtValorCheque');
		$('#formCheque\\:txtCuentaCheque').val('');
		$('#formCheque\\:txtNumeroCheque').val('');
		$('#formCheque\\:txtCuotaCheque').val('');
		PF('somBancoChequeWV').selectValue(0);
	} else if (tipoPagoVar.toUpperCase() == "PP") {
		guardarFacturaRC();
	}
}

function esq() {
	ocultarPanel('panelFormaPago');
	ocultarPanel('panelPtoEmision');
	ocultarPanel('panelVendedor');
	ocultarPanel('panelBuscarProducto');
	ocultarPanel('panelCantidadProducto');
	mostrarPanel('panelTabla');
	mostrarPanel('panelDatos');
}

function nuevaFactura() {
	nuevaFacturaRC();
}

function comprobarDatosFactura() {
	comprobarRC();
}

var producto;

function focusProducto() {
	if (producto != undefined) {
		producto.focus();
		setTimeout(function() {
			producto.focus();
		}, 300);
	}
}

function obtenerFilaProducto(registroProducto) {
	console.log(registroProducto);
	producto = document.getElementById(registroProducto);
	console.log(producto);
}
