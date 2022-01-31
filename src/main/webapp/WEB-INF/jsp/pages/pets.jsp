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
<jsp:include page="includes/modais/modalImagem.jsp" />
<!-- DOWNLOAD EXCEL -->


<script>


function verImagem(){
	var foto = document.getElementById("pathImagem").value;
	if(foto != null && foto != ''){
		modalImagem(foto)	
	}
}

function verImagemLink(foto){
	if(foto != null && foto != ''){
		modalImagem(foto)	
	}
}

function fMasc(objeto,mascara) {
	obj=objeto
	masc=mascara
	setTimeout("fMascEx()",1)
}
function fMascEx() {
	obj.value=masc(obj.value)
}
function mTel(tel) {
	tel=tel.replace(/\D/g,"")
	tel=tel.replace(/^(\d)/,"($1")
	tel=tel.replace(/(.{3})(\d)/,"$1)$2")
	if(tel.length == 9) {
		tel=tel.replace(/(.{1})$/,"-$1")
	} else if (tel.length == 10) {
		tel=tel.replace(/(.{2})$/,"-$1")
	} else if (tel.length == 11) {
		tel=tel.replace(/(.{3})$/,"-$1")
	} else if (tel.length == 12) {
		tel=tel.replace(/(.{4})$/,"-$1")
	} else if (tel.length > 12) {
		tel=tel.replace(/(.{4})$/,"-$1")
	}
	return tel;
}
function mCNPJ(cnpj){
	cnpj=cnpj.replace(/\D/g,"")
	cnpj=cnpj.replace(/^(\d{2})(\d)/,"$1.$2")
	cnpj=cnpj.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3")
	cnpj=cnpj.replace(/\.(\d{3})(\d)/,".$1/$2")
	cnpj=cnpj.replace(/(\d{4})(\d)/,"$1-$2")
	return cnpj
}
function mCPF(cpf){
	cpf=cpf.replace(/\D/g,"")
	cpf=cpf.replace(/(\d{3})(\d)/,"$1.$2")
	cpf=cpf.replace(/(\d{3})(\d)/,"$1.$2")
	cpf=cpf.replace(/(\d{3})(\d{1,2})$/,"$1-$2")
	return cpf
}
function mCEP(cep){
	cep=cep.replace(/\D/g,"")
	cep=cep.replace(/^(\d{5})(\d)/,"$1-$2")
	return cep
}
function mNum(num){
	num=num.replace(/\D/g,"")
	return num
}

function acao(valor){
	document.getElementById("acao").value = valor;
}

function cancelar(){
	document.getElementById("nome").value = '';
	document.getElementById("especie").value = '';
	document.getElementById("genero").value = '';
	document.getElementById("raca").value = '';
	document.getElementById("peso").value = '';
	document.getElementById("castracao").value = '';
	document.getElementById("dataNascimento").value = '';
	document.getElementById("vacinas").value = '';
	document.getElementById("responsaveis").value = '';
	document.getElementById("pathImagem").value = '';
	document.getElementById("observacoes").value = '';
	
	
	document.getElementById("acao").value = '';
	document.getElementById("atualizar").style.display = "none";
	document.getElementById("salvar").style.display = "block";
	document.getElementById("cancelar").style.display = "none";
}

function editarPet(id){
	document.getElementById("acao").value = 'atualizar';
	document.getElementById("atualizar").style.display = "block";
	document.getElementById("salvar").style.display = "none";
	document.getElementById("cancelar").style.display = "block";
	
	var inicio = 'x';
	var fim = 'x';
	<c:forEach items="${pets }" var="p" varStatus="s">
		if(${p.id} == id){
			document.getElementById("nome").value = '${p.nome}';
			document.getElementById("especie").value = '${p.especie}';
			document.getElementById("genero").value = '${p.genero}';
			document.getElementById("raca").value = '${p.raca}';
			document.getElementById("peso").value = '${p.peso}';
			if('${p.castracao}' == 'true'){
				document.getElementById("castracao").value = '1';	
			} else{
				document.getElementById("castracao").value = '0';
			}
			
			var nasc = '${p.nascimento }'.replace('T00:00','');
			document.getElementById("dataNascimento").value = nasc;
			
			var vac = '';
			<c:forEach items='${p.vacina}' var='vc'>
				vac = vac + '${vc.nome}' + ','
			</c:forEach>
			vac = vac.substring(0, vac.length - 1);
			document.getElementById("vacinas").value = vac;
			
			
			var resp = '';
			resp = '${p.responsavel.nome}'
			document.getElementById("responsaveis").value = resp;
			
			
			document.getElementById("idPet").value = '${p.id}';
			document.getElementById("pathImagem").value = '${p.pathImagem}';
			document.getElementById("observacoes").value = '${p.observacoes}';
		}
	</c:forEach>

		
}


</script>











