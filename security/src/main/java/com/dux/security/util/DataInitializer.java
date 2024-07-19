package com.dux.security.util;

import com.dux.security.model.User;
import com.dux.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Value("${dataInitializer.enable}")
    private Boolean isEnabled = true;
    @Value("${dataInitializer.defaultPassword}")
    private String defaultPassword = "";
    @Value("${dataInitializer.defaultUsername}")
    private String defaultUsername = "";


    private static final String DATA_INITIALIZER_LOG = ":: SE HA INICIALIZADO CON LOS DATOS: username: {}, password: {} ::";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if( Boolean.TRUE.equals(isEnabled) && userRepository.findByUsername(defaultUsername).isEmpty() ) {
            String encodedPassword = passwordEncoder.encode(defaultPassword);

            User user = new User();
            user.setUsername(defaultUsername);
            user.setPassword(encodedPassword);

            userRepository.save(user);

            log.info(DATA_INITIALIZER_LOG, defaultUsername,defaultPassword);
        }
    }
}
