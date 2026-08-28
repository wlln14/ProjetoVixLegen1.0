package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
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

    private String status;
    private String areaDireito;
    private String tipoAcao;
    private String faseProcessual;
    private String descricaoObjeto;

    @OneToOne
    @JoinColumn(name = "processo_id", nullable = false)
    private ProcessoJuridico processo;
}
