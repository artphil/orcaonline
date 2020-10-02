package com.orcaolineapi.modelo.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.orcaolineapi.modelo.AbstractModel;
import com.orcaolineapi.modelo.orcamento.Status;
import com.orcaolineapi.security.util.GeradorSenha;

@Entity
public class Usuario extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 5, max = 150)
	@Email
	@NotBlank
	private String email;

	@Size(min = 6, max = 300)
	@NotBlank
	private String senha;

	@Digits(integer = 14, fraction = 0)
	@Size(min = 14, max = 14)
	private String cnpj;

	@Size(min = 0, max = 300)
	@Pattern(regexp = "[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
	private String nome;

	@Size(min = 0, max = 300)
	@Pattern(regexp = "[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
	private String nomeFantasia;

	@ManyToOne
	@JoinColumn(name = "id_tipo_usuario")
	@NonNull
	private TipoUsuario tipoUsuario;

	public Usuario() {

	}

	public Usuario(String email, String senha, String cnpj, String razaoSocial, String nomeFantasia,
			TipoUsuario tipoUsuario) {
		this.email = email;
		this.senha = senha;
		this.cnpj = cnpj;
		this.nome = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.tipoUsuario = tipoUsuario;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public static List<Status> usedStatus() {
		List<Status> list = new ArrayList<>();
		list.add(Status.ATIVO);
		list.add(Status.INATIVO);
		return list;
	}
	
	public void encodaSenha() {
		setSenha(GeradorSenha.gerarSenha(getSenha()));
	}
}
