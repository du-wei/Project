package com.webapp.JavaTXml;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ContentHandler;

public class JavaXmlUtils {

	// SAX dom4j Stax Jaxb apache configuration
	private static Logger logger = LogManager.getLogger(JavaXmlUtils.class);
	private static Object object;
	private static Marshaller marshaller;

	// jaxb
	public static void main(String[] args) throws Exception {
		XmlObject object = new XmlObject();
		object.setId(1);
		object.setName("hello");

		JavaXmlUtils.marshal(Arrays.asList(object, object));// .toFile(new
		// File("F:/javaToXml.xml"));
	}

	private static JavaXmlUtils instance = null;

	private JavaXmlUtils() {
	};

	private static JavaXmlUtils newInstance() {
		if (instance == null) {
			synchronized (JavaXmlUtils.class) {
				if (instance == null) {
					instance = new JavaXmlUtils();
				}
			}
		}
		return instance;
	}

	public static <T> JavaXmlUtils marshal(T t) {
		return marshal(Arrays.asList(t));
	}

	public static <T> JavaXmlUtils marshal(List<T> list) {
		ArrayList<T> newList = new ArrayList<>(list);

		@SuppressWarnings("unchecked")
		// Class<List<T>> clz = (Class<List<T>>)newList.getClass();
		// marshaller = getMarshaller(clz);
		Marshaller mar = getMarshaller(XmlObjPool.class);
		XmlObjPool<XmlObject> pool = new XmlObjPool<>();

		XmlObject objects = new XmlObject();
		objects.setId(1);
		objects.setName("hello");

		pool.add(objects);
		try {
			mar.marshal(pool, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		// if(clz.isAnnotationPresent(XmlRootElement.class)){
		// object = newList;
		// }else {
		// object = getJAXBElements(clz, newList);
		// }
		return newInstance();
	}

	public File toFile(File file) {
		return getMarshalResult(file);
	}

	public OutputStream toOutputStream(OutputStream os) {
		return getMarshalResult(os);
	}

	public Writer toWriter(Writer writer) {
		return getMarshalResult(writer);
	}

	public XMLStreamWriter toXMLStreamWriter(XMLStreamWriter xsw) {
		return getMarshalResult(xsw);
	}

	public ContentHandler toContentHandler(ContentHandler ch) {
		return getMarshalResult(ch);
	}

	public Result toResult(Result result) {
		return getMarshalResult(result);
	}

	public void toView() {
		getMarshalResult(System.out);
	}

	// private static <T> JAXBElement<T> getJAXBElement(Class<T> clz, T t){
	// return new JAXBElement<T>(new QName(clz.getSimpleName()), clz, t);
	// }

	private static <T> JAXBElement<List<T>> getJAXBElements(Class<List<T>> clz,
			List<T> list) {
		return new JAXBElement<List<T>>(new QName(clz.getSimpleName()), clz,
				list);
	}

	public <T> T getMarshalResult(T out) {
		try {
			if (out instanceof File) {
				marshaller.marshal(object, (File) out);
			} else if (out instanceof OutputStream) {
				marshaller.marshal(object, (OutputStream) out);
			} else if (out instanceof Writer) {
				marshaller.marshal(object, (Writer) out);
			} else if (out instanceof XMLStreamWriter) {
				marshaller.marshal(object, (XMLStreamWriter) out);
			} else if (out instanceof ContentHandler) {
				marshaller.marshal(object, (ContentHandler) out);
			} else if (out instanceof Result) {
				marshaller.marshal(object, (Result) out);
			}
			return out;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static <T> Marshaller getMarshaller(Class<T> clz) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(clz);
			Marshaller marshaller = ctx.createMarshaller();
			// marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "");
			// marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,
			// "");
			return marshaller;
		} catch (JAXBException e) {
			logger.error("创建Marshaller出错", e);
		}
		return null;
	}

	@XmlRootElement
	public static class XmlObjPool<T> extends ArrayList<T> {

		private static final long serialVersionUID = 1L;

		@XmlElement
		public List<T> getResult() {
			return this;
		}

	}

}
