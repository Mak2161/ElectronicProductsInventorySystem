package com.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.model.ProductType;
import com.shop.model.ProductsDetails;
import com.shop.service.Service;

@CrossOrigin
@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private Service service;

	@RequestMapping(path = "/addProduct", method = RequestMethod.POST)
	public @ResponseBody boolean addProductDetail(@RequestBody ProductsDetails productsDetails) {
		try {
			return service.addProductDetail(productsDetails);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(path = "/getallProduct", method = RequestMethod.GET)
	public @ResponseBody List<ProductsDetails> getallProductDetail() {
		try {
			return service.getallProductDetail();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(path = "/getallActiveProduct", method = RequestMethod.GET)
	public @ResponseBody List<ProductsDetails> getallActiveProductDetail() {
		try {
			return service.getallActiveProducts();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(path = "/getallInActiveProduct", method = RequestMethod.GET)
	public @ResponseBody List<ProductsDetails> getallInActiveProductDetail() {
		try {
			return service.getallInActiveProducts();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/deleteProduct/{productById}", method = RequestMethod.DELETE)
	public @ResponseBody Boolean doDelete(@PathVariable int productById) {
		try {
			return service.deleteProductDetail(productById);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value = "/updateProduct/{productById}", method = RequestMethod.PUT)
	public @ResponseBody boolean doPut(@RequestBody ProductsDetails productsDetails, @PathVariable int productById) {
		try {
			return service.updateProductdetail(productsDetails, productById);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(path = "/getProduct/{productById}", method = RequestMethod.GET)
	public @ResponseBody ProductsDetails getProductDetail(@PathVariable int productById) {
		try {
			return service.getProductDetail(productById);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(path = "/addProductType", method = RequestMethod.POST)
	public @ResponseBody String addProductType(@ModelAttribute ProductType productType) {
		try {
			service.addProductType(productType);
			return "done";
		} catch (Exception e) {
			e.printStackTrace();
			return "something went wrong";
		}
	}

	@RequestMapping(value = "/deleteProductType/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Boolean deleteProductType(@PathVariable int id) {
		try {
			return service.deleteProductType(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping(path = "/getallProductType", method = RequestMethod.GET)
	public @ResponseBody List<ProductType> getallProductType() {
		try {
			return service.getallProductType();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
