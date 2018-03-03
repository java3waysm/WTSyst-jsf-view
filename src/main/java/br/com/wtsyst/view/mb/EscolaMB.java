package br.com.wtsyst.view.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.wtsyst.bean.Escola;
import br.com.wtsyst.controle.EscolaBCI;

@ManagedBean(name ="EscolaMB")
@ViewScoped
public class EscolaMB extends SpringBeanAutowiringSupport {
	
	private Escola bean;
	private Escola alteraEscola;
	private List<Escola> list;
	private String labelCurso;
	
	@Autowired
	private EscolaBCI controle;
	
	@PostConstruct
	public void init() {
		this.bean =new Escola();
		this.alteraEscola =new Escola();
		this.list = controle.select();
		this.labelCurso  = "Selecione : ";
	}
	
	public void preparaUpdate(){
		this.bean = this.alteraEscola;	
	}
	
	public void mostraSelecaoCurso(){
		this.labelCurso = "Cursos Selecionado :";
	}
	
	public String getLabelCurso() {
		return labelCurso;
	}

	public void setLabelCurso(String labelCurso) {
		this.labelCurso = labelCurso;
	}
	
	public Escola getAlteraEscola() {
		return alteraEscola;
	}

	public void setAlteraEscola(Escola alteraEscola) {
		this.alteraEscola = alteraEscola;
	}

	public Escola getBean() {
		return bean;
	}
	
	public void setBean(Escola bean) {
		this.bean=bean;
	}
	
	public List<Escola> getList(){
		return list ;
	}

	public void setList(List<Escola>list) {
		this.list = list;
	}
	
	public void insert() {	
		this.controle.insert(this.bean);
		this.init();	
		FacesContext context = 
				FacesContext.getCurrentInstance();
		        context.addMessage(null,new FacesMessage("Sucesso","Escola cadastrada com sucesso!" ) );	
	}
}






