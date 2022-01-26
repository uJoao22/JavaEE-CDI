package br.com.alura.alura_lib.factory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class JSFFactory {

	//Este método produzi um FacesContext
	@Produces
	//As informçaões que tem dentro deste método mudam acaba request, então ele possui um escopo de resquest
	@RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
}