package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    @Query("select p from produto p " +
            "where 1=1 " +
            "and ( upper(p.nome) like concat('%', upper(?1), '%') " +
            "       or upper(p.descricao) like concat('%', upper(?1), '%') " +
            ")")
    List<ProdutoEntity> searchProduct(String nome);

    @Query(value="select * from produto p " +
            "where 1=1 " +
            "and status  = 1 " +
            "and (length(?1) = 0 or p.nome like concat('%', ?1, '%')) " +
            "and (length(?2) = 0 or id_categoria= ?2) "+
            "and (?3=1  or id_marca= ?3) "+
            "and (?4=1 or id_modelo= ?4) "
            , nativeQuery = true)
    List<ProdutoEntity> searchProduct2(String keyword, Long idCategoria, Long idMarca, Long idModelo);

    @Query(value="select p.* from produto p where 1=1 and status=1 limit 8", nativeQuery = true)
    public List<ProdutoEntity> findAllByActive();

    @Query("select p from produto p where 1=1 and status=1 and id_categoria=?1")
    public List<ProdutoEntity> findAllByCategoryId(Long categoryId);

    @Query(value = "SELECT p.*, sum(quantity) as qtds\n" +
            "FROM detalhe_pedido o\n" +
            "inner join produto p on p.id_produto = o.id_produto \n" +
            "where 1=1 " +
            "and p.status=1\n" +
            "group by p.id_produto\n" +
            "order by sum(quantity) desc\n" +
            "limit ?1", nativeQuery = true )
    public List<ProdutoEntity> topMostOrderedProducts(Integer top);
}
