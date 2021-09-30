package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.dao.CidadeDAO;
import br.com.foursys.locadora.dao.EstadoDAO;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 *
 * @author Kaue Schneider Nobrega
 * @since 30 de abr. de 2021
 * @version 1.0
 */

@ManagedBean(name = "funcionarioBacking")
@SessionScoped
public class FuncionarioBacking implements Serializable {

	private static final long serialVersionUID = 1L;
	// atributos da tela de cadastro
	private String nome;
	private String cpf;
	private String rg;
	private String dataNascimento;
	private String idade;
	private String sexo;
	private String logradouro;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String telefone;
	private String celular;
	private String email;
	private String login;
	private String senha;
	private String tipoLogin;
	private int estado;
	private int cidade;
	private Funcionario funcionario;
	private Endereco end;
	private Contato contato;
	private int tabIndex;

	// atributos da tela de consulta
	private String nomePesquisar;
	private Funcionario funcionarioSelecionado;
	
	private ArrayList<Funcionario> listaFuncionario;

	public ArrayList<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}
	
	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public void setListaFuncionario(ArrayList<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	// atributos auxiliares
	private ArrayList<String> listaUsuario;
	private ArrayList<Estado> listaEstados;
	private ArrayList<Cidade> listaCidades;
	private ArrayList<String> listaSexo;

	public ArrayList<String> getListaSexo() {
		return listaSexo;
	}

	public void setListaSexo(ArrayList<String> listaSexo) {
		this.listaSexo = listaSexo;
	}

	public FuncionarioBacking() {
		carregarEstado();
		carregarPerfil();
		listarFuncionarios();
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public ArrayList<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(ArrayList<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public ArrayList<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(ArrayList<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoLogin() {
		return tipoLogin;
	}

	public void setTipoLogin(String tipoLogin) {
		this.tipoLogin = tipoLogin;
	}

	public ArrayList<String> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(ArrayList<String> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	private void carregarEstado() {
		try {
			listaEstados = new EstadoDAO().buscarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void carregarCidade() {
		try {
			listaCidades = new CidadeDAO().buscarPorEstado(new Estado(estado));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * método para carregar a lista de generos
	 */
	public void carregarPerfil() {
		listaUsuario = new ArrayList<String>();

		for (Perfil p : Perfil.values()) {
			listaUsuario.add(p.getDescricao());
		}
	}

	public void cancelar() {

		limpaCampos();
	}

	private void limpaCampos() {
		setNome(null);
		setRg(null);
		setCpf(null);
		setDataNascimento(null);
		setIdade(null);
		setSexo(null);
		setLogradouro(null);
		setNumero(null);
		setEndereco(null);
		setComplemento(null);
		setBairro(null);
		setCep(null);
		setLogin(null);
		setSenha(null);
		setTipoLogin(null);
		setTelefone(null);
		setCelular(null);
		setEmail(null);
		setEstado(0);
		setCidade(0);
		setTabIndex(0);
	}

	/*
	 * m�todo que captura a a��o do bot�o CADASTRAR na tela cad-filme.jsp
	 */
	public void cadastrar() {
		if (validar()) {
			try {
				funcionario = new Funcionario();
				end = new Endereco();
				contato = new Contato();

				end = getFuncionarioEndereco();
				contato = getFuncionarioContato();
				funcionario = getFuncionarioDados();

				new ContatoController().salvar(contato);
				new EnderecoController().salvar(end);
				funcionario.setContatoIdContato(contato);
				funcionario.setEnderecoIdEndereco(end);

				new FuncionarioController().salvar(funcionario);
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}

	public boolean validar() {
		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NOME_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CPF_VAZIO);
			return false;
		} else {

		}

		if (Valida.isEmptyOrNull(rg)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.RG_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		}

		if (!Valida.isInteger(idade)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.IDADE_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.IDADE_INVALIDA);
			return false;
		}

		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.SEXO_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(logradouro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.TIPO_LOGRADOURO_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(endereco)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ENDERECO_VAZIO);
			return false;
		}

		if (!Valida.isInteger(numero)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NUMERO_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(numero))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NUMERO_INVALIDO);
			return false;
		}

		if (Valida.isEmptyOrNull(bairro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.BAIRRO_VAZIO);
			return false;
		}

		// fazer estado e cidade

		return true;
	}

	private Funcionario getFuncionarioDados() {
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setDataNascimento(dataNascimento);
		funcionario.setIdade(Integer.parseInt(idade));
		funcionario.setSexo(sexo);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setPerfilAcesso(tipoLogin);

		return funcionario;
	}

	private Endereco getFuncionarioEndereco() {
		end.setTipoLogradouro(logradouro);
		end.setEndereco(endereco);
		end.setNumero(Integer.parseInt(numero));
		end.setComplemento(complemento);
		end.setBairro(bairro);
		end.setCep(cep);
		end.setCidadeIdCidade(new Cidade(cidade));

		return end;
	}

	private Contato getFuncionarioContato() {
		contato.setCelular(celular);
		contato.setTelefone(telefone);
		contato.setEmail(email);
		return contato;
	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * m�todo para alterar filme
	 */
	public void alterar() {
		carregarEstado();
		carregarCidade();
		nome = funcionarioSelecionado.getNome();
		cpf = funcionarioSelecionado.getCpf();
		rg = funcionarioSelecionado.getRg();
		dataNascimento = funcionarioSelecionado.getDataNascimento();
		idade = funcionarioSelecionado.getIdade() + "";
		sexo = funcionarioSelecionado.getSexo();
		logradouro = funcionarioSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		endereco = funcionarioSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = funcionarioSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = funcionarioSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = funcionarioSelecionado.getEnderecoIdEndereco().getBairro();
		cep = funcionarioSelecionado.getEnderecoIdEndereco().getCep();
		estado = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		celular = funcionarioSelecionado.getContatoIdContato().getCelular();
		telefone = funcionarioSelecionado.getContatoIdContato().getTelefone();
		email = funcionarioSelecionado.getContatoIdContato().getEmail();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-funcionario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/*
	 * m�todo que captura a a��o do bot�o CADASTRAR na tela cad-filme.jsp
	 */
	public void alterarFuncionario() {
		if (validar()) {
			try {
				getFuncionarioAlterar();
				new EnderecoController().salvar(end);
				new ContatoController().salvar(contato);
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}
	
	private void getFuncionarioAlterar() {
		funcionario = funcionarioSelecionado;
		getEnderecoAlterar();
		getContatoAlterar();
		funcionario.setEnderecoIdEndereco(end);
		funcionario.setContatoIdContato(contato);
	}

	private void getEnderecoAlterar() {
		end = funcionario.getEnderecoIdEndereco();
		end.setTipoLogradouro(logradouro);
		end.setEndereco(endereco);
		end.setNumero(Integer.parseInt(numero));
		end.setComplemento(complemento);
		end.setBairro(bairro);
		end.setCep(cep);
		end.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContatoAlterar() {
		contato = funcionario.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}
	
	public void detalhar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pesquisar() {
		try {
			listaFuncionario = new ArrayList<Funcionario>();
			for (Funcionario funcionario : new FuncionarioController().buscarPorNome(nomePesquisar)) {
				if (funcionario.getPerfilAcesso().equals(Perfil.DEV.getDescricao())) {
					if (LoginBacking.funcionarioLogado.getPerfilAcesso().equals(Perfil.DEV.getDescricao())) {
						listaFuncionario.add(funcionario);
					}
				} else {
					listaFuncionario.add(funcionario);
				}
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ERRO_CONSULTA_FUNCIONARIO);
		}
	}

	private void listarFuncionarios() {
		try {
			listaFuncionario = new ArrayList<Funcionario>();
			for (Funcionario funcionario : new FuncionarioController().buscarTodos()) {
				if (funcionario.getPerfilAcesso().equals(Perfil.DEV.getDescricao())) {
					if (LoginBacking.funcionarioLogado.getPerfilAcesso().equals(Perfil.DEV.getDescricao())) {
						listaFuncionario.add(funcionario);
					}
				} else {
					listaFuncionario.add(funcionario);
				}
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ERRO_CONSULTA_FUNCIONARIO);
		}
	}

	public void excluir() {
		try {
			new FuncionarioController().excluir(funcionarioSelecionado);
			new EnderecoController().excluir(funcionarioSelecionado.getEnderecoIdEndereco());
			new ContatoController().excluir(funcionarioSelecionado.getContatoIdContato());
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ERRO_CONSULTA_FUNCIONARIO);
		}
	}

}
