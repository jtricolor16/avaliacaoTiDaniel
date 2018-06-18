package br.com.AvaliacaoTiDaniel.restControllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.AvaliacaoTiDaniel.entities.Pessoa;
import br.com.AvaliacaoTiDaniel.requestBodies.PessoaRequestBody;
import br.com.AvaliacaoTiDaniel.services.PessoaService;

@RestController
public class PessoaController {

	public PessoaService pessoaService;
	
	public PessoaController(PessoaService pessoaService){
		this.pessoaService=pessoaService;
	}
	
	
	@PostMapping("/salvar")
	public ResponseEntity<Boolean> salvar(@RequestBody PessoaRequestBody pessoaRequestBody){
		return ResponseEntity.ok(this.pessoaService.salvar(new Pessoa(pessoaRequestBody)));
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Boolean> editar(@RequestBody PessoaRequestBody pessoaRequestBody){
		return ResponseEntity.ok(this.pessoaService.editar(new Pessoa(pessoaRequestBody)));
	}
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Boolean> excluir(@PathVariable Long id){
		return ResponseEntity.ok(this.pessoaService.excluirPessoa(id));
	}
	
	@PostMapping("/excluirPorLote")
	public ResponseEntity<Boolean>excluirPorLote(@RequestBody List<Pessoa> pessoas){
		return ResponseEntity.ok(this.pessoaService.excluirPessoas(pessoas));
	}
	
	@GetMapping("/listarPessoas")
	public ResponseEntity<Iterable<Pessoa>> listarPessoas(){
		return ResponseEntity.ok(this.pessoaService.listarPessoas());
	}
	
	@PostMapping("/validaPessoaPorCpf")
	public ResponseEntity<Boolean> buscarPessoaPorCpf(@RequestBody PessoaRequestBody pessoaRequestBody){
		return ResponseEntity.ok(this.pessoaService.validaPorCpf(new Pessoa(pessoaRequestBody).getCpf(), new Pessoa(pessoaRequestBody).getId()));
	}
	
	@PostMapping("/validaPessoaPorEmail")
	public ResponseEntity<Boolean> buscarPessoaPorEmail(@RequestBody PessoaRequestBody pessoaRequestBody){
		return ResponseEntity.ok(this.pessoaService.validaPorEmail(new Pessoa(pessoaRequestBody).getEmail(), new Pessoa(pessoaRequestBody).getId()));
	}
	
	@PostMapping("/consultar")
	public ResponseEntity<List<Pessoa>> consultar(@RequestBody PessoaRequestBody pessoaRequestBody){
		return ResponseEntity.ok(this.pessoaService.consultar(new Pessoa(pessoaRequestBody)));
	}
	
	@GetMapping("/carregarPessoa/{id}")
	public ResponseEntity<Pessoa> carregarPessoaPorId(@PathVariable Long id){
		return ResponseEntity.ok(this.pessoaService.buscaPorId(id));
	}
	
}
