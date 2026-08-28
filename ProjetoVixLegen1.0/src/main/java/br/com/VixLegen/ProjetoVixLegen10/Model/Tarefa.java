package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarefa;

    @NotNull
    private LocalDate dataAtribuicao;

    @NotNull
    private LocalDate prazo;

    @NotBlank
    private String tipoTarefa;

    @NotBlank
    private String status;

    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    private ProcessoJuridico processo;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioResponsavel;
}