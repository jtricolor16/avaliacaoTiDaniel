package br.com.AvaliacaoTiDaniel.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.AvaliacaoTiDaniel.entities.Pessoa;
import br.com.AvaliacaoTiDaniel.repositories.PessoaRepository;
import br.com.AvaliacaoTiDaniel.rules.PessoaRules;

@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;
	private PessoaRules pessoaRules;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository, PessoaRules pessoaRules){
		this.pessoaRepository=pessoaRepository;
		this.pessoaRules=pessoaRules;
	}
	
	public boolean salvar(Pessoa pessoa){
		try{
			if(this.pessoaRules.validaPessoa(pessoa)==true){
				this.pessoaRepository.save(pessoa);
				return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean editar(Pessoa pessoa){
		try{
			if(this.pessoaRules.validaPessoa(pessoa)==true){
				this.pessoaRepository.save(pessoa);
				return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean excluirPessoa(Long id){
		try{
			this.pessoaRepository.delete(id);;
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean excluirPessoas(Iterable<Pessoa>pessoas){
		try{
			this.pessoaRepository.delete(pessoas);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public Iterable<Pessoa> listarPessoas(){
		return this.pessoaRepository.findAllByOrderByNomeAsc();
	}
	
	public Pessoa buscaPorId(Long id){
		return this.pessoaRepository.findOne(id);
	}
	
	public Pessoa buscaPorCpf(String cpf){
		return this.pessoaRepository.findByCpf(cpf);
	}
	
	public List<Pessoa> buscaPorNome(String nome){
		return this.pessoaRepository.findByNomeContainingIgnoreCaseOrderByNome(nome);
	}
	
	public List<Pessoa> consultar(Pessoa pessoa){
		List<Pessoa>pessoas = new ArrayList<Pessoa>();
		if(StringUtils.isNotBlank(pessoa.getCpf()) && StringUtils.isNotBlank(pessoa.getNome())){
			pessoas=this.pessoaRepository.findByNomeContainingIgnoreCaseAndCpfOrderByNomeAsc(pessoa.getNome(), pessoa.getCpf());
		}else if(StringUtils.isNotBlank(pessoa.getCpf())){
			if(this.pessoaRepository.findByCpf(pessoa.getCpf())!=null)
			pessoas.add(this.pessoaRepository.findByCpf(pessoa.getCpf()));
		}else{
			pessoas=this.pessoaRepository.findByNomeContainingIgnoreCaseOrderByNome(pessoa.getNome());
		}
		
		return pessoas;
	}
	
	public boolean validaPorEmail(String email, Long id){
		return this.pessoaRules.validaEmail(email, id);
	}
	
	public boolean validaPorCpf(String cpf, Long id){
		return this.pessoaRules.validaCpf(cpf, id);
	}
	
	
}
