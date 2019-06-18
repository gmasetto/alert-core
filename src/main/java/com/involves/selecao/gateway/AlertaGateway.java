package com.involves.selecao.gateway;

import java.util.List;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.TipoAlerta;

public interface AlertaGateway {
	
	public void salvar(Alerta alerta);

	public List<Alerta> buscar(int page, int size);
	
	public long alertasTotalizador();

	public TipoAlerta saveTipoAlerta(TipoAlerta tipoAlerta);

	public TipoAlerta buscarTipoAlerta(String nome);

}
