package br.edu.infnet.pousada.dto;

import java.util.List;

public class RequestErrorsDTO {

	private List<String> erros;
	private String mensagemPrincipal;

	public List<String> getErros() {
		return erros;
	}
	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	public String getMensagemPrincipal() {
		return mensagemPrincipal;
	}
	public void setMensagemPrincipal(String mensagemPrincipal) {
		this.mensagemPrincipal = mensagemPrincipal;
	}
	
}
