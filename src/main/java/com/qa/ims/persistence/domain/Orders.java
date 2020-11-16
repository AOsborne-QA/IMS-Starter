package com.qa.ims.persistence.domain;

public class Orders {
	
	private Long id;
	private Long itemId;
	private Long customerId;
	private Double itemPrice;
	private Long quantity;
	private Long orderId;
	
	
	@Override
	public String toString() {
		return "Order id= " + id + ", itemId= " + itemId + ", customerId= " + customerId + ", itemPrice= " + itemPrice
				+ ", quantity= " + quantity + ", order Item Id=" + orderId + "]";
	}

	public Orders(Long orderId, Long customerId) {
		this.customerId = customerId;
		this.orderId = orderId;
	}
	
	public Orders(Long itemId, Long customerId, Long quantity) {
		this.customerId = customerId;
		this.itemId = itemId;
		this.quantity = quantity;
	}
	
	public Orders(Long id, Double itemPrice, Long itemId, Long customerId, Long quantity, Long orderId) {
		this.id = id;
		this.itemId = itemId;
		this.customerId = customerId;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.orderId = orderId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getItemId() {
		return itemId;
	}


	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Double getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (itemPrice == null) {
			if (other.itemPrice != null)
				return false;
		} else if (!itemPrice.equals(other.itemPrice))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}



	
	


}
