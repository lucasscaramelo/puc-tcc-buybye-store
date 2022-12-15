package br.com.buybye.adapter.database.repository;

import br.com.buybye.adapter.database.entities.ModeloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModeloRepository extends JpaRepository<ModeloEntity, Long> {

    ModeloEntity findByNome(String nome);

    @Query(value="select * from modelo m where 1=1 and nome=?1 and id_marca=?2", nativeQuery = true)
    public ModeloEntity findByName2(String nome, Long idMarca);

    @Query(value="select * from modelo m where 1=1 and (id_modelo=1 or id_marca=?1)", nativeQuery = true)
    public List<ModeloEntity> getModels(Long idMarca);
}
