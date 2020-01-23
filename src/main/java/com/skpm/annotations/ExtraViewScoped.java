/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skpm.annotations;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Suresh Pun <suresh.punmagar@syntechnepal.com>
 */
@Target(ElementType.TYPE )
@Retention(RetentionPolicy.SOURCE)
@ViewScoped
public @interface ExtraViewScoped {
}
