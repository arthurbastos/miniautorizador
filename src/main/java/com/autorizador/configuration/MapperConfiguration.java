package com.autorizador.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

@Component
public class MapperConfiguration {

	@Bean
	public Mapper dozerMapper() {
		return DozerBeanMapperBuilder.buildDefault();
	}
}
