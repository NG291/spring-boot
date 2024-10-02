package com.demospringboot.controller;

import com.demospringboot.model.Customer;
import com.demospringboot.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView showList() {
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customers/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("/customers/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/update")
    public ModelAndView showFormUpdate(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customer = customerService.findByID(id);
            ModelAndView modelAndView = new ModelAndView("/customers/update");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/update")
    public ModelAndView update(Customer customer) {
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        customerService.save(customer);
        modelAndView.addObject("message", "Update customer created successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showFormDelete(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customer = customerService.findByID(id);
            ModelAndView modelAndView = new ModelAndView("/customers/delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public ModelAndView delete(Customer customer) {
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        customerService.delete(customer.getId());
        modelAndView.addObject("message", "Delete customer created successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView showView(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customer = customerService.findByID(id);
            ModelAndView modelAndView = new ModelAndView("/customers/view");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }
}
