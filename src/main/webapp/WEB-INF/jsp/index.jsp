<!DOCTYPE html>
<html lang="en">
<head>
	<title>PETSHOP</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/login/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/fonts/vendor/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/login/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/login/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="/login/css/main.css">
<!--===============================================================================================-->
</head>
<body onload="iniciando()" style="background-color: #666666;">
<jsp:include page="pages/includes/modais/modalMensagem.jsp" />

<script>
function iniciando(){
	if('${mensagem}' != null && '${mensagem}' != ''){
		modalMensagem('${mensagem}');
	}
}
</script>

	
	<div class="limiter">
		<div class="container-login100">
			<div style="padding-top:0px" class="wrap-login100">
				<form style="padding-top:40px" class="login100-form validate-form" action="/" method="post" accept-charset="utf-8">
					<span class="login100-form-title p-b-43">
						<span class="h1">PETSHOP</span><br>
						<span style="max-width:50px">
							<svg version="1.1" style="max-width:25px" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" 	 width="51.062px" height="51.062px" viewBox="0 0 551.062 551.062" style="enable-background:new 0 0 551.062 551.062;" 	 xml:space="preserve"> <g> 	<path d="M465.19,453.459c14.749,67.688-58.752,82.375-91.127,73.562s-98.41-10.281-98.41-10.281s-66.218,1.469-98.593,10.281 		c-32.375,8.874-105.937-5.875-91.249-73.562s79.438-64.75,97.186-155.999c17.687-91.249,92.718-85.374,92.718-85.374 		s74.847-5.875,92.535,85.374C385.875,388.709,450.502,385.771,465.19,453.459z M343.586,206.15 		c39.841,11.505,83.844-19.951,98.349-70.258c14.504-50.245-5.998-100.307-45.839-111.812 		c-39.842-11.506-83.844,19.951-98.349,70.258C283.243,144.583,303.745,194.645,343.586,206.15z M508.703,187.852 		c-38.372-15.668-85.496,10.894-105.264,59.363c-19.768,48.471-4.712,100.43,33.66,116.035 		c38.372,15.606,85.496-10.894,105.264-59.364C562.131,255.416,547.076,203.519,508.703,187.852z M207.416,206.15 		c39.841-11.506,60.343-61.567,45.839-111.812s-58.568-81.702-98.349-70.196c-39.78,11.505-60.343,61.566-45.839,111.812 		C123.572,186.199,167.575,217.655,207.416,206.15z M113.963,363.25c38.373-15.667,53.428-67.626,33.66-116.035 		s-66.892-75.031-105.264-59.363C3.987,203.519-11.068,255.478,8.7,303.886C28.467,352.356,75.591,378.917,113.963,363.25z"/> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> </svg>
						</span>
					</span>
					
					
					<div class="wrap-input100 validate-input" data-validate = "Email inválido, exemplo: seuemail@provedor">
						<input autocomplete="off" class="input100" type="text" name="usuarioVal">
						<span class="focus-input100"></span>
						<span class="label-input100">Email</span>
					</div>
					
					
					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<input autocomplete="off" class="input100" type="password" name="senhaVal">
						<span class="focus-input100"></span>
						<span class="label-input100">Password</span>
					</div>

					<div class="flex-sb-m w-full p-t-3 p-b-32">
						<div>
							<a href="#" class="txt1">
								Esqueceu sua senha?
							</a>
						</div>
					</div>
			

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Entrar
						</button>
					</div>
				</form>

				<div class="login100-more" style="background-image: url('login/images/bg-01.jpg');">
				</div>
			</div>
		</div>
	</div>
	
	

	
	
<!--===============================================================================================-->
	<script src="/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="/login/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="/login/vendor/bootstrap/js/popper.js"></script>
	<script src="/login/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="/login/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="/login/vendor/daterangepicker/moment.min.js"></script>
	<script src="/login/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="/login/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="/login/js/css/main.js"></script>

</body>
</html>