package br.edu.infnet.novopousadapordosol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.novopousadapordosol.model.Pessoa;
import br.edu.infnet.novopousadapordosol.repository.PessoaRepository;
import br.edu.infnet.novopousadapordosol.utils.ValidacoesUtils;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ValidacoesUtils validacoesUtils;
	
	public List<Pessoa> buscar() {
		return pessoaRepository.findAll();
	}

	public Pessoa buscar(Integer id) {
		return pessoaRepository.getById(id);
	}

	public Pessoa inserir(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);		
	}

	public Pessoa atualizar(Integer id, Pessoa pessoa) {
		
		Pessoa pessoaAnterior = pessoaRepository.getById(id);
		
		pessoaAnterior.setNome(pessoa.getNome());
		pessoaAnterior.setCpf(pessoa.getCpf());
		pessoaAnterior.setEndereco(pessoa.getEndereco());
		pessoaAnterior.setTelefone(pessoa.getTelefone());
		pessoaAnterior.setAtivo(pessoa.getAtivo());
		
		return pessoaRepository.save(pessoa);
	}

	public void excluir(Integer id) {
		Pessoa pessoa = pessoaRepository.getById(id);
		pessoaRepository.delete(pessoa);
	}

}
