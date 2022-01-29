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

<!-- DOWNLOAD EXCEL -->
<jsp:include page="includes/jquery/excel/downloadExcel.jsp" />
<!-- DOWNLOAD EXCEL -->


<script>

function acao(valor){
	document.getElementById("acao").value = valor;
}

function cancelar(){

	document.getElementById("nome").value = '';
	document.getElementById("precoValor").value = '';
	document.getElementById("acao").value = '';
	document.getElementById("atualizar").style.display = "none";
	document.getElementById("salvar").style.display = "block";
	document.getElementById("cancelar").style.display = "none";
}

function editar(id){
	document.getElementById("acao").value = 'atualizar';
	document.getElementById("atualizar").style.display = "block";
	document.getElementById("salvar").style.display = "none";
	document.getElementById("cancelar").style.display = "block";

	
	var inicio = 'x';
	var fim = 'x';
	<c:forEach items="${grupos }" var="p" varStatus="s">
		if(${p.id} == id){
			document.getElementById("nome").value = '${p.nome}';
			document.getElementById("precoValor").value = '${p.preco}';
			document.getElementById("idValor").value = ${p.id};
		}
	</c:forEach>

		
}


</script>


<!-- start: page -->
<c:if test="${usuario.perfil.admin }">
<div class="row">
<form action="/precos" method="post" accept-charset="utf-8">
	<div class="col-md-12">
		<div data-collapsed="0" class="panel">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<h2 class="panel-title" id="">Cadastrar novo Pre�o </h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-4 form-group">
						<input type="text" placeholder="Nome" name="nome" id="nome" class="form-control" required>
					</div>
					<div class="col-md-4 form-group">
						<input type="number" step="0.010" placeholder="Pre�o" min="0" name="precoValor" id="precoValor" class="form-control" required>
					</div>
					<div class="col-md-2 form-group" id="salvar">
						<input type="submit" class="btn btn-primary" onclick="acao('salvar')" value="Criar">
					</div>
					<div class="col-md-2 form-group" id="atualizar" style="display:none">
						<input type="submit" class="btn btn-primary" onclick="acao('atualizar')" value="Atualizar">
					</div>
					<div class="col-md-2 form-group" id="cancelar" style="display:none">
						<input type="button" class="btn btn-danger" onclick="cancelar()" value="Voltar">
					</div>
					
					<input type="hidden" id="idValor" name="idValor" value="">
					<input type="hidden" id="acao" name="acao" value="salvar">
				</div>
			</div>
		</div>
	</div>
</form>
</div>
</c:if>














<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Registro dos Pre�os</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<c:if test="${usuario.perfil.admin }">
												<th>Editar</th>
											</c:if>
											<c:if test="${!usuario.perfil.cliente }">
												<th>ID</th>
											</c:if>
											<th>Nome</th>
											<th>Pre�o</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${grupos }" var="p">
											<tr class="gradeX">
												<c:if test="${usuario.perfil.admin }">
													<td>
														<i class="fa fa-trash" onclick="modalDeletar('precos', ${p.id}) "></i> &nbsp
														<i class="fa fa-pencil" onclick="editar(${p.id }) "></i>
													</td>
												</c:if>
												<c:if test="${!usuario.perfil.cliente }">
													<td>${p.id }</td>
												</c:if>
												<td>${p.nome }</td>
												<td>R$${fn:replace(p.preco, '.', ',')}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="panel-footer">
								<button type="button" class="btn btn-primary" onclick="tableToExcel('datatable-default', 'Documento')">Download</button>
							</div>
						</section>





<!-- end: page -->
	</section>
</div>







<!-- FIM BODY -->
</div>
<!-- FIM BODY -->
<!-- MAIN NO HEADER -->
</main>
<!-- HEADER -->
<jsp:include page="includes/footer.jsp" />
<!-- HEADER -->