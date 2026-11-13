package br.com.fiap.chuva.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BairroDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 80, message = "O nome deve ter até 80 caracteres.")
    private String nome;

    @NotNull(message = "A zona é obrigatória.")
    @Pattern(regexp = "Centro|Norte|Sul|Leste|Oeste",
            message = "A zona é obrigatória: Centro, Norte, Sul, Leste ou Oeste.")
    private String zona;

    @NotNull(message = "A população é obrigatória.")
    @PositiveOrZero(message = "A população deve ser maior ou igual a zero.")
    private Integer populacao;
}
