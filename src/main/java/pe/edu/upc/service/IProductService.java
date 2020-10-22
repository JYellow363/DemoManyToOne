package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Product;

public interface IProductService {
	public Integer insert(Product product);

	public void delete(long idProduct);

	List<Product> list();

}
