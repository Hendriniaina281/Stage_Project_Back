package com.spring.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@Service
public class FirebaseService {

    private final String bucket = "stage-1b901.appspot.com";

    // @PostConstruct
    // public void initializeFirebase() throws IOException {
    //     FirebaseOptions options = new FirebaseOptions.Builder()
    //             .setCredentials(GoogleCredentials.getApplicationDefault())
    //             .setStorageBucket(bucket)
    //             .build();
    //     FirebaseApp.initializeApp(options);
    // }

    // @PostConstruct
    // public void initializeFirebase() throws IOException {
    //     FileInputStream serviceAccount =
    //         new FileInputStream("src\\main\\resources\\stage-educom-web.json");

    //     FirebaseOptions options = new FirebaseOptions.Builder()
    //         .setCredentials(GoogleCredentials.fromStream(serviceAccount))
    //         .setStorageBucket(bucket)
    //         .build();

    //     FirebaseApp.initializeApp(options);
    // }

   @PostConstruct
    public void initializeFirebase() throws IOException {
    if (FirebaseApp.getApps().isEmpty()) {
        InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("stage-educom-web.json");
        if (serviceAccount == null) {
            throw new FileNotFoundException("Le fichier de compte de service est manquant.");
        }
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setStorageBucket(bucket)
            .build();
        FirebaseApp.initializeApp(options);
    }
}



    public String uploadFile(MultipartFile file) throws IOException {
        return uploadToFirebase(file);
    }

    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        List<String> fileUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            fileUrls.add(uploadToFirebase(file));
        }
        return fileUrls;
    }

    private String uploadToFirebase(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        StorageClient.getInstance().bucket().create(fileName, file.getInputStream(), file.getContentType());
        return String.format("https://firebasestorage.googleapis.com/v0/b/"+ bucket +"/o/%s?alt=media",URLEncoder.encode(fileName,StandardCharsets.UTF_8));
    }
}
