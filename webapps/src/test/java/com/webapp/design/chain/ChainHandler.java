package com.webapp.design.chain;

public abstract class ChainHandler {

    protected ChainHandler handler = null;

    public ChainHandler getHandler() {
	return handler;
    }

    public void setHandler(ChainHandler handler) {
	this.handler = handler;
    }

    public abstract String handleRequest(String str);

    public static void main(String[] args) {
	ChainHandler handler1 = new UserChainHandler();
	ChainHandler handler2 = new StuChainHandler();

	handler1.setHandler(handler2);

	handler1.handleRequest("hello2");
    }
}

class UserChainHandler extends ChainHandler {

    @Override
    public String handleRequest(String str) {
	String result = "";
	if (str.length() <= 5) {
	    result = str;
	} else {
	    if (this.getHandler() != null) {
		result = this.getHandler().handleRequest(str);
	    }
	}
	System.out.println(result);
	return result;
    }

}

class StuChainHandler extends ChainHandler {

    @Override
    public String handleRequest(String str) {
	String result = "";
	if (str.length() > 5) {
	    result = str;
	} else {
	    if (this.getHandler() != null) {
		result = this.getHandler().handleRequest(str);
	    }
	}
	System.out.println(result);
	return result;
    }

}
