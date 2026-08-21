package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="partesEnvolvidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ParteEnvolvida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idParte;

    private String posicaoCliente;
    private String parteContraria;
    private String advogadoContrario;
    private String juizResponsavel;



}
