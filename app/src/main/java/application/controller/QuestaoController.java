package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Questao;
import application.repository.AlternativaRepository;
import application.repository.QuestaoRepository;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/questao")
public class QuestaoController {
    
    @Autowired
    private QuestaoRepository questaoRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("questoes", questaoRepository.findAll());
        return "/questao/list";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "/questao/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam String enunciado, Model ui) {
        Questao questao = new Questao();
        questao.setEnunciado(enunciado);
        questaoRepository.save(questao);
        return "redirect:/questao/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id, Model ui) {
        Optional<Questao> questao = questaoRepository.findById(id);
        if (questao.isPresent()) {
            ui.addAttribute("questao", questao.get());
            return "/questao/delete";
        }

        return "redirect:/questao/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam Long id) {
        questaoRepository.deleteById(id);
        return "redirect:/questao/list";
    }

    @RequestMapping("/update")
    public String update(@RequestParam Long id, Model ui) {
        System.out.println(id);
        ui.addAttribute("quest", questaoRepository.findById(id).get());

        return "/questao/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam Long id, @RequestParam String enunciado, Model ui) {
        Optional<Questao> questao = questaoRepository.findById(id);

        if (questao.isPresent()) {
            questao.get().setEnunciado(enunciado);
            questaoRepository.save(questao.get());

        }

        return "redirect:/questao/list";
    }
}
