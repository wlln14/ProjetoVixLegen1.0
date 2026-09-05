package br.com.VixLegen.ProjetoVixLegen10.Model;

import br.com.VixLegen.ProjetoVixLegen10.Enums.StatusProcesso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "processosJuridicos")
@Getter
@Setter
public class ProcessoJuridico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcesso;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String numeroProcesso;

    @NotBlank
    private String vara;

    @NotBlank
    private String comarca;

    @NotBlank
    private String tribunal;

    @NotBlank
    private String instancia;

    private boolean segredoJustica;

    @NotNull
    private LocalDate dataAbertura;

    private LocalDate dataEncerramento;

    @Enumerated(EnumType.STRING)
    private StatusProcesso status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}