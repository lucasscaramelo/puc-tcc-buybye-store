package br.com.buybye.port;

import br.com.buybye.adapter.database.entities.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProdutoPort {
    List<ProdutoEntity> findAll();
    List<ProdutoEntity> findAllByActive();
    Page<ProdutoEntity> findAllByCategoryId(Long categoryId, Pageable pageable);
    List<ProdutoEntity> topMostOrderedProducts(Integer top);
    Page<ProdutoEntity> searchResults(String keyword, Long idCategoria, Long idMarca, Long idModelo, Pageable pageable);
    String ImageUpload(Long productId, MultipartFile imagem);
    ProdutoEntity get(long id);
    void save(ProdutoEntity produto);
    void delete(long id);
}
