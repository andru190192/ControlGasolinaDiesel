package ec.com.mariscalSucre.tesisMatriculacion.utils.service;

import static ec.com.mariscalSucre.tesisMatriculacion.utils.UtilsAplicacion.presentaMensaje;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@Service
public class ReporteServiceImpl implements ReporteService {

	private FacesContext facesContext;

	private <T> JasperPrint generadorReporte(String nombreReporte, Map<String, Object> parametros,
			List<T> listaReporte) {
		try {
			return JasperFillManager
					.fillReport(
							FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes") + "/"
									+ nombreReporte + ".jasper",
							parametros, new JRBeanCollectionDataSource(listaReporte));
		} catch (JRException e) {
			e.printStackTrace();
		}
		return new JasperPrint();
	}

	public <T> void generarReportePDF(List<T> listaReporte, Map<String, Object> parametros, String nombreReporte,
			String nombre) {
		facesContext = FacesContext.getCurrentInstance();
		if (listaReporte == null || listaReporte.isEmpty())
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "No hay datos para imprimir");
		else {
			// parametros.put("logo", leerImagen(null, getRutaLogo()));
			parametros.put("SUBREPORT_DIR",
					FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes") + "/");
			respondeServidor(generadorReporte(nombreReporte, parametros, listaReporte), nombre);
		}
	}

	public <T> File generarReportePDFFile(List<T> listaReporte, Map<String, Object> parametros, String nombreReporte,
			String nombre) {
		facesContext = FacesContext.getCurrentInstance();
		if (listaReporte == null || listaReporte.isEmpty())
			presentaMensaje(FacesMessage.SEVERITY_ERROR, "No hay datos para imprimir");
		else {
			// parametros.put("logo", leerImagen(null, getRutaLogo()));
			parametros.put("SUBREPORT_DIR",
					FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes") + "/");
			return responderFile(generadorReporte(nombreReporte, parametros, listaReporte), nombre);
		}
		return null;
	}

	public <T> void generarReporteXLS(List<T> listaReporte, Map<String, Object> parametros, String nombreReporte,
			String nombre) {
		facesContext = FacesContext.getCurrentInstance();
		try {
			// parametros.put("logo", leerImagen(null, getRutaImagen() +
			// "logo.png"));
			parametros.put("SUBREPORT_DIR",
					FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes") + "/");

			JasperPrint jasperPrint = generadorReporte(nombreReporte, parametros, listaReporte);

			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(nombre + ".xls"));
			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
			configuration.setOnePagePerSheet(false);
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(false);
			exporter.setConfiguration(configuration);
			exporter.exportReport();

			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-disposition", "attachment; filename=" + nombre + ".xls");

			ServletOutputStream servletStream = response.getOutputStream();

			File f = new File(nombre + ".xls");
			InputStream in = new FileInputStream(f);
			int bit = 256;
			while (bit >= 0) {
				bit = in.read();
				servletStream.write(bit);
			}

			servletStream.flush();
			servletStream.close();
			in.close();

			facesContext.responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generarReporteXLSSencillo(List<String> listaReporte, String nombreReporte, String UA, String C,
			String tituloRepor, int numCol) {
		facesContext = FacesContext.getCurrentInstance();
		try {
			respondeServidor(nombreReporte + ".xlsx", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generarReporteCSV(List<String> listaReporte, String nombreReporte) {
		facesContext = FacesContext.getCurrentInstance();
		try {
			respondeServidor(nombreReporte + ".csv", 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File responderFile(JasperPrint jasperPrint, String nombreReporte) {
		try {
			File file = File.createTempFile(nombreReporte, ".pdf");

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			exporter.setConfiguration(configuration);

			exporter.exportReport();
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void respondeServidor(String nombreReporte, int tipo) {
		ExternalContext econtext = facesContext.getExternalContext();
		try {
			HttpServletResponse response = (HttpServletResponse) econtext.getResponse();

			if (tipo == 1)
				response.setContentType("application/vnd.ms-excel");
			if (tipo == 2)
				response.setContentType("text/plain");
			response.addHeader("Content-disposition", "attachment; filename=" + nombreReporte);

			ServletOutputStream servletStream = response.getOutputStream();

			File f = new File(nombreReporte);
			InputStream in = new FileInputStream(f);
			int bit = 256;
			while (bit >= 0) {
				bit = in.read();
				servletStream.write(bit);
			}
			servletStream.flush();
			servletStream.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		facesContext.responseComplete();
	}

	public <T> void responderServidor(File archivo, String nombreReporte) {
		ExternalContext econtext = facesContext.getExternalContext();
		try {
			HttpServletResponse response = (HttpServletResponse) econtext.getResponse();
			response.setContentType("application/pdf");
			response.addHeader("Content-disposition", "attachment; filename=" + nombreReporte + ".pdf");

			ServletOutputStream servletStream = response.getOutputStream();

			InputStream in = new FileInputStream(archivo);
			int bit = 256;
			while (bit >= 0) {
				bit = in.read();
				servletStream.write(bit);
			}

			servletStream.flush();
			servletStream.close();
			in.close();

			facesContext.responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void respondeServidor(JasperPrint jasperPrint, String nombreReporte) {
		ExternalContext econtext = facesContext.getExternalContext();
		try {

			HttpServletResponse response = (HttpServletResponse) econtext.getResponse();
			response.setContentType("application/pdf");
			response.addHeader("Content-disposition", "attachment; filename=" + nombreReporte + ".pdf");

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			exporter.setConfiguration(configuration);

			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		facesContext.responseComplete();
	}
}