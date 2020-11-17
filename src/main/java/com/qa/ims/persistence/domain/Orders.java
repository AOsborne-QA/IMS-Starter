package com.qa.ims.persistence.domain;

public class Orders {
	
	private Long customerId;
	private Long orderId;
	private Long orderItemId;
	private Long itemId;
	private Double itemPrice;
	private Long quantity;
	private Double orderCost;
	

	@Override
	public String toString() {
		return "orderId= " + orderId + "customerId= " + customerId +", orderItemId= " + orderItemId
				+ ", itemId= " + itemId + ", itemPrice= £" + itemPrice + ", quantity= " + quantity + ", orderCost= £"
				+ orderCost ;
	}


	public Orders(Long orderId) {
		this.orderId = orderId;
	}
	
	
	public Orders(Long orderId, Long customerId) {
		this.customerId = customerId;
		this.orderId = orderId;
	}
	
	
	public Orders(Double itemPrice, Long quantity) {
		this.itemPrice = itemPrice;
		this.quantity = quantity;
	}
	
	public Orders(Long itemId, Long customerId, Long quantity) {
		this.customerId = customerId;
		this.itemId = itemId;
		this.quantity = quantity;
	}
	

	public Orders(Long customerId, Long orderId, Long orderItemId, Long itemId, Double itemPrice, Long quantity,
			Double orderCost) {
		super();
		this.customerId = customerId;
		this.orderId = orderId;
		this.orderItemId = orderItemId;
		this.itemId = itemId;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.orderCost = orderCost;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public Long getOrderItemId() {
		return orderItemId;
	}


	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}


	public Long getItemId() {
		return itemId;
	}


	public void setItemId(Long itemId) {
		this.itemId = itemId;
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


	public Double getOrderCost() {
		return orderCost;
	}


	public void setOrderCost(Double orderCost) {
		this.orderCost = orderCost;
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
		if (orderCost == null) {
			if (other.orderCost != null)
				return false;
		} else if (!orderCost.equals(other.orderCost))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderItemId == null) {
			if (other.orderItemId != null)
				return false;
		} else if (!orderItemId.equals(other.orderItemId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}


}
