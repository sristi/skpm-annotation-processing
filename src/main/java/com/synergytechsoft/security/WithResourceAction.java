/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.synergytechsoft.security;

import com.synergytechsoft.config.ActionEnum;
import com.synergytechsoft.config.ResourceEnum;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import javax.inject.Qualifier;
import javax.interceptor.InterceptorBinding;

/**
 *
 * @author Suresh Pun <suresh.punmagar@syntechnepal.com>
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE})
@Qualifier
public @interface WithResourceAction {
    ResourceEnum resource() default ResourceEnum.USER;
    ActionEnum [] actions() default ActionEnum.VIEW;
}