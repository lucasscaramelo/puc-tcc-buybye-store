package br.com.buybye.domain.usecase;

import br.com.buybye.adapter.database.entities.ProdutoEntity;
import br.com.buybye.adapter.database.repository.ProdutoRepository;
import br.com.buybye.port.ProdutoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ProdutoUseCase implements ProdutoPort {

    @Autowired
    private ProdutoRepository productRepository;

    @Override
    public List<ProdutoEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProdutoEntity> findAllByActive() {
        return productRepository.findAllByActive();
    }

    @Override
    public Page<ProdutoEntity> findAllByCategoryId(Long categoryId, Pageable pageable) {
        List<ProdutoEntity> products = productRepository.findAllByCategoryId(categoryId);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ProdutoEntity> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<ProdutoEntity> pageProducts = new PageImpl<ProdutoEntity>(list, PageRequest.of(currentPage, pageSize), products.size());

        return pageProducts;
    }

    @Override
    public List<ProdutoEntity> topMostOrderedProducts(Integer top) {
        return productRepository.topMostOrderedProducts(top);
    }

    @Override
    public Page<ProdutoEntity> searchResults(String keyword, Long idCategoria, Long idMarca, Long idModelo, Pageable pageable) {
        List<ProdutoEntity> products = productRepository.searchProduct2(keyword, idCategoria, idMarca, idModelo);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ProdutoEntity> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<ProdutoEntity> pageProducts = new PageImpl<ProdutoEntity>(list, PageRequest.of(currentPage, pageSize), products.size());

        return pageProducts;
    }

    @Override
    public String ImageUpload(Long productId, MultipartFile imagem) {
        String fileName="";

        String productFolder = "src/main/resources/static/upload/product";
        //Save image
        try {
            byte[] bytes = imagem.getBytes();

            //Create directory if not exists
            File file = new File(productFolder+"/"+productId);
            if(!file.exists()){
                file.mkdirs();
            }

            fileName = imagem.getName()+".png";

            String fileWithFolderName = productFolder+"/"+ productId +"/"+ fileName;

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(fileWithFolderName)));

            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @Override
    public ProdutoEntity get(long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void save(ProdutoEntity produto) {
        productRepository.save(produto);

        if(produto.getImagem_carregada().getSize() > 0
        ) {
            if(produto.getImagem_carregada().getSize() > 0 ) {
                String imagem = ImageUpload(produto.getId(), produto.getImagem_carregada());
                produto.setImagem(imagem);
            }

            productRepository.save(produto);
        }
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
