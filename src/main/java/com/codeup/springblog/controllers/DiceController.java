package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDiceGuess(@PathVariable int n, Model model) {
        Random random = new Random();
        int diceRoll = random.nextInt((6 - 1) + 1) + 1;

        model.addAttribute("guess", n);
        model.addAttribute("roll", diceRoll);
        return "roll-answer";
    }

}
