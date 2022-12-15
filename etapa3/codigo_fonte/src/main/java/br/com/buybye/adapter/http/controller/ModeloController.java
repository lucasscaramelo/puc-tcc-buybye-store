package br.com.buybye.adapter.http.controller;

import br.com.buybye.adapter.database.entities.MarcaEntity;
import br.com.buybye.adapter.database.entities.ModeloEntity;
import br.com.buybye.port.MarcaPort;
import br.com.buybye.port.ModeloPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/modelo")
public class ModeloController {

    @Autowired
    private MarcaPort marcaPort;

    @Autowired
    private ModeloPort modeloPort;

    private String add_edit_template="modelo/add-edit-modelo";
    private String list_template="modelo/list-modelo";
    private String list_redirect="redirect:/modelo/list";

    @GetMapping("/add")
    public String addModel(ModeloEntity model2, org.springframework.ui.Model model){
        model.addAttribute("modelo", model2);

        List<MarcaEntity> listMake = marcaPort.findAll();
        model.addAttribute("listMarca", listMake);

        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editModel(@PathVariable("id") int id, org.springframework.ui.Model model){
        ModeloEntity model2 = modeloPort.get(id);
        model.addAttribute("modelo", model2);

        List<MarcaEntity> listMake = marcaPort.findAll();
        model.addAttribute("listMarca", listMake);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveModel(@Valid @ModelAttribute("modelo") ModeloEntity model2, BindingResult result, org.springframework.ui.Model model){
        model.addAttribute("modelo", model2);

        List<MarcaEntity> listMake = marcaPort.findAll();
        model.addAttribute("listMarca", listMake);

        if(result.hasErrors()){
            return add_edit_template;
        }
        modeloPort.save(model2);

        return list_redirect+"?success";
    }

    @GetMapping("/list")
    public String listModel(org.springframework.ui.Model model) {
        List<ModeloEntity> listModel = modeloPort.findAll();
        model.addAttribute("listModelo", listModel);

        List<MarcaEntity> listMake = marcaPort.findAll();
        model.addAttribute("listMarca", listMake);

        return list_template;
    }
}
