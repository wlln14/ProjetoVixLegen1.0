package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacaoProcessual")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovimentacaoProcessual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimentacao;

    private LocalDateTime data;
    private String descricao;


}
