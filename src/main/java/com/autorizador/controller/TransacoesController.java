package com.autorizador.controller;

import java.util.Optional;

import com.autorizador.util.TransacaoEnun;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.entity.Cartao;
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
	public ResponseEntity<?> realizarTransacao(@RequestBody CartaoDTO dto){
		
		Optional<Cartao> cartao = cartaoService.findById(dto.getNumeroCartao());

		TransacaoEnun enumEscolido = null;

		if (cartao.isPresent()) {
			
			if (cartao.get().getSenha().equals(dto.getSenha())) {
				if (cartao.get().getSaldo() >= dto.getValor()) {
					cartaoService.realizarTransacao(dto);
					
					return ResponseEntity.status(HttpStatus.CREATED).build();
				}else {
					enumEscolido  = TransacaoEnun.valueOf("SALDO_INSUFICIENTE");
				}
			}else {
				enumEscolido  = TransacaoEnun.valueOf("SENHA_INVALIDA");
			}
			
		}else {
			enumEscolido  = TransacaoEnun.valueOf("CARTAO_INEXISTENTE");
		}

		return ResponseEntity.unprocessableEntity().body(enumEscolido);
		
	}
}
