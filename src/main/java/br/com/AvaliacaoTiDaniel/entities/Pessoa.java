package br.com.AvaliacaoTiDaniel.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import br.com.AvaliacaoTiDaniel.requestBodies.PessoaRequestBody;

@Entity
@Table(name="pessoa")
public class Pessoa {
	@Id
	@Column(name="pessoa_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="cpf", nullable=false)
	private String cpf;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="dataNascimento", nullable=false)
	private Date dataNascimento;
	
	@Column(name="sexo", nullable=false)
	private String sexo;
	
	@Column(name="estadoCivil", nullable=false)
	private String estadoCivil;
	
	@Column(name="ativo", nullable=false)
	private Boolean ativo;

	public Pessoa(){
		
	}
	
	public Pessoa(PessoaRequestBody pessoaRequestBody){
		this.setAtivo(pessoaRequestBody.ativo);
		this.setCpf(pessoaRequestBody.cpf);
		this.setDataNascimento(pessoaRequestBody.dataNascimento);
		this.setEmail(pessoaRequestBody.email);
		this.setEstadoCivil(pessoaRequestBody.estadoCivil);
		this.setId(pessoaRequestBody.id);
		this.setNome(pessoaRequestBody.nome);
		this.setSexo(pessoaRequestBody.sexo);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(StringUtils.isNotBlank(nome))
			this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(StringUtils.isNotBlank(email))
			this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		if(dataNascimento!=null)
			this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		if(StringUtils.isNotBlank(sexo))
			this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		if(StringUtils.isNotBlank(estadoCivil))
			this.estadoCivil = estadoCivil;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		if(ativo!=null)
			this.ativo = ativo;
	}
}
