package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.dao.CidadeDAO;
import br.com.foursys.locadora.dao.EstadoDAO;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 *
 * @author Kaue Schneider Nobrega
 * @since 29 de abr. de 2021
 * @version 1.0
 */

@ManagedBean(name = "clienteBacking")
@SessionScoped
public class ClienteBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	public ClienteBacking() {
		carregarEstado();
	}

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
	private Cliente cliente;
	private Endereco end;
	private Contato contato;
	private int tabIndex;

	// atributos auxiliares
	private String nomePesquisar;
	private Cliente clienteSelecionado;
	private ArrayList<Cliente> listaCliente;

	public ArrayList<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(ArrayList<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
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

	private int estado;
	private int cidade;

	// atributos auxiliares
	private ArrayList<Estado> listaEstados;
	private ArrayList<Cidade> listaCidades;

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

	/*
	 * método para carregar a combo de estados
	 */
	private void carregarEstado() {
		try {
			listaEstados = new EstadoDAO().buscarTodos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * método para carregar a combo de Cidades
	 */
	public void carregarCidade() {
		try {
			listaCidades = new CidadeDAO().buscarPorEstado(new Estado(estado));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * método responsavel por cancelar
	 */
	public void cancelar() {

		limpaCampos();
	}

	/*
	 * métpdp responsavel por limpar os campos
	 */
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
		setTelefone(null);
		setCelular(null);
		setEmail(null);
		setEstado(0);
		setCidade(0);
		setTabIndex(0);
	}
	
	/*
	 * método responsavel por cadastrar um novo cliente
	 */
	public void cadastrar() {
		if (validar()) {
			try {
				cliente = new Cliente();
				end = new Endereco();
				contato = new Contato();

				end = getClienteEndereco();
				contato = getClienteContato();
				cliente = getClienteDados();

				new ContatoController().salvar(contato);
				new EnderecoController().salvar(end);
				cliente.setContatoIdContato(contato);
				cliente.setEnderecoIdEndereco(end);

				new ClienteController().salvar(cliente);
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}
	
	/*
	 * método para validar os dados 
	 */
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

	private Cliente getClienteDados() {
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setDataNascimento(dataNascimento);
		cliente.setIdade(Integer.parseInt(idade));
		cliente.setSexo(sexo);

		return cliente;
	}

	private Endereco getClienteEndereco() {
		end.setTipoLogradouro(logradouro);
		end.setEndereco(endereco);
		end.setNumero(Integer.parseInt(numero));
		end.setComplemento(complemento);
		end.setBairro(bairro);
		end.setCep(cep);
		end.setCidadeIdCidade(new Cidade(cidade));

		return end;
	}

	private Contato getClienteContato() {
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

	public void alterar() {
		carregarEstado();
		carregarCidade();
		nome = clienteSelecionado.getNome();
		cpf = clienteSelecionado.getCpf();
		rg = clienteSelecionado.getRg();
		dataNascimento = clienteSelecionado.getDataNascimento();
		idade = clienteSelecionado.getIdade() + "";
		sexo = clienteSelecionado.getSexo();
		logradouro = clienteSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		endereco = clienteSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = clienteSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = clienteSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = clienteSelecionado.getEnderecoIdEndereco().getBairro();
		cep = clienteSelecionado.getEnderecoIdEndereco().getCep();
		estado = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		celular = clienteSelecionado.getContatoIdContato().getCelular();
		telefone = clienteSelecionado.getContatoIdContato().getTelefone();
		email = clienteSelecionado.getContatoIdContato().getEmail();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-cliente.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * m�todo para pesquisar filmes
	 */
	public String pesquisar() {
		try {
			listaCliente = new ClienteController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CONSULTA_FUNCIONARIO, Mensagem.ERRO_CONSULTA_FUNCIONARIO);

		}
		return "";
	}

	/*
	 * m�todo para alterar filme
	 */
	public void excluir() {
		new ClienteController().excluir(clienteSelecionado);
		JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_EXCLUIDO);
	}

	public void alterarFuncionario() {

		if (validar()) {

			try {
				getClienteAlterar();
				new ClienteController().salvar(cliente);
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.ALTERAR_FILME, Mensagem.FILME_ALTERADO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.ALTERAR_FILME, Mensagem.FILME_ERRO_ALTERADO);
			}

		}

	}

	public void alterarCliente() {
		if (validar()) {
			try {
				getClienteAlterar();
				new EnderecoController().salvar(end);
				new ContatoController().salvar(contato);
				limpaCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_SALVO);
			}
		}
	}

	private void getClienteAlterar() {
		cliente = clienteSelecionado;
		getEnderecoAlterar();
		getContatoAlterar();
		cliente.setEnderecoIdEndereco(end);
		cliente.setContatoIdContato(contato);
	}

	private void getEnderecoAlterar() {
		end = cliente.getEnderecoIdEndereco();
		end.setTipoLogradouro(logradouro);
		end.setEndereco(endereco);
		end.setNumero(Integer.parseInt(numero));
		end.setComplemento(complemento);
		end.setBairro(bairro);
		end.setCep(cep);
		end.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContatoAlterar() {
		contato = cliente.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}
	
	public void carregarTela(Cliente cliente) {
		nome = cliente.getNome();
		cpf = cliente.getCpf();
		rg = cliente.getRg();

		dataNascimento = cliente.getDataNascimento();

		idade = cliente.getIdade() + "";
		sexo = cliente.getSexo();
		logradouro = cliente.getEnderecoIdEndereco().getTipoLogradouro();
		endereco = cliente.getEnderecoIdEndereco().getEndereco();
		numero = cliente.getEnderecoIdEndereco().getNumero() + "";
		complemento = cliente.getEnderecoIdEndereco().getComplemento();
		bairro = cliente.getEnderecoIdEndereco().getBairro();
		cep = cliente.getEnderecoIdEndereco().getCep();
		estado = cliente.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = cliente.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = cliente.getContatoIdContato().getTelefone();
		celular = cliente.getContatoIdContato().getCelular();
		email = cliente.getContatoIdContato().getEmail();
	}
	
	public void detalhar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-cliente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
