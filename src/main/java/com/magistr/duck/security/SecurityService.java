package com.magistr.duck.security;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {

    void autoLogin(HttpServletRequest request, String username, CharSequence password);
}
