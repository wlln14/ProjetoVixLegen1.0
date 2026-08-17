package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank
    private String primeiroNome;

    @NotBlank
    private String ultimoNome;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String senhaHash;

    @NotBlank
    private String telefone;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String rg;

    @NotBlank
    private String empresa;

    @NotBlank
    private String numeroOAB;

    @NotBlank
    private LocalDate dataNascimento;

    @NotBlank
    private String estado;

    @NotBlank
    private String cidade;

    @NotBlank
    private String cep;

    private boolean ativo;
}
