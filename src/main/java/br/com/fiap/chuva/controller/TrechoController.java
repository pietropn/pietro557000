package br.com.fiap.chuva.controller;

import br.com.fiap.chuva.dto.TrechoDTO;
import br.com.fiap.chuva.service.BairroService;
import br.com.fiap.chuva.service.RegraNegocioException;
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
@RequestMapping("/trechos")
@RequiredArgsConstructor
public class TrechoController {

    private final TrechoService trechoService;
    private final BairroService bairroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("trechos", trechoService.listar());
        return "trechos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("trecho", new TrechoDTO());
        return formulario(model);
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("trecho", trechoService.buscar(id));
        return formulario(model);
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("trecho") TrechoDTO trecho,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return formulario(model);
        }
        trechoService.salvar(trecho);
        redirect.addFlashAttribute("mensagem", "Trecho salvo com sucesso.");
        return "redirect:/trechos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            trechoService.excluir(id);
            redirect.addFlashAttribute("mensagem", "Trecho excluído com sucesso.");
        } catch (RegraNegocioException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/trechos";
    }

    private String formulario(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        model.addAttribute("tiposCalcada", TrechoService.TIPOS_CALCADA);
        return "trecho-form";
    }
}
