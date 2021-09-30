package br.com.foursys.locadora.util;

/**
 *
 * @author Kaue Schneider Nobrega
 * @since 29 de abr. de 2021
 * @version 1.0
 */
public enum Sexo {
	MASCULINO("Masculino"),
	FEMININO("Feminino");
	
	private String descricao;

	Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
