package com.tent.master.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by haobingfu on 2019/7/21.
 */
public class InvaildCaptchaException extends AuthenticationException {
    private static final long serialVersionUID = -9213074846415410892L;

    public InvaildCaptchaException() {
    }
}