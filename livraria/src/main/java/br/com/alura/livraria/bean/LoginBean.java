package br.com.alura.livraria.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.alura_lib.helper.MessageHelper;
import br.com.alura.livraria.dao.UsuarioDao;
import br.com.alura.livraria.modelo.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	
	//Usando o @Inject para dizer que a linha abaixo é uma dependencia dessa class, e que o CDI deve injeta-la
	//@Inject
	private UsuarioDao usuarioDao;
	private FacesContext context;
	private MessageHelper helper;
	
	//Fazendo a injeção de dependencias através do construtor da classe ou de um método inicializador, que recebemm todas as 
	//dependencias da class
	//A anotação Inject faz com que o CDI busque alguem pelo código que possa produzir os atributos abaixo, no caso vai encontrar
	//a class JSFFactory que produziz FacesContext
	@Inject
	public LoginBean(UsuarioDao usuarioDao, FacesContext context, MessageHelper helper) {
		this.usuarioDao = usuarioDao;
		this.context = context;
		this.helper = helper;
	}


	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
		System.out.println("fazendo login do usuario " + this.usuario.getEmail());
		
		boolean existe = usuarioDao.existe(this.usuario);
		if(existe ) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		
		helper.onFlash().addMessage(new FacesMessage("Usuário não encontrado"));
		
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	 }
}