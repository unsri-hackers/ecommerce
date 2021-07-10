package com.unsri.ecommerce.presentation.controllers.request;

public class PhotoDTO {
    private final String name;
    private final String path;

    PhotoDTO(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }
}
