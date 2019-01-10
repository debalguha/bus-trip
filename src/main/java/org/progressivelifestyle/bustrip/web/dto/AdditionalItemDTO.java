package org.progressivelifestyle.bustrip.web.dto;

public class AdditionalItemDTO {
	private long id;
	private int quantity;
	private String item;
	private Double price;
	
	public AdditionalItemDTO() {}
	public AdditionalItemDTO(long itemId, int quantity) {
		super();
		this.id = itemId;
		this.quantity = quantity;
	}
	
	public AdditionalItemDTO(long itemId, int quantity, String item, Double price) {
		super();
		this.id = itemId;
		this.quantity = quantity;
		this.item = item;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long itemId) {
		this.id = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
