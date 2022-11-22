package com.app.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.shop.model.Product;
import com.app.shop.model.ProductRepository;
import com.app.shop.model.UserRepository;

@RestController
public class AppController {
	@Autowired
	private UserRepository urepo;
	@Autowired
	private ProductRepository prepo;
	// view products
	@GetMapping("/view")
	public List<Product> findAllProducts(){
		return prepo.findAll();
	}
	// add product
	@PostMapping("/add")
	public Product placeOrder(@RequestBody Product obj) {
		Product p = prepo.save(obj);
		return p;
	}
	// update product
	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product obj) {
		int pid = obj.getProductid();
		Optional<Product> product = prepo.findById(pid);
		Product update = null;
		if(product.isPresent())
		{
			update = product.get();
			//update.setProductid(obj.getProductid());
			update.setProductname(obj.getProductname());
			update.setCategory(obj.getCategory());
			update.setPrice(obj.getPrice());
			update.setQuantity(obj.getQuantity());
			prepo.save(update);
		}
		return update;
	}
	// update product quantity
	@PutMapping("/update_qty")
	public Product updateQty(@RequestBody Product obj) {
		int pid = obj.getProductid();
		Optional<Product> product = prepo.findById(pid);
		Product update = null;
		if(product.isPresent())
		{
			update = product.get();
			update.setQuantity(obj.getQuantity());
			prepo.save(update);
		}
		return update;
	}
	@DeleteMapping("/delete")
	public void deleteTask(@RequestBody Product obj) {
		Optional<Product> p = prepo.findById(obj.getProductid());
		if(p.isPresent()) {
			prepo.deleteById(obj.getProductid());
			System.out.println("Delete successful");
		}
		else {
			System.out.println("Product not found");
		}
	}
}
