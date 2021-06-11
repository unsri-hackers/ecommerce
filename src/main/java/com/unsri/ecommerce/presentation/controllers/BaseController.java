package com.unsri.ecommerce.presentation.controllers;

import com.unsri.ecommerce.infrastructure.security.service.SellerDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class BaseController {

    public int getAuthorizedUser(Principal principal) {

        SellerDetailsImpl sellerDetails = (SellerDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return sellerDetails.getId();
    }
}
