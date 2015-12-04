package ec.com.mariscalSucre.tesisMatriculacion.utils.service;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface ReporteService {

	public <T> void generarReportePDF(List<T> listaReporte, Map<String, Object> parametros, String nombreReporte,
			String nombre);

	public <T> File generarReportePDFFile(List<T> listaReporte, Map<String, Object> parametros, String nombreReporte,
			String nombre);

	public <T> void generarReporteXLS(List<T> listaReporte, Map<String, Object> parametros, String nombreReporte,
			String nombre);

	public void generarReporteXLSSencillo(List<String> listaReporte, String nombreReporte, String UA, String C,
			String tituloRepor, int numCol);

	public void generarReporteCSV(List<String> listaReporte, String nombreReporte);

	public <T> void responderServidor(File archivo, String nombreReporte);

}