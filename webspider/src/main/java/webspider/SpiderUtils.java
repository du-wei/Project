package webspider;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SpiderUtils {
	
	public static void main(String[] args) throws Exception {
	    spider();
    }
	
	public static void domain() throws Exception{
		WebClient client = new WebClient();
		String url = "http://my.chinaz.com/login.html?returnurl=http://mytool.chinaz.com";
		HtmlPage page = client.getPage(url);
		
		HtmlElement username = page.getHtmlElementById("username");
		username.click();
		username.type("googlecosplay@163.com");
		
		HtmlElement pwd = page.getHtmlElementById("password");
		pwd.click();
		
		HtmlElement submit = page.getHtmlElementById("submit_button");
		
		HtmlPage mypage = submit.click();
    }
	
	public static void spider() throws Exception {
		File file = new File("C:\\Users\\Lenovo\\Desktop\\file.htm");
	    Document parse = Jsoup.parse(file, "utf-8");
	    /*Elements table = parse.getElementById("jbxx").getElementsByClass("detailsList");
	    Elements basic = table.select("tr");
	    basic.remove(0);
	    
	    Iterator<Element> trs = basic.iterator();
	    for(;trs.hasNext();){
	    	Element next = trs.next();
	    	
	    	Iterator<Element> tds = next.children().iterator();
	    	for( ;tds.hasNext(); ){
	    		Element td = tds.next();
	    		if(td.hasText()){
	    			System.out.println(td.html() + " -> " + tds.next().html());
	    		}
	    	}
	    }*/
	    
	    Element gudong = parse.getElementById("tzr");
//	    XElements evaluate = Xsoup.compile("").evaluate(gudong);
//	    Elements elements = evaluate.getElements();
	    
    }
	
}
