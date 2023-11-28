package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.repository.AlternativaRepository;
import org.springframework.ui.Model;

import application.model.Alternativa;

@Controller
@RequestMapping("/alternativa")
public class AlternativaController {

    @Autowired
    private AlternativaRepository alternativaRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("alternativas", alternativaRepository.findAll());
        return "/alternativa/list";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "/alternativa/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam String texto,
            @RequestParam(value = "correta", required = false, defaultValue = "false") boolean correta, Model ui) {
        Alternativa alternativa = new Alternativa();
        alternativa.setTexto(texto);
        alternativa.setCorreta(correta);
        alternativaRepository.save(alternativa);
        return "redirect:/alternativa/list";
    }

    @RequestMapping("/update")
    public String update(@RequestParam Long id, Model ui) {
        ui.addAttribute("alternativa", alternativaRepository.findById(id));
        return "/alternativa/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam Long id, @RequestParam String texto,
            @RequestParam(value = "correta", required = false, defaultValue = "false") boolean correta, Model ui) {
        Optional<Alternativa> alternativa = alternativaRepository.findById(id);

        if (alternativa.isPresent()) {
            alternativa.get().setTexto(texto);
            alternativa.get().setCorreta(correta);
            alternativaRepository.save(alternativa.get());
        }
        return "redirect:/alternativa/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id, Model ui) {
        Optional<Alternativa> alternativa = alternativaRepository.findById(id);
        if (alternativa.isPresent()) {
            ui.addAttribute("alternativa", alternativa.get());
            return "/alternativa/delete";
        }
        return "redirect:/alternativa/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam Long id) {
        alternativaRepository.deleteById(id);
        return "redirect:/alternativa/list";
    }
    

}
