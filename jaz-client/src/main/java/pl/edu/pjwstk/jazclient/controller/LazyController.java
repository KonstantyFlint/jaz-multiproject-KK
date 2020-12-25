package pl.edu.pjwstk.jazclient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping()
public class LazyController {
    @GetMapping("{url}")
    public ResponseEntity<Object> get(@PathVariable("url") String relativeUrl) throws URISyntaxException {
        URI uri = new URI("http://jazapi:11111/" + relativeUrl);
        System.out.println(uri);
        RestTemplate template = new RestTemplate();
        Object object = template.getForObject(uri,Object.class);
        return ResponseEntity.of(Optional.of(object));
    }
    @GetMapping("/trains/id/{url}")
    public ResponseEntity<Object> get2(@PathVariable("url") String relativeUrl) throws URISyntaxException {
        URI uri = new URI("http://jazapi:11111//trains/id/" + relativeUrl);
        System.out.println(uri);
        RestTemplate template = new RestTemplate();
        Object object = template.getForObject(uri,Object.class);
        return ResponseEntity.of(Optional.of(object));
    }
    @GetMapping("/carts/id/{url}")
    public ResponseEntity<Object> get3(@PathVariable("url") String relativeUrl) throws URISyntaxException {
        URI uri = new URI("http://jazapi:11111//carts/id/" + relativeUrl);
        System.out.println(uri);
        RestTemplate template = new RestTemplate();
        Object object = template.getForObject(uri,Object.class);
        return ResponseEntity.of(Optional.of(object));
    }
}
