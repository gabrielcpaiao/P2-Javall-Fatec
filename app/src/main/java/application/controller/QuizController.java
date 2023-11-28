package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import application.repository.QuizRepository;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("quizzes", quizRepository.findAll());
        return "quiz/list";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "quiz/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Quiz quiz = new Quiz();
        quiz.setNome(nome);
        quizRepository.save(quiz);
        System.out.println("Nome: " + nome);
        return "redirect:/quiz/list";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("id") Long id, Model ui) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isPresent()) {
            ui.addAttribute("quiz", quiz.get());
            return "quiz/update";
        }

        return "redirect:/quiz/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Long id, @RequestParam("nome") String nome) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isPresent()) {
            quiz.get().setNome(nome);
            quizRepository.save(quiz.get());
        }

        return "redirect:/quiz/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model ui) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isPresent()) {
            ui.addAttribute("quiz", quiz.get());
            return "quiz/delete";
        }

        return "redirect:/quiz/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Long id) {
        quizRepository.deleteById(id);
        return "redirect:/quiz/list";
    }
}
