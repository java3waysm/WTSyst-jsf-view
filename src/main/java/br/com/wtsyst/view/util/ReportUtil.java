package br.com.wtsyst.view.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportUtil {
	private static final String PATH = "/resources/reports/";
	
	public static void exportarPDF(Map<String, Object> parametros, List<Object> list, String file) throws JRException, IOException {		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(PATH+file));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros,
				new JRBeanCollectionDataSource(list));
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
		ServletOutputStream stream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

}
