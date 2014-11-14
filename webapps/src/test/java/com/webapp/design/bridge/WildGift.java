package com.webapp.design.bridge;

public class WildGift extends Gift {
	// TODO: It is a good practice to call super() in a constructor
	public WildGift(GiftImpl impl) {
		this.giftImpl = impl;
	}
}
