package br.com.wtsyst.view.mb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.wtsyst.bean.DataBean;
import br.com.wtsyst.view.util.JSFUtil;
import br.com.wtsyst.view.util.ReportUtil;
import net.sf.jasperreports.engine.JRException;

@ManagedBean(name = "ReportsMB")
public class ReportsMB {

	public void gerarRelatorioLista() {
		Map<String, Object> parametros = new LinkedHashMap<String, Object>();

		parametros.put("ReportTitle", "Exemplo de Lista");
		parametros.put("Author", "João das Neves");

		List<Object> list = new ArrayList<Object>();
		
		list.add(new DataBean("Thiago", "Brasil"));
		list.add(new DataBean("Clarismilton", "USA"));
		list.add(new DataBean("Wilton", "Thailandia"));
		list.add(new DataBean("Fernando", "Cuba"));
		list.add(new DataBean("Ricardo", "Canadá"));
		list.add(new DataBean("Pedro", "Colômbia"));

		try {
			ReportUtil.exportarPDF(parametros, list, "list.jasper");
		} catch (JRException e) {
			JSFUtil.adicionarMensagemErro("problemas no arquivo .jasper!");
			e.printStackTrace();
		} catch (IOException e) {
			JSFUtil.adicionarMensagemErro("Arquivo de relatório não encontrado!");
			e.printStackTrace();
		}
	}

	public void gerarRelatorioExemplo() {

		Map<String, Object> parametros = new LinkedHashMap<String, Object>();

		parametros.put("nome_usuario", "João das Neves");
		parametros.put("cpf_usuario", "789456123-12");
		parametros.put("n_contrato", "10004561265");

		try {
			ReportUtil.exportarPDF(parametros, null, "exemplo_1.jasper");
		} catch (JRException e) {
			JSFUtil.adicionarMensagemErro("problemas no arquivo .jasper!");
			e.printStackTrace();
		} catch (IOException e) {
			JSFUtil.adicionarMensagemErro("Arquivo de relatório não encontrado!");
			e.printStackTrace();
		}

	}

}
