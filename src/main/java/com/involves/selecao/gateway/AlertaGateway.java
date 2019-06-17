package com.involves.selecao.gateway;

import java.util.List;

import com.involves.selecao.alerta.Alerta;

public interface AlertaGateway {
	
	void salvar(Alerta alerta);

	public List<Alerta> buscar(int page, int size);
	
	public long alertasTotalizador();
}
