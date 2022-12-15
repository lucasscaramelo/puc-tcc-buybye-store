package br.com.buybye.adapter.http.controller;

import br.com.buybye.adapter.database.entities.UsuarioEntity;
import br.com.buybye.domain.model.Usuario;
import br.com.buybye.port.UsuarioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UsuarioPort usuarioPort;

    @GetMapping("/login")
    public String login(Model model) {

        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Usuario userRegistrationDto = new Usuario();
        model.addAttribute("userRegistrationDto", userRegistrationDto);

        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("userRegistrationDto") Usuario userRegistrationDto, BindingResult result, Model model) {
        model.addAttribute("userRegistrationDto", userRegistrationDto);
        UsuarioEntity userExists = usuarioPort.findByUsername(userRegistrationDto.getUsername());

        if (userExists != null) {
            return "redirect:register?username";
        }
        if(result.hasErrors()){
            return "auth/register";
        }
        usuarioPort.save(userRegistrationDto);

        return "redirect:register?success";
    }
}
