package com.dhtmlx.demoapp.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.progress.open4gl.ConnectException;
import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.SystemErrorException;
import com.sinergitec.calendar.dao.InformeEjecutivoDaoImpl;
import com.sinergitec.calendar.dao.LocalDaoImpl;
import com.sinergitec.calendar.dao.OpOSDocsDaoImpl;
import com.sinergitec.calendar.model.CtUsuarioWeb;
import com.sinergitec.calendar.model.InfEjecutivo;
import com.sinergitec.calendar.model.OpOSDocs;

@Controller
@SessionAttributes("usuarioIniciado")
public class ReporteController {

	@RequestMapping(value = "/reporte", method = RequestMethod.GET)
	String Inicio(Model model, 
			@ModelAttribute("usuarioIniciado") CtUsuarioWeb usuarioWebCompania, 
			HttpServletResponse response) throws Open4GLException, IOException {
		
		/*Cookie compania = new Cookie("compania", usuarioWebCompania.getCtUsuaCompWeb().getcCveCia());
		Cookie cliente = new Cookie("cliente", usuarioWebCompania.getcCliente());
		response.addCookie(compania);
		response.addCookie(cliente);*/

		return "reporte";
	}

	@RequestMapping(value = "/BuscaSucursal", headers = "Accept=application/json")
	public @ResponseBody String BuscaSucursal(String cCveCia, String cCliente) 
			throws Open4GLException, IOException {

		LocalDaoImpl valor = new LocalDaoImpl();
		String lista = "";

		lista = new Gson().toJson(valor.listaLocal(cCveCia, cCliente));

		return lista;

	}

	@RequestMapping(value = "/ejecutivo", method = RequestMethod.GET)
	String Ejecutivo(Model model) throws Open4GLException, IOException {
		return "ejecutivo";
	}

	@RequestMapping(value = "/reporteEjecutivo", headers = "Accept=application/json")
	public @ResponseBody String Reporte(String cCveCia, 
			String cCliente, String cSucursal, Model model)	
					throws Open4GLException, IOException {

		InformeEjecutivoDaoImpl valor = new InformeEjecutivoDaoImpl();
		String lista = "";

		lista = new Gson().toJson(valor.listaInforme(cCveCia, cCliente, cSucursal));
		return lista;
	}

	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
	public ModelAndView downloadPDF(@RequestParam("sucursal") String sucursal, 
			@ModelAttribute("usuarioIniciado") CtUsuarioWeb usuarioWebCompania) 
					throws Open4GLException, IOException {
		
		// create some sample data
		InformeEjecutivoDaoImpl valor = new InformeEjecutivoDaoImpl();
		List<InfEjecutivo> listEjecutivo = new ArrayList<InfEjecutivo>();

		listEjecutivo = valor.listaInforme(usuarioWebCompania.getCtUsuaCompWeb().getcCveCia(),
				usuarioWebCompania.getcCliente(), sucursal);

		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("pdfView", "listBooks", listEjecutivo);
	}

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel(@RequestParam("sucursal") String sucursal, 
			@ModelAttribute("usuarioIniciado") CtUsuarioWeb usuarioWebCompania) 
					throws Open4GLException, IOException {

		// create some sample data
		InformeEjecutivoDaoImpl valor = new InformeEjecutivoDaoImpl();
		List<InfEjecutivo> listEjecutivo = new ArrayList<InfEjecutivo>();

		listEjecutivo = valor.listaInforme(usuarioWebCompania.getCtUsuaCompWeb().getcCveCia(),
				usuarioWebCompania.getcCliente(), sucursal);

		// return a view which will be resolved by an excel view resolver
		// Crea el excel de 0
		return new ModelAndView("excelView", "listBooks", listEjecutivo);

		// return a view which will be resolved by an excel view resolver
		// Utiliza una plantilla y crea el excel
		// return new ModelAndView("excelViewRW", "listBooks", listEjecutivo);
	}
	
	@RequestMapping(value = "/confirmaArchivo", headers = "Accept=application/json")
	public @ResponseBody String  confirmaArchivo(String cOServID,
			HttpServletResponse response, @ModelAttribute("usuarioIniciado") CtUsuarioWeb usuarioWebCompania) 
					throws ConnectException, SystemErrorException, Open4GLException {
		
		Integer iOServID = Integer.parseInt(cOServID);
		String resultado = null;
		
		try {

			// Instancia del modelo y del dao
			OpOSDocs documento = new OpOSDocs();
			OpOSDocsDaoImpl traerDocumento = new OpOSDocsDaoImpl();
			documento = traerDocumento.getOpOSDocs(usuarioWebCompania.getCtUsuaCompWeb().getcCveCia(), 
					iOServID, 1);
			
			if(documento.getbImagen() != null){
				resultado = "Si Existe Archivo Ligado";
				}
			} catch (IOException ex) {
			System.out.println(ex);
		}
		
		return new Gson().toJson(resultado);
	}

	@RequestMapping(value = "/getFile/{iOServID}", method=RequestMethod.GET)
	public @ResponseBody void  getFile(@PathVariable Integer iOServID,
			HttpServletResponse response, @ModelAttribute("usuarioIniciado") CtUsuarioWeb usuarioWebCompania) 
					throws ConnectException, SystemErrorException, Open4GLException {
		try {

			// Instancia del modelo y del dao
			OpOSDocs documento = new OpOSDocs();
			OpOSDocsDaoImpl traerDocumento = new OpOSDocsDaoImpl();
			documento = traerDocumento.getOpOSDocs(usuarioWebCompania.getCtUsuaCompWeb().getcCveCia(), 
					iOServID, 1);
			
			if(documento.getbImagen() != null){
				
				// get your file as InputStream
				InputStream pdf = new ByteArrayInputStream(documento.getbImagen());

				// copy it to response's OutputStream
				org.apache.commons.io.IOUtils.copy(pdf, response.getOutputStream());
				response.flushBuffer();
				}
			} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	/*c�digo Adriana*/
	
	@RequestMapping(value = "/incidencias", method=RequestMethod.GET)
	public String Incidencias(@ModelAttribute("usuarioIniciado") 
	CtUsuarioWeb usuarioWebCompania) throws Open4GLException, IOException {

		return "incidencias";

	}
	
	/* end c�digo Adriana*/
	
	@RequestMapping(value = "/incidencias/datos", headers = "Accept=application/json")
	public @ResponseBody String IncidenciasDatos(String cCveCia, 
			String cCliente) throws Open4GLException, IOException {
		
		//Instacia de las incidencias
		InformeEjecutivoDaoImpl incidencia = new InformeEjecutivoDaoImpl();
		
		String listaP = "";
		listaP = new Gson().toJson(incidencia.listaCalidad(cCveCia, cCliente));
		
		return listaP;

	}	
	
}
