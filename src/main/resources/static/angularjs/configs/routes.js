(function(){
	
	'use strict';

	angular.module('DanielTiApp').config(function($routeProvider, $locationProvider) {
	    
		$locationProvider.html5Mode({
  			enabled: true,
  			requireBase: false
		});


	    $routeProvider
	    
	    .when("/tiDaniel", {
	        templateUrl : "/tiDaniel/views/home.html",
	        controller: 'HomeController',
	        controllerAs: 'hc'
	    })

	    .when("/tiDaniel/cadastrar", {
	        templateUrl : "views/cadastro.html",
	        controller: 'CadastroController',
	        controllerAs: 'cc'
	    })

	     .when("/tiDaniel/:id", {
	    	 templateUrl : "views/cadastro.html",
		     controller: 'CadastroController',
	         controllerAs:'cc'
	     });

	    // .when("/configuration", {
	    // 	templateUrl:"/static/pages/configuration.html",
	    // 	controller:'ConfigController',
	    // 	controllerAs: 'cc'
	    // })

	    // .when("/edit", {
	    // 	templateUrl: "/static/pages/user.html",
	    // 	controller:'UserController',
	    // 	controllerAs:'uc'
	    // });
	});
})();