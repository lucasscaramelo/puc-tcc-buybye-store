package br.com.buybye.adapter.http.controller;

import br.com.buybye.adapter.database.entities.CategoriaEntity;
import br.com.buybye.domain.usecase.CategoriaUseCase;
import br.com.buybye.port.CategoriaPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaPort categoriaPort;

    private String add_edit_template="categoria/add-edit-categoria";
    private String list_template="categoria/list-categoria";
    private String list_redirect="redirect:/categoria/list";

    @GetMapping("/add")
    public String addCategory(CategoriaEntity category, Model model){
        model.addAttribute("categoria", category);
        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") int id, Model model){
        CategoriaEntity category = categoriaPort.get(id);
        model.addAttribute("categoria", category);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute("categoria") CategoriaEntity category, BindingResult result, Model model){
        model.addAttribute("categoria", category);
        if(result.hasErrors()){
            return add_edit_template;
        }

        categoriaPort.save(category);

        return list_redirect+"?success";
    }

    @GetMapping("/list")
    public String listCategory(Model model) {
        List<CategoriaEntity> listCategories = categoriaPort.findAll();
        model.addAttribute("listCategorias", listCategories);

        return list_template;
    }
}
