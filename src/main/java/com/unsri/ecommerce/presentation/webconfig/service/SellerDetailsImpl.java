package com.unsri.ecommerce.presentation.webconfig.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unsri.ecommerce.application.domain.Seller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SellerDetailsImpl implements UserDetails {

    private final int id;

    private final String username;

    private final String email;

    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public SellerDetailsImpl(int id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static SellerDetailsImpl build(Seller seller) {
        List<GrantedAuthority> authorities =
            AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"); // Temporary

        return new SellerDetailsImpl(
            seller.getId(),
            seller.getUsername(),
            seller.getEmail(),
            seller.getPassword(),
            authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SellerDetailsImpl sellerDetailsImpl = (SellerDetailsImpl) obj;
        return Objects.equals(id, sellerDetailsImpl.id);
    }
}
