package com.autorizador.service;

import java.util.Optional;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.entity.Cartao;

public interface CartaoService {
	
	public CartaoDTO addCartao(CartaoDTO dto);
	
	public Optional<Cartao> findById(Long numeroCartao);
	
	public Cartao realizarTransacao(CartaoDTO dto);

}
