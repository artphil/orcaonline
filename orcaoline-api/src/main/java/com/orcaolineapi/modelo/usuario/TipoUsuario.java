package com.orcaolineapi.modelo.usuario;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.orcaolineapi.modelo.AbstractModel;

@Entity
@Table(name="tipo_usuario")
public class TipoUsuario extends AbstractModel{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;
	
	private String descricao;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "permissao_tipo_usuario", joinColumns = @JoinColumn(name = "id_tipo_usuario"), inverseJoinColumns = @JoinColumn(name = "id_permissao"))
	private List<Permissao> permissoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
}
