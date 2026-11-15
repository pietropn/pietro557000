package br.com.fiap.chuva.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GS000_LEITURA")
@Getter
@Setter
@NoArgsConstructor
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Integer lamina;

    @Column(nullable = false, length = 10)
    private String situacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trecho_id", nullable = false)
    private Trecho trecho;
}
