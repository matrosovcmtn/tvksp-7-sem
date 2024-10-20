package ru.matrosov.TVKSP_3prac.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.matrosov.TVKSP_3prac.model.Person;
import ru.matrosov.TVKSP_3prac.repository.PersonRepository;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyController {
    private final PersonRepository repository;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/add")
    public Person addPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping("/get")
    public List<Person> findAllPeople() {
        return repository.findAll();
    }

    @GetMapping("/getImg")
    public ResponseEntity<Resource> getImg() throws MalformedURLException {
        Path path = Paths.get("./public").resolve("pic.png");
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}
