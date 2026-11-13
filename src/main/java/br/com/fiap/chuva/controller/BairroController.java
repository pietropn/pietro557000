package br.com.fiap.chuva.controller;

import br.com.fiap.chuva.dto.BairroDTO;
import br.com.fiap.chuva.service.BairroService;
import br.com.fiap.chuva.service.RegraNegocioException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bairros")
@RequiredArgsConstructor
public class BairroController {

    private final BairroService bairroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        return "bairros";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("bairro", new BairroDTO());
        return formulario(model);
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("bairro", bairroService.buscar(id));
        return formulario(model);
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("bairro") BairroDTO bairro,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return formulario(model);
        }
        bairroService.salvar(bairro);
        redirect.addFlashAttribute("mensagem", "Bairro salvo com sucesso.");
        return "redirect:/bairros";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            bairroService.excluir(id);
            redirect.addFlashAttribute("mensagem", "Bairro excluído com sucesso.");
        } catch (RegraNegocioException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/bairros";
    }

    /** O formulario precisa da lista de zonas para a selecao. */
    private String formulario(Model model) {
        model.addAttribute("zonas", BairroService.ZONAS);
        return "bairro-form";
    }
}
