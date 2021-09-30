package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.util.HibernateUtil;

/**
 *
 * @author Kaue Schneider Nobrega
 * @since 29 de abr. de 2021
 * @version 1.0
 */
public class EstadoDAO {
	
	@SuppressWarnings("unchecked")
    public ArrayList<Estado> buscarTodos() throws Exception {

        ArrayList<Estado> listaRetorno = new ArrayList<Estado>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Estado.class);
        criteria.addOrder(Order.asc("idEstado"));
        listaRetorno = (ArrayList<Estado>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    @SuppressWarnings("unchecked")
	public ArrayList<Estado> buscarPorNome(String nome) throws Exception {

        ArrayList<Estado> listaRetorno = new ArrayList<Estado>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Estado.class);
        criteria.add(Restrictions.like("nome", nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Estado>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public Estado buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Estado filme = (Estado) sessao.get(Estado.class, codigo);
        sessao.close();
        return filme;
    }
    
    
	
	
}
