package br.edu.infnet.novopousadapordosol.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.novopousadapordosol.model.Pessoa;
import br.edu.infnet.novopousadapordosol.service.PessoaService;

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
	public Pessoa buscar(@PathVariable(name = "id")  Integer id) {
		return pessoaService.buscar(id);
	}
	
	@PostMapping
	public Pessoa inserir(@RequestBody Pessoa pessoa) {
		return pessoaService.inserir(pessoa);
	}
	
	@PatchMapping("/{id}")
	public Pessoa atualizar(@PathVariable(name = "id") Integer id, @RequestBody Pessoa pessoa) {
		return pessoaService.atualizar(id, pessoa);
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable(name = "id") Integer id) {
		pessoaService.excluir(id);
	}
	
}
