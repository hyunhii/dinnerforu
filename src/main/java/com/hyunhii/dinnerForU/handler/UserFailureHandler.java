package com.hyunhii.dinnerForU.handler;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class UserFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        
        String msg = "";

        /*if(exception instanceof UsernameNotFoundException) {
            msg = "UL01";
        } else*/ if(exception instanceof BadCredentialsException) {
            msg = "UL02";
        } else if(exception instanceof AccountExpiredException) {
            msg = "UL03";
        } else if(exception instanceof CredentialsExpiredException) {
            msg = "UL04";
        } else if(exception instanceof DisabledException) {
            msg = "UL05";
        } else if(exception instanceof LockedException) {
            msg = "UL06";
        } else if (exception instanceof AuthenticationServiceException) {
            msg = "UL07";
        }


        String errorMsg = URLEncoder.encode(msg, "UTF-8");

        setDefaultFailureUrl("/login?error=true&exception="+errorMsg);




        super.onAuthenticationFailure(request, response, exception);
    }
}
