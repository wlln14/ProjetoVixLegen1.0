package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoriasDocumento")
@Getter
@Setter
public class CategoriaDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoCategoriaDocumento;

    @NotBlank
    private String descricao;

    @NotNull
    private Integer prazoMaximoUtilizacao;

    @NotNull
    private BigDecimal valorTaxaDiariaMulta;

    @OneToMany(mappedBy = "categoriaDocumento")
    private List<DocumentoJuridico> documentos = new ArrayList<>();
}