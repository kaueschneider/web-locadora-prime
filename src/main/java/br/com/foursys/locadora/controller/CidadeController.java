package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.dao.CidadeDAO;

/**
 *
 * @author Kaue Schneider Nobrega
 * @since 29 de abr. de 2021
 * @version 1.0
 */
public class CidadeController {
	/*
	    * método para retornar os estados gravados na tabela
	    */
	    public ArrayList<Cidade> buscarPorEstado(Estado estado) {
	        //lista auxiliar para retornar no método
	        ArrayList<Cidade> retorno = null;
	        
	        try {
	            //lista de retorno de cidades por estado
	            retorno = new CidadeDAO().buscarPorEstado(estado);
	        } catch (Exception ex) {
	            //exibindo erro no caso de exceção
	        	ex.printStackTrace();
	        }
	        
	        return retorno;
	    }
}
