package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.FormaPagamentoController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Util;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe responsavel por controlar os componentes do front-end " cad-filme"
 *
 * @author Kaue Schneider Nobrega
 * @since 27 de abr. de 2021
 * @version 1.0
 */

@ManagedBean(name = "locacaoBacking")
@ViewScoped
public class LocacaoBacking implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int tipoPesquisa;
	private String dataDevolucao;
	private String nomeCliente;
	private String devolvido;
	private Date dataInicial;
	private Date dataFinal;
	private boolean data;
	private String dataAtual;
	public static Locacao locacaoSelecionada;
	private ArrayList<Locacao> listaLocacoes;

	private int clienteCombo;
	private int filmeCombo;
	private String valor;
	private String dataLocacao;
	private int formaPagamentoCombo;

	private double valorTotal;
	private boolean bloqueio;

	private Date minDate;
	private Date maxDate;

	private Locacao locacao;
	private Filme filmeSelecionado;

	private ArrayList<Cliente> listaClientes;
	private ArrayList<Filme> listaFilmes;
	private ArrayList<Filme> listaFilmesLocacao;
	private ArrayList<FormaPagamento> listaFormaPagamento;
	
	/*
	 * método construtor da classe
	 */
	public LocacaoBacking() {
		carregarTela();
		carregarClientes();
		carregarFilmes();
		carregarFormaPagamentos();
		carregarDataLocacao();
		setValor(NumberFormat.getCurrencyInstance().format(0.0));
		carregarDataLimiteLocacao();
		setBloqueio(true);
		listaFilmesLocacao = new ArrayList<Filme>();
		carregarDatas();
		setTipoPesquisa(4);
		pesquisar();
	}
	
	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	

	public String getNomeCliente() {
		return nomeCliente;
	}



	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}



	public String getDevolvido() {
		return devolvido;
	}



	public void setDevolvido(String devolvido) {
		this.devolvido = devolvido;
	}



	public int getTipoPesquisa() {
		return tipoPesquisa;
	}



	public void setTipoPesquisa(int tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}



	public Date getDataInicial() {
		return dataInicial;
	}



	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}



	public Date getDataFinal() {
		return dataFinal;
	}



	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}



	public boolean isData() {
		return data;
	}



	public void setData(boolean data) {
		this.data = data;
	}



	public String getDataAtual() {
		return dataAtual;
	}



	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public Locacao getLocacaoSelecionada() {
		return locacaoSelecionada;
	}

	public void setLocacaoSelecionada(Locacao locacaoSelecionada) {
		LocacaoBacking.locacaoSelecionada = locacaoSelecionada;
	}



	public ArrayList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}



	public void setListaLocacoes(ArrayList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}



	public double getValorTotal() {
		return valorTotal;
	}



	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}



	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}



	public int getClienteCombo() {
		return clienteCombo;
	}

	public void setClienteCombo(int clienteCombo) {
		this.clienteCombo = clienteCombo;
	}

	public int getFilmeCombo() {
		return filmeCombo;
	}

	public void setFilmeCombo(int filmeCombo) {
		this.filmeCombo = filmeCombo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public int getFormaPagamentoCombo() {
		return formaPagamentoCombo;
	}

	public void setFormaPagamentoCombo(int formaPagamentoCombo) {
		this.formaPagamentoCombo = formaPagamentoCombo;
	}

	public boolean isBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public ArrayList<Filme> getListaFilmesLocacao() {
		return listaFilmesLocacao;
	}

	public void setListaFilmesLocacao(ArrayList<Filme> listaFilmesLocacao) {
		this.listaFilmesLocacao = listaFilmesLocacao;
	}

	public ArrayList<FormaPagamento> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(ArrayList<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}
	
	/*
	 * método para salvar a locação 
	 */
	public void salvar() {

		if (validar()) {

			try {
				getLocacao();
				new LocacaoController().salvar(locacao);

				salvarLocacaoFilmes();

				cancelar();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_ERRO_SALVO);
			}

		}

	}
	
	/*
	 * método para capturar os dados da locação
	 */
	private void getLocacao() {
		locacao = new Locacao();

		locacao.setDataLocacao(dataLocacao);
		locacao.setDataDevolucao(dataDevolucao + "");

		locacao.setValor(valorTotal);
		locacao.setDevolvido("Não");

		locacao.setFuncionarioIdFuncionario(LoginBacking.funcionarioLogado);
		locacao.setFormaPagamentoIdFormaPagamento(getFormaPagamentoLista());
		locacao.setClienteIdCliente(getClienteLista());

	}
	
