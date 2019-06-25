package com.involves.selecao.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.involves.selecao.AlertaController;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Evento;
import com.involves.selecao.alerta.TipoAlerta;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class AlertaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertaController alertaController;

    @Test
    public void testAlertas() throws Exception {
        Alerta alerta = new Alerta();
        alerta.setDescricao("Preço abaixo do estipulado!");
        alerta.setFlTipo(3);
        alerta.setMargem(5);
        alerta.setPontoDeVenda("Padaria do seu João");
        alerta.setProduto("Ovo de Pascoa Kinder 48");

        List<Alerta> alertas = new ArrayList<>();
        alertas.add(alerta);

        given(this.alertaController.alertas(1, 10, null, null)).willReturn(alertas);

        this.mockMvc.perform(get("/alertas")
                .param("page", "1").param("size", "10")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("[{\"pontoDeVenda\":\"Padaria do seu João\",\"descricao\":" +
                        "\"Preço abaixo do estipulado!\",\"produto\":\"Ovo de Pascoa Kinder 48\"," +
                        "\"categoria\":null,\"flTipo\":3,\"margem\":5}]")).andDo(print());
    }

    @Test
    public void testAlertasTotalizadores() throws Exception {
        given(this.alertaController.alertasToalizador()).willReturn(10L);

        this.mockMvc.perform(get("/alertas/totalizador")).andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void testSalvarTipoAlerta() throws Exception {
        TipoAlerta tipoAlerta = new TipoAlerta();
        tipoAlerta.setAlerta("Produto em falta!");
        tipoAlerta.setCompararValor(false);
        Evento evento = new Evento();
        evento.setDescricao("ruptura");
        evento.setTipo(1);
        tipoAlerta.setEvento(evento);

        System.out.println(new ObjectMapper().writeValueAsString(tipoAlerta));
        when(this.alertaController.salvarTipoAlerta(any(TipoAlerta.class))).thenReturn(tipoAlerta);

        this.mockMvc.perform(post("/alertas/tipo-alerta")
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(tipoAlerta))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"alerta\":\"Produto em falta!\",\"compararValor\":false," +
                        "\"evento\":{\"tipo\":1,\"descricao\":\"ruptura\"}}"));
    }

    @Test
    public void removerTipoAlerta() throws Exception {
        TipoAlerta tipoAlerta = new TipoAlerta();
        tipoAlerta.setAlerta("Produto em falta!");
        tipoAlerta.setCompararValor(false);
        Evento evento = new Evento();
        evento.setDescricao("ruptura");
        evento.setTipo(1);
        tipoAlerta.setEvento(evento);

        when(this.alertaController.removeTipoAlerta(tipoAlerta.getAlerta())).thenReturn(tipoAlerta);

        mockMvc.perform(delete("/alertas/tipo-alerta")
                .param("alerta", "Produto em falta!")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\"alerta\":\"Produto em falta!\",\"compararValor\":false," +
                        "\"evento\":{\"tipo\":1,\"descricao\":\"ruptura\"}}")).andDo(print());
    }

    @Test
    public void buscarTipoAlerta() throws Exception {
        TipoAlerta tipoAlerta = new TipoAlerta();
        tipoAlerta.setAlerta("Produto em falta!");
        tipoAlerta.setCompararValor(false);
        Evento evento = new Evento();
        evento.setDescricao("ruptura");
        evento.setTipo(1);
        tipoAlerta.setEvento(evento);

        when(this.alertaController.buscarTipoAlerta(tipoAlerta.getAlerta())).thenReturn(tipoAlerta);

        mockMvc.perform(get("/alertas/tipo-alerta")
                .param("alerta", "Produto em falta!"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"alerta\":\"Produto em falta!\",\"compararValor\":false," +
                        "\"evento\":{\"tipo\":1,\"descricao\":\"ruptura\"}}")).andDo(print());
    }

    @Test
    public void buscarTiposAlertas() throws Exception {
        TipoAlerta tipoAlerta = new TipoAlerta();
        tipoAlerta.setAlerta("Produto em falta!");
        tipoAlerta.setCompararValor(false);
        Evento evento = new Evento();
        evento.setDescricao("ruptura");
        evento.setTipo(1);
        tipoAlerta.setEvento(evento);
        List<TipoAlerta> tiposAlertas = new ArrayList();
        tiposAlertas.add(tipoAlerta);

        when(this.alertaController.buscarTiposAlertas()).thenReturn(tiposAlertas);

        mockMvc.perform(get("/alertas/tipos-alertas")
                .param("alerta", "Produto em falta!"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"alerta\":\"Produto em falta!\",\"compararValor\":false," +
                        "\"evento\":{\"tipo\":1,\"descricao\":\"ruptura\"}}]")).andDo(print());
    }

}

