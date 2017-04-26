<html data-ng-app>
<head>
<TITLE>Spring MVC Example with AJAX call</TITLE>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.1/angular.min.js"></script>

<!-- for autocomplete -->
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="assets/autocomplete/jquery.autocomplete.min.js"></script>
<link href="assets/autocomplete/main.css" rel="stylesheet">



<script type="text/javascript">
	function Ajax() {

		var a = $('#a').val();
		var b = $('#b').val();
		$.ajax({
			url : 'ajaxtest.html',
			data : "num1=" + a + "&num2=" + b,
			success : function(data) {
				$('#result').html(data);
			}
		});
	}
</script>
<script type="text/javascript">
	function doSearch() {
		$.getJSON("Search",

		{
			CHARS : $('#searchbox').val()
		},

		function(data) {
			console.log(data);
			$('#searchresult').text('');
			window.globalJSON = data;
			for ( var index in data) {
				if (data[index].type === 'user') {

					$('#user').append('<p>' + data[index].name + '</p>');
				} else {

					$('#company').append('<p>' + data[index].name + '</p>');
				}
			}
		});
	}
</script>

<script>
	function AJAXCtrl($scope, $http) {

		console.log("inside Ctrl");

		$http({
			method : "GET",
			url : url + "/looseSearch",
			params : {
				CHARS : $('#mysearch').val()
			}
		}).then(function(response) {

			console.log("inside THEN");
			$scope.names = response.data;
		});

	}
	/*
	console.log("called");
	
	var nameApp = angular.module("testAJAX");
	
	/* 
	nameApp.controller('AJAXCtrl', function ($scope,$http){
	  	  
	//$scope.names = ['Larry', 'Curly', 'Moe'];
	console.log("inside Ctrl");
	
	$http({
	    method: "GET",
	    url: url + "/looseSearch",
	    params: {
	        CHARS : $('#mysearch').val()
	    }
	}).then(function(response) {
	                	
		console.log("inside THEN");
	    $scope.names = response.data;
	});
	});
	} */
</script>
<script>
var x = [];
	$(document).ready(function() {

		$('#dynamicsearchcomp').autocomplete({
			serviceUrl : 'Search',
			paramName : "CHARS",
			delimiter : ",",
			transformResult : function(response) {

				return {
					suggestions : $.map($.parseJSON(response), function(user) {
						x.push(user);
						console.log(x);
						return {
							value : user.name,
							data : user.id
						};

					})

				};
				
			}

		});
		
		/* $('#dynamicsearchcomp').on('change',function(){
			  //alert(x.name);
			  alert($(this).val());
			  
			  
			  //window.location.replace("");
			  
		  }); */
		$('#searchbutton').click(function() {
			var userSelect = $('#dynamicsearchcomp').val();
			console.log(userSelect);
			var userSelectId = -1;
			var companyName = "xyz"
			var type = "xyz"
			console.log(x);
			for (var i=0;i<x.length;i++) {
				console.log(x[i].name);
		  		if (x[i].name == userSelect) {
		  			userSelectId = x[i].id;	  
		  			type = x[i].type;
		  			companyName = x[i].name;
		  			break;
		  		}
		  	}
			console.log(userSelectId);
			if (userSelectId !== -1) {
				if (type === 'user') {
					window.location.replace("/viewProfile?userName"+userSelectId);// ill tak to nevil tom abt ths url
				} else {
					if (companyName !== 'xyz')
						window.location.replace("Company?companyname="+companyName);
					else 
						$('#notfound').text("No results found.");// not found
				}
			} else {
				$('#notfound').text("No results found.");// k go ahead
				//window.location.replace("wtf.com");//not found 
			}
		});
	
	});
</script>



<script>
	$(document).ready(function() {

		$('#dynamicsearchuser').autocomplete({
			serviceUrl : 'looseSearch',
			paramName : "CHARS",
			delimiter : ",",
			transformResult : function(response) {

				return {
					//must convert json to javascript object before process
					suggestions : $.map($.parseJSON(response), function(user) {

						return {
							value : user.userName,
							data : user.userId
						};
					})

				};

			}

		});

	});
</script>
<script>
	$(document).ready(function() {

		$('#dynamicsearchcompany').autocomplete({
			serviceUrl : 'looseSearch2',
			paramName : "CHARS",
			delimiter : ",",
			transformResult : function(response) {

				return {
					//must convert json to javascript object before process
					suggestions : $.map($.parseJSON(response), function(company) {

						return {
							value : company.company_name,
							data : company.company_id
						};
					})

				};

			}

		});

	});
</script>

</head>

<body>
	<jsp:directive.include file="scripts.jsp" />

	<div align="center">
		<br> <br> ${message} <br> <br> <input
			type="number" value="1" id="a" onchange="Ajax()" /> <input
			type="number" value="2" id="b" onchange="Ajax()" /> <br>
		<div id="result"></div>

		<br> <br>
		<br> <input type="text" id="searchbox" onKeyUp="doSearch();">
		<br>Normal search :
		<div id=user"></div>
		<br />
		<div id="company"></div>

		<br>AutoComplete search : <input type="text" id="dynamicsearchcomp" value=""> 
		<input class="btn btn-sm btn-primary" type="button" value="Search" id="searchbutton">
		<br>
		<div id="notfound" style="color:red"></div>
		<br>AutoComplete search User: <input type="text" id="dynamicsearchuser" value="">
		<br>AutoComplete search Company: <input type="text" id="dynamicsearchcompany" value="">

	</div>


	<!-- <div data-ng-controller="AJAXCtrl">
    <br><br>
        <input type ="text" id="mysearch" onKeyUp="AJAXCtrl();" data-ng-model="my"/>
        
    <br>{{my}}<br>    
    <ul>
      <li data-ng-repeat="name in names">{{name.userName}} - {{name.name}}</li>
    </ul>
    </div> -->

</body>
</html>