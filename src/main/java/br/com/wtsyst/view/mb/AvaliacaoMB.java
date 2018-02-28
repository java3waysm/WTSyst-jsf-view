package br.com.wtsyst.view.mb;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import br.com.wtsyst.bean.Avaliacao;
import br.com.wtsyst.controle.AvaliacaoBCI;



@ManagedBean(name = "AvaliacaoMB")
@ViewScoped
public class AvaliacaoMB extends SpringBeanAutowiringSupport {
	
	private Avaliacao beanAvaliacao;
	private List<Avaliacao> lstAvaliacao;
	private String labelCurso;
	private String labelAluno;
	
	@Autowired
	private AvaliacaoBCI controle;
	
	@PostConstruct
	public void init() {
		
		this.beanAvaliacao = new Avaliacao();
		this.lstAvaliacao = controle.select();
		this.labelCurso = "Selecione";
		this.labelAluno = "Selecione";
	}

	
	public void insert() {
		this.controle.insert(this.beanAvaliacao);
		this.init();
	}

	public void mostrarSelecaoCurso() {
		this.labelCurso = this.beanAvaliacao.getCurso().getNome();
	}
	
	public void mostrarSelecaoAluno() {
		this.labelAluno = this.beanAvaliacao.getAluno().getNome();
	}
	
	
//Metodos Gets e Sets
	
	public Avaliacao getBeanAvaliacao() {
		return beanAvaliacao;
	}


	public String getLabelAluno() {
		return labelAluno;
	}


	public void setLabelAluno(String labelAluno) {
		this.labelAluno = labelAluno;
	}


	public String getLabelCurso() {
		return labelCurso;
	}


	public void setLabelCurso(String labelCurso) {
		this.labelCurso = labelCurso;
	}


	public void setBeanAvaliacao(Avaliacao beanAvaliacao) {
		this.beanAvaliacao = beanAvaliacao;
	}


	public List<Avaliacao> getLstAvaliacao() {
		return lstAvaliacao;
	}


	public void setLstAvaliacao(List<Avaliacao> lstAvaliacao) {
		this.lstAvaliacao = lstAvaliacao;
	}
	
	
	
	
	
	
}
