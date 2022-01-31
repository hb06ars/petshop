package brandaoti.sistema.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import brandaoti.sistema.dao.AssuntoDao;
import brandaoti.sistema.dao.ConsultaDao;
import brandaoti.sistema.dao.PerfilDao;
import brandaoti.sistema.dao.PetDao;
import brandaoti.sistema.dao.PrecoDao;
import brandaoti.sistema.dao.UsuarioDao;
import brandaoti.sistema.dao.VacinaDao;
import brandaoti.sistema.model.Assunto;
import brandaoti.sistema.model.Consulta;
import brandaoti.sistema.model.Perfil;
import brandaoti.sistema.model.Pet;
import brandaoti.sistema.model.Preco;
import brandaoti.sistema.model.Usuario;
import brandaoti.sistema.model.Vacina;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class SistemaController extends HttpServlet {

		private static final long serialVersionUID = 1L;
		@Autowired
		private UsuarioDao usuarioDao;
		@Autowired
		private PerfilDao perfilDao;
		@Autowired
		private PrecoDao precoDao;
		@Autowired
		private AssuntoDao assuntoDao;
		@Autowired
		private ConsultaDao consultaDao;
		@Autowired
		private VacinaDao vacinaDao;
		@Autowired
		private PetDao petDao;
		
		public String gerarChamado(Usuario usuarioSessao) {
			Random gerador = new Random();
	    	Calendar data = Calendar.getInstance();
	    	int ano = data.get(Calendar.YEAR);
	    	int mes = data.get(Calendar.MONTH);
	    	mes++;
	    	int m = mes;
	    	int dia = data.get(Calendar.DAY_OF_MONTH);
	        int hora = data.get(Calendar.HOUR_OF_DAY); 
	        int min = data.get(Calendar.MINUTE);
	        int seg = data.get(Calendar.SECOND);
	        int numero = gerador.nextInt(100);
	        String chamado = ""+ano+m+dia+hora+min+seg+numero+usuarioSessao.getId();
	        return chamado;
		}
		
		public String gerarMatricula(Usuario usuarioSessao) {
			Random gerador = new Random();
	    	Calendar data = Calendar.getInstance();
	    	int ano = data.get(Calendar.YEAR);
	    	int mes = data.get(Calendar.MONTH);
	    	mes++;
	    	int m = mes;
	    	int dia = data.get(Calendar.DAY_OF_MONTH);
	        int hora = data.get(Calendar.HOUR_OF_DAY); 
	        int min = data.get(Calendar.MINUTE);
	        int seg = data.get(Calendar.SECOND);
	        int numero = gerador.nextInt(100);
	        String matricula = ""+ano+m+dia+hora+min+seg+numero+usuarioSessao.getId();
	        return matricula;
		}
		
		public void resetaMes(HttpSession session, Integer mesSelecionado, Integer anoSelecionado ) {
			Calendar calendar = new GregorianCalendar();
			mesSelecionado = calendar.get(Calendar.MONTH);
			mesSelecionado = mesSelecionado+1;
			anoSelecionado = calendar.get(Calendar.YEAR);
			if(session.getAttribute("mesSelecionado") != null) {
				session.setAttribute( "mesSelecionado" , mesSelecionado );
			}
			if(session.getAttribute("anoSelecionado") != null) {
				session.setAttribute( "anoSelecionado" , anoSelecionado );
			}
		}
		
		
		public void iniciarDia(HttpSession session) {
			Calendar calendar = new GregorianCalendar();
			Integer diaDeHoje = calendar.get(Calendar.DAY_OF_MONTH);
			Integer mesDeHoje = calendar.get(Calendar.MONTH);
			mesDeHoje = mesDeHoje+1;
			Integer anoDeHoje = calendar.get(Calendar.YEAR);
			
			if(session.getAttribute("diaSelecionado") == null) {
				session.setAttribute( "diaSelecionado" , diaDeHoje );
			}
			if(session.getAttribute("mesSelecionado") == null) {
				session.setAttribute( "mesSelecionado" , mesDeHoje );
			}
			if(session.getAttribute("anoSelecionado") == null) {
				session.setAttribute( "anoSelecionado" , anoDeHoje );
			}
		}
		
		
		@RequestMapping(value = {"/","/index"}, produces = "text/plain;charset=UTF-8", method = RequestMethod.GET) // Pagina de Vendas
		public void login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "nome", required = false, defaultValue = "Henrique Brandão") String nome) throws SQLException, ServletException, IOException { //Funcao e alguns valores que recebe...
			//Caso não haja registros
			HttpSession session = request.getSession();
			iniciarDia(session);
			// Excluir ----------------------------------------------------------------------------------------------------
			
			List<Usuario> u = usuarioDao.buscarTudo();
			List<Perfil> p = perfilDao.buscarTudo();
			List<Preco> pl = precoDao.buscarTudo();
			List<Assunto> as = assuntoDao.buscarTudo();
			List<Consulta> consultas = consultaDao.buscarTudo();
			
			if(p == null || p.size() == 0) {
				Perfil perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("1");
				perfil.setNome("Administrador");
				perfil.setAdmin(true);
				perfil.setFuncionario(true);
				perfil.setCliente(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("2");
				perfil.setNome("Cliente");
				perfil.setAdmin(false);
				perfil.setFuncionario(false);
				perfil.setCliente(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("3");
				perfil.setNome("Funcionário");
				perfil.setAdmin(false);
				perfil.setFuncionario(true);
				perfil.setCliente(false);
				perfilDao.save(perfil);
				
				
			}
			if(pl == null || pl.size() == 0) {
				Preco pr = new Preco();
				pr.setCodigo("1");
				pr.setNome("Banho");
				pr.setPreco(99.99);
				pr.setAtivo(true);
				precoDao.save(pr);
				pr = new Preco();
				pr.setCodigo("2");
				pr.setNome("Tosa");
				pr.setAtivo(true);
				pr.setPreco(99.99);
				precoDao.save(pr);
				pr = new Preco();
				pr.setCodigo("3");
				pr.setNome("Vacina");
				pr.setAtivo(true);
				pr.setPreco(99.99);
				precoDao.save(pr);
				pr = new Preco();
				pr.setCodigo("4");
				pr.setNome("Banho e Tosa");
				pr.setAtivo(true);
				pr.setPreco(99.99);
				precoDao.save(pr);
			}
			
			
			if(u == null || u.size() == 0) {
				// Henrique
				Usuario h = new Usuario();
				h.setAtivo(true);
				h.setMatricula("adm");
				h.setCpf("22233344455");
				h.setEmail("teste@teste.com");
				h.setSenha("adm");
				h.setNome("Henrique");
				h.setTelefone("(11)98931-6271");
				h.setCelular("(11)98931-6271");
				h.setEndereco("Teste...");
				h.setCep("00000-000");
				h.setBairro("Jd da Alegria");
				h.setDataNascimento(LocalDate.now());
				h.setBairro("São Paulo");
				h.setEstado("SP");
				h.setPerfil(perfilDao.buscarAdm().get(0));
				usuarioDao.save(h);
				
				Usuario h2 = new Usuario();
				h2.setAtivo(true);
				h2.setMatricula("admb");
				h2.setCpf("434344433433");
				h2.setEmail("teste@testeb.com");
				h2.setSenha("admb");
				h2.setNome("HenriqueB");
				h2.setTelefone("(11)98931-6271");
				h2.setCelular("(11)98931-6271");
				h2.setEndereco("Teste...");
				h2.setCep("00000-000");
				h2.setBairro("Jd da Alegria");
				h2.setDataNascimento(LocalDate.now());
				h2.setBairro("São Paulo");
				h2.setEstado("SP");
				h2.setPerfil(perfilDao.buscarAdm().get(0));
				usuarioDao.save(h2);
				
				Usuario d = new Usuario();
				d.setAtivo(true);
				d.setMatricula("123");
				d.setCpf("11122233344");
				d.setEmail("teste@teste.com");
				d.setSenha("123");
				d.setNome("Mah");
				d.setTelefone("(11)99999-9999");
				d.setCelular("(11)99999-9999");
				d.setEndereco("Teste...");
				d.setCep("00000-000");
				d.setBairro("Jd da Alegria");
				d.setCidade("São Paulo");
				d.setEstado("SP");
				d.setDataNascimento(LocalDate.now());
				d.setPerfil(perfilDao.buscarCliente().get(0));
				d.setPathImagem("https://instagram.fcgh11-1.fna.fbcdn.net/v/t51.2885-19/s150x150/121259006_145932033910851_5986443377023843247_n.jpg?_nc_ht=instagram.fcgh11-1.fna.fbcdn.net&_nc_ohc=K7QuPMx_HTsAX9F8mer&tp=1&oh=4f66c284e537eb8c6a37a16ecfa2d339&oe=605329B4");
				
				Vacina vac = new Vacina();
				vac.setNome("Raiva");
				vacinaDao.save(vac);
				
				Vacina vac2 = new Vacina();
				vac2.setNome("Sarna");
				vacinaDao.save(vac2);
				
				List<Vacina> listaVacina = new ArrayList<Vacina>();
				listaVacina.add(vac);
				
				Pet pet = new Pet();
				pet.setCastracao(true);
				pet.setEspecie("Cachorro");
				pet.setGenero("Masculino");
				pet.setNome("Max");
				pet.setObservacoes("Fez cirurgia no olho");
				pet.setPeso(11.0);
				pet.setRaca("Lhasa");
				pet.setNascimento(LocalDateTime.of(2008, 8, 10, 0, 0));
				pet.setVacina(listaVacina);
				petDao.save(pet);
				
				Pet pet2 = new Pet();
				pet2.setCastracao(true);
				pet2.setEspecie("Cachorro");
				pet2.setGenero("Feminino");
				pet2.setNome("Mel");
				pet2.setObservacoes("Solta muito pêlo");
				pet2.setPeso(11.0);
				pet2.setRaca("Desconhecido");
				pet2.setNascimento(LocalDateTime.of(2008, 8, 10, 0, 0));
				pet2.setVacina(listaVacina);
				petDao.save(pet2);
				
				List<Pet> listaPet = new ArrayList<Pet>();
				listaPet.add(pet);
				listaPet.add(pet2);
				
				d.setPet(listaPet);
				
				usuarioDao.save(d);	
				
				
				pet.setResponsavel(usuarioDao.findById(d.getId()).get());
				petDao.save(pet);
				pet2.setResponsavel(usuarioDao.findById(d.getId()).get());
				petDao.save(pet2);
				
				// Rafael
				Usuario r = new Usuario();
				r.setAtivo(true);
				r.setMatricula("456");
				r.setCpf("11122233355");
				r.setEmail("teste@teste.com");
				r.setSenha("456");
				r.setNome("Rafael");
				r.setTelefone("(11)99999-9999");
				r.setCelular("(11)99999-9999");
				r.setEndereco("Teste...");
				r.setCep("00000-000");
				r.setBairro("Jd da Alegria");
				r.setCidade("São Paulo");
				d.setDataNascimento(LocalDate.now());
				r.setEstado("SP");
				r.setPathImagem("https://instagram.fcgh11-1.fna.fbcdn.net/v/t51.2885-19/s150x150/147640101_432656427934162_7502532548051698688_n.jpg?_nc_ht=instagram.fcgh11-1.fna.fbcdn.net&_nc_ohc=1AwA0-HsIfEAX_ePNx8&tp=1&oh=c494c72522c18470b00e66fa92c9e1b7&oe=6052EE90");
				r.setPerfil(perfilDao.buscarFuncionario().get(0));
				usuarioDao.save(r);

			}
			
			//Horario
			if(consultas.size() == 0) {
				Consulta hor = new Consulta();
				hor.setCliente("Einstein");
				hor.setData(LocalDateTime.now());
				hor.setInicio(LocalDateTime.now());
				hor.setFim(LocalDateTime.now());
				hor.setObservacoes("Alguma...");
				hor.setPreco(10.33);
				hor.setProfissional(usuarioDao.buscarFuncionarios().get(0));
				hor.setServico(precoDao.buscarTudo().get(0));
				consultaDao.save(hor);
			}
			// Excluir ----------------------------------------------------------------------------------------------------
			
			if(session.getAttribute("usuarioSessao") != null) {
				response.sendRedirect("/home");
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response); //retorna a variavel
			}
		}
		
		@RequestMapping(value = {"/","/index"}, produces = "text/plain;charset=UTF-8", method = {RequestMethod.POST}) // Pagina de Vendas
		public void index_post(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "usuarioVal", defaultValue = "") String usuarioVal, @RequestParam(value = "senhaVal", defaultValue = "") String senhaVal) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			String link = "index";
			Usuario usuarioSessao = usuarioDao.fazerLogin(usuarioVal, senhaVal);
			if(usuarioSessao != null && usuarioSessao.getId() != null) {
				session.setAttribute("usuarioSessao",usuarioSessao);
				response.sendRedirect("/home");
			} else {
				request.setAttribute("mensagem", "Usuário(a) / Senha incorreto(s).");
				request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
			}
		}
		
		@RequestMapping(value = "/deslogar", method = {RequestMethod.GET}) // Link que irÃ¡ acessar...
		public void deslogar(HttpServletRequest request, HttpServletResponse response ) throws IOException { //Funcao e alguns valores que recebe...
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("/");
		}
		
		
		@RequestMapping(value = {"/home"}, produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Vendas
		public void home(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "usuarioVal", defaultValue = "") String usuarioVal, @RequestParam(value = "senhaVal", defaultValue = "") String senhaVal) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			String link = "pages/deslogar";
			List<Consulta> consultas = new ArrayList<Consulta>();
			Integer clientes = usuarioDao.buscarClientes().size();
			
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				
				if(usuarioSessao.getPerfil().getAdmin()) {
					consultas = consultaDao.buscarTudo();
				} else {
					consultas = consultaDao.buscarAgendaCliente(usuarioSessao.getId());
				}
				link = "pages/home";
				request.setAttribute("clientes", clientes);
				request.setAttribute("consultas", consultas);
				request.setAttribute("usuarioSessao", usuarioSessao);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}

		
		
		@RequestMapping(value = "/deletando", method = {RequestMethod.POST})
		public void deletando(HttpServletRequest request, HttpServletResponse response, String tabela,Integer id) throws ServletException, IOException { //Função e alguns valores que recebe...
			String link = "pages/deslogar";
			HttpSession session = request.getSession();
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				//Caso esteja logado.
				if(usuarioSessao.getPerfil().getFuncionario()) {
					if(tabela.equals("usuario") && usuarioSessao.getPerfil().getAdmin() ) {
						link = "pages/clientes";
						Usuario objeto = usuarioDao.findById(id).get();
						usuarioDao.delete(objeto);
						List<Usuario> usuarios = usuarioDao.buscarTudo();
						request.setAttribute("usuarios", usuarios);
						List<Preco> grupos = precoDao.buscarTudo();
						request.setAttribute("grupos", grupos);
					}
					if(tabela.equals("funcionario")  && usuarioSessao.getPerfil().getAdmin() ) {
						link = "pages/funcionarios";
						Usuario objeto = usuarioDao.findById(id).get();
						objeto.setAtivo(false);
						usuarioDao.save(objeto);
						List<Usuario> usuarios = usuarioDao.buscarFuncionarios();
						request.setAttribute("usuarios", usuarios);
						List<Preco> grupos = precoDao.buscarTudo();
						request.setAttribute("grupos", grupos);
					}
					if(tabela.equals("precos")) {
						link = "pages/precos";
						precoDao.delete(precoDao.findById(id).get());
						List<Preco> pl = precoDao.buscarTudo();
						request.setAttribute("precos", pl);
					}
					if(tabela.equals("consultas")) {
						link = "pages/minhaAgenda";
						consultaDao.delete(consultaDao.findById(id).get());
						List<Consulta> pl = consultaDao.buscarTudo();
						request.setAttribute("consultas", pl);
					}
					if(tabela.equals("pet")) {
						link = "pages/pets";
						Pet p = petDao.findById(id).get();
						System.out.println("Pet: "+p.getNome());
						List<Usuario> usupet = usuarioDao.buscarPet(id);
						System.out.println("Lista: "+usupet.size());
						for(Usuario us : usupet) {
							System.out.println("   - Dono: "+us.getNome());
							us.getPet().remove(p);
							usuarioDao.save(us);
						}
						petDao.delete(p);
						request.setAttribute("pets", petDao.findAll());
					}
				}
			}
			request.setAttribute("usuario", usuarioSessao);
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		
		@RequestMapping(value = "/clientes", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void clientes(HttpServletRequest request, HttpServletResponse response, Usuario cliente, String acao ) throws SQLException, ParseException, ServletException, IOException {
			String link = "pages/deslogar";
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				link = "pages/clientes";
				String matriculaPadrao = gerarMatricula(usuarioSessao);
				request.setAttribute("matriculaPadrao", matriculaPadrao);
				
				Boolean repetido = false;
				if(usuarioDao.buscarClientesRepetidos(cliente.getMatricula(), cliente.getCpf()).size() > 0) {
					repetido = true;
				}
				if(cliente.getMatricula() != null && (acao.equals("salvar")) && !repetido) {
					try {
				    	Usuario a = new Usuario();
						a = cliente;
						a.setSenha(cliente.getCpf().replace(".", "").replace("-", ""));
						a.setPerfil(perfilDao.buscarSomenteCliente().get(0));
						usuarioDao.save(a);
						usuarioDao.save(a);
						String msg = "Solicitação confirmada com sucesso!";
						request.setAttribute("mensagem", msg);
						request.setAttribute("tipoMensagem", "info");
					} catch(Exception e) {
					request.setAttribute("erro", e);
					}
				} else if (cliente.getMatricula() != null && (acao.equals("atualizar")) && repetido){
					Usuario a = usuarioDao.buscarMatricula(cliente.getMatricula());
					a.setNome(cliente.getNome());
					a.setDataNascimento(cliente.getDataNascimento());
					a.setTelefone(cliente.getTelefone());
					a.setCelular(cliente.getCelular());
					a.setEndereco(cliente.getEndereco());
					a.setEmail(cliente.getEmail());
					a.setPathImagem(cliente.getPathImagem());
					a.setCep(cliente.getCep());
					a.setBairro(cliente.getBairro());
					a.setCidade(cliente.getCidade());
					a.setEstado(cliente.getEstado());
					a.setOutroResponsavel(cliente.getOutroResponsavel());
					usuarioDao.save(a);
					String msg = "Atualização confirmada com sucesso!";
					request.setAttribute("mensagem", msg);
					request.setAttribute("tipoMensagem", "info");
					
				} else if(cliente.getMatricula() != null && (acao.equals("salvar")) && repetido) {
					request.setAttribute("mensagem", "Já existe este CPF / Matrícula.");
					request.setAttribute("tipoMensagem", "erro");
				}
				List<Preco> grupos = precoDao.buscarTudo();
				request.setAttribute("grupos", grupos);			
				request.setAttribute("usuario", usuarioSessao);
				List<Usuario> usuarios = usuarioDao.buscarClientes();
				request.setAttribute("usuarios", usuarios);
				request.setAttribute("usuarioSessao", usuarioSessao);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}
		
		
		@RequestMapping(value = "/funcionarios", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void funcionarios(HttpServletRequest request, HttpServletResponse response, Usuario funcionario, String acao, String perfil_codigo, String grupo_codigo) throws SQLException, ServletException, IOException {
			String link = "pages/deslogar";
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				link = "pages/funcionarios";
				//Gerando matrícula aleatória
				String matriculaPadrao = gerarMatricula(usuarioSessao);
				request.setAttribute("matriculaPadrao", matriculaPadrao);
				Boolean repetido = false;
				if(usuarioDao.buscarFuncionariosRepetidos(funcionario.getMatricula(), funcionario.getCpf()).size() > 0) {
					repetido = true;
				}
				if(funcionario.getMatricula() != null && (acao.equals("salvar")) && !repetido) {
					try {
						Usuario a = new Usuario();
						a = funcionario;
						a.setSenha(funcionario.getCpf().replace(".", "").replace("-", ""));
						if(usuarioSessao.getPerfil().getAdmin()) {
							a.setPerfil(perfilDao.buscarCodigo(perfil_codigo));
						} else {
							a.setPerfil(perfilDao.buscarFuncionario().get(0));
						}
						usuarioDao.save(a);
						String msg = "Solicitação confirmada com sucesso!";
						request.setAttribute("mensagem", msg);
						request.setAttribute("tipoMensagem", "info");
					} catch(Exception e) {
						request.setAttribute("erro", e);
						System.out.println("Erro: "+e);
					}
				} else if (funcionario.getMatricula() != null && (acao.equals("atualizar")) && repetido){
					Usuario a = usuarioDao.buscarMatricula(funcionario.getMatricula());
					a.setNome(funcionario.getNome());
					a.setDataNascimento(funcionario.getDataNascimento());
					a.setTelefone(funcionario.getTelefone());
					a.setCelular(funcionario.getCelular());
					a.setEndereco(funcionario.getEndereco());
					a.setEmail(funcionario.getEmail());
					a.setPathImagem(funcionario.getPathImagem());
					a.setCep(funcionario.getCep());
					a.setBairro(funcionario.getBairro());
					a.setCidade(funcionario.getCidade());
					a.setEstado(funcionario.getEstado());
					a.setPerfil(perfilDao.buscarCodigo(perfil_codigo));
					usuarioDao.save(a);
					String msg = "Atualização confirmada com sucesso!";
					request.setAttribute("mensagem", msg);
					request.setAttribute("tipoMensagem", "info");
				} else if(funcionario.getMatricula() != null && (acao.equals("salvar")) && repetido) {
					request.setAttribute("mensagem", "Já existe este CPF / Matrícula.");
					request.setAttribute("tipoMensagem", "erro");    
				}
				List<Usuario> usuarios = usuarioDao.buscarFuncionarios();
				request.setAttribute("usuarios", usuarios);
				
				List<Preco> grupos = precoDao.buscarTudo();
				request.setAttribute("grupos", grupos);
				
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}
		
		
		@RequestMapping(value = "/precos", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void grupos(HttpServletRequest request, HttpServletResponse response, String acao, Preco precos, String precoValor, Integer idValor) throws SQLException, ServletException, IOException {
			String link = "pages/deslogar";
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				link = "pages/precos";
				if(acao != null) {
					Preco p = new Preco();
					if(acao.equals("atualizar")) {
						p = precoDao.findById(idValor).get();
					}
					p.setAtivo(true);
					p.setPreco(Double.parseDouble(precoValor.replace(",", ".")));
					p.setNome(precos.getNome());
					precoDao.save(p);
					String msg = "Preço cadastrado com sucesso!";
					request.setAttribute("mensagem", msg);
					request.setAttribute("tipoMensagem", "info");
				}
				List<Preco> gruposTodos = precoDao.buscarTudo();
				request.setAttribute("grupos", gruposTodos);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}
		
		
		@RequestMapping(value = "/alterarSenha", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void alterarSenha(HttpServletRequest request, HttpServletResponse response, Integer acao, String matricula,String senha,String novaSenha,String confirmaSenha) throws SQLException, ServletException, IOException {
			String link = "pages/deslogar";
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				link = "pages/alterarSenha";
				String msg = "";
				if(acao != null) {
					if(acao > 0) {
						Usuario u = usuarioDao.fazerLogin(matricula, senha);
						if(u != null && (novaSenha.equals(confirmaSenha)) ) {
							u.setSenha(novaSenha);
							usuarioDao.save(u);
							msg = "Senha alterada com sucesso!";
							request.setAttribute("mensagem", msg);
							request.setAttribute("tipoMensagem", "info");
						} else {
							msg = "Usuário / Senha inválida!";
							request.setAttribute("mensagem", msg);
							request.setAttribute("tipoMensagem", "erro");
						}
					}
				}
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}
		
		
		
		@RequestMapping(value = "/agendamento", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void agendamento(HttpServletRequest request, HttpServletResponse response, Boolean salvar, Boolean proximo, Boolean anterior, Integer mesAtual,   String nomeCliente, String dataSubmit, String horaEscolhida, String servicoSelecionado, Integer petSelecionado,  String profissionalSelecionado, String precoSubmit, String obs ) throws SQLException, ParseException, IOException, ServletException {
			//Salvando ------------------------------------------------------------
			HttpSession session = request.getSession();
			String link = "pages/deslogar";
			Integer mesSelecionado = 0;
			Integer anoSelecionado = 0;
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				if(session.getAttribute("mesSelecionado") != null) {
					mesSelecionado = (Integer) session.getAttribute("mesSelecionado");
				}
				if(session.getAttribute("anoSelecionado") != null) {
					anoSelecionado = (Integer) session.getAttribute("anoSelecionado");
				}
				String msg = "";
				Boolean erro = false;
				try {
					Integer i = Integer.parseInt(servicoSelecionado);
				}catch(Exception e) {
					if(profissionalSelecionado != null) {
						salvar = null;
						msg = "Serviço não selecionado.";
						erro = true;
					}
				}
				if(salvar!= null && salvar) {
					Consulta c = new Consulta ();
					c.setAtivo(true);
					c.setCancelado(false);
					c.setCliente(nomeCliente);
					if(usuarioSessao.getPerfil().getCliente() && !usuarioSessao.getPerfil().getAdmin() && !usuarioSessao.getPerfil().getFuncionario()) {
						c.setClienteSistema(usuarioSessao);
					}
					c.setConfirmado(false);
					
					String str = dataSubmit+" 00:00";
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
					LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
					c.setData(dateTime);
					
					str = dataSubmit+" "+horaEscolhida;
					dateTime = LocalDateTime.parse(str, formatter);
					c.setInicio(dateTime);
					c.setFim(dateTime);
					c.setPreco(precoDao.findById(Integer.parseInt(servicoSelecionado)).get().getPreco());
					if(!profissionalSelecionado.equals("")) {
						c.setProfissional(usuarioDao.findById(Integer.parseInt(profissionalSelecionado)).get());
					}
					c.setServico(precoDao.findById(Integer.parseInt(servicoSelecionado)).get());
					c.setObservacoes(obs);
					
					if(petSelecionado != null && petSelecionado > 0) {
						c.setPet(petDao.findById(petSelecionado).get());
					}
					//Favor validar se este profissional possui data disponivel neste periodo antes de salvar
					String s = dataSubmit.substring(6, 10) + "-" + dataSubmit.substring(3, 5) + "-" + dataSubmit.substring(0, 2);
					List<Consulta> consultas = consultaDao.buscarInvalidos(usuarioSessao.getId(),s,horaEscolhida);
					
					if(consultas.size() == 0) {
						consultaDao.save(c);
						msg = "Solicitado a reserva.";
						erro = false;
					} else {
						msg = "Data e Horário inválidos.";
						erro = true;
					}
					
				}
				//Salvando ------------------------------------------------------------
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");	
			    Calendar calendar = new GregorianCalendar();
			    
				if(proximo != null && proximo) {
					mesSelecionado = mesSelecionado+1;
					if(mesSelecionado > 12) {
						mesSelecionado = 1;
						anoSelecionado = anoSelecionado + 1;
					}
				}
				if(anterior != null && anterior) {
					mesSelecionado = mesSelecionado-1;
					if(mesSelecionado < 1) {
						mesSelecionado = 12;
						anoSelecionado = anoSelecionado - 1;
					}
				}
				if((proximo == null && anterior == null) || calendar.get(Calendar.MONTH) == (mesSelecionado-1) ) {
					proximo = null;
					anterior = null;
					resetaMes(session, mesSelecionado, anoSelecionado);
				}
				
				link = "pages/agendamento";
				int maxDiasMes = 28;
				List<Integer> listaDias = new ArrayList<Integer>();
					
				int ano = anoSelecionado;
				int mes = 0;
				int dia = 1;
				    
				if(proximo == null && anterior == null ) {
				   	mes = calendar.get(Calendar.MONTH);
				   	mes++;
				   	dia = calendar.get(Calendar.DAY_OF_MONTH);
				} else {
				   	mes = mesSelecionado;
				   	dia = 1;
				}
				String semana = "";
				String mesStr = "";
				//String dia val
					String diaVal = "";
					if(dia < 10) {
						diaVal = "0"+dia;
					} else {
						diaVal = ""+dia;
					}
				//Primeiro dia da semana do mes:
				    String diaPrimeiroSemana = "---";
				    GregorianCalendar gc = new GregorianCalendar();
				    GregorianCalendar primeiro = new GregorianCalendar();
				    if((proximo == null && anterior == null) || calendar.get(Calendar.MONTH) == (mesSelecionado-1) ) {
				    	gc.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(diaVal+"/"+mes+"/"+ano));
				    } else {
				    	gc.setTime(new SimpleDateFormat("dd/MM/yyyy").parse("01/"+mes+"/"+ano));
				    }
				    int semanaVal  = gc.get(Calendar.DAY_OF_WEEK);
				    
				    primeiro.setTime(new SimpleDateFormat("dd/MM/yyyy").parse("01/"+mes+"/"+ano));
				    switch (primeiro.get(Calendar.DAY_OF_WEEK)) {
			            case Calendar.SUNDAY:
			            	diaPrimeiroSemana = "DOM";
			                break;
			            case Calendar.MONDAY:
			            	diaPrimeiroSemana = "SEG";
			                break;
			            case Calendar.TUESDAY:
			            	diaPrimeiroSemana = "TER";
			            break;
			            case Calendar.WEDNESDAY:
			            	diaPrimeiroSemana = "QUA";
			                break;
			            case Calendar.THURSDAY:
			            	diaPrimeiroSemana = "QUI";
			                break;
			            case Calendar.FRIDAY:
			            	diaPrimeiroSemana = "SEX";
			                break;
			            case Calendar.SATURDAY:
			            	diaPrimeiroSemana = "SAB";
				    }
				    
				    switch(semanaVal) {
					    case 1:
					    	semana = "Domingo";
					    	break;
					    case 2:
					    	semana = "Segunda-Feira";
					    	break;
					    case 3:
					    	semana = "Terça-Feira";
					    	break;
					    case 4:
					    	semana = "Quarta-Feira";
					    	break;
					    case 5:
					    	semana = "Quinta-Feira";
					    	break;
					    case 6:
					    	semana = "Sexta-Feira";
					    	break;
					    case 7:
					    	semana = "Sábado";
					    	break;
					    default:
					}
				    
				    switch(mes) {
					    case 1:
					    	mesStr = "Janeiro";
					    	break;
					    case 2:
					    	mesStr = "Fevereiro";
					    	break;
					    case 3:
					    	mesStr = "Mar&ccedil;o";
					    	break;
					    case 4:
					    	mesStr = "Abril";
					    	break;
					    case 5:
					    	mesStr = "Maio";
					    	break;
					    case 6:
					    	mesStr = "Junho";
					    	break;
					    case 7:
					    	mesStr = "Julho";
					    	break;
					    case 8:
					    	mesStr = "Agosto";
					    	break;
					    case 9:
					    	mesStr = "Setembro";
					    	break;
					    case 10:
					    	mesStr = "Outubro";
					    	break;
					    case 11:
					    	mesStr = "Novembro";
					    	break;
					    case 12:
					    	mesStr = "Dezembro";
					    	break;
					    default:
				  }
				  
				  if(proximo == null && anterior == null ) {
					  Calendar datas = new GregorianCalendar();
				      datas.set(Calendar.MONTH, Calendar.MONTH);//2
				      maxDiasMes = datas.getActualMaximum (Calendar.DAY_OF_MONTH);
				      for(int i = 1; i <= maxDiasMes; i++) {
				        	listaDias.add(i);
				      }
				  } else {
					  GregorianCalendar gcb = new GregorianCalendar();
					  gcb.setTime(new SimpleDateFormat("dd/MM/yyyy").parse("01/"+mes+"/"+ano));
				      maxDiasMes = gcb.getActualMaximum (Calendar.DAY_OF_MONTH);
				      for(int i = 1; i <= maxDiasMes; i++) {
				        	listaDias.add(i);
				      }
				  }
				    
			      request.setAttribute("mesSelecionado", mesSelecionado);
				  request.setAttribute("maxDiasMes", maxDiasMes);
				  request.setAttribute("hoje", "Dia " + diaVal + ": " + semana);
				  request.setAttribute("diaVal", diaVal);
				  request.setAttribute("dia", dia);
				  request.setAttribute("mes", mesStr);
				  if(mes < 10) {
					  request.setAttribute("mesNumero", "0"+mes);
				  } else {
					  request.setAttribute("mesNumero", mes);
				  }
				  request.setAttribute("ano", ano);
				  request.setAttribute("listaDias", listaDias);
				  request.setAttribute("diaPrimeiroSemana", diaPrimeiroSemana);
				  
				  List<Usuario> funcionarios = usuarioDao.buscarFuncionarios();
				  request.setAttribute("funcionarios", funcionarios);
				  List<Preco> precos = precoDao.buscarTudo();
				  request.setAttribute("precos", precos);
				  List<Consulta> consultas = consultaDao.buscarTudo();
				  request.setAttribute("consultas", consultas);
				  request.setAttribute("mensagem", msg);
				  if(erro) {
					  request.setAttribute("tipoMensagem", "erro");  
				  } else {
					  request.setAttribute("tipoMensagem", "info");
				  }
				  
				session.setAttribute("mesSelecionado",mesSelecionado);
				session.setAttribute("anoSelecionado",anoSelecionado);
				request.setAttribute("itemMenuSelecionado", "home");
				request.getRequestDispatcher("/WEB-INF/jsp/pages/agendamento.jsp").forward(request, response);
			} else {
				response.sendRedirect("/");
			}
		}
		
		
		@RequestMapping(value = "/minhaAgenda", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void minhaAgenda(HttpServletRequest request, HttpServletResponse response, String acao, String tabelaSolicitada,Integer idValor, String data_str, String inicioHora_str, String fimHora_str, String cliente_str, String servico_str, String preco_str, String observacao_str) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			String link = "pages/deslogar";
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				link = "pages/minhaAgenda";
				
				//... Salvando dados.
				if(acao != null) {
					if(acao.equals("salvar")) {
						Consulta c = consultaDao.findById(idValor).get();
						Boolean valido = false;
						List<Consulta> validacao = consultaDao.buscarInvalidosDuasDatas(idValor ,usuarioSessao.getId(), data_str, inicioHora_str, fimHora_str);
						if( validacao.size() == 0 ) {
							valido = true;
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
							LocalDateTime dateTime = LocalDateTime.parse(data_str+" "+inicioHora_str, formatter);
							c.setInicio(dateTime);
							dateTime = LocalDateTime.parse(data_str+" "+fimHora_str, formatter);
							c.setFim(dateTime);
							formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
							dateTime = LocalDateTime.parse(data_str+" 00:00", formatter);
							c.setData(dateTime);
						}preco_str = preco_str.replace("R$", "").replace(",", ".");
						Double va = Double.parseDouble(preco_str);
						if(va >= 0) {
							c.setPreco(va);
						} else {
							 String msg = "O Valor deve ser maior ou igual a zero.";
							 request.setAttribute("mensagem", msg);
							 request.setAttribute("tipoMensagem", "erro");  
						}
						c.setObservacoes(observacao_str);
						if(tabelaSolicitada.equals("confirmar") && valido && va >= 0) {
							c.setProfissional(usuarioSessao);
							c.setConfirmado(true);
							c.setCancelado(false);
							consultaDao.save(c);
							 String msg = "Solicitação confirmada com sucesso!";
							 request.setAttribute("mensagem", msg);
							 request.setAttribute("tipoMensagem", "info");
						}
						if(tabelaSolicitada.equals("confirmar") && !valido) {
							 String msg = "Data inválida.";
							 request.setAttribute("mensagem", msg);
							 request.setAttribute("tipoMensagem", "erro");
						}
						if(tabelaSolicitada.equals("recusar")) {
							if(c.getProfissional() != null) {
								c.setConfirmado(false);
								c.setCancelado(true);
								consultaDao.save(c);
								 String msg = "Solicitação recusada com sucesso.";
								 request.setAttribute("mensagem", msg);
								 request.setAttribute("tipoMensagem", "erro");  
							}
						}
					}
				}
				
				
				List<Consulta> consultas = consultaDao.buscarMinhaAgendaOrdenadaData(usuarioSessao.getId());
				if(usuarioSessao.getPerfil().getAdmin()) {
					consultas = consultaDao.buscarTudo();
				} else {
					consultas = consultaDao.buscarAgendaCliente(usuarioSessao.getId());
				}
				request.setAttribute("consultas", consultas);
				request.setAttribute("usuario", usuarioSessao);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}
		
		
		
		@RequestMapping(value = "/meu_registro", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void meu_registro(HttpServletRequest request, HttpServletResponse response, Usuario funcionario, String acao, String perfil_codigo, String grupo_codigo) throws SQLException, ServletException, IOException {
			String link = "pages/deslogar";
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				link = "pages/meu_registro";
				//Gerando matrícula aleatória
				String matriculaPadrao = gerarMatricula(usuarioSessao);
				request.setAttribute("matriculaPadrao", matriculaPadrao);
				
				Boolean repetido = false;
				if(usuarioDao.buscarFuncionariosRepetidos(funcionario.getMatricula(), funcionario.getCpf()).size() > 0) {
					repetido = true;
				}
				if(funcionario.getMatricula() != null && (acao.equals("salvar")) && !repetido) {
					try {
						Usuario a = new Usuario();
						a = funcionario;
						a.setSenha(funcionario.getCpf().replace(".", "").replace("-", ""));
						if(usuarioSessao.getPerfil().getAdmin()) {
							a.setPerfil(perfilDao.buscarCodigo(perfil_codigo));
						} else {
							a.setPerfil(perfilDao.buscarFuncionario().get(0));
						}
						usuarioDao.save(a);
						String msg = "Solicitação confirmada com sucesso!";
						request.setAttribute("mensagem", msg);
						request.setAttribute("tipoMensagem", "info");
					} catch(Exception e) {
						request.setAttribute("erro", e);
						System.out.println("Erro: "+e);
					}
				} else if (funcionario.getMatricula() != null && (acao.equals("atualizar")) && repetido){
					Usuario a = usuarioDao.findById(usuarioSessao.getId()).get();
					a.setNome(funcionario.getNome());
					a.setDataNascimento(funcionario.getDataNascimento());
					a.setTelefone(funcionario.getTelefone());
					a.setCelular(funcionario.getCelular());
					a.setEndereco(funcionario.getEndereco());
					a.setEmail(funcionario.getEmail());
					a.setPathImagem(funcionario.getPathImagem());
					a.setCep(funcionario.getCep());
					a.setBairro(funcionario.getBairro());
					a.setCidade(funcionario.getCidade());
					a.setEstado(funcionario.getEstado());
					a.setOutroResponsavel(funcionario.getOutroResponsavel());
					a.setPerfil(perfilDao.buscarCodigo(perfil_codigo));
					usuarioDao.save(a);
					String msg = "Atualização confirmada com sucesso!";
					request.setAttribute("mensagem", msg);
					request.setAttribute("tipoMensagem", "info");
				} else if(funcionario.getMatricula() != null && (acao.equals("salvar")) && repetido) {
					request.setAttribute("mensagem", "Já existe este CPF / Matrícula.");
					request.setAttribute("tipoMensagem", "erro");    
				}
				List<Usuario> usuarios = usuarioDao.buscarFuncionarios();
				request.setAttribute("usuarios", usuarios);
				
				List<Preco> grupos = precoDao.buscarTudo();
				request.setAttribute("grupos", grupos);
				
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}
		
		
		
		@RequestMapping(value = "/meus_pets", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET}) // Pagina de Vendas
		public void meus_pets(HttpServletRequest request, HttpServletResponse response, Usuario funcionario, String acao, String perfil_codigo, String grupo_codigo) throws SQLException, ServletException, IOException {
			String link = "pages/deslogar";
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				link = "pages/meus_pets";
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}
		
		
		@RequestMapping(value = "/pets", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET}) // Pagina de Vendas
		public void pets(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			String link = "pages/deslogar";
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				if(usuarioSessao.getPerfil().getFuncionario()) {
					link = "pages/pets";
					request.setAttribute("pets", petDao.findAll());
					
				} else {
					link = "pages/home";
					request.setAttribute("mensagem", "Você não possui permissão para essa página.");
				}
				
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}
		
		
		@RequestMapping(value = "/pet_{idPet}", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET}) // Pagina de Vendas
		public void petSelecionado(HttpServletRequest request, HttpServletResponse response, Usuario funcionario, String acao, @PathVariable("idPet") Integer idPet) throws SQLException, ServletException, IOException {
			String link = "pages/deslogar";
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				Pet pet = petDao.findById(idPet).get();
				Boolean encontrado = false;
				
				
				for(Pet pts : usuarioSessao.getPet() ) {
					if(	pts.getId()	== pet.getId()) {
						encontrado = true;
					}
				}
				
				if(usuarioSessao.getPerfil().getFuncionario()) {
					encontrado = true;
					Usuario responsavelPet = usuarioDao.buscarPet(idPet).get(0);
					request.setAttribute("responsavelPet", responsavelPet);
				}
				if(encontrado) {
					link = "pages/petSelecionado";
					request.setAttribute("vacinas", vacinaDao.findAll());
					request.setAttribute("petSelecionado", pet);
				} else {
						request.setAttribute("mensagem", "O pet não corresponde ao seu login.");
						link = "pages/home";
				}
			}
			//JSP que irá acessar.
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); //retorna a variavel
		}
		
		
		
		@RequestMapping(value = "/atualizar_pet", produces = "text/plain;charset=UTF-8", method = {RequestMethod.POST}) // Pagina de Vendas
		public void atualizar_pet(HttpServletRequest request, HttpServletResponse response, String cpf, Pet petOriginal, Integer idPet, String pet_dataNascimento, String pet_vacina, String pet_responsaveis) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			if(session.getAttribute("usuarioSessao") != null) {
				Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
				usuarioSessao = usuarioDao.findById(usuarioSessao.getId()).get();
				request.setAttribute("usuarioSessao", usuarioSessao);
				Boolean encontrado = false;
				Pet pet = petDao.findById(idPet).get();
				for(Pet p : usuarioSessao.getPet()) {
					if(p.getId() == pet.getId()) {
						encontrado = true;
					}
				}
				
				if(usuarioSessao.getPerfil().getFuncionario()) {
					encontrado = true;
				}
				
				if(encontrado) {
					Pet petAtualizar = new Pet();
					if(petOriginal != null && petOriginal.getId() != null) {
						petAtualizar = petDao.findById(petOriginal.getId()).get();
					}
					if(petAtualizar.getId() == null ) {
						petAtualizar = petDao.findById(idPet).get();
					}
					petAtualizar.setEspecie(petOriginal.getEspecie());
					petAtualizar.setNome(petOriginal.getNome());
					petAtualizar.setGenero(petOriginal.getGenero());
					petAtualizar.setRaca(petOriginal.getRaca());
					petAtualizar.setPeso(petOriginal.getPeso());
					petAtualizar.setCastracao(petOriginal.getCastracao());
					petAtualizar.setNascimento(petOriginal.getNascimento());
					petAtualizar.setPathImagem(petOriginal.getPathImagem());
					petAtualizar.setObservacoes(petOriginal.getObservacoes());
					
					if(pet_dataNascimento != null) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
						LocalDateTime dateTime = LocalDateTime.parse(pet_dataNascimento+" 00:00", formatter);
						petAtualizar.setNascimento(dateTime);
					}
					
					List<Vacina> listaVac = new ArrayList<Vacina>();
					System.out.println("pet_vacina: "+pet_vacina);
					if(pet_vacina != null && !pet_vacina.equals("")) {
						for(String strVac : pet_vacina.split(",")) {
							Vacina v = vacinaDao.findById(Integer.parseInt(strVac)).get();
							listaVac.add(v);
						}
						petAtualizar.setVacina(listaVac);
					} else {
						petAtualizar.setVacina(listaVac);
					}
					
					
					List<Usuario> listaRes = new ArrayList<Usuario>();
					
					
					if(cpf != null && !cpf.equals("")) {
						Usuario usuAdd = usuarioDao.buscarCpf(cpf);
						petAtualizar.setResponsavel(usuAdd);
					}
					
					petDao.save(petAtualizar);
					
					response.sendRedirect("/pet_"+idPet);
				} else {
					response.sendRedirect("/deslogar");
				}
			} else {
				response.sendRedirect("/");
			}
		}
		
}
	
	
	




