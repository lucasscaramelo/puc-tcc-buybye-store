package br.com.buybye.adapter.http.controller;

import br.com.buybye.adapter.database.entities.CategoriaEntity;
import br.com.buybye.adapter.database.entities.MarcaEntity;
import br.com.buybye.adapter.database.entities.ModeloEntity;
import br.com.buybye.adapter.database.entities.ProdutoEntity;
import br.com.buybye.domain.model.Dropdown;
import br.com.buybye.domain.usecase.CategoriaUseCase;
import br.com.buybye.port.MarcaPort;
import br.com.buybye.port.ModeloPort;
import br.com.buybye.port.ProdutoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoPort produtoPort;

    @Autowired
    private CategoriaUseCase categoriaPort;

    @Autowired
    private MarcaPort marcaPort;

    @Autowired
    private ModeloPort modeloPort;

    private String add_edit_template="produto/add-edit-produto";
    private String list_template="produto/list-produto";
    private String list_redirect="redirect:/produto/list";


    @GetMapping("/add")
    public String addProduct(ProdutoEntity product, org.springframework.ui.Model model){
        model.addAttribute("produto", product);

        List<CategoriaEntity> categories = categoriaPort.findAll();
        model.addAttribute("categorias", categories);

        List<MarcaEntity> listMake = marcaPort.findAll();
        model.addAttribute("listMarca", listMake);

        List<ModeloEntity> listModel = modeloPort.getModels(listMake.get(0).getId());
        model.addAttribute("listModelo", listModel);

        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, org.springframework.ui.Model model){
        ProdutoEntity product = produtoPort.get(id);
        model.addAttribute("produto", product);

        List<CategoriaEntity> categories = categoriaPort.findAll();
        model.addAttribute("categorias", categories);

        List<MarcaEntity> listMake = marcaPort.findAll();
        model.addAttribute("listMarca", listMake);

        List<ModeloEntity> listModel = modeloPort.getModels(listMake.get(0).getId());
        if(product.getMarca() !=null) {
            listModel = modeloPort.getModels(product.getMarca().getId());
        }
        model.addAttribute("listModelo", listModel);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("produto") ProdutoEntity product, BindingResult result, org.springframework.ui.Model model){
        model.addAttribute("produto", product);

        List<CategoriaEntity> categories = categoriaPort.findAll();
        model.addAttribute("categorias", categories);

        List<MarcaEntity> listMake = marcaPort.findAll();
        model.addAttribute("listMarca", listMake);

        if(result.hasErrors()){
            return add_edit_template;
        }
        produtoPort.save(product);

        return list_redirect+"?success";
    }

    @GetMapping("/list")
    public String listProduct(org.springframework.ui.Model model) {
        List<CategoriaEntity> categories = categoriaPort.findAll();
        model.addAttribute("categorias", categories);

        List<ProdutoEntity> listProducts = produtoPort.findAll();
        model.addAttribute("listProdutos", listProducts);

        return list_template;
    }

    @RequestMapping(value = "/models")
    @ResponseBody
    public List<Dropdown> getModels(@RequestParam Long make) {
        List<ModeloEntity> modelList = modeloPort.getModels(make);
        List<Dropdown> dropdownList=new ArrayList<>();

        for (ModeloEntity model: modelList) {
            dropdownList.add(new Dropdown(model.getId(), model.getNome()));
        }

        return dropdownList;
    }
}
