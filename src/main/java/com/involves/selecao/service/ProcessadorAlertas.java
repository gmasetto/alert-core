package com.involves.selecao.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.TipoAlerta;
import com.involves.selecao.alerta.mensagem.Mensagem;
import com.involves.selecao.alerta.mensagem.MensagemDiferencaValor;
import com.involves.selecao.alerta.mensagem.MensagemPadrao;
import com.involves.selecao.gateway.AlertaGateway;

@Service
public class ProcessadorAlertas {

	@Autowired
	private AlertaGateway gateway;
	
	public void processa() throws IOException {
		URL url = new URL("https://selecao-involves.agilepromoter.com/pesquisas");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		Pesquisa[] ps =  gson.fromJson(content.toString(), Pesquisa[].class);
		for (int i = 0; i < ps.length; i++){
			for (int j = 0; j < ps[i].getRespostas().size(); j++){
				Resposta resposta = ps[i].getRespostas().get(j);
				
				TipoAlerta tipoAlerta = gateway.buscarTipoAlerta(resposta.getPergunta());
				Mensagem mensagem = null;
				
				if (tipoAlerta != null) {
					if (tipoAlerta.isCompararValor()) {
						mensagem = new MensagemDiferencaValor();
						Alerta alerta = mensagem.gerarAlerta(ps[i], resposta, tipoAlerta);
						gateway.salvar(alerta);
					} else {
						mensagem = new MensagemPadrao();
						Alerta alerta = mensagem.gerarAlerta(ps[i], resposta, tipoAlerta);
						gateway.salvar(alerta);
					}
				} else {
					System.out.println("Alerta ainda não implementado!");
				}
			} 
		}
	}
}

