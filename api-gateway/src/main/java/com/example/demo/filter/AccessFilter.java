package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhenyu.guo
 * @date 2018/5/2.
 */
@Slf4j
public class AccessFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        //获取当下上下文信息
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest contextRequest = context.getRequest();
        log.info("send {} request to {}", contextRequest.getMethod(), contextRequest.getRequestURL().toString());
        String accessToken = contextRequest.getParameter("accessToken");
        if (accessToken == null) {
            log.warn("access token is empty");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
