package com.ray.demo.admin.model.master;

import java.io.Serializable;
import java.util.List;

public class SellerAndBank implements Serializable {

	private static final long serialVersionUID = -6493905679614684877L;
	
	private Seller seller;
	
	private List<SellerBank> sellerBank;

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public List<SellerBank> getSellerBank() {
		return sellerBank;
	}

	public void setSellerBank(List<SellerBank> sellerBank) {
		this.sellerBank = sellerBank;
	}

}
