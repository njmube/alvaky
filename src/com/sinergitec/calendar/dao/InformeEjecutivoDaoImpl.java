package com.sinergitec.calendar.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

//import com.progress.open4gl.BooleanHolder;
import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.ResultSetHolder;
//import com.progress.open4gl.StringHolder;
import com.progress.open4gl.javaproxy.Connection;
import com.sinergitec.calendar.model.InfEjecutivo;
import com.sinergitec.calendar.util.DBConexion;

import alvaky.sinergitec.appserver.yacatmto;

public class InformeEjecutivoDaoImpl {
	
	@SuppressWarnings("static-access")
	public List<InfEjecutivo> listaInforme (String cCveCia, String cCliente) throws Open4GLException, IOException{
		
		//Conexion a la base de datos
		Connection conexion = new DBConexion().getConnection();
		yacatmto app = new yacatmto(conexion);

		//Variables para guardar errores
		/*StringHolder  texto  = new StringHolder();
		BooleanHolder error  = new BooleanHolder();*/
		
		//Lista que almacena la informacion obtenida
		List<InfEjecutivo> informe_List = new ArrayList<InfEjecutivo>();
		
		//Tabla temporal que almacena los resultados
		ResultSetHolder ttReporte  = new ResultSetHolder();
		
		try {
			
			//Transformar de String a Gregorian Calendar
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date = df.parse("02/01/2016");
			GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
			cal.setTime(date);
			
			Date fin = df.parse("02/29/2016");
			GregorianCalendar fin2 = (GregorianCalendar) Calendar.getInstance();
			fin2.setTime(fin);
			
			app.as_InfEjecutivo_rep(cCveCia, cal, fin2, cCliente, ttReporte);
			//System.out.println(texto.getValue().toString());

			ResultSet rs_tt_Reporte = ttReporte.getResultSetValue();
			
			while(rs_tt_Reporte.next()){
				
				InfEjecutivo obj = new InfEjecutivo();
				
				obj.setiPartida(rs_tt_Reporte.getInt("iPartida"));
				obj.setiOservID(rs_tt_Reporte.getInt("iOservID"));
				obj.setcSucursal(rs_tt_Reporte.getString("cSucursal"));
				obj.setcNomSuc(rs_tt_Reporte.getString("cNomSuc"));
				obj.setDtFechaV(rs_tt_Reporte.getDate("dtFechaV"));
				obj.setDePHEnt(rs_tt_Reporte.getDouble("dePHEnt"));
				obj.setDePHFilt(rs_tt_Reporte.getDouble("dePHFilt"));
				obj.setDePHUV(rs_tt_Reporte.getDouble("dePHUV"));
				obj.setDeSTDEnt(rs_tt_Reporte.getDouble("deSTDEnt"));
				obj.setDeSTDFilt(rs_tt_Reporte.getDouble("deSTDFilt"));
				obj.setDeSTDUV(rs_tt_Reporte.getDouble("deSTDUV"));
				obj.setDeDurEnt(rs_tt_Reporte.getDouble("deDurEnt"));
				obj.setDeDurFilt(rs_tt_Reporte.getDouble("deDurFilt"));
				obj.setDeDurUV(rs_tt_Reporte.getDouble("deDurUV"));
				obj.setDeCloroEnt(rs_tt_Reporte.getDouble("deCloroEnt"));
				obj.setDeCloroFilt(rs_tt_Reporte.getDouble("deCloroFilt"));
				obj.setDeCloroUV(rs_tt_Reporte.getDouble("deCloroUV"));
				obj.setDeAlcEnt(rs_tt_Reporte.getDouble("deAlcEnt"));
				obj.setDeAlcFilt(rs_tt_Reporte.getDouble("deAlcFilt"));
				obj.setDeAlcUV(rs_tt_Reporte.getDouble("deAlcUV"));
				obj.setDeConsSed(rs_tt_Reporte.getDouble("deConsSed"));
				obj.setDeConsSal(rs_tt_Reporte.getDouble("deConsSal"));
				obj.setDeConsSalP(rs_tt_Reporte.getDouble("deConsSalP"));
				obj.setDeConsCarb(rs_tt_Reporte.getDouble("DeConsCarb"));
				obj.setcFiltCarb(rs_tt_Reporte.getString("cFiltCarb"));
				obj.setcFiltMemb(rs_tt_Reporte.getString("cFiltMemb"));
				obj.setcPRevHidro(rs_tt_Reporte.getString("cPRevHidro"));
				obj.setcPRevPSist(rs_tt_Reporte.getString("cPRevPSist"));
				obj.setcPRevUV(rs_tt_Reporte.getString("cPRevUV"));
				obj.setcPRevDepAgua(rs_tt_Reporte.getString("cPRevDepAgua"));
				obj.setcOtRefacc(rs_tt_Reporte.getString("cOtRefacc"));
				obj.setDeORCant(rs_tt_Reporte.getDouble("deORCant"));
				obj.setcAcciones(rs_tt_Reporte.getString("cAcciones"));
				obj.setcRecomienda(rs_tt_Reporte.getString("cRecomienda"));
				obj.setcHoraEntrada(rs_tt_Reporte.getString("cHoraEntrada"));
				obj.setcHoraSalida(rs_tt_Reporte.getString("cHoraSalida"));
				obj.setcTiempoTot(rs_tt_Reporte.getString("cTiempoTot"));
				obj.setcEvalResp(rs_tt_Reporte.getString("cEvalResp"));
				obj.setcEvalSol(rs_tt_Reporte.getString("cEvalSol"));
				obj.setcEvalLimp(rs_tt_Reporte.getString("cEvalLimp"));
				obj.setcEvalAct(rs_tt_Reporte.getString("cEvalAct"));
				obj.setcEvalApar(rs_tt_Reporte.getString("cEvalApar"));
				obj.setcNumCotiza(rs_tt_Reporte.getString("cNumCotiza"));
				obj.setDtFechaCot(rs_tt_Reporte.getDate("dtFechaCot"));
				obj.setcEstadoCot(rs_tt_Reporte.getString("cEstadoCot"));
				obj.setDtFechaRCot(rs_tt_Reporte.getDate("dtFechaRCot"));
				
				informe_List.add(obj);				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally {
			app._release();
			conexion.finalize();
		}
		
		return informe_List;
	}
	
	@SuppressWarnings("static-access")
	public List<InfEjecutivo> listaInforme (String cCveCia, String cCliente, String cSucursal) throws Open4GLException, IOException{
		
		//Conexion a la base de datos
		Connection conexion = new DBConexion().getConnection();
		yacatmto app = new yacatmto(conexion);

		//Variables para guardar errores
		/*StringHolder  texto  = new StringHolder();
		BooleanHolder error  = new BooleanHolder();*/
		
		//Lista que almacena la informacion obtenida
		List<InfEjecutivo> informe_List = new ArrayList<InfEjecutivo>();
		
		//Tabla temporal que almacena los resultados
		ResultSetHolder ttReporte  = new ResultSetHolder();
		
		try {
			
			/*//Transformar de String a Gregorian Calendar
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date date = df.parse("02/01/2016");
			GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
			cal.setTime(date);
			
			Date fin = df.parse("02/29/2016");
			GregorianCalendar fin2 = (GregorianCalendar) Calendar.getInstance();
			fin2.setTime(fin);*/
			
			app.as_InfEjecutivo_repV3(cCveCia, cCliente, cSucursal, ttReporte);
			//System.out.println(texto.getValue().toString());

			ResultSet rs_tt_Reporte = ttReporte.getResultSetValue();
			
			while(rs_tt_Reporte.next()){
				
				InfEjecutivo obj = new InfEjecutivo();
				
				obj.setiPartida(rs_tt_Reporte.getInt("iPartida"));
				obj.setiOservID(rs_tt_Reporte.getInt("iOservID"));
				obj.setcSucursal(rs_tt_Reporte.getString("cSucursal"));
				obj.setcNomSuc(rs_tt_Reporte.getString("cNomSuc"));
				obj.setDtFechaV(rs_tt_Reporte.getDate("dtFechaV"));
				obj.setDePHEnt(rs_tt_Reporte.getDouble("dePHEnt"));
				obj.setDePHFilt(rs_tt_Reporte.getDouble("dePHFilt"));
				obj.setDePHUV(rs_tt_Reporte.getDouble("dePHUV"));
				obj.setDeSTDEnt(rs_tt_Reporte.getDouble("deSTDEnt"));
				obj.setDeSTDFilt(rs_tt_Reporte.getDouble("deSTDFilt"));
				obj.setDeSTDUV(rs_tt_Reporte.getDouble("deSTDUV"));
				obj.setDeDurEnt(rs_tt_Reporte.getDouble("deDurEnt"));
				obj.setDeDurFilt(rs_tt_Reporte.getDouble("deDurFilt"));
				obj.setDeDurUV(rs_tt_Reporte.getDouble("deDurUV"));
				obj.setDeCloroEnt(rs_tt_Reporte.getDouble("deCloroEnt"));
				obj.setDeCloroFilt(rs_tt_Reporte.getDouble("deCloroFilt"));
				obj.setDeCloroUV(rs_tt_Reporte.getDouble("deCloroUV"));
				obj.setDeAlcEnt(rs_tt_Reporte.getDouble("deAlcEnt"));
				obj.setDeAlcFilt(rs_tt_Reporte.getDouble("deAlcFilt"));
				obj.setDeAlcUV(rs_tt_Reporte.getDouble("deAlcUV"));
				obj.setDeConsSed(rs_tt_Reporte.getDouble("deConsSed"));
				obj.setDeConsSal(rs_tt_Reporte.getDouble("deConsSal"));
				obj.setDeConsSalP(rs_tt_Reporte.getDouble("deConsSalP"));
				obj.setDeConsCarb(rs_tt_Reporte.getDouble("DeConsCarb"));
				obj.setcFiltCarb(rs_tt_Reporte.getString("cFiltCarb"));
				obj.setcFiltMemb(rs_tt_Reporte.getString("cFiltMemb"));
				obj.setcPRevHidro(rs_tt_Reporte.getString("cPRevHidro"));
				obj.setcPRevPSist(rs_tt_Reporte.getString("cPRevPSist"));
				obj.setcPRevUV(rs_tt_Reporte.getString("cPRevUV"));
				obj.setcPRevDepAgua(rs_tt_Reporte.getString("cPRevDepAgua"));
				obj.setcOtRefacc(rs_tt_Reporte.getString("cOtRefacc"));
				obj.setDeORCant(rs_tt_Reporte.getDouble("deORCant"));
				obj.setcAcciones(rs_tt_Reporte.getString("cAcciones"));
				obj.setcRecomienda(rs_tt_Reporte.getString("cRecomienda"));
				obj.setcHoraEntrada(rs_tt_Reporte.getString("cHoraEntrada"));
				obj.setcHoraSalida(rs_tt_Reporte.getString("cHoraSalida"));
				obj.setcTiempoTot(rs_tt_Reporte.getString("cTiempoTot"));
				obj.setcEvalResp(rs_tt_Reporte.getString("cEvalResp"));
				obj.setcEvalSol(rs_tt_Reporte.getString("cEvalSol"));
				obj.setcEvalLimp(rs_tt_Reporte.getString("cEvalLimp"));
				obj.setcEvalAct(rs_tt_Reporte.getString("cEvalAct"));
				obj.setcEvalApar(rs_tt_Reporte.getString("cEvalApar"));
				obj.setcNumCotiza(rs_tt_Reporte.getString("cNumCotiza"));
				obj.setDtFechaCot(rs_tt_Reporte.getDate("dtFechaCot"));
				obj.setcEstadoCot(rs_tt_Reporte.getString("cEstadoCot"));
				obj.setDtFechaRCot(rs_tt_Reporte.getDate("dtFechaRCot"));
				
				informe_List.add(obj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}finally {
			app._release();
			conexion.finalize();
		}
		
		return informe_List;
	}

}