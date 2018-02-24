package br.com.wtsyst.view.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.wtsyst.bean.Aluno;
import br.com.wtsyst.bean.Curso;
import br.com.wtsyst.controle.AlunoBCI;
import br.com.wtsyst.controle.CursoBCI;


@ManagedBean(name = "CursoMB")
@ViewScoped
public class CursoMB extends SpringBeanAutowiringSupport{
	
	private Curso bean;
	private List<Aluno> list;
	@Autowired
	private CursoBCI controle;
	
	@PostConstruct
	public void init() {
		
		this.bean = new Curso();
		this.list = controle.select();
	}

	
	
}
