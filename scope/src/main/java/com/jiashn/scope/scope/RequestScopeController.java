package com.jiashn.scope.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangjs
 * @date 2023-03-12 11:01
 * @description 访问scope
 */
@RestController
@RequestMapping("/scope/use")
public class RequestScopeController {
    @Lazy
    @Autowired
    private ScopeForRequest request;
    @Lazy
    @Autowired
    private ScopeForSession session;
    @Lazy
    @Autowired
    private ScopeForApplication application;

    @GetMapping("/range.do")
    public String scopeRange(){
        return  "<ul>" +
                "<li>request scope: " +  request + "</li>" +
                "<li>session scope: " +  session + "</li>" +
                "<li>application scope: " +  application + "</li>" +
                "</ul>";
    }
}
