package com.bfst.titrefoncier.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserService userService) {
        return args -> {
          /*userService.createUser(
                  User.builder()
                          .username("admin")
                          .name("Gaius Ocklefort Yan-bena")
                          .password("admin")
                          .role(Role.ADMIN)
                          .isEnabled(true)
                          .build());*/
        };
    }
}
