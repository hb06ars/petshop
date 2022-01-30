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
function redirecionar(site){
	window.location.href=site;
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

	document.getElementById("matricula").value = ${matriculaPadrao };
	document.getElementById("nome").value = '';
	document.getElementById("cpf").value = '';
	document.getElementById("dataNascimento").value = '';
	document.getElementById("telefone").value = '';
	document.getElementById("celular").value = '';
	document.getElementById("email").value = '';
	document.getElementById("endereco").value = '';
	document.getElementById("bairro").value = '';
	document.getElementById("cidade").value = '';
	document.getElementById("estado").value = '';
	document.getElementById("cep").value = '';
	document.getElementById("pathImagem").value = '';
	document.getElementById("perfil").value = 0;
	document.getElementById("grupo").value = 0;

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
	<c:forEach items="${usuarios }" var="u" varStatus="s">
		if(${u.id} == id){
			document.getElementById("matricula").value = '${u.matricula}';
			document.getElementById("nome").value = '${u.nome}';
			document.getElementById("cpf").value = '${u.cpf}';
			document.getElementById("dataNascimento").value = '${u.dataNascimento}';
			document.getElementById("telefone").value = '${u.telefone}';
			document.getElementById("celular").value = '${u.celular}';
			document.getElementById("email").value = '${u.email}';
			document.getElementById("endereco").value = '${u.endereco}';
			document.getElementById("bairro").value = '${u.bairro}';
			document.getElementById("cidade").value = '${u.cidade}';
			document.getElementById("estado").value = '${u.estado}';
			document.getElementById("cep").value = '${u.cep}';
			document.getElementById("pathImagem").value = '${u.pathImagem}';
			document.getElementById("perfil").value = ${u.perfil.codigo};
		}
	</c:forEach>

		
}


</script>


<!-- start: page -->
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
					<span class="panel-title" >
						<img src="${petSelecionado.pathImagem }" style="max-width:100px" />
						<span style="position:relative; left:10px; top:10px" class="h1">${petSelecionado.nome }</span>
					</span>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-3 form-group">
						<label>ID:</label>
						<input type="text" placeholder="Login" name="id" id="id" class="form-control" value="${petSelecionado.id }" readonly>
					</div>
					<div class="col-md-3 form-group">
						<label>Nome:</label>
						<input type="text" placeholder="Nome" name="nome" id="nome" class="form-control" value="${petSelecionado.nome }" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Espécie:</label>
						<input type="text" placeholder="Espécie" name="especie" id="especie" class="form-control" value="${petSelecionado.especie }" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Gênero:</label>
						<select id="genero" name="genero" class="form-control">
							<option value="Masculino" <c:if test="${petSelecionado.genero == 'Masculino' }" >selected</c:if> >Masculino</option>
							<option value="Feminino" <c:if test="${petSelecionado.genero == 'Feminino'  }" >selected</c:if> >Feminino</option>
						</select>
						
					</div>
					<div class="col-md-3 form-group">
						<label>Raça:</label>
						<input type="text" placeholder="Raça" name="raca" id="raca" class="form-control" value="${petSelecionado.raca }" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Peso (kg):</label>
						<input type="number" step="0.01" placeholder="Peso" name="peso" id="peso" class="form-control" value="${petSelecionado.peso }" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Castrado:</label>
						<select id="castracao" name="castracao" class="form-control">
							<option value="1" <c:if test="${petSelecionado.castracao}" >selected</c:if> >Sim</option>
							<option value="0" <c:if test="${!petSelecionado.castracao}" >selected</c:if> >Não</option>
						</select>
						
					</div>
					<div class="col-md-3 form-group">
						<label>Nascimento:</label>
						<input type="date" placeholder="Data de Nascimento" name="pet_dataNascimento" id="pet_dataNascimento" class="form-control" value="${fn:replace(petSelecionado.nascimento,'T00:00','') }" required>
					</div>
					<div class="col-md-3 form-group">
						<label>Path Imagem:</label>
						<input type="text" placeholder="Imagem URL" name="pathImagem" id="pathImagem" class="form-control" value="${petSelecionado.pathImagem }" required>
					</div>
					<div class="col-md-9 form-group">
						<label>Observações:</label>
						<input type="text" placeholder="Observações" name="observacoes" id="observacoes" class="form-control" value="${petSelecionado.observacoes }" required>
					</div>
					
					
					<div class="col-md-3 form-group">
						<label>Vacina:</label><br>
						<c:forEach items="${vacinas }" var="v">
							<c:set var = "encontrouVacina" value = "0"/>
							<c:forEach items="${petSelecionado.vacina }" var="vac">
								<c:if test="${v.id == vac.id }">
									<c:set var = "encontrouVacina" value = "1"/>
									<input type="checkbox" checked name="pet_vacina" id="pet_vacina" value="${vac.id }">
								</c:if>
							</c:forEach>
							<c:if test="${encontrouVacina == 0 }">
								<input type="checkbox" name="pet_vacina" id="pet_vacina" value="${v.id }">
							</c:if>
							<label for="vacina"> ${v.nome }</label><br>
						</c:forEach>
					</div>
					
					<div class="col-md-3 form-group">
						<label>Responsáveis:</label><br>
						<c:forEach items="${petSelecionado.responsaveis }" var="res">
							<input type="checkbox" checked name="pet_responsaveis" id="pet_responsaveis" value="${res.id }">
							<label for="responsaveis"> ${res.nome }</label><br>
						</c:forEach>
					</div>
					
					
					<c:if test="${usuarioSessao.perfil.cliente }">
						<div class="col-md-3 form-group">
							<label>Adicionar outro Responsável:</label><br>
							<input type="text" placeholder="CPF" name="cpf" id="cpf" class="form-control" >
						</div>
					
						<div class="col-md-12 form-group" id="atualizar" >
							<input type="submit" class="btn btn-primary" onclick="acao('atualizar')" value="Atualizar">
						</div>
						<input type="hidden" id="acao" name="acao" value="atualizar">
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="idPet" id="idPet" value="${petSelecionado.id }">
</form>




</div>















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