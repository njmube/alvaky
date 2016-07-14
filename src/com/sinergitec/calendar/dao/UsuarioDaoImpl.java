package com.sinergitec.calendar.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.progress.open4gl.BooleanHolder;
import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.ResultSetHolder;
import com.progress.open4gl.StringHolder;
import com.progress.open4gl.javaproxy.Connection;
import com.sinergitec.calendar.model.CtUsuaCompWeb;
import com.sinergitec.calendar.model.CtUsuarioWeb;
import com.sinergitec.calendar.util.DBConexion;
import com.sinergitec.calendar.util.VectorResultSet;

import alvaky.sinergitec.appserver.yacatmto;

public class UsuarioDaoImpl {
	
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public String Inserta(String cUsuario, CtUsuarioWeb obj_ctUsuarioWeb) throws Open4GLException, IOException{
		
		List<CtUsuarioWeb> Lista = new ArrayList<CtUsuarioWeb>();
		Lista.add(obj_ctUsuarioWeb);
		
		Vector vecTabla1, vecRow1;
		vecTabla1 = new Vector();

		// Variables para guardar errores
		StringHolder texto = new StringHolder();
		BooleanHolder error = new BooleanHolder();
		
		// Conexion a la base de datos
		Connection conexion = new DBConexion().getConnection();
		yacatmto app = new yacatmto(conexion);
		
		for (CtUsuarioWeb objCtUsuarioWeb : Lista) {
			vecRow1 = objCtUsuarioWeb.getVectorDatos();
			vecTabla1.add(vecRow1);
		}
		
		ResultSetHolder ttCtUsuarioWeb = new ResultSetHolder(new VectorResultSet(vecTabla1));
		
		try {
			
			app.as_ctUsuario_Inserta(cUsuario, ttCtUsuarioWeb, error, texto);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			app._release();
			DBConexion.closeConnection(conexion);
		}
		
		return texto.getStringValue();
	}
	
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public String Actualiza(String cUsuario, CtUsuarioWeb obj_ctUsuarioWeb) throws Open4GLException, IOException{
		
		List<CtUsuarioWeb> Lista = new ArrayList<CtUsuarioWeb>();
		Lista.add(obj_ctUsuarioWeb);
		
		Vector vecTabla1, vecRow1;
		vecTabla1 = new Vector();

		// Variables para guardar errores
		StringHolder texto = new StringHolder();
		BooleanHolder error = new BooleanHolder();
		
		// Conexion a la base de datos
		Connection conexion = new DBConexion().getConnection();
		yacatmto app = new yacatmto(conexion);
		
		for (CtUsuarioWeb objCtUsuarioWeb : Lista) {
			vecRow1 = objCtUsuarioWeb.getVectorDatos();
			vecTabla1.add(vecRow1);
		}
		
		ResultSetHolder ttCtUsuarioWebMod = new ResultSetHolder(new VectorResultSet(vecTabla1));
		
		try {
			
			//app.as_ctUsuario_Actualiza(cUsuario, ttCtUsuarioWebMod, error, texto);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			app._release();
			DBConexion.closeConnection(conexion);
		}

		return texto.getStringValue();
	}
	
