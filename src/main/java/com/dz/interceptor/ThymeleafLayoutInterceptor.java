package com.dz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dz.annotation.Layout;

public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {
	private static final String DEFAULT_LAYOUT = "layout/main";
	private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "content";

	private String defaultLayout = DEFAULT_LAYOUT;
	private String viewAttributeName = DEFAULT_VIEW_ATTRIBUTE_NAME;

	public void setDefaultLayout(String defaultLayout) {
		Assert.hasLength(defaultLayout);
		this.defaultLayout = defaultLayout;
	}

	public void setViewAttributeName(String viewAttributeName) {
		Assert.hasLength(defaultLayout);
		this.viewAttributeName = viewAttributeName;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 if (modelAndView == null || !modelAndView.hasView()) {
	            return;
	        }
		String originalViewName = modelAndView.getViewName();
		if (originalViewName==null||isRedirectOrForward(originalViewName)) {
			return;
		}
		String layoutName = getLayoutName(handler);
		if (Layout.NONE.equals(layoutName)) {
			
			return;
		}else if("".equalsIgnoreCase(layoutName)){
			 layoutName=DEFAULT_LAYOUT;
		}
		String viewAttribute = getViewAttribute(handler);
		if (Layout.NONE.equals(viewAttribute)) {
			return;
		}else if("".equalsIgnoreCase(viewAttribute)){
			viewAttribute=this.viewAttributeName;
		}
		modelAndView.setViewName(layoutName);
		modelAndView.addObject(viewAttribute, originalViewName);
		
	}

	private boolean isRedirectOrForward(String viewName) {
		return viewName.startsWith("redirect:")
				|| viewName.startsWith("forward:");
	}


	private String getLayoutName(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Layout layout = getMethodOrTypeAnnotation(handlerMethod);
		if (layout == null) {
			return Layout.NONE;
		} else {
			return layout.value();
		}
	}

	private String getViewAttribute(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Layout layout = getMethodOrTypeAnnotation(handlerMethod);
		if (layout == null) {
			return Layout.NONE;
		} else {
			return layout.viewAttribute();
		}
	}
	 private Layout getMethodOrTypeAnnotation(HandlerMethod handlerMethod) {
	        Layout layout = handlerMethod.getMethodAnnotation(Layout.class);
	        if (layout == null) {
	            return handlerMethod.getBeanType().getAnnotation(Layout.class);
	        }
	        return layout;
	    }
}