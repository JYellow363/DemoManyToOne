package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
	@Query("select count(c.name) from Category c where c.name =:name")
	public int buscarNombreCategoria(@Param("name") String nombreCategoria);
}
