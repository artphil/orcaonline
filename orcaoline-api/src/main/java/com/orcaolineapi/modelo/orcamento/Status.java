package com.orcaolineapi.modelo.orcamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.orcaolineapi.modelo.AbstractModel;

@Entity
public class Status extends AbstractModel {

	public static final Long STATUS_ABERTO = 1L;
	public static final Long STATUS_ATIVO = 2L;
	public static final Long STATUS_FECHADO = 3L;
	public static final Long STATUS_CANCELADO = 4L;
	public static final Long STATUS_INATIVO = 5L;
	public static final Long STATUS_EM_ANDAMENTO = 6L;

	public static final Status ABERTO = new Status(STATUS_ABERTO);
	public static final Status ATIVO = new Status(STATUS_ATIVO);
	public static final Status FECHADO = new Status(STATUS_FECHADO);
	public static final Status CANCELADO = new Status(STATUS_CANCELADO);
	public static final Status INATIVO = new Status(STATUS_INATIVO);
	public static final Status EM_ANDAMENTO = new Status(STATUS_EM_ANDAMENTO);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 0, max = 200)
	@Pattern(regexp = "^$|[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
	private String nome;

	public Status() {
	}

	public Status(String nome) {
		this.nome = nome;
	}

	public Status(Long idStatus) {
		id = idStatus;
		nome = getNome(id);
	}

	public static String getNome(Long id) {
		if (STATUS_ABERTO.equals(id)) {
			return "Aberto";
		} else if (STATUS_ATIVO.equals(id)) {
			return "Ativo";
		} else if (STATUS_FECHADO.equals(id)) {
			return "Fechado";
		} else if (STATUS_CANCELADO.equals(id)) {
			return "Cancelado";
		} else if (STATUS_INATIVO.equals(id)) {
			return "Inativo";
		} else if (STATUS_EM_ANDAMENTO.equals(id)) {
			return "Em andamento";
		}
		return "Sem status";
	}

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
}