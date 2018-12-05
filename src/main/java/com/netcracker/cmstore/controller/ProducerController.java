package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.model.Producer;
import com.netcracker.cmstore.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/")
@Controller
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        super();
        this.producerService = producerService;
    }

    @GetMapping("/producer/list")
    public String listProducer(ModelMap model) {
        List producers = producerService.getProducers();
        model.addAttribute("producers", producers);
        return "ListProducer";
    }

    @PostMapping("/producer/delete/{producerId}")
    public String deleteProducer(@PathVariable String producerId) {
        producerService.removeProducer(Integer.valueOf(producerId));
        return "redirect:/producer/list";
    }

    @GetMapping("/producer/insert")
    public String newProducer(ModelMap model) {
        Producer producer = new Producer();
        model.addAttribute("producer", producer);
        model.addAttribute("edit", false);
        return "Producer";
    }

    @PostMapping("/producer/updateOrInsert")
    public String saveProducer(@Valid Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return "Producer";
        }
        producerService.insertOrUpdateProducer(producer);
        return "redirect:/producer/list";
    }

    @GetMapping("/producer/edit/{producerId}")
    public String editProducer(@PathVariable String producerId, ModelMap model) {
        Producer producer = producerService.getProducerById(Integer.valueOf(producerId));
        model.addAttribute("producer", producer);
        model.addAttribute("edit", true);
        return "Producer";
    }
}
