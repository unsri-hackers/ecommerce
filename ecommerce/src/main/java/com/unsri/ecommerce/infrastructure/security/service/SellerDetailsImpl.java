package com.unsri.ecommerce.infrastructure.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unsri.ecommerce.domain.models.Seller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class SellerDetailsImpl implements UserDetails {

    private int id;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public SellerDetailsImpl(int id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static SellerDetailsImpl build(Seller seller) {
        List<GrantedAuthority> authorities =
            AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"); // Temporary

        return new SellerDetailsImpl(
            seller.getId(),
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
        return null;
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
