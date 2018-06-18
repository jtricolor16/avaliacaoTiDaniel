package br.com.AvaliacaoTiDaniel.rules;

import java.sql.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.AvaliacaoTiDaniel.entities.Pessoa;
import br.com.AvaliacaoTiDaniel.repositories.PessoaRepository;

@Configuration
public class PessoaRules {

	public PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaRules(PessoaRepository pessoaRepository){
		this.pessoaRepository=pessoaRepository;
	}
	
	public boolean validaPessoa(Pessoa pessoa){
		if(this.validaAtivo(pessoa.getAtivo()) &&
		   this.validaCpf(pessoa.getCpf(), pessoa.getId()) &&
		   this.validaDataNascimento(pessoa.getDataNascimento()) &&
		   this.validaEmail(pessoa.getEmail(), pessoa.getId()) &&
		   this.validaNome(pessoa.getNome()) &&
		   this.validaSexo(pessoa.getSexo()) &&
		   this.validaEstadoCivil(pessoa.getEstadoCivil())){
			return true;
			
		}
		return false;
	}
	
	private boolean validaNome(String nome){
		if(StringUtils.isNotBlank(nome))
			return true;
		
		return false;
	}
	
	private boolean validaSexo(String sexo){
		if(StringUtils.isNotBlank(sexo))
			return true;
		
		return false;
	}
	
	private boolean validaEstadoCivil(String estadoCivil){
		if(StringUtils.isNotBlank(estadoCivil))
			return true;
		
		return false;
	}
	
	private boolean validaAtivo(Boolean ativo){
		if(ativo!=null)
			return true;
		return false;
	}
	
	private boolean validaDataNascimento(Date dataNascimento){
		if(dataNascimento!=null)
			return true;
		return false;
	}
	
	public boolean validaCpf(String cpf, Long id){
		if(this.validaCpfLength(cpf)==false){
			return false;
		}	
		if(id==null){
			return this.validaExistenciaCpf(cpf);
		}else{
			if(this.pessoaRepository.findByCpf(cpf)== null || this.pessoaRepository.findOne(id)== null){
				return true;
			}else if(this.pessoaRepository.findByCpf(cpf)!= null && this.pessoaRepository.findOne(id)!= null && this.pessoaRepository.findByCpf(cpf)==this.pessoaRepository.findOne(id)){
				return true;
			}else{
				return this.validaExistenciaCpf(cpf);
			}
		}
	}
	
	private boolean validaCpfLength(String cpf){
		if(cpf.length()==11)
			return true;
		else
			return false;
	}
	
	private boolean validaExistenciaCpf(String cpf){
		if(StringUtils.isNotBlank(cpf)){
			if(this.pessoaRepository.findByCpf(cpf)!=null){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
	
	public boolean validaEmail(String email, Long id){
		if(id==null){
			return this.validaExistenciaEmail(email);
		}else{
			if(this.pessoaRepository.findByEmail(email) == null || this.pessoaRepository.findOne(id)== null){
				return true;
			}else if(this.pessoaRepository.findByEmail(email) != null && this.pessoaRepository.findOne(id)!=null && this.pessoaRepository.findByEmail(email).getId()==this.pessoaRepository.findOne(id).getId()){
				return true;
			}else{
				return this.validaExistenciaEmail(email);
			}
		}
	}
	
	private boolean validaExistenciaEmail(String email){
		if(StringUtils.isNotBlank(email)){
			if(this.pessoaRepository.findByEmail(email)!=null){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
}
