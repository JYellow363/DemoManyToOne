package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Category;

public interface ICategoryService {
	public Integer insert(Category categoria);

	public void delete(long idCategoria);

	List<Category> list();

}
