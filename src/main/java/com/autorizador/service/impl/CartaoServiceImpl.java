package com.autorizador.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.autorizador.dto.CartaoDTO;
import com.autorizador.entity.Cartao;
import com.autorizador.respository.CartaoRepository;
import com.autorizador.service.CartaoService;
import com.github.dozermapper.core.Mapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
		var cartao = new Cartao();
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


	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public Cartao realizarTransacao(CartaoDTO dto) {
		var cartao = cartaoRepository.findById(dto.getNumeroCartao()).get();
		cartao.setSaldo(cartao.getSaldo() - dto.getValor());
		return cartaoRepository.save(cartao);
	}

}
