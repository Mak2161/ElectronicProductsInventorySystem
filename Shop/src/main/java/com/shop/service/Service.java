package com.shop.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.factory.SessionFactoryProvider;
import com.shop.model.ProductType;
import com.shop.model.ProductsDetails;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private SessionFactoryProvider factory;

	// add productDetail
	public boolean addProductDetail(ProductsDetails productsDetails) throws SQLException {
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		productsDetails.setActive(productsDetails.getQty() > 0);
		boolean yearValidate = launchYearMfgYear(productsDetails.getLaunchYear(), productsDetails.getMfgDate());
		if (yearValidate) {
			session.save(productsDetails);
			transaction.commit();
			return true;
		}
		session.close();
		return false;
	}

	// update productDetail
	public boolean updateProductdetail(ProductsDetails productsDetails, int productById) throws SQLException {
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		ProductsDetails newProductsDetails = session.get(ProductsDetails.class, productById);
		newProductsDetails.setName(productsDetails.getName());
		newProductsDetails.setDescription(productsDetails.getDescription());
		newProductsDetails.setRefurbished(productsDetails.isRefurbished());
		newProductsDetails.setLaunchYear(productsDetails.getLaunchYear());
		newProductsDetails.setMfgDate(productsDetails.getMfgDate());
		newProductsDetails.setLoatNo(productsDetails.getLoatNo());
		newProductsDetails.setQty(productsDetails.getQty());
		newProductsDetails.setActive(productsDetails.getQty() > 0);
		newProductsDetails.setProductType(productsDetails.getProductType());
		boolean yearValidate = launchYearMfgYear(productsDetails.getLaunchYear(), productsDetails.getMfgDate());
		if (yearValidate) {
			session.update(newProductsDetails);
			transaction.commit();
			session.close();
			return true;
		}
		session.close();
		return false;
	}

	// delete productDetail
	public boolean deleteProductDetail(int productById) throws SQLException {
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		ProductsDetails productsDetails = session.get(ProductsDetails.class, productById);
		session.delete(productsDetails);
		transaction.commit();
		session.close();
		return true;
	}

	// get productDetail by id
	public ProductsDetails getProductDetail(int productById) throws SQLException {
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		ProductsDetails productsDetails = session.get(ProductsDetails.class, productById);
		transaction.commit();
		session.close();
		return productsDetails;
	}

	// get allproductDetail
	public List<ProductsDetails> getallProductDetail() throws SQLException {
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<ProductsDetails> productsDetailsList = session.createQuery("FROM ProductsDetails").list();
		transaction.commit();
		session.close();
		return productsDetailsList;
	}

	// get active product
	public List<ProductsDetails> getallActiveProducts() throws SQLException {
		String hql = "FROM ProductsDetails WHERE isActive = :isActive";
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query activeProducts = session.createQuery(hql);
		activeProducts.setParameter("isActive", true);
		List<ProductsDetails> productsDetailsList = activeProducts.getResultList();
		transaction.commit();
		session.close();
		return productsDetailsList;
	}

	// get inactive product
	public List<ProductsDetails> getallInActiveProducts() throws SQLException {
		String hql = "FROM ProductsDetails WHERE isActive = :isActive";
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query activeProducts = session.createQuery(hql);
		activeProducts.setParameter("isActive", false);
		List<ProductsDetails> productsDetailsList = activeProducts.getResultList();
		transaction.commit();
		session.close();
		return productsDetailsList;
	}

	// add Product Type
	public void addProductType(ProductType productType) throws SQLException {
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(productType);
		transaction.commit();
		session.close();
	}

	// get Product Type
	public ProductType getProductType(int id) {
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		ProductType productType = session.get(ProductType.class, id);
		transaction.commit();
		session.close();
		return productType;
	}

	// delete Product Type
	public boolean deleteProductType(int id) throws SQLException {
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		ProductType productType = session.get(ProductType.class, id);
		session.delete(productType);
		transaction.commit();
		session.close();
		return true;
	}
	
	//get list of ProductType
	public List<ProductType> getallProductType() {
		SessionFactory sessionFactory = factory.provideSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<ProductType> productTypeList = session.createQuery("FROM ProductType").list();
		transaction.commit();
		session.close();
		return productTypeList;
	}

	// Launch Year after MfgDate
	public Boolean launchYearMfgYear(int launchYear, Date mfgYear) {
		// the getYear method return value by -1900
		if ((mfgYear.getYear() + 1900) >= launchYear) {
			return true;
		}
		return false;
	}

}
