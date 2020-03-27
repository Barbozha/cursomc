package com.barboza.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.barboza.cursomc.domain.Categoria;
import com.barboza.cursomc.domain.Cidade;
import com.barboza.cursomc.domain.Cliente;
import com.barboza.cursomc.domain.Endereco;
import com.barboza.cursomc.domain.Estado;
import com.barboza.cursomc.domain.Pagamento;
import com.barboza.cursomc.domain.PagamentoComBoleto;
import com.barboza.cursomc.domain.PagamentoComCartao;
import com.barboza.cursomc.domain.Pedido;
import com.barboza.cursomc.domain.Produto;
import com.barboza.cursomc.domain.enums.EstadoPagamento;
import com.barboza.cursomc.domain.enums.TipoCliente;
import com.barboza.cursomc.repositories.CategoriaRepository;
import com.barboza.cursomc.repositories.CidadeRepository;
import com.barboza.cursomc.repositories.ClienteRepository;
import com.barboza.cursomc.repositories.EnderecoRepository;
import com.barboza.cursomc.repositories.EstadoRepository;
import com.barboza.cursomc.repositories.PagamentoRepository;
import com.barboza.cursomc.repositories.PedidoRepository;
import com.barboza.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	//Cria-se uma variável dependência para chamar o categoriaReposirory para
	//Gravar os dados na base de dados
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Vamos instanciar a classe Categoria conforme o nosso domínio
		Categoria cat01 = new Categoria(null,"Informática");
		Categoria cat02 = new Categoria(null,"Som");
		
		//Vamos instanciar a classe produto conforme o nosso domínio
		Produto prod01 = new Produto(null, "PC", 2500.00);
		Produto prod02 = new Produto(null, "Fone de Ouvido", 90.00);
		Produto prod03 = new Produto(null, "NoteBook", 3000.00);
		
		//Relacionando as categorias aos produtos
		cat01.getProdutos().addAll(Arrays.asList(prod01,prod03));
		cat02.getProdutos().addAll(Arrays.asList(prod02));
		
		
		//Relacionando os produtos as categorias.
		prod01.getCategorias().addAll(Arrays.asList(cat01));
		prod02.getCategorias().addAll(Arrays.asList(cat02));
		prod03.getCategorias().addAll(Arrays.asList(cat01));
		
		
		//Salvando as categorias no repository
		categoriaRepository.saveAll(Arrays.asList(cat01, cat02));
		
		//Salvando os produtos no repository
		produtoRepository.saveAll(Arrays.asList(prod01, prod02, prod03));
		
		
		//Incluindo dados para Estado e Cidade
		//Realizando as instancias das cidades para Estados
		Estado est01 = new Estado(null, "SP");
		Estado est02 = new Estado(null, "MG");
		Estado est03 = new Estado(null, "RJ");
		
		Cidade cid01 = new Cidade(null,"São Paulo", est01);
		Cidade cid02 = new Cidade(null, "Extrema", est02);
		Cidade cid03 = new Cidade(null, "Campinas", est01);
		Cidade cid04 = new Cidade(null, "Rio de Janeiro", est03);
		
		//Instanciando Estado para cidade
		est01.getCidade().addAll(Arrays.asList(cid01,cid03));
		est02.getCidade().addAll(Arrays.asList(cid02));
		est03.getCidade().addAll(Arrays.asList(cid04));
		
		//Salvando as categorias de Cidade no Ropository
		//Na regra de negócio (dominio) deve-se salvar primeiro o estado
		//para depois salvar as cidades dentro do estado
		estadoRepository.saveAll(Arrays.asList(est01,est02,est03));
		
		//Salvando as cidades dentro do estado
		cidadeRepository.saveAll(Arrays.asList(cid01,cid02,cid03,cid04));
		
		
		//Pela ordem precisamos instanciar o cliente primeiro, conforme o nosso caso de dominio
		//Em seguida instanciar o telefone e depois instanciar o endereço
		Cliente cli01 = new Cliente(null,"Jorge Paulo Barboza de Sousa", "barbozha@gmail.com","06436138897", TipoCliente.PESSOAFISICA);
		cli01.getTelefones().addAll(Arrays.asList("11983232866","1133688748"));
		Cliente cli02 = new Cliente(null, "Angélica Migliori dos Santos de Sousa", "amigliori@gmail.com", "0865438766", TipoCliente.PESSOAFISICA);
		cli02.getTelefones().addAll(Arrays.asList("1195454344","1133688748"));
		
		//Instanciando o endereço do cliente
		Endereco end01 = new Endereco(null,"Rua A","500","Casa 07","Vila Taquari","08230010",cli01,cid01);
		Endereco end02 = new Endereco(null, "Rua Rural", "300", "Zona Rural", "Pessegueiros", "03223232", cli02, cid02);
		
		//Fazer o cliente conhecer o endereço dele
		cli01.getEnderecos().addAll(Arrays.asList(end01));
		cli02.getEnderecos().addAll(Arrays.asList(end02));
		
		//Salvando Cliente
		clienteRepository.saveAll(Arrays.asList(cli01));
		enderecoRepository.saveAll(Arrays.asList(end01));
		
		//Salvando Endereco
		clienteRepository.saveAll(Arrays.asList(cli02));
		enderecoRepository.saveAll(Arrays.asList(end02));
		
		//Instanciando a data para pegar um formato válido para o nossa classe Pedido do domínio
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		//Gerando os pedidos dos clientes
		Pedido ped01 = new Pedido(null, sdf.parse("12/02/2020 13:00"), cli01, end01);
		Pedido ped02 = new Pedido(null, sdf.parse("15/03/2020 16:32"), cli01, end01);
		
		//Associar os clientes com os pedidos deles conforme a UML
		cli01.getPedidos().addAll(Arrays.asList(ped01,ped02));
		
		//Realizando a forma de pagamento dos produtos adquiridos
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped01, 6);
		ped01.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped02, sdf.parse("15/03/2020 00:00"),null);
		ped02.setPagamento(pagto2);
		
		
		
		
		//Pedidos do cliente cli02
		
		//Instanciando a data para pegar um formato válido para o nossa classe Pedido do domínio
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		//Gerando os pedidos dos clientes
		Pedido ped03 = new Pedido(null, sdf1.parse("01/02/2020 15:00"), cli02, end02);
		Pedido ped04 = new Pedido(null, sdf1.parse("02/03/2020 10:12"), cli02, end02);
		
		//Associar os clientes com os pedidos deles conforme a UML
		cli02.getPedidos().addAll(Arrays.asList(ped03,ped04));
		
		//Realizando a forma de pagamento dos produtos adquiridos
		Pagamento pagto03 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, ped03, 10);
		ped03.setPagamento(pagto03);
		
		Pagamento pagto04 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped04, sdf.parse("05/03/2020 00:00"), null);
		ped04.setPagamento(pagto04);
				
				
		//Salvo primeiro o pedido Cliente 01
		pedidoRepository.saveAll(Arrays.asList(ped01,ped02));
				
		//Salvo agora os pagamentos
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		//Salvo primeiro o pedido Cliente 02
		pedidoRepository.saveAll(Arrays.asList(ped03,ped04));
						
		//Salvo agora os pagamentos
		pagamentoRepository.saveAll(Arrays.asList(pagto03,pagto04));
	
	}

}
