package br.com.foursys.locadora.util;

/**
 *
 * @author Kaue Schneider Nobrega
 * @since 29 de abr. de 2021
 * @version 1.0
 */
public enum Perfil {
	ADMIN("Administrador"),
    DEV("Desenvolvedor"),
	USER("Usuário");
	
	private String descricao;
	
	Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
