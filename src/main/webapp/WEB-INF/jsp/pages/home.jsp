<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<!-- HEADER -->
<!-- TAGS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- TAGS -->
<!-- INICIO BODY -->

<!-- Script -->
<script>
function redirecionar(link){
	window.location.href=link;
}
</script>
<!-- Script -->

<c:set var = "pendentes" value = "0"/>
<c:set var = "confirmada" value = "0"/>
<c:set var = "recusada" value = "0"/>

<c:forEach items="${consultas }" var="c">
	<c:if test="${c.confirmado }">
		<c:set var = "confirmada" value = "${confirmada + 1 }"/>
	</c:if>
	<c:if test="${c.cancelado }">
		<c:set var = "recusada" value = "${recusada + 1 }"/>
	</c:if>
	<c:if test="${!c.confirmado && !c.cancelado }">
		<c:set var = "pendentes" value = "${pendentes + 1 }"/>
	</c:if>
</c:forEach>

<!-- start: page -->
                <!-- Start content -->
                <div class="content">
                    <div class="container">
                        <!-- Page-Title -->
                        <div class="row" >
                            <div class="col-sm-2 text-left" style="padding-bottom:10px; ">
                            	<img style="max-width:100px; border-radius: 50%;" src="assets/images/avatar-1.jpg">
                            </div>
                            <div class="col-sm-9 text-left">
                                <h1> Olá ${usuarioSessao.nome }!</h1>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 col-lg-3" onclick="redirecionar('/minhaAgenda')" style="cursor:pointer">
                                <div class="widget-bg-color-icon card-box fadeInDown animated">
                                    <div class="bg-icon bg-icon-primary pull-left">
                                        <i class="md md-thumb-up text-primary"></i>
                                    </div>
                                    <div class="text-right">
                                        <h4 class="text-dark"><b class="counter">Confirmadas</b></h4>
                                        <p class="text-muted">${confirmada}</p>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </div>

                            <div class="col-md-6 col-lg-3" onclick="redirecionar('/minhaAgenda')" style="cursor:pointer">
                                <div class="widget-bg-color-icon card-box">
                                    <div class="bg-icon bg-icon-pink pull-left">
                                        <i class="md  md-thumb-down text-pink"></i>
                                    </div>
                                    <div class="text-right">
                                    	<h4 class="text-dark"><b class="counter">Recusadas</b></h4>
                                        <p class="text-muted">${recusada}</p>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </div>

                            <div class="col-md-6 col-lg-3" onclick="redirecionar('/minhaAgenda')" style="cursor:pointer">
                                <div class="widget-bg-color-icon card-box">
                                    <div class="bg-icon bg-icon-warning pull-left">
                                        <i class="md md-warning text-warning"></i>
                                    </div>
                                    <div class="text-right">
                                        <h4 class="text-dark"><b class="counter">Pendentes</b></h4>
                                        <p class="text-muted">${pendentes }</p>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                            
                            
							<c:if test="${usuarioSessao.perfil.funcionario }">
                            <div class="col-md-6 col-lg-3" onclick="redirecionar('/minhaAgenda')" style="cursor:pointer">
                                <div class="widget-bg-color-icon card-box">
                                    <div class="bg-icon bg-icon-success pull-left">
                                        <i class="md md-people text-success"></i>
                                    </div>
                                    <div class="text-right">
                                        <h4 class="text-dark"><b class="counter">Clientes</b></h4>
                                        <c:if test="${clientes > 0 }">
                                        	<p class="text-muted">${clientes }</p>
                                        </c:if>
                                        <c:if test="${clientes == null }">
                                        	<p class="text-muted">Nenhuma</p>
                                        </c:if>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                            </c:if>
                        </div>


                        <!-- end row -->
                        <c:if test="${usuarioSessao.perfil.cliente }">
                        	<div class="row">
	                        	<div class="col-lg-12">
	                        		<div class="card-box" style="padding-top:0px">
	                                        <div class="row">
	                                        	<div style="padding:0px" class="col-sm-12">
	                                        		<h3><b>Meus Pets</b></h3>
	                                        	</div>
	                                        	<c:forEach items="${usuarioSessao.pet }" var="o">
		                                            <div class="col-sm-4" onclick="redirecionar('/pet_${o.id }')" >
													  <img src="${o.pathImagem }" class="card-img-top" style="max-width:100px; border-radius:10px; cursor:pointer">
													  <div class="card-body">
													    <h4 class="card-title">${o.nome }</h4>
													    <p class="card-text">
														    Espécie: ${o.especie }<br>
														    Gênero: ${o.genero }<br>
														    Raça: ${o.raca }<br>
														    Peso: ${o.peso }kg<br>
														    Castrado: 
														    <c:if test="${o.castracao }">Sim</c:if>
														    <c:if test="${!o.castracao }">Não</c:if><br>
														   	Nasc: 
														   	<fmt:parseDate value="${o.nascimento }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
															<fmt:formatDate pattern="dd/MM/yyyy" value="${ parsedDateTime }" /><br>
														   	Obs: ${o.observacoes }<br>
														    Vacinas: 
														    <c:forEach items="${o.vacina }" var="v" varStatus="loop">
														    	${v.nome }
														    	<c:if test="${o.vacina.size() > 1}">
															    	<c:if test="${(loop.index+1) < o.vacina.size()}">
															    	,
															    	</c:if>
														    	</c:if>
														    </c:forEach>
														    <br>
														    Responsável: ${o.responsavel.nome }
														    <br>
													    </p>
													    
													    
													    <a href="#" class="btn btn-primary">Editar</a>
													    <br>&nbsp
													  </div>
													</div>
	                                            </c:forEach>
	                                        </div>
	                        		</div>
	                        	</div>
	                        </div>
                        </c:if>

						<c:if test="${!usuarioSessao.perfil.cliente || usuarioSessao.perfil.admin}">
	                        <div class="row">
	                        	<div class="col-lg-12">
	                        		<div class="card-box">
	                        			<h4 class="text-dark header-title m-t-0">Atividades</h4>
	                        			<p class="text-muted m-b-30 font-13">
											Calendário das reservas.
										</p>
	                        			<div class="nicescroll p-20" style="height: 295px;">
	                                        <div class="timeline-2">
	                                        
	                                        	<c:forEach items="${consultas }" var="c">
		                                            <div class="time-item">
		                                                <div class="item-info">
		                                                    <div class="text-muted">
		                                                    	<small>${c.apenasData } ${c.inicioHora } até 
		                                                    		<c:if test="${c.confirmado }">${c.fimHora }</c:if> 
		                                                    		<c:if test="${!c.confirmado }">?</c:if> 
		                                                    	</small>
		                                                    </div>
		                                                    <c:if test="${c.confirmado }">
		                                                    	<p>
		                                                    		<strong><a class="text-info">Confirmado</a></strong>
		                                                    		<br>Cliente: ${c.cliente } <c:if test="${c.clienteSistema != null }">(Aberto por: ${c.clienteSistema.nome })</c:if>
		                                                    		<br>Pet: ${c.pet.nome } (${c.pet.raca })
		                                                    		<br>${c.servico.nome }
																	<br><strong>${c.observacoes }</strong>
		                                                    	</p>
		                                                    </c:if>
		                                                    
		                                                    <c:if test="${!c.confirmado && !c.cancelado }">
		                                                    	<p>
		                                                    		<strong><a class="text-warning">Pendente</a></strong>
		                                                    		<br>Cliente: ${c.cliente } <c:if test="${c.clienteSistema != null }">(Aberto por: ${c.clienteSistema.nome })</c:if>
		                                                    		<br>Pet: ${c.pet.nome } (${c.pet.raca })
		                                                    		<br>${c.servico.nome }
																	<br><strong>${c.observacoes }</strong>
		                                                    	</p>
		                                                    </c:if>
		                                                    
		                                                    <c:if test="${c.cancelado }">
		                                                    	<p>
		                                                    		<strong><a class="text-danger">Recusado</a></strong>
		                                                    		<br>Cliente: ${c.cliente } <c:if test="${c.clienteSistema != null }">(Aberto por: ${c.clienteSistema.nome })</c:if>
		                                                    		<br>Pet: ${c.pet.nome } (${c.pet.raca })
		                                                    		<br>${c.servico.nome }
																	<br><strong>${c.observacoes }</strong>
		                                                    	</p>
		                                                    </c:if>
		                                                    
		                                                     
		                                                </div>
		                                            </div>
	                                            </c:forEach>
	                                            
	                                        </div>
	                                    </div>
	                        		</div>
	                        	</div>
	                        </div>
						</c:if>

                    </div> <!-- container -->

                </div> <!-- content -->






<!-- end: page -->




<!-- FOOTER -->
<jsp:include page="includes/footer.jsp" />
<!-- FOOTER -->