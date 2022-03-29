package br.edu.infnet.pousada.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.pousada.exceptions.PessoaNotFoundEception;
import br.edu.infnet.pousada.model.Pessoa;
import br.edu.infnet.pousada.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Pessoa> buscar() {
		return pessoaRepository.findAll();
	}

	public Pessoa buscar(Integer id) {
		return pessoaRepository.findById(id)
				.orElseThrow(PessoaNotFoundEception::new);
	}
	
	public Pessoa buscar(String nome) {
		Optional<Pessoa> pessoa = pessoaRepository.findByNome(nome);
		if (pessoa.isPresent())
			return pessoa.get();
		else
			return null;
	}

	public Pessoa inserir(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);		
	}

	public Pessoa atualizar(Integer id, Pessoa pessoaNew) {
		Pessoa pessoaOld = buscar(id);
		pessoaOld.setNome(pessoaNew.getNome());
		pessoaOld.setCpf(pessoaNew.getCpf());
		pessoaOld.setEndereco(pessoaNew.getEndereco());
		pessoaOld.setTelefone(pessoaNew.getTelefone());
		pessoaOld.setAtivo(pessoaNew.getAtivo());
			
		return pessoaRepository.save(pessoaOld);
	}

	public void excluir(Integer id) {
		Pessoa pessoa = buscar(id);
		pessoaRepository.delete(pessoa);
	}

}
