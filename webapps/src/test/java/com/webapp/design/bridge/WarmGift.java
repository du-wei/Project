package com.webapp.design.bridge;

public class WarmGift extends Gift {
	// TODO: It is a good practice to call super() in a constructor
	public WarmGift(GiftImpl impl) {
		this.giftImpl = impl;
	}
}
