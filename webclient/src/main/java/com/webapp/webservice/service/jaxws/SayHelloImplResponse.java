package com.webapp.webservice.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for sayHelloImplResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="sayHelloImplResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sayHelloResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sayHelloImplResponse", propOrder = { "sayHelloResult" })
public class SayHelloImplResponse {

	protected String sayHelloResult;

	/**
	 * Gets the value of the sayHelloResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSayHelloResult() {
		return sayHelloResult;
	}

	/**
	 * Sets the value of the sayHelloResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSayHelloResult(String value) {
		this.sayHelloResult = value;
	}

}
