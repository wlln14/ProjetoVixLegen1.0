package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "documentosJuridicos")
@Getter
@Setter
public class DocumentoJuridico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataCadastro;

    @NotBlank
    private String arquivo;

    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    private ProcessoJuridico processo;

    @ManyToOne
    @JoinColumn(name = "categoria_documento_id", nullable = false)
    private CategoriaDocumento categoriaDocumento;
}