/*
 * método para validação de dados
 */
	private boolean validar() {

		if (Valida.isIntZero(clienteCombo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.CLIENTE_VAZIO);
			return false;
		}

		if (Valida.isIntZero(listaFilmesLocacao.size())) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FILME_VAZIO);
			return false;
		}

		if (Valida.isIntZero(formaPagamentoCombo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FORMA_PAGAMENTO_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(dataDevolucao)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.DATA_DEVOLUCAO_VAZIO);
			return false;
		}

		return true;
	}
	
	/*
	 * método para cancelar locação
	 */
	public void cancelar() {
		setClienteCombo(0);
		setBloqueio(true);
		limparCampos();
	}
	
	/*
	 * método para limpar os campos de locação
	 */
	private void limparCampos() {
		carregarFilmes();
		setFilmeCombo(0);
		listaFilmesLocacao = new ArrayList<Filme>();
		setFormaPagamentoCombo(0);
		setDataDevolucao(null);
		valorTotal = 0.0;
		setValor(NumberFormat.getCurrencyInstance().format(0.0));
	}
	
	/*
	 * método para carregar a combo de clientes
	 */
	private void carregarClientes() {
		try {
			listaClientes = new ClienteController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * método para carregar a combo de filmes
	 */
	private void carregarFilmes() {
		try {
			listaFilmes = new FilmeController().buscarDisponivel("Sim");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * método para carregar a combo de forma de pagamento
	 */
	private void carregarFormaPagamentos() {
		try {
			listaFormaPagamento = new FormaPagamentoController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * método para carregar a data de locação atual
	 */
	private void carregarDataLocacao() {
		dataLocacao = Util.getDataAtual();
	}
	
	/*
	 * método para adicionar o filme a tabela de locação
	 */
	public void adicionar() {
		if (filmeCombo > 0) {
			int index = listaFilmes.indexOf(new Filme(filmeCombo));
			Filme filme = listaFilmes.get(index);

			if (filme.getFaixaEtaria() > getClienteLista().getIdade()) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, "Filme não permitido para o cliente selecionado!");
			} else {
				listaFilmesLocacao.add(filme);
				listaFilmes.remove(filme);


				if (filme.getPromocao().equals("Sim")) {
					valorTotal += filme.getValorPromocao();
				} else {
					valorTotal += filme.getValor();
				}
				valor = NumberFormat.getCurrencyInstance().format(valorTotal);
			}

		} else {
			JSFUtil.addErrorMessage("Efetuar Locação", "Selecione um filme!");
		}

	}
	
	/*
	 * método de exclusão de filme da tabela
	 */
	public void excluir() {
		listaFilmesLocacao.remove(filmeSelecionado);
		listaFilmes.add(filmeSelecionado);


		if (filmeSelecionado.getPromocao().equals("Sim")) {
			valorTotal -= filmeSelecionado.getValorPromocao();
		} else {
			valorTotal -= filmeSelecionado.getValor();
		}
		valor = NumberFormat.getCurrencyInstance().format(valorTotal);

	}
	
	/*
	 * método para salvar as locações de filmes e mudar o filme como não disponivel
	 */
	private void salvarLocacaoFilmes() {
		for (Filme filme : listaFilmesLocacao) {
			try {
				LocacaoFilme locacaoFilme = new LocacaoFilme();
				locacaoFilme.setFilmeIdFilme(filme);
				locacaoFilme.setLocacaoIdLocacao(locacao);

				new LocacaoFilmeController().salvar(locacaoFilme);

				filme.setDisponivel("Não");
				new FilmeController().salvar(filme);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private FormaPagamento getFormaPagamentoLista() {
		int indFormaPagamento = listaFormaPagamento.indexOf(new FormaPagamento(formaPagamentoCombo));
		return listaFormaPagamento.get(indFormaPagamento);
	}

	private Cliente getClienteLista() {
		int indCliente = listaClientes.indexOf(new Cliente(clienteCombo));
		return listaClientes.get(indCliente);
	}
	
	/*
	 * método que desbloqueia a combo de filmes
	 */
	public void desbloqueioFilmes() {
		if (clienteCombo > 0) {
			setBloqueio(false);
		} else {
			setBloqueio(true);
		}
		limparCampos();
	}
	
	/*
	 * método que carrega a data limite de locação
	 */
	private void carregarDataLimiteLocacao() {
		setMinDate(Util.getDateAtual());
		setMaxDate(Util.getMaxDate(30));
	}
	
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarDatas() {
		setMaxDate(Util.getDateAtual());
		setDataAtual(Util.getDataAtual());
	}

	public void pesquisar() {
		switch (tipoPesquisa) {
		case 1:
			setData(true);
			listaLocacoes = new ArrayList<Locacao>();
			break;
		case 2:
			setData(false);
			setDataInicial(null);
			setDataFinal(null);
			buscarDevolvidos();
			break;
		case 3:
			setData(false);
			setDataInicial(null);
			setDataFinal(null);
			buscarNaoDevolvidos();
			break;
		case 4:
			setData(false);
			setDataInicial(null);
			setDataFinal(null);
			buscarTodos();
			break;
		}
	}

	public void pesquisarLocacoes() {
		if (validar()) {
			try {
				getLocacoes();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	private void buscarDevolvidos() {
		try {
			listaLocacoes = new LocacaoController().buscarDevolvido("Sim");
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CONSULTA_LOCACAO, Mensagem.ERRO_CONSULTA_LOCACAO);
		}
	}

	private void buscarNaoDevolvidos() {
		try {
			listaLocacoes = new LocacaoController().buscarDevolvido("Não");
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CONSULTA_LOCACAO, Mensagem.ERRO_CONSULTA_LOCACAO);
		}
	}

	private void buscarTodos() {
		try {
			listaLocacoes = new LocacaoController().buscarTodos();
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CONSULTA_LOCACAO, Mensagem.ERRO_CONSULTA_LOCACAO);
		}
	}

	@SuppressWarnings("deprecation")
	private void getLocacoes() throws ParseException {
		listaLocacoes = new ArrayList<Locacao>();
		for (Locacao locacao : new LocacaoController().buscarTodos()) {
			Date data = Util.getStringToDate(locacao.getDataLocacao());

			if ((data.getDate() == dataInicial.getDate() || data.getDate() == dataFinal.getDate())
					|| (data.after(dataInicial) && data.before(dataFinal))) {
				listaLocacoes.add(locacao);
			}
		}
	}

	public void detalhar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-locacao.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void carregarTela() {
		listaFilmes = new ArrayList<Filme>();
		setNomeCliente(locacao.getClienteIdCliente().getNome());
		setDataLocacao(locacao.getDataLocacao());
		setDataDevolucao(locacao.getDataDevolucao());
		setValor(locacao.getValorFormatado());
		setDevolvido(locacao.getDevolvido());

		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarPorLocacao(locacao)) {
			listaFilmes.add(locacaoFilme.getFilmeIdFilme());
		}

	}
	
	public void alterar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-filme.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	


}
