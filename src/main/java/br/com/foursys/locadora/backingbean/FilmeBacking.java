	package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.util.Genero;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe responsavel por controlar os componentes do front-end " cad-filme"
 *
 * @author Kaue Schneider Nobrega
 * @since 27 de abr. de 2021
 * @version 1.0
 */

@ManagedBean(name = "filmeBacking")
@SessionScoped
public class FilmeBacking implements Serializable{
	private static final long serialVersionUID = 1L;
	// atributos da tela
	private int tipoPesquisa;

	private String nome;
	private String valor;
	private String disponivel;
	private String promocao;
	private String valorPromocao;
	private String diretor;
	private String anoLancamento;
	private String faixaEtaria;
	private String genero;
	
	//atributos da tela de consulta
	private String nomePesquisar;
	private Filme filmeSelecionado;
	
	
	//atributos auxiliares
	private Filme filme;
	private ArrayList<Filme> listaFilmes;
	private ArrayList<String> listaGeneros;
	
	public FilmeBacking() {
		carregarGeneros();
		carregarTela(filmeSelecionado);
		setTipoPesquisa(3);
		carregarPesquisa();
	}
	// getters and setters
	
	public ArrayList<String> getListaGeneros() {
		return listaGeneros;
	}


	public int getTipoPesquisa() {
		return tipoPesquisa;
	}



	public void setTipoPesquisa(int tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}



