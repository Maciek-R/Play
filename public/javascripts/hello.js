if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

function helloFun() {
    console.log("Hello Fun");
    return 'helloFunFun';
}

var app = angular.module("myShoppingList", []);

app.controller("myCtrl", function($scope) {
  $scope.products = ["Milk", "Bread", "Cheese"];
});