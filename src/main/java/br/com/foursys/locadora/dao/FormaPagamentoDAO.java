package br.com.foursys.locadora.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.util.HibernateUtil;

public class FormaPagamentoDAO extends GenericDAO {

    @SuppressWarnings("unchecked")
    public ArrayList<FormaPagamento> buscarTodos() throws Exception {

        ArrayList<FormaPagamento> listaRetorno = new ArrayList<FormaPagamento>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(FormaPagamento.class);
        criteria.addOrder(Order.asc("idFormaPagamento"));
        listaRetorno = (ArrayList<FormaPagamento>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    @SuppressWarnings("unchecked")
	public ArrayList<FormaPagamento> buscarPorNome(String nome) throws Exception {

        ArrayList<FormaPagamento> listaRetorno = new ArrayList<FormaPagamento>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(FormaPagamento.class);
        criteria.add(Restrictions.like("nome", nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<FormaPagamento>) criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public FormaPagamento buscarPorCodigo(int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        FormaPagamento filme = (FormaPagamento) sessao.get(FormaPagamento.class, codigo);
        sessao.close();
        return filme;
    }

}
