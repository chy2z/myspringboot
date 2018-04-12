package com.springboot.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2017/9/1.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private  boolean debug=false;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(debug) {

            logger.info("在请求处理之前进行调用（Controller方法调用之前）");

            /**
             * 对来自后台的请求统一进行日志处理
             */
            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
            System.out.println(request.getParameterMap());
            logger.info(String.format("请求参数, url: %s, method: %s, uri: %s, params: %s", url, method, uri, queryString));

            // 跳转登录
            //String url = "/login";
            //response.sendRedirect(url);

            // 只有返回true才会继续向下执行，返回false取消当前请求

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //logger.info("请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //logger.info("在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}