package brandaoti.sistema.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //Esse número é o ID automático gerado.
	
	@Column
	private String nome;
	
	@Column
	private String especie;
	
	@Column
	private String genero;
	
	@Column
	private String raca;
	
	@Column
	private Double peso = 0.0;
	
	@Column
	private Boolean castracao = false;
	
	@Column
	private LocalDateTime nascimento = LocalDateTime.now(); 
	
	@ManyToMany
	(fetch = FetchType.LAZY)
	private List<Vacina> vacina;
	
	@ManyToMany
	(fetch = FetchType.LAZY)
	private List<Usuario> responsaveis;
	
	@Column
	private String pathImagem = "/assets/images/avatar-1.jpg";
	
	@Column
	private String observacoes;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	
	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Boolean getCastracao() {
		return castracao;
	}

	public void setCastracao(Boolean castracao) {
		this.castracao = castracao;
	}

	public List<Vacina> getVacina() {
		return vacina;
	}

	public void setVacina(List<Vacina> vacina) {
		this.vacina = vacina;
	}

	public List<Usuario> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Usuario> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public String getPathImagem() {
		return pathImagem;
	}

	public void setPathImagem(String pathImagem) {
		this.pathImagem = pathImagem;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public LocalDateTime getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDateTime nascimento) {
		this.nascimento = nascimento;
	}

	
	
	
	
}
