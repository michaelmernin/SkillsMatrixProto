package prototype_skills.prototypeskills;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController
@SpringBootApplication
public class PrototypeskillsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeskillsApplication.class, args);
    }

//    @Value("${client.pseudo.property}")
//    private String pseudoProperty;
//
//    @GetMapping("/property")
//    public ResponseEntity<String> getProperty() {
//        return ResponseEntity.ok(pseudoProperty);
//    }
}
