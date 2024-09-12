package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("person")
public class PersonController {
    List<Person> people = new ArrayList<>();

    public PersonController() {
        people.add(new Person("Jan Kowalski", "jk@poczta.pl"));
        people.add(new Person("Anna Nowak", "an@poczta.pl"));
    }

    @GetMapping()
    public String get(Model model) {
        model.addAttribute("person", people);
        model.addAttribute("newPerson", new Person("", ""));
        return "person";
    }

    @PostMapping("/add_person")
    public String add(@ModelAttribute Person person) {
        people.add(person);
        return "redirect:/person";
    }
}
