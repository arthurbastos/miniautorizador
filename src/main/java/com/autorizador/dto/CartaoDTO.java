package com.autorizador.dto;


import lombok.Data;

@Data
public class CartaoDTO {
	
	private Long numeroCartao;

	private Long senha;
	
	private Long valor;
}
