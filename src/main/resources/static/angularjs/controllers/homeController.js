(function(){

	'use strict';

	angular.module('DanielTiApp').controller('HomeController', function($scope, $http, $log, $location, $localStorage){
		
		var self=this;
		
		self.pessoas=[];
		
		self.pessoasRemovidas=[];
		
		self.pessoa={};
		
		self.pessoaConsulta={};
		
		self.carregarPessoas = function(){
			$http.get('/tiDaniel/listarPessoas').
    		success(function(results){
    			self.pessoas=results;
    			$log.log(results);
    		}).
    		error(function(results){
    			$log.log(results);
    		})
		}
		
		self.carregarPessoas();
		
		self.consultar = function(){
			$http.post("/tiDaniel/consultar", self.pessoaConsulta).
			success(function(results){
				self.pessoas=results;
				self.pessoaConsulta={};
    			$log.log(results);
			}).
			error(function(results){
				$log.log(results);
			})
		}
		
		self.desmarcarTodos = function(){
			for(var i=0; i<self.pessoasRemovidas.length; i++){
				delete(self.pessoas[i].selected);
				delete(self.pessoasRemovidas[i].selected);
			}
		}
		
		self.excluir = function(pessoa, indice){
			self.desmarcarTodos();
			$http.delete('/tiDaniel/excluir/' + pessoa.id).
    		success(function(results){
    			if(results==true){
    				$log.log(results);
    				self.pessoasRemovidas=[];
        			self.carregarPessoas();
        			self.message='Pessoa excluída com sucesso!';
    			}else{
    				self.message='Erro ao excluir pessoa!';
    			}
    		}).
    		error(function(results){
    			$log.log(results);
    			self.message='Erro ao excluir pessoa!';
    		})
		}
		
		self.adicionarTudoParaRemocao = function(){
			if(self.pessoas.select==true){
				self.pessoasRemovidas=[];
				for(var i=0; i<self.pessoas.length; i++){
					self.pessoasRemovidas.push(self.pessoas[i]);
					self.pessoas[i].selected=true;
				}
				self.pessoas.select=true;
			}else{
				self.desmarcarTodos();
				self.pessoas.select=false;
				self.pessoasRemovidas=[];
			}
		}
		
		self.adicionarPessoaParaRemocao = function(pessoa){
			if(self.pessoasRemovidas.indexOf(pessoa)!=-1){
				self.pessoasRemovidas.splice(self.pessoasRemovidas.indexOf(pessoa), 1);
			}else{
				self.pessoasRemovidas.push(pessoa);
			}
		}
		
		
		self.excluirSelecionados = function(){
			self.desmarcarTodos();
			$http.post('/tiDaniel/excluirPorLote', self.pessoasRemovidas).
    		success(function(results){
    			if(results==true){
    				$log.log(results);
        			self.message='Sucesso ao excluir pessoas!';
        			self.pessoasRemovidas=[];
        			self.carregarPessoas();
    			}else{
    				self.message='Erro ao excluir pessoas!';
    			}
    		}).
    		error(function(results){
    			$log.log(results);
    			self.message='Erro ao excluir pessoas!';
    		})
		}
		
		self.editar = function(id){
			$location.path("/tiDaniel/editar/" + id);
		}

	})	

})()