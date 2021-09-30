package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.dao.EnderecoDAO;

/**
 * Classe respons�vel por acessar o objeto DAO e efetuar altera��o e consultas
 * na base de dados
 *
 * @author Kaue Schneider Nobrega
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class EnderecoController {
	
	public void salvar(Endereco endereco) {
		try {
			new EnderecoDAO().salvar(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Endereco> buscarPorNome(String nome){
        ArrayList<Endereco> retorno = new ArrayList<Endereco>();
        
        try {
			retorno = new EnderecoDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return retorno;
	}
	
	public void excluir(Endereco endereco) {
        //objeto DAO para inserir um registro
        EnderecoDAO dao = new EnderecoDAO();

        try {
            //excluindo um registro na tabela
            dao.excluir(endereco);
            // mensagem de sucesso
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