<!-- start: page -->
<c:if test="${usuarioSessao.perfil.admin == true}">
<div class="row">
<form action="/atualizar_pet" method="post" accept-charset="utf-8">
	<div class="col-md-12">
		<div data-collapsed="0" class="panel">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<span class="h2 panel-title" id="">Cadastrar/Editar Pet</span>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-6 form-group">
						<label>Nome: </label>
						<input type="text" placeholder="Nome" name="nome" id="nome" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Espécie: </label>
						<input type="text" placeholder="Espécie" name="especie" id="especie" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Gênero: </label>
						<input type="text" placeholder="Gênero" name="genero" id="genero" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Raça: </label>
						<input type="text" placeholder="Raça" name="raca" id="raca" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Peso: </label>
						<input type="text" placeholder="Peso" name="peso" id="peso" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Castração: </label>
						<select class="form-control select2" id="castracao" name="castracao">
							<option value="0">Não</option>
							<option value="1">Sim</option>
						</select>
					</div>
					<div class="col-md-3 form-group">
						<label>Nascimento: </label>
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-birthday-cake"></i>
							</span>
							<input type="date" name="dataNascimento" id="dataNascimento" class="form-control" />
						</div>
					</div>
					<div class="col-md-6 form-group">
						<label>Vacinas: </label>
						<input type="text" placeholder="Vacinas" name="vacinas" id="vacinas" class="form-control" required>
					</div>
					<div class="col-md-6 form-group">
						<label>Responsáveis: </label>
						<input type="text" placeholder="Responsáveis" name="responsaveis" id="responsaveis" class="form-control" required>
					</div>
					<div class="col-md-6 form-group">
						<label>URL Imagem: </label>
						<div class="input-group">
							<span style="cursor:pointer" onclick="verImagem()" class="input-group-addon">
								<i class="fa fa-photo"></i>
							</span>
							<input type="text" placeholder="URL Imagem" name="pathImagem" id="pathImagem" class="form-control" required>
						</div>
					</div>
					<div class="col-md-6 form-group">
						<label>Observações: </label>
						<input type="text" placeholder="Observações" name="observacoes" id="observacoes" class="form-control" required>
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
					<div class="col-md-2 form-group" id="contrato" style="display:none">
						<input type="button" class="btn btn-success" onclick="verContrato()" value="Ver Contrato">
					</div>
					<input type="hidden" id="idPet" name="idPet">
					<input type="hidden" id="acao" name="acao" value="salvar">
				</div>
			</div>
		</div>
	</div>
</form>
</div>
</c:if>




<c:if test="${!usuarioSessao.perfil.cliente || usuarioSessao.perfil.admin}">
<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<span class="h2 panel-title">Pets</span>
							</header>
							<div class="panel-body" style="overflow:auto">
								<table class="table table-bordered table-striped mb-none" id="datatable-default">
									<thead>
										<tr>
											<c:if test="${usuarioSessao.perfil.admin == true}">
												<th>Editar</th>
												<th>ID</th>
											</c:if>
											<th>Nome</th>
											<th>Espécie</th>
											<th>Gênero</th>
											<th>Raça</th>
											<th>Peso</th>
											<th>Castração</th>
											<th>Nascimento</th>
											<th>Vacina</th>
											<th>Responsáveis</th>
											<th>URL Imagem</th>
											<th>Observações</th>
											
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pets }" var="p">
											<tr class="gradeX">
												<c:if test="${usuarioSessao.perfil.admin == true}">
													<td>
														<i class="fa fa-trash" onclick="modalDeletar('pet', ${p.id}) "></i> &nbsp
														<i class="fa fa-pencil" onclick="editarPet(${p.id })"></i>
													</td>
												</c:if>
												<td>${p.id }</td>
												<td>${p.nome }</td>
												<td>${p.especie }</td>
												<td>${p.genero }</td>
												<td>${p.raca }</td>
												<td>${p.peso }kg</td>
												<td>
												<c:if test="${p.castracao }">Sim</c:if>
												<c:if test="${!p.castracao }">Não</c:if>
												</td>
												<fmt:parseDate value="${p.nascimento }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
												<td><fmt:formatDate pattern="dd/MM/yyyy" value="${ parsedDateTime }" />
												
												<td>
												<c:forEach items="${p.vacina }" var="v" varStatus="loop">
													${v.nome }
													<c:if test="${o.vacina.size() > 1}">
														<c:if test="${(loop.index+1) < o.vacina.size()}">
														,
														</c:if>
													</c:if>
												</c:forEach>
												</td>
												
												<td>
													${p.responsavel.nome }
												</td>
												<td style="cursor:pointer" onclick="verImagemLink('${p.pathImagem }')" ><i class="fa fa-photo"></i></td>
												<td>${p.observacoes }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="panel-footer">
								<button type="button" class="btn btn-primary" onclick="tableToExcel('datatable-default', 'Documento')">Download</button>
							</div>
						</section>
</c:if>




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