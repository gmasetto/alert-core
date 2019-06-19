package com.involves.selecao;

import java.io.IOException;
import java.util.List;

import com.involves.selecao.alerta.TipoAlerta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.service.BuscaAlertasService;
import com.involves.selecao.service.ProcessadorAlertas;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

	@Autowired
	private BuscaAlertasService buscaAlertasService;
	
	@Autowired
	private ProcessadorAlertas processador;
	
	@GetMapping
    public List<Alerta> alertas(@RequestParam int page,
								@RequestParam int size,
								@RequestParam(required = false) String produto,
								@RequestParam(required = false) String pdv) {

		return buscaAlertasService.buscar(produto, pdv, page, size);
    }
	
	@GetMapping("/totalizador")
    public long alertasToalizador() {
		return buscaAlertasService.alertasTotalizador();
	}

	@PostMapping("/tipo-alerta")
	public TipoAlerta salvarTipoAlerta(@RequestBody TipoAlerta tipoAlerta) {
		return buscaAlertasService.saveTipoAlerta(tipoAlerta);
	}

	@GetMapping("/tipo-alerta")
	public TipoAlerta alertas(@RequestParam String alerta) {
		return buscaAlertasService.buscarTipoAlerta(alerta);
	}

	@GetMapping("/processar")
    public void processar() {
		try {
			processador.processa();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
