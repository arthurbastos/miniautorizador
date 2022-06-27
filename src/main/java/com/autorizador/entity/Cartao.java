package com.autorizador.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cartao{
	
	@Id
	private Long numeroCartao;

	@Column
	private Long senha;
	
	@Column
	private Long saldo;
}
