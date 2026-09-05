package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classificacaoProcesso")
public class ClassificacaoProcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String status;

    @NotBlank
    private String areaDireito;

    @NotBlank
    private String tipoAcao;

    @NotBlank
    private String faseProcessual;

    @NotBlank
    private String descricaoObjeto;

    @OneToOne
    @JoinColumn(name = "processo_id", nullable = false)
    private ProcessoJuridico processo;
}
