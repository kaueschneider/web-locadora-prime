package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.dao.EstadoDAO;

/**
 *
 * @author Kaue Schneider Nobrega
 * @since 29 de abr. de 2021
 * @version 1.0
 */
public class EstadoController {
	/*
	 * método para retornar os estados gravados na tabela
	 */
	public ArrayList<Estado> buscarTodos() {
		// lista auxiliar para retornar no método
		ArrayList<Estado> retorno = null;

		try {
			retorno = new EstadoDAO().buscarTodos();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return retorno;
	}
}
