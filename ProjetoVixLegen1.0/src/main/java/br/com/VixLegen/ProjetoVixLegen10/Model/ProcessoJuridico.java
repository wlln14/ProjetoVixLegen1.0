package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "processosJuridicos")
@Getter
@Setter
public class ProcessoJuridico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcesso;

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
}