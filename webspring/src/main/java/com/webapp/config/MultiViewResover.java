package com.webapp.config;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class MultiViewResover implements ViewResolver {  
  
    private Map<String, ViewResolver> resolvers;  
    private String def = "jsp";
    private String split = ".";
  
    @Override  
    public View resolveViewName(String viewName, Locale locale)  
            throws Exception {  
    	Iterator<String> iterator = resolvers.keySet().iterator();
    	for( ;iterator.hasNext(); ){
    		if(viewName.endsWith(split + iterator.next())){
    			return resolveView(viewName, locale);
    		}
    	}
        return resolvers.get(def).resolveViewName(viewName, locale);  
    }  
    
    private View resolveView(String viewName, Locale locale) throws Exception {
    	int n = viewName.lastIndexOf(split);
        String suffix = viewName.substring(n + 1);  
        ViewResolver resolver = resolvers.get(suffix);  
        viewName = viewName.substring(0, n);  
        return resolver.resolveViewName(viewName, locale);  
    }
  
    public Map<String, ViewResolver> getResolvers() {  
        return resolvers;  
    }  
  
    public void setResolvers(Map<String, ViewResolver> resolvers) {  
        this.resolvers = resolvers;  
    }  
} 