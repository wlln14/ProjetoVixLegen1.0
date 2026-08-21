package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "categoriasDocumento")
@Getter
@Setter
public class CategoriaDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank
    private String descricao;

    @NotNull
    private Integer prazoMaximoUtilizacao;

    @NotNull
    private BigDecimal valorTaxaDiariaMulta;
}