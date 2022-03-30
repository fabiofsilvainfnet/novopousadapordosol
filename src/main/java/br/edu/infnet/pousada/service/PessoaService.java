package br.edu.infnet.pousada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.pousada.dto.PessoaDTO;
import br.edu.infnet.pousada.exceptions.PessoaNotFoundEception;
import br.edu.infnet.pousada.model.Pessoa;
import br.edu.infnet.pousada.model.Usuario;
import br.edu.infnet.pousada.repository.PessoaRepository;
import br.edu.infnet.pousada.repository.UsuarioRepository;

@Service
@Transactional
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
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

	public Pessoa inserir(PessoaDTO pessoaDto) throws Exception {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setCpf(pessoaDto.getCpf());
		pessoa.setEmail(pessoaDto.getEmail());
		pessoa.setTelefone(pessoaDto.getTelefone());
		pessoa.setEndereco(pessoaDto.getEndereco());
		pessoa.setAtivo(true);
		pessoa = pessoaRepository.save(pessoa);		

		
		Usuario usuario = new Usuario();
		usuario.setLogin(pessoaDto.getLogin());
		usuario.setSenha(pessoaDto.getSenha());
		usuario.setPessoa(pessoa);
		
		usuario = usuarioRepository.save(usuario);

		pessoa.setUsuarios(usuarioRepository.findByPessoaId(pessoa.getId()));
		
		return pessoa;
		
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
