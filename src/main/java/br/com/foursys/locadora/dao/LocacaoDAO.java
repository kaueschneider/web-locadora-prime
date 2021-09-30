package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.util.HibernateUtil;

public class LocacaoDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<Locacao> buscarTodos() throws Exception {

        ArrayList<Locacao> listaRetorno = new ArrayList<Locacao>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Locacao.class);
        criteria.addOrder(Order.asc("idLocacao"));
        listaRetorno = (ArrayList<Locacao>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    @SuppressWarnings("unchecked")
	public ArrayList<Locacao> buscarPorNome(String nome) throws Exception {

        ArrayList<Locacao> listaRetorno = new ArrayList<Locacao>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Locacao.class);
        criteria.add(Restrictions.like("nome", nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Locacao>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public Locacao buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Locacao locacao = (Locacao) sessao.get(Locacao.class, codigo);
        sessao.close();
        return locacao;
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<Locacao> buscarDevolvido(String devolvido) throws Exception {
        ArrayList<Locacao> listaRetorno = new ArrayList<Locacao>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Locacao.class);
        criteria.add(Restrictions.eq("devolvido", devolvido));
        criteria.addOrder(Order.asc("idLocacao"));
        listaRetorno = (ArrayList<Locacao>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

}
