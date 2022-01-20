package br.com.alura.alura_lib.tx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

//Sempre que uma nova anotação for criada, eu preciso definir duas coisas para ela:
//Onde essa anotação será usada, em uma class, em um método, atributo e etc
//Quando tempo essa anotação vai durar, se ela estará apenas no compilador, se estará no código e etc

//Usando a anotação abaixo para dizer que esta anotação Transacional está associada com o GerenciadorDeTransacao
@InterceptorBinding
//Definindo através do target, passando um array de elementType, que essa anotação será usada em um método e em uma class
@Target({ElementType.METHOD, ElementType.TYPE})
//Definindo que a anotação estará disponível apenas em tempo de execução
@Retention(RetentionPolicy.RUNTIME)
public @interface Transacional {

	
	
}