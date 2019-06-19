package com.involves.selecao.service;

import java.util.List;

import com.involves.selecao.alerta.TipoAlerta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class BuscaAlertasService {
	
	@Autowired
	private AlertaGateway gateway;
	
	public List<Alerta> buscar(String produto, String pdv, int page, int size) {
		return gateway.buscar(produto, pdv, page, size);
	}
	
	public long alertasTotalizador() {
		return gateway.alertasTotalizador();
	}

	public TipoAlerta saveTipoAlerta(TipoAlerta tipoAlerta) {
		 return gateway.saveTipoAlerta(tipoAlerta);
	}

	public TipoAlerta buscarTipoAlerta(String alerta) {
		return gateway.buscarTipoAlerta(alerta);
	}
}
