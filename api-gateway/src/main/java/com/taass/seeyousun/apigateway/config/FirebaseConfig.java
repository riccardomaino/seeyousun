package com.taass.seeyousun.apigateway.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseAuth firebaseAuth() {
        FirebaseApp firebaseApp;
        try {
            FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            firebaseApp = FirebaseApp.initializeApp(options);
            log.info("Inizializzazione Firebase Admin SDK avvenuta con successo");
        } catch (Exception e) {
            log.error("Inizializzazione Firebase Admin SDK fallita: " + e.getMessage());
            throw new RuntimeException("Errore nell'inizializzazione del bean FirebaseApp");
        }
        return FirebaseAuth.getInstance(firebaseApp);
    }
}
