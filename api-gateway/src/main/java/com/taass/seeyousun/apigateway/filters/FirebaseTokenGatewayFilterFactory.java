package com.taass.seeyousun.apigateway.filters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.taass.seeyousun.apigateway.exceptions.AuthenticationRequiredException;
import com.taass.seeyousun.apigateway.exceptions.FirebaseValidationException;
import com.taass.seeyousun.apigateway.exceptions.InvalidAuthenticationTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirebaseTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<FirebaseTokenGatewayFilterFactory.Config> {
    private final FirebaseAuth firebaseAuth;
    public FirebaseTokenGatewayFilterFactory(FirebaseAuth firebaseAuth) {
        super(Config.class);
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String idToken;
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if(authHeader == null || authHeader.isEmpty()){
                log.info("Fallimento, token di autenticazione mancante");
                throw new AuthenticationRequiredException("Fallimento, token di autenticazione mancante. Necessaria autenticazione");
            }
            if(authHeader.startsWith("Bearer ")){
                idToken = authHeader.substring(7);
            } else {
                log.info("Fallimento, tipologia di token di autenticazione sbagliata. Necessario l'utilizzo di un Bearer token");
                throw new InvalidAuthenticationTokenException("Fallimento, tipologia di token di autenticazione sbagliata. Necessario l'utilizzo di un Bearer token");
            }
            FirebaseToken decodedToken = extractToken(idToken);
            if(decodedToken == null){
                log.info("Fallimento, token di autenticazione non valido o scaduto. Riprovare.");
                throw new InvalidAuthenticationTokenException("Fallimento, token di autenticazione non valido o scaduto. Riprovare.");
            }
            String uid = decodedToken.getUid();
            String name = decodedToken.getName();
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("X-User-UID", uid)
                    .header("X-User-Name", name)
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    private FirebaseToken extractToken(String idToken){
        FirebaseToken decodedToken;
        try{
            decodedToken = this.firebaseAuth.verifyIdToken(idToken);
        }catch (FirebaseAuthException e){
            log.error("Fallimento, errore nella verifica del token: " + e.getMessage());
            throw new FirebaseValidationException("Fallimento, errore nella verifica del token: " + e.getMessage());
        }
        return decodedToken;
    }

    public static class Config{}
}