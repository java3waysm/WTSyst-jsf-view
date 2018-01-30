package br.com.wtsyst.view.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

	public static void adicionarMensagemSucesso(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", mensagem);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		facesContext.addMessage(null, msg);
	}
	
	public static void adicionarMensagemErro(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ops. Algo deu errado!", mensagem);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		facesContext.addMessage(null, msg);
	}
}
