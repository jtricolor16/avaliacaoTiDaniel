(function(){
	
	'use strict';

	angular.module('DanielTiApp').directive("rgMessage", function(){


		return{
			templateUrl: '/tiDaniel/views/diretivas/message.html',
			restrict: 'AE',
			scope: {
				message: "@"
			}
		}
	})

}());/**
 * 
 */