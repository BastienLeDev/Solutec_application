package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.TypeProduct;
import fr.solutec.repository.TypeProductRepository;

@RestController
@CrossOrigin("*")
public class TypeProductRest {

	@Autowired
	private TypeProductRepository typeProductRepo;
	
	@GetMapping("typeProduct/liste")
	public Iterable<TypeProduct> getAll() {
		return typeProductRepo.findAll();
	}
}
