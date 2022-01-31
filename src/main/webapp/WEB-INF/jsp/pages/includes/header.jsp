<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Sistema para Petshop">
        <meta name="author" content="Henrique Brandão">
        <meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <link rel="shortcut icon" href="/assets/images/avatar-1.jpg">
        <title>PETSHOP</title>
        <!--calendar css-->
        <link href="/assets/plugins/fullcalendar/css/fullcalendar.min.css" rel="stylesheet" />
        <link href="/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="/assets/css/core.css" rel="stylesheet" type="text/css" />
        <link href="/assets/css/components.css" rel="stylesheet" type="text/css" />
        <link href="/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="/assets/css/pages.css" rel="stylesheet" type="text/css" />
        <link href="/assets/css/responsive.css" rel="stylesheet" type="text/css" />
        
        <!-- SELECT2 -->
        <link href="/assets/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" />
        <link href="/assets/plugins/switchery/css/switchery.min.css" rel="stylesheet" />
        <link href="/assets/plugins/multiselect/css/multi-select.css"  rel="stylesheet" type="text/css" />
        <link href="/assets/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="/assets/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />
        <link href="/assets/plugins/bootstrap-touchspin/css/jquery.bootstrap-touchspin.min.css" rel="stylesheet" />
        <!-- SELECT2 -->
        
        
        <!-- TIMER -->
        <link href="/assets/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
		<link href="/assets/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
		<link href="/assets/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
		<link href="/assets/plugins/clockpicker/css/bootstrap-clockpicker.min.css" rel="stylesheet">
		<link href="/assets/plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
		<!-- TIMER -->
		<link rel="shortcut icon" href="/assets/images/avatar-1.jpg">
		<script src="assets/outros/jqueryLoader.min.js"></script>
        <script src="/assets/js/modernizr.min.js"></script>
        
        <script>
			//Loading ---------------------------------------
			jQuery(function($){
				$(".loader").fadeOut("slow"); //retire o delay quando for copiar!
			});
			// Loading ---------------------------------------
		</script>
		
    </head>
    
    <style>
    .loader {
			position: fixed;
			left: 0px;
			top: 0px;
			width: 100%;
			height: 100%;
			z-index: 9999;
			background-color: white;
		}
	</style>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
	<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
	
	<jsp:include page="mensagens.jsp" />    
    <jsp:include page="javaScript.jsp" />
    
    
    <body class="fixed-left" onload="iniciando()" >
    
    	<div id="loader" class="loader">
				<div class="col-sm-12 text-center" style="top:30%; color: #302010">
					<div class="col-sm-12 text-center">
						<img src="/assets/images/avatar-1.webp" onerror="this.src='/assets/images/avatar-1.jpg" style="max-width:100px" />
						<br>
						Aguarde...
						<br>
					</div>
					<div class="col-sm-12 text-center">
						<span class='fa fa-spinner fa-spin fa-2x'></span>
					</div>
				</div>
		</div>
		
        <!-- Begin page -->
        <div id="wrapper">
            <!-- Top Bar Start -->
            <div class="topbar">
                <!-- LOGO -->
                <div class="topbar-left">
                    <div class="text-center">
                        <a href="/" class="logo"><i class="fa fa-calendar icon-c-logo"></i><span>Atendimento</span></a>
                        <!-- Image Logo here -->
                        <!--<a href="index.html" class="logo">-->
                            <!--<i class="icon-c-logo"> <img src="/assets/images/logo_sm.png" height="42"/> </i>-->
                            <!--<span><img src="/assets/images/logo_light.png" height="20"/></span>-->
                        <!--</a>-->
                    </div>
                </div>
                <!-- Button mobile view to collapse sidebar menu -->
                <div class="navbar navbar-default" role="navigation">
                    <div class="container">
                        <div class="">
                            <div class="pull-left">
                                <button class="button-menu-mobile open-left waves-effect waves-light">
                                    <i class="md md-menu"></i>
                                </button>
                                <span class="clearfix"></span>
                            </div>
                            <ul class="nav navbar-nav navbar-right pull-right">
                                <li class="hidden-xs">
                                    <a href="#" id="btn-fullscreen" class="waves-effect waves-light"><i class="icon-size-fullscreen"></i></a>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <!-- Top Bar End -->

            <!-- ========== Left Sidebar Start ========== -->
            <div class="left side-menu">
                <div class="sidebar-inner slimscrollleft">
                    <!--- Divider -->
                    <div id="sidebar-menu">
                        <ul>
							<li class="has_sub">
                                <a href="javascript:void(0);" class="waves-effect"><i class="ti-book">
                                	</i><span>Agenda </span> <span class="menu-arrow"></span>
                                </a>
                                <ul>
                                	<li>
										<a href="/agendamento"><span>Marcar Serviço</span></a>
									</li>
	                                <li>
	                                	<a href="/minhaAgenda"><span>Minha Agenda</span></a>
									</li>
                                </ul>
                            </li>
                            
                            <c:if test="${usuarioSessao.perfil.cliente }">
                            	<li class="has_sub">
                                <a href="javascript:void(0);" class="waves-effect"><i class="ti-search">
                                	</i><span>Meus Dados </span> <span class="menu-arrow"></span>
                                </a>
                                <ul>
                                    <li>
                                        <a href="/meu_registro"><span>Meu Registro</span></a>
                                    </li>
                                    <li>
                                        <a href="/meus_pets"><span>Meus Pets</span></a>
                                    </li>
                                </ul>
                            </li>
                            </c:if>
                            
                            <c:if test="${usuarioSessao.perfil.funcionario }">
	                            <li class="has_sub">
	                                <a href="javascript:void(0);" class="waves-effect"><i class="ti-user">
	                                	</i><span>Cadastro </span> <span class="menu-arrow"></span>
	                                </a>
	                                <ul>
	                                    <li>
	                                        <a href="/clientes"><span>Clientes</span></a>
	                                    </li>
	                                    <li>
	                                        <a href="/pets"><span>Pets</span></a>
	                                    </li>
	                                    <li>
	                                        <a href="/funcionarios"><span>Funcionários</span></a>
	                                    </li>
	                                    <li>
	                                        <a href="/precos"><span>Preços</span></a>
	                                    </li>
	                                </ul>
	                            </li>
                            </c:if>
                            
                            <li class="has_sub">
                            	<a href="/alterarSenha" class="waves-effect"><i class="ti-key"></i>
                            		<span>Trocar senha</span>
                            	</a>
                            </li>
                            
                            <li class="has_sub">
                            	<a href="/deslogar" class="waves-effect"><i class="ti-power-off"></i>
                            		<span>Sair</span>
                            	</a>
                            </li>
                        </ul>
                        
                        
                        <div class="clearfix"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <!-- Left Sidebar End --> 



            <!-- ============================================================== -->
            <!-- START PAGE right Content here -->
            <!-- ============================================================== -->                      
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container">

