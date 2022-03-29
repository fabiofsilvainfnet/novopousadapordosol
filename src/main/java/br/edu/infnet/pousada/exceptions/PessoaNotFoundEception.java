package br.edu.infnet.pousada.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pessoa não encontrada")
public class PessoaNotFoundEception extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
