package com.mall.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Spring Security 用户登录实体类
 * @author bingshan
 * @since 2026/1/2 16:39
 */
public class UserLoginInfo implements Serializable, UserDetails {

    private int id;

    private String username;
    private String password;

    private String role;

    private List<Role> roleList = new ArrayList<>();

    public UserLoginInfo(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * 得到用户的权限，如果权限表和用户表是分开的，则需要重新定义一个实体类实现UserDetails
     * 并且继承于Uer类
     * 交给security 的权限，放在 UserDetailService进行处理
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 指示用户的账户是否已过期。无法验证过期的账户。
     * 如果用户的账户有效（即未过期），则返回true，如果不在有效就返回false
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * 指示用户是锁定还是解锁。无法对锁定的用户进行身份验证。
     * 如果用户未被锁定，则返回true，否则返回false
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * 指示用户的凭证（密码）是否已过期。过期的凭证阻止身份验证
     * 如果用户的凭证有效（即未过期），则返回true
     * 如果不在有效（即过期），则返回false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * 指示用户是启用还是禁用。无法对禁用的用户进行身份验证
     * 如果启用了用户，则返回true，否则返回false
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

}
