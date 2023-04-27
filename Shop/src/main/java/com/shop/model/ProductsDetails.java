package com.shop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ProductsDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(max = 30, min = 3)
	@NotBlank
	private String name;
	@Column(length = 250)
	@NotBlank
	private String description;
	@NotNull
	private boolean refurbished;
//	@LaunchYearBeforeMfgDate(MfgYear = 2000) // Custom validation constraint
	@Min(value = 1700)
	@Max(value = 2023)
	@NotNull
	private int launchYear;
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "MfgDate")
	private Date mfgDate;
	@NotNull
	@Min(value = 0)
	private int LoatNo;
	@NotNull
	@Range(min = 0, max = 1000)
	private int Qty;
	private boolean isActive;
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private ProductType productType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRefurbished() {
		return refurbished;
	}

	public void setRefurbished(boolean refurbished) {
		this.refurbished = refurbished;
	}

	public int getLaunchYear() {
		return launchYear;
	}

	public void setLaunchYear(Integer launchYear) {
		this.launchYear = launchYear;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	public int getLoatNo() {
		return LoatNo;
	}

	public void setLoatNo(int loatNo) {
		LoatNo = loatNo;
	}

	public int getQty() {
		return Qty;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
