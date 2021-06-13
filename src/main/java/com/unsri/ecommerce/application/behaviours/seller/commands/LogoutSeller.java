package com.unsri.ecommerce.application.behaviours.seller.commands;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.JwtUser;
import com.unsri.ecommerce.infrastructure.repository.JwtUserRepository;

import java.util.Optional;

public class LogoutSeller implements BaseCommand<String> {

    private final JwtUserRepository jwtUserRepository;
    private final String jwt;

    public LogoutSeller(JwtUserRepository jwtUserRepository, String jwt) {
        this.jwtUserRepository = jwtUserRepository;
        this.jwt = jwt;
    }

    @Override
    public String execute(Optional<String> param) {
        Optional<JwtUser> jwtUser = jwtUserRepository.findByJwt(jwt);
        jwtUser.get().setInvalidated(true);
        jwtUserRepository.save(jwtUser.get());

        return "Seller is logged out successfully";
    }
}
