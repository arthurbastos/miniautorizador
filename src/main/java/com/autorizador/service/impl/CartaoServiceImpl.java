package com.autorizador.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.entity.Cartao;
import com.autorizador.respository.CartaoRepository;
import com.autorizador.service.CartaoService;
import com.github.dozermapper.core.Mapper;

@Service
public class CartaoServiceImpl implements CartaoService{

	private final CartaoRepository cartaoRepository;
	private final Mapper mapper;
	
	
	public CartaoServiceImpl(CartaoRepository cartaoRepository,
			Mapper mapper) {
		super();
		this.cartaoRepository = cartaoRepository;
		this.mapper = mapper;
	}


	@Override
	public CartaoDTO addCartao(CartaoDTO dto) {
		Cartao cartao = new Cartao();
		this.mapper.map(dto, cartao);
		
		Long valor = 500L;
		
		cartao.setSaldo(valor);
		
		cartaoRepository.save(cartao);
		
		return dto;
	}


	@Override
	public Optional<Cartao> findById(Long numeroCartao) {
		return cartaoRepository.findById(numeroCartao);
	}

}
