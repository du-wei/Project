package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MarkDownController {

	public static void main(String[] args) {
//		Parser parse = Parboiled.createParser(Parser.class, Parser.DefaultParseRunnerProvider);
//		ParseRunnerProvider provider = Parser.DefaultParseRunnerProvider;
//		Parser parse = new Parser(Parser.ALL, 10000L, provider);
//		ToHtmlSerializerPlugin

//		Builder builder = PegDownPlugins.builder();
//		builder = builder.withPlugin(Parser.class, parse);
//		ToHtmlSerializerPlugin
//		builder.withHtmlSerializer();
//		PegDownPlugins plugins = builder.build();
//
//		PegDownProcessor processor = new PegDownProcessor(Extensions.ALL);


//		String html = processor.markdownToHtml("``code``");
//		RootNode ast = processor.parseMarkdown("``code``".toCharArray());

//		List<ToHtmlSerializerPlugin> serializePlugins = new ArrayList<>(1);
//		String html = new ToHtmlSerializer(new LinkRenderer()).toHtml(ast);
//		System.out.println(html);
	}

	@RequestMapping(value={"markdown", "md"})
	public ModelAndView index(ModelAndView mav){
		mav.setViewName("redirect:/markdown/index.html");
		return mav;
	}

}
