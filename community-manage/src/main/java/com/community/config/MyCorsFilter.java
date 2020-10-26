package com.community.config;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* 解决前端请求的跨域问题，配置类
*/
@Component
public class MyCorsFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

      HttpServletRequest request = (HttpServletRequest) servletRequest;

      HttpServletResponse response = (HttpServletResponse) servletResponse;

      //这里填写你允许进行跨域的主机ip
      response.setHeader("Access-Control-Allow-Origin","*");
      //允许跨域携带Cookie
      response.setHeader("Access-Control-Allow-Credentials", "true");
      //允许的访问方法
      response.setHeader("Access-Control-Allow-Methods", "*");
      //Access-Control-Max-Age 用于 CORS 相关配置的缓存
      response.setHeader("Access-Control-Max-Age", "3600");
      response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
      if (request.getMethod().equals("OPTIONS")) {    	  
    	  response.setStatus(HttpStatus.SC_OK);
			// hresp.setContentLength(0);
    	  response.getWriter().write("OPTIONS returns OK");
          return;
      }
      
      filterChain.doFilter(servletRequest,servletResponse);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void destroy() {

  }
}
