package ua.bestlunch.web.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ua.bestlunch.AuthorizedUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Виктор on 20.04.2017.
 */
public class ModelInterceptor extends HandlerInterceptorAdapter{
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null && !modelAndView.isEmpty()){
            AuthorizedUser authorizedUser = AuthorizedUser.safeGet();
            if(authorizedUser != null){
                modelAndView.getModelMap().addAttribute("userTo", authorizedUser.getUserTo());
            }
        }
    }
}
