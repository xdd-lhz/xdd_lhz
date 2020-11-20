package com.club.club_manager.aop;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @author: lhz
 * @date: 2020/10/29
 **/
@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PointC {
}
