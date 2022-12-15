package br.com.buybye.adapter.http.controller;

import br.com.buybye.adapter.database.entities.MarcaEntity;
import br.com.buybye.port.MarcaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaPort marcaPort;

    private String add_edit_template="marca/add-edit-marca";
    private String list_template="marca/list-marca";
    private String list_redirect="redirect:/marca/list";

    @GetMapping("/add")
    public String addMake(MarcaEntity marca, Model model){
        model.addAttribute("marca", marca);
        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editMake(@PathVariable("id") int id, Model model){
        MarcaEntity make = marcaPort.get(id);
        model.addAttribute("marca", make);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveMake(@Valid @ModelAttribute("marca") MarcaEntity marca, BindingResult result, Model model){
        model.addAttribute("marca", marca);

        if(result.hasErrors()){
            return add_edit_template;
        }
        marcaPort.save(marca);

        return list_redirect+"?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteMake(@PathVariable("id") int id, Model model) {
        marcaPort.delete(id);
        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listMake(Model model) {
        List<MarcaEntity> listMarca = marcaPort.findAll();
        model.addAttribute("listMarca", listMarca);

        return list_template;
    }

}
