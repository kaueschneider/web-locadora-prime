package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.dao.ContatoDAO;

/**
 * Classe respons�vel por acessar o objeto DAO e efetuar altera��o e consultas
 * na base de dados
 *
 * @author Kaue Schneider Nobrega
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class ContatoController {
	
	public void salvar(Contato contato) {
		try {
			new ContatoDAO().salvar(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Contato> buscarPorNome(String nome){
        ArrayList<Contato> retorno = new ArrayList<Contato>();
        
        try {
			retorno = new ContatoDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return retorno;
	}
	
	public void excluir(Contato contato) {
        //objeto DAO para inserir um registro
        ContatoDAO dao = new ContatoDAO();

        try {
            //excluindo um registro na tabela
            dao.excluir(contato);
            // mensagem de sucesso
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
