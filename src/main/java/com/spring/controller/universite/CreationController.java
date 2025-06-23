package com.spring.controller.universite;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.model.Academy;
import com.spring.model.Academyusers;
import com.spring.model.Users;
import com.spring.service.AcademyService;
import com.spring.service.AcademyusersService;
import com.spring.service.FirebaseService;
import com.spring.service.TokenService;
import com.spring.service.UsersService;
import com.spring.utility.Response;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/creation")
@CrossOrigin(origins = "*")
public class CreationController {
    @Autowired
    private AcademyService serviceacademy;

    @Autowired
    private UsersService serviceusers;

    @Autowired
    private FirebaseService servicefirebase;

    @Autowired
    private AcademyusersService serviceAU;

    @PostMapping("/uploadFileToFirebase")
    public ResponseEntity<String> uploadFileToFirebase(@RequestParam("logo") MultipartFile logo) {
        try {
            String url = servicefirebase.uploadFile(logo);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
    
    @PostMapping("/universite")
    public ResponseEntity<Response> infoUniversite(
            @RequestPart("academy") Academy academy,
            @RequestPart("users") Users users,
            @RequestPart("logo") MultipartFile logo) {
        Response response = new Response();
        try {
            String urlLogo = servicefirebase.uploadFile(logo);
            academy.setLogo(urlLogo);
            serviceusers.create(users);
            serviceacademy.create(academy);
            // Maka ny Id academy sy users ho ao aminy academyusers
            Academyusers au = new Academyusers();
            Long u = serviceusers.getIdByEmailAndNom(users.getEmail(), users.getMdp());
            Long idacademy = serviceacademy.getIdAcademy(academy.getNom(), academy.getEmail());
            au.setAcademy_id(Integer.parseInt(idacademy.toString()));
            au.setUsers(Integer.parseInt(u.toString()));
            serviceAU.create(au);

            response.setData("Création de l'université réussie !");
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    

   /*@PostMapping("/filiere_matiere")
    public ResponseEntity<Response> creation(@RequestHeader("Authorization") String authorizationHeader , @RequestParam("file_matiere") MultipartFile fileMatiereFiliere ,@RequestParam("file_filiere") MultipartFile fileFiliere) {
        Response response = new Response();
        try {
            servicetoken.checkRole(authorizationHeader, 2);
            List<Importation_matiere> list = serviceImportation.lireExcel(fileMatiereFiliere);
            serviceImportation.saveList(list);
            Academy a = servicetoken.getAcademy(authorizationHeader);
            // Insertion des Filiere
            List<Filiere_universite> listeFiliere = servicefiliere.lireExcel(fileFiliere , Long.parseLong(a.getId().toString()));
            servicefiliere.saveAll(listeFiliere);
            
            // ------- Appelle des fonction d insertion dans les autre table -------------
            sevicesemestre.savewithImportation(Long.parseLong(a.getId().toString()));
            servicemspf.savewithImportation();


            response.setData("Creation des Filieres a reussi !");
            response.setStatus_code("200");
            response.setMessage("réussi");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus_code("401");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }*/

     @PostMapping("/upload")
    public List<String> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws IOException {
        // Upload files to Firebase
        List<String> fileUrls = servicefirebase.uploadFiles(files);

        // Save each file URL to PostgreSQL
        // List<FileEtudiant> fileEntities = fileUrls.stream()
        //         .map(url -> {
        //             FileEntity fileEntity = new FileEntity();
        //             fileEntity.setUrl(url);
        //             return fileEntity;
        //         })
        //         .collect(Collectors.toList());

        // fileRepository.saveAll(fileEntities);

        return fileUrls;
    }

}
