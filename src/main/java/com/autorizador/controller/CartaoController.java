package com.autorizador.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.dto.CartaoResponse;
import com.autorizador.entity.Cartao;
import com.autorizador.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	private final CartaoService cartaoService;
	
	public CartaoController(CartaoService cartaoService) {
		super();
		this.cartaoService = cartaoService;
	}

	@GetMapping("{numeroCartao}")
	public ResponseEntity<?> get(@PathVariable Long numeroCartao){
		
		Optional<Cartao> cartao = cartaoService.findById(numeroCartao);
		
		if (cartao.isPresent()) {
			return ResponseEntity.ok(cartao.get().getSaldo());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<CartaoResponse> addCartao(@RequestBody CartaoDTO dto){
		
		var cartaoResponse = new CartaoResponse();
		
		Optional<Cartao> cartao =cartaoService.findById(dto.getNumeroCartao());
		
		if (cartao.isPresent()) {
			
			cartaoResponse.setNumeroCartao(dto.getNumeroCartao());
			cartaoResponse.setSenha(dto.getSenha());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(cartaoResponse);
		}else {
			
			cartaoService.addCartao(dto);
			
			cartaoResponse.setNumeroCartao(dto.getNumeroCartao());
			cartaoResponse.setSenha(dto.getSenha());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(cartaoResponse);
		}
		
	}
}
