package br.com.wtsyst.view.mb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.wtsyst.bean.DataBean;
import br.com.wtsyst.view.util.ReportUtil;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "RelatorioExemplo")
@ViewScoped
public class RelatorioExemplo {

	public void gerarLista() {
		List<Object> dataBeanList = new ArrayList<Object>();

		dataBeanList.add(new DataBean("Manisha", "India"));
		dataBeanList.add(new DataBean("Dennis Ritchie", "USA"));
		dataBeanList.add(new DataBean("V.Anand", "India"));
		dataBeanList.add(new DataBean("Shrinath", "California"));

		Map<String, Object> parametros = new LinkedHashMap<String, Object>();

		parametros.put("ReportTitle", "Exemplo de Lista");
		parametros.put("Author", "João das Neves");

		try {
			ReportUtil.exportarPDF(parametros, dataBeanList, "list.jasper");
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void gerarPdf() {
		Map<String, Object> parametros = new LinkedHashMap<String, Object>();

		parametros.put("nome_usuario", "Thiago F");
		parametros.put("n_contrato", "1522562");
		parametros.put("cpf_usuario", "001038411-13");

		try {
			ReportUtil.exportarPDF(parametros, null, "relatorio_1.jasper");
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
