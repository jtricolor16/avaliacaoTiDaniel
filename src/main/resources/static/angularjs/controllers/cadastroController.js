(function(){
	
	'use strict';

	angular.module('DanielTiApp').controller('CadastroController', function($http, $scope, $log, $routeParams, $location){
		
		var self = this;

		self.pessoa={};
		
		self.pessoa.sexo="Masculino";
		
		self.pessoa.ativo=true;
		
		$scope.emailFormat = /\S+@\S+\.\S+/;
		
		self.carregarPessoa = function(){
			if($routeParams.id!=undefined){
				$http.get('/tiDaniel/carregarPessoa/' + $routeParams.id).
	    		success(function(results){
	    			$log.log(results);
	    			self.pessoa=results;
	    			self.pessoa.dataNascimento=new Date(self.pessoa.dataNascimento + ' 0:00:00');
	    		}).
	    		error(function(results){
	    			$log.log(results);
	    			self.message='Error!';
	    		})
			}
		}
		self.carregarPessoa();
		self.valorAtivo = function(){
			if(self.pessoa.ativo==true){
				self.pessoa.ativo=false;
			}else{
				self.pessoa.ativo=true;
			}
		}
		
		self.salvar = function(){
			$http.post('/tiDaniel/salvar', self.pessoa).
    		success(function(results){
    			$log.log(results);
    			if(results==true){
    				self.message='Success!'
    	    		$location.path("/tiDaniel");
    			}else{
    				self.message='Error!';
    			}
    			
    		}).
    		error(function(results){
    			$log.log(results);
    			self.message='Error!';
    		})
		}
		
		self.buscarPeloEmail = function(){
			$http.post("/tiDaniel/validaPessoaPorEmail", self.pessoa)
			.success(function(results){
				if(results==false){
					self.pessoa.email=undefined;
				}
			})
			.error(function(results){
				$log.log(results);
			})
		}
		
		self.buscarPeloCpf = function(){
			$http.post("/tiDaniel/validaPessoaPorCpf", self.pessoa)
			.success(function(results){
				if(results==false){
					self.pessoa.cpf=undefined;
				}
				
			})
			.error(function(results){
				$log.log(results);
			})
		}

	})

})()