package br.com.fiap.chuva.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GS000_TRECHO")
@Getter
@Setter
@NoArgsConstructor
public class Trecho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String logradouro;

    // Regular, Irregular, Escadaria ou Sem iluminação (guardado como texto, sem enum)
    @Column(name = "tipo_calcada", nullable = false, length = 20)
    private String tipoCalcada;

    // altitude do trecho, em metros
    @Column(nullable = false)
    private Integer altitude;

    // todo trecho pertence a um unico bairro
    @ManyToOne(optional = false)
    @JoinColumn(name = "bairro_id", nullable = false)
    private Bairro bairro;
}
