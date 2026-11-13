package br.com.fiap.chuva.controller;

import br.com.fiap.chuva.dto.LeituraDTO;
import br.com.fiap.chuva.service.LeituraService;
import br.com.fiap.chuva.service.TrechoService;
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
@RequestMapping("/leituras")
@RequiredArgsConstructor
public class LeituraController {

    private final LeituraService leituraService;
    private final TrechoService trechoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("leituras", leituraService.listar());
        return "leituras";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("leitura", new LeituraDTO());
        return formulario(model);
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("leitura", leituraService.buscar(id));
        return formulario(model);
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("leitura") LeituraDTO leitura,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return formulario(model);
        }
        leituraService.salvar(leitura);
        redirect.addFlashAttribute("mensagem", "Leitura salva com sucesso.");
        return "redirect:/leituras";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        leituraService.excluir(id);
        redirect.addFlashAttribute("mensagem", "Leitura excluída com sucesso.");
        return "redirect:/leituras";
    }

    /** O formulario precisa da lista de trechos para a selecao pelo logradouro. */
    private String formulario(Model model) {
        model.addAttribute("trechos", trechoService.listar());
        return "leitura-form";
    }
}
