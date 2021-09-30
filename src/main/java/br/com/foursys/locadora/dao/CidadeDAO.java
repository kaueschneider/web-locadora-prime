package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.util.HibernateUtil;

public class CidadeDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Cidade> buscarTodos() throws Exception {

        ArrayList<Cidade> listaRetorno = new ArrayList<Cidade>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Cidade.class);
        criteria.addOrder(Order.asc("idCidade"));
        listaRetorno = (ArrayList<Cidade>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    @SuppressWarnings("unchecked")
	public ArrayList<Cidade> buscarPorNome(String nome) throws Exception {

        ArrayList<Cidade> listaRetorno = new ArrayList<Cidade>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Cidade.class);
        criteria.add(Restrictions.like("nome", nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Cidade>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public Cidade buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Cidade filme = (Cidade) sessao.get(Cidade.class, codigo);
        sessao.close();
        return filme;
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<Cidade> buscarPorEstado(Estado estado) throws Exception {
        ArrayList<Cidade> listaRetorno = new ArrayList<Cidade>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Cidade.class);
        criteria.add(Restrictions.eq("estadoIdEstado", estado));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Cidade>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

}
