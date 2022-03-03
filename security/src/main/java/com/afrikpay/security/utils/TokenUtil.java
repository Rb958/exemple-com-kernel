package com.afrikpay.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public class TokenUtil {

    private TokenUtil(){}

    public static String getToken(String secret, Map<String, ?> payload){
        Algorithm algo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withPayload(payload)
                .sign(algo);
    }

    public static DecodedJWT verifyToken(String secret, String token) throws JWTVerificationException{
        Algorithm algo = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algo)
                .build();
        return jwtVerifier.verify(token);
    }
}
