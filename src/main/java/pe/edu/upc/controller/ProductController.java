package pe.edu.upc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Product;
import pe.edu.upc.service.ICategoryService;
import pe.edu.upc.service.IProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService pService;
	@Autowired
	private ICategoryService cService;

	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}

	@GetMapping("/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listCategories", cService.list());
		return "product/product";
	}

	@PostMapping("/save")
	public String saveProduct(@Valid Product product, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listCategories", cService.list());
			return "product/product";
		} else {
			int rpta = pService.insert(product);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listCategories", cService.list());
				return "/product/product";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listProducts", pService.list());

		return "/product/listProducts";
	}

	@GetMapping("/list")
	public String listProduct(Model model) {
		try {
			model.addAttribute("product", new Product());
			model.addAttribute("listProducts", pService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/product/listProducts";
	}

	@RequestMapping("/delete")
	public String deleteProduct(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un producto");
		}
		model.put("listProducts", pService.list());

//		return "redirect:/categories/list";
		return "/product/listProducts";
	}
}
