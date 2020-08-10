
package com.angle.service.impl;

import com.angle.domain.Courier;
import com.angle.service.ICourierService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    private ICourierService loginService;

    public void setLoginService(ICourierService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 构建角色列表
        List<GrantedAuthority> grantAuths = new ArrayList();
        grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        // 得到客户对象
        Courier courier = loginService.findByUsername(username);
        if (courier != null && courier.getTag() == 1) {
            return new User(username, courier.getPassword(), grantAuths);
        } else {
            return null;
        }
    }
}