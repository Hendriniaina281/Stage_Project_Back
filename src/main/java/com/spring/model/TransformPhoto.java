package com.spring.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import java.util.UUID;

public class TransformPhoto {

    private String data;
    private String contentType;

    public TransformPhoto() {

    }

    public TransformPhoto(String data, String contentType) {
        this.data = data;
        this.contentType = contentType;
    }

    public File toFile() throws FileNotFoundException, IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(this.data);
        File file = new File(this.generateRandomFileName());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        }

        return file;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String generateRandomFileName() {
        String fileName = UUID.randomUUID().toString().concat("." + this.contentType.split("/")[1]);

        return fileName;
    }

}
