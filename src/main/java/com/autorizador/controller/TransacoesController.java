package com.autorizador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.service.CartaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {
	
	private final CartaoService cartaoService;

	public TransacoesController(CartaoService cartaoService) {
		super();
		this.cartaoService = cartaoService;
	}

	@PostMapping
	public ResponseEntity<?> realizarTransacao(CartaoDTO dto){
		
		return ResponseEntity.notFound().build();
	}
}
