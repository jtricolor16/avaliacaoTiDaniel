package br.com.AvaliacaoTiDaniel.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.AvaliacaoTiDaniel.entities.Pessoa;


public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long>  {
	
	public List<Pessoa> findByNomeContainingIgnoreCaseOrderByNome(@Param("nome") String nome);
	public Pessoa findByCpf(@Param("cpf") String cpf);
	public Pessoa findByEmail(@Param("email") String email);
	public List<Pessoa> findAllByOrderByNomeAsc();
	public List<Pessoa> findByNomeContainingIgnoreCaseAndCpfOrderByNomeAsc(@Param("nome") String nome, @Param("cpf") String cpf);
}
