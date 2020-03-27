package com.barboza.cursomc.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	//Criamos duas variavies caso queira imprimir os valores de cada enumeracao
		private int cod;
		private String descricao;
		
		//Criando um construtor para este tipo unum
		//Sempre será private
		private EstadoPagamento(int cod, String descricao) {
			this.cod = cod;
			this.descricao = descricao;
		}
		
		//Faremos o método get para cada campos
		public int getCod() {
			return cod;
		}
		
		public String getDescricao() {
			return descricao;
		}
		
		//Para finalizar a nossa implementacao do enumeracao
		//Faremos uma opracao que recebe o código e me retornará um objeto
		//Tipo cliente já instaciado conforme o codigo que passar
		
		public static EstadoPagamento toEnum(Integer cod) {//deve ser estatic porque esta operacao deverá ser executada mesmo sem instanciar o objeto
			if (cod == null) {
				return null;
			}
			for (EstadoPagamento x : EstadoPagamento.values()) {
				if(cod.equals(x.getCod())) {
					return x;
				}
			}
			throw new IllegalArgumentException("Id Inválido: " + cod);
		}
		
}