	@SuppressWarnings("static-access")
	public List<CtUsuarioWeb> ListaUsuarioWeb(Boolean lActivo) throws Open4GLException, IOException{

		// Variables para guardar errores
		StringHolder texto = new StringHolder();
		BooleanHolder error = new BooleanHolder();
		
		// Tabla Temporal
		ResultSetHolder tt_ctUsuario = new ResultSetHolder();
		
		//Lista
		List<CtUsuarioWeb> listaUsuarios = new ArrayList<CtUsuarioWeb>();
		
		// Conexion a la base de datos
		Connection conexion = new DBConexion().getConnection();
		yacatmto app = new yacatmto(conexion);
		
		try {
			
			app.as_ctUsuarioWeb_Carga(lActivo, tt_ctUsuario, error, texto);
			ResultSet rs_tt_ctUsuarioWeb = tt_ctUsuario.getResultSetValue();
			
			while(rs_tt_ctUsuarioWeb.next()){
				
				CtUsuarioWeb obj = new CtUsuarioWeb();
				obj.setcUsuarioWeb(rs_tt_ctUsuarioWeb.getString("cUsuarioWeb"));
				obj.setcPassword(rs_tt_ctUsuarioWeb.getString("cPassword"));
				obj.setlActivo(rs_tt_ctUsuarioWeb.getBoolean("lActivo"));
				obj.setcCliente(rs_tt_ctUsuarioWeb.getString("cCliente"));
				obj.setDtCreado(rs_tt_ctUsuarioWeb.getDate("dtCreado").toString());
				obj.setDtModificado(rs_tt_ctUsuarioWeb.getDate("dtModificado").toString());
				obj.setcUsuario(rs_tt_ctUsuarioWeb.getString("cUsuario"));
				obj.setcNombre(rs_tt_ctUsuarioWeb.getString("cNombre"));
				
				//Para llenar el objeto de ctUsuCompWeb
				CtUsuaCompWeb objUsuaCompWeb = new CtUsuaCompWeb();
				objUsuaCompWeb.setcCveCia(rs_tt_ctUsuarioWeb.getString("cCveCia"));
				/*objUsuaCompWeb.setlActivo(rs_tt_ctUsuarioWeb.getBoolean("lActivo"));
				objUsuaCompWeb.setDtCreado(rs_tt_ctUsuarioWeb.getDate("dtCreado").toString());
				objUsuaCompWeb.setDtModificado(rs_tt_ctUsuarioWeb.getDate("dtModificado").toString());
				objUsuaCompWeb.setcUsuario(rs_tt_ctUsuarioWeb.getString("cUsuario"));*/
				
				obj.setCtUsuaCompWeb(objUsuaCompWeb);
				obj.setError(error.getBooleanValue());
				obj.setErrorTexto(texto.getStringValue());
				obj.setId(rs_tt_ctUsuarioWeb.getBytes("id"));
				
				listaUsuarios.add(obj);
				
			}
			
			System.out.println(texto.getStringValue());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Este error imprime: "+e);
		} finally {
			app._release();
			DBConexion.closeConnection(conexion);
		}
		
		return listaUsuarios;
	}
	
	@SuppressWarnings("static-access")
	public CtUsuarioWeb GetUsuarioWeb(String cUsuario) throws Open4GLException, IOException{
		
		// Conexion a la base de datos
		Connection conexion = new DBConexion().getConnection();
		yacatmto app = new yacatmto(conexion);

		// Variables para guardar errores
		StringHolder texto = new StringHolder();
		BooleanHolder error = new BooleanHolder();
		return null;
	}
	
	@SuppressWarnings("static-access")
	public String Borra(String cUsuario, String cUsuarioWeb) throws Open4GLException, IOException{
		
		// Conexion a la base de datos
		Connection conexion = new DBConexion().getConnection();
		yacatmto app = new yacatmto(conexion);

		// Variables para guardar errores
		StringHolder texto = new StringHolder();
		BooleanHolder error = new BooleanHolder();
		
		try {
			
			//app.as_ctUsuario_Borra(cUsuario, tt_Viejos, error, texto);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			app._release();
			DBConexion.closeConnection(conexion);
		}
		
		return null;
	}
	
	@SuppressWarnings("static-access")
	public CtUsuarioWeb Valida(String cUsuario, String cPassword) throws Open4GLException, IOException{
		
		//Conexion a la base de datos
		Connection conexion = new DBConexion().getConnection();
		yacatmto app = new yacatmto(conexion);
		
		//Variables para guardar errores
		StringHolder texto  = new StringHolder();
		BooleanHolder error  = new BooleanHolder();
		
		//Variables para compa�ia y cliente
		StringHolder compania  = new StringHolder();
		StringHolder cliente  = new StringHolder();

		try {
			app.as_AccesoWeb_Carga(cUsuario, cPassword, compania, cliente, error, texto);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			app._release();
			conexion.finalize();
		}
		
		//Creando Objeto de ctUsuarioWeb
		CtUsuarioWeb usuarioWeb = new CtUsuarioWeb();
		CtUsuaCompWeb usuarioWebCompania = new CtUsuaCompWeb();
		
		usuarioWebCompania.setcCveCia(compania.getStringValue());
		usuarioWeb.setCtUsuaCompWeb(usuarioWebCompania);
		usuarioWeb.setcCliente(cliente.getStringValue());
		usuarioWeb.setError(Boolean.parseBoolean(error.getValue().toString()));
		usuarioWeb.setErrorTexto(texto.getStringValue());
		
		return usuarioWeb;
	}
}
