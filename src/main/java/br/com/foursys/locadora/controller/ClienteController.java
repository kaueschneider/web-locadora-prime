package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.dao.ClienteDAO;

/**
 *
 * @author Kaue Schneider Nobrega
 * @since 29 de abr. de 2021
 * @version 1.0
 */
public class ClienteController {
	
	public void salvar(Cliente cliente) {
		try {
			new ClienteDAO().salvar(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cliente> buscarPorNome(String nome){
        ArrayList<Cliente> retorno = new ArrayList<Cliente>();
        
        try {
			retorno = new ClienteDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return retorno;
	}
	
	public void excluir(Cliente cliente) {
        //objeto DAO para inserir um registro
        ClienteDAO dao = new ClienteDAO();

        try {
            //excluindo um registro na tabela
            dao.excluir(cliente);
            // mensagem de sucesso
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	public ArrayList<Cliente> buscarTodos() {
		ArrayList<Cliente> retorno = new ArrayList<Cliente>();
		try {
			retorno = new ClienteDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
