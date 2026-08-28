package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotNull
    private String nomeCompleto;

    @NotNull
    private String email;

    @NotNull
    private String telefone;

    @NotNull
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<ProcessoJuridico> processos = new ArrayList<>();
}
