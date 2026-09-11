package br.com.VixLegen.ProjetoVixLegen10.DTOs.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtribuirTarefaRequest {

    private Long idUsuario;

    private Long idProcesso;
}