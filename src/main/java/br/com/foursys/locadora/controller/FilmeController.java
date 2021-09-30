package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.dao.FilmeDAO;

/**
 * Classe respons�vel por acessar o objeto DAO e efetuar altera��o e consultas
 * na base de dados
 *
 * @author Kaue Schneider Nobrega
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class FilmeController {
	
	public void salvar(Filme filme) {
		try {
			new FilmeDAO().salvar(filme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Filme> buscarPorNome(String nome) {
		ArrayList<Filme> retorno = new ArrayList<Filme>();
		try {
			retorno = new FilmeDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public ArrayList<Filme> buscarPorNome(String nome, String disponivel) {
		ArrayList<Filme> retorno = new ArrayList<Filme>();
		try {
			retorno = new FilmeDAO().buscarPorNome(nome, disponivel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public ArrayList<Filme> buscarTodos(){
        ArrayList<Filme> retorno = new ArrayList<Filme>();
        
        try {
			retorno = new FilmeDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return retorno;
	}
	
	public void excluir(Filme filme) {
        //objeto DAO para inserir um registro
        FilmeDAO dao = new FilmeDAO();

        try {
            //excluindo um registro na tabela
            dao.excluir(filme);
            // mensagem de sucesso
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	public ArrayList<Filme> buscarDisponivel(String disponivel) {
		ArrayList<Filme> retorno = new ArrayList<Filme>();
		try {
			retorno = new FilmeDAO().buscarDisponivel(disponivel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
