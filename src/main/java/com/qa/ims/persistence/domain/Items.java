package com.qa.ims.persistence.domain;

public class Items {
	
	private Long id;
	private String productName;
	private Double price;
	
	public Items(String productName, Double price) {
		this.productName = productName;
		this.price = price;
	}
	
	public Items(Long id, String productName, Double price) {
		this.id = id;
		this.productName = productName;
		this.price = price;
	}

	public String getName() {
		return productName;
	}

	public void setName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "id:" + id + " Product Name:" + productName + " Product Price:" + "£" + price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
	
}