	public void setListaGeneros(ArrayList<String> listaGeneros) {
		this.listaGeneros = listaGeneros;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}
	
	
	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}


	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}


	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public String getPromocao() {
		return promocao;
	}

	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}

	public String getValorPromocao() {
		return valorPromocao;
	}

	public void setValorPromocao(String valorPromocao) {
		this.valorPromocao = valorPromocao;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	/*
	 * m�todo que captura a a��o do bot�o CADASTRAR na tela cad-filme.jsp
	 */
	public void cadastrar() {
		if (validar()) {
			try {
				getFilme();
				new FilmeController().salvar(filme);
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_SALVO);
			}
		}
	}
	

	/*
	 * m�todo que captura a a��o do bot�o Cancelar na tela cad-filme.jsp
	 */
	public void cancelar() {
		
		limpaCampos();
	}

	/*
	 * m�todo que captura a a��o do bot�o Sair na tela cad-filme.jsp
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/*
	 *  m�todo para abrir tela de cadastro
	 */

	public String cadastroFilme() {
		limpaCampos();
		return "";
	}
	
	/*
	 * m�todo para abrir a tela de consulta de filme
	 */
	public String consultaFilme() {
		nomePesquisar = null;
		listaFilmes = null;
		return "";
	}
	
	/*
	 * m�todo para validar as informa��es
	 */
	public boolean validar() {
		if(Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.NOME_VAZIO);
			return false;
		}
		
		if (Valida.isEmptyOrNull(valor)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_VAZIO);
			return false;
		} else if (Valida.isDoubleZero(Double.parseDouble(valor))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_INVALIDO);
			return false;
		}
		
		if(Valida.isEmptyOrNull(disponivel)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.DISPONIVEL_VAZIO);
			return false;
		}
		
		if(Valida.isEmptyOrNull(promocao)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.PROMOCAO_VAZIO);
			return false;
		}
		
		if(promocao.equals("Sim")) {
			if (Valida.isEmptyOrNull(valorPromocao)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_PROMOCAO_VAZIO);
				return false;
			} else if (Valida.isDoubleZero(Double.parseDouble(valorPromocao))) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_PROMOCAO_INVALIDO);
				return false;
			}
		}
		
		if(Valida.isEmptyOrNull(diretor)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.DIRETOR_VAZIO);
			return false;
		}
		
		if(!Valida.isInteger(anoLancamento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ANO_LANCAMENTO_INVALIDO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(anoLancamento))){
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ANO_LANCAMENTO_VAZIO);
			return false;
		}
		
		if(!Valida.isInteger(faixaEtaria)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FAIXA_ETARIA_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(faixaEtaria))){
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FAIXA_ETARIA_INVALIDO);
			return false;
		}
		
		if(Valida.isEmptyOrNull(genero)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.GENERO_VAZIO);
			return false;
		}
		
		
		
		return true;
	}
	
	/*
	 * m�todo para retornar um objeto filme
	 */
	private void getFilme() {
		filme = new Filme();
		filme.setNome(nome);
		filme.setValor(Double.parseDouble(valor));
		filme.setDisponivel(disponivel);
		filme.setPromocao(promocao);
		if (promocao.equals("Sim")) {
			filme.setValorPromocao(Double.parseDouble(valorPromocao));
		}
		filme.setDiretor(diretor);
		filme.setAnoLancamento(anoLancamento);
		filme.setFaixaEtaria(Integer.parseInt(faixaEtaria));
		filme.setGenero(genero);
	}
	
	/*
	 * m�todo para limpar os campos da tela
	 */
	private void limpaCampos() {
		setNome(null);
		setValor(null);
		setDisponivel(null);
		setPromocao(null);
		setValorPromocao(null);
		setDiretor(null);
		setAnoLancamento(null);
		setFaixaEtaria(null);
		setGenero(null);
	}
	
	
	/*
	 * m�todo para alterar filme
	 */
	public void alterar() {
		nome = filmeSelecionado.getNome();
		valor = filmeSelecionado.getValor() + "";
		disponivel = filmeSelecionado.getDisponivel();
		promocao = filmeSelecionado.getPromocao();
		valorPromocao = filmeSelecionado.getValorPromocao() + "";
		diretor = filmeSelecionado.getDiretor();
		anoLancamento = filmeSelecionado.getAnoLancamento();
		faixaEtaria = filmeSelecionado.getFaixaEtaria() + "";
		genero = filmeSelecionado.getGenero();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-filme.xhtml");
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	/*
	 * m�todo para retornar um objeto filme
	 */
	private void getFilmeAlterar() {
		filme = filmeSelecionado;
		filme.setNome(nome);
		filme.setValor(Double.parseDouble(valor));
		filme.setDisponivel(disponivel);
		filme.setPromocao(promocao);

		if (promocao.equals("Sim")) {
			filme.setValorPromocao(Double.parseDouble(valorPromocao));
		}

		filme.setDiretor(diretor);
		filme.setAnoLancamento(anoLancamento);
		filme.setFaixaEtaria(Integer.parseInt(faixaEtaria));
		filme.setGenero(genero);
	}
	
	/*
	 * m�todo que captura a a��o do bot�o CADASTRAR na tela cad-filme.jsp
	 */
	public void alterarFilme() {

		if (validar()) {

			try {
				getFilmeAlterar();
				new FilmeController().salvar(filme);
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.ALTERAR_FILME, Mensagem.FILME_ALTERADO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.ALTERAR_FILME, Mensagem.FILME_ERRO_ALTERADO);
			}

		}

	}
	
	/*
	 * método para carregar a lista de generos
	 */
	public void carregarGeneros() {
		listaGeneros = new ArrayList<String>();
		
		for (Genero	g : Genero.values()) {
			listaGeneros.add(g.getDescricao());
		}
	}
	
	public void detalhar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-filme.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void carregarTela(Filme filme) {
		setNome(filme.getNome());
		setValor(filme.getValor() + "");
		setDisponivel(filme.getDisponivel());
		setPromocao(filme.getPromocao());
		setValorPromocao(filme.getValorPromocao() + "");
		setDiretor(filme.getDiretor());
		setAnoLancamento(filme.getAnoLancamento());
		setFaixaEtaria(filme.getFaixaEtaria() + "");
		setGenero(filme.getGenero());
	}
	
	public void pesquisar() {
		try {
			switch (tipoPesquisa) {
			case 1:
				listaFilmes = new FilmeController().buscarPorNome(nomePesquisar, "Sim");
				break;
			case 2:
				listaFilmes = new FilmeController().buscarPorNome(nomePesquisar, "Não");
				break;
			case 3:
				listaFilmes = new FilmeController().buscarPorNome(nomePesquisar);
				break;
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CONSULTA_FILME, Mensagem.ERRO_CONSULTA_FILME);
		}
	}

	public void carregarPesquisa() {
		switch (tipoPesquisa) {
		case 1:
			setNomePesquisar("");
			listaFilmes = new FilmeController().buscarDisponivel("Sim");
			break;
		case 2:
			setNomePesquisar("");
			listaFilmes = new FilmeController().buscarDisponivel("Não");
			break;
		case 3:
			setNomePesquisar("");
			listaFilmes = new FilmeController().buscarTodos();
			break;
		}
	}
	

	public void excluir() {
		try {
			new FilmeController().excluir(filmeSelecionado);
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_EXCLUIDO);
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_EXCLUIR);
		}
	}

}
