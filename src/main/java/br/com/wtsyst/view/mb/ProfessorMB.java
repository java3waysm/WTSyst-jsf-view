package br.com.wtsyst.view.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.wtsyst.bean.Professor;
import br.com.wtsyst.controle.ProfessorBCI;

@ManagedBean(name = "ProfessorMB")
@ViewScoped
public class ProfessorMB  extends SpringBeanAutowiringSupport {


	private Professor bean;
	private List<Professor> list;
	@Autowired
	private ProfessorBCI controle;
	
	@PostConstruct
	public void init() {
		
		this.bean = new Professor();
		this.list = controle.select();
	}

	public Professor getBean() {
		return bean;
	}

	public void setBean(Professor bean) {
		this.bean = bean;
	}

	public List<Professor> getList() {
		return list;
	}

	public void setList(List<Professor> list) {
		this.list = list;
	}
	
	public void insert() {
		this.controle.insert(this.bean);
		this.init();
	}
	
}
