package br.com.VixLegen.ProjetoVixLegen10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacao;

    @NotBlank
    private String mensagem;

    @NotNull
    private LocalDateTime dataEnvio;

    @NotBlank
    private String canal;

    @NotBlank
    private String status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
