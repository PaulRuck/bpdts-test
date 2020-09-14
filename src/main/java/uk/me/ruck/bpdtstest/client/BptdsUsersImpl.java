package uk.me.ruck.bpdtstest.client;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.me.ruck.bpdtstest.model.User;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@ConfigurationProperties(value = "bptds.users", ignoreUnknownFields = false)
public class BptdsUsersImpl implements BptdsUsers {
    @Setter
    private String apihost;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    private RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public List<User> allUsers() {
        ResponseEntity<User[]> response = getUserListFromUrl(apihost + "users");
        User[] users = response.getBody();

        log.info("Total users found: {}", users.length);

        return Arrays.asList(users);
    }

    @Override
    public List<User> usersByCity(String cityName) {
        ResponseEntity<User[]> response = getUserListFromUrl(apihost + "city/" + cityName + "/users");
        User[] users = response.getBody();

        log.info("Users found in {}: {}", cityName, users.length);

        return Arrays.asList(users);
    }

    protected ResponseEntity<User[]> getUserListFromUrl(String url){
        return restTemplate.getForEntity(url, User[].class);
    }
}