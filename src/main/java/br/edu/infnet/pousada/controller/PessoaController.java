package br.edu.infnet.pousada.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.pousada.dto.PessoaDTO;
import br.edu.infnet.pousada.dto.RequestErrorsDTO;
import br.edu.infnet.pousada.exceptions.PessoaNotFoundEception;
import br.edu.infnet.pousada.model.Pessoa;
import br.edu.infnet.pousada.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/")
	public List<Pessoa> buscar() {
		return pessoaService.buscar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable(name = "id")  Integer id) {
		
		Pessoa pessoa = pessoaService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(pessoa);
	}
	
	@PostMapping
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<Pessoa> inserir(@Valid @RequestBody PessoaDTO pessoa) throws Exception {
		Pessoa pessoaNew = pessoaService.inserir(pessoa);
		
		if (pessoaNew != null) {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(pessoaNew);
		} else {
			return 	ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
	
	@PatchMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<Pessoa> atualizar(@PathVariable(name = "id") Integer id, @RequestBody Pessoa pessoa) {
		
		Pessoa pessoaNew = pessoaService.atualizar(id, pessoa);
		if (pessoaNew != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(pessoaNew);
		} else {
			throw new PessoaNotFoundEception();
		}
	}
	
	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:8080")
	public void excluir(@PathVariable(name = "id") Integer id) {
		pessoaService.excluir(id);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public RequestErrorsDTO handlePayloadException(MethodArgumentNotValidException ex) {
	    List<String> errors = new ArrayList<>();
	    RequestErrorsDTO dto = new RequestErrorsDTO();
	    
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.add(fieldName + " | " + errorMessage);
	    });
	    
	    dto.setErros(errors);
	    dto.setMensagemPrincipal("Foram identificados os seguintes erros nos parâmetros passados para a requisição.");
	    
	    return dto;
	}

}
