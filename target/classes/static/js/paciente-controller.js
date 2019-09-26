angular.module('alurapic').controller('PacienteController', function ($scope, $http) {

    $scope.pacientes = [];

    var promise = $http.get('listarPacientes');
    promise.then(function (retorno) {
        $scope.pacientes = retorno.data;
    }).catch(function (reason) {
        console.log(reason);
    });

});