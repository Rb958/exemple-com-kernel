package com.afrikpay.security.service.authentication;

import com.afrikpay.security.exception.SecurityException;
import com.afrikpay.security.manager.ParameterManager;
import com.afrikpay.security.model.User;
import com.afrikpay.security.utils.HttpUtil;
import com.afrikpay.security.utils.TokenUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dbgateway.exception.DBConnectionConfigException;
import okhttp3.Response;
import org.springframework.beans.factory.support.ManagedMap;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

    private final ParameterManager parameterManager;
    private static final ObjectMapper mapper;

    public AuthenticationService() throws DBConnectionConfigException {
        this.parameterManager = new ParameterManager();
    }

    static {
        mapper = new ObjectMapper();
    }

    public String auth(String login, String password) throws SecurityException {
        try {
            // Check if user exists using his credentials
            Map<String, String> userDetailMap = new HashMap<>();
            userDetailMap.put("login", login);
            userDetailMap.put("password", password);

            String getUserApi = this.parameterManager.get("get_user_api");
            Response response = HttpUtil.sendPOST(getUserApi, userDetailMap);
            User user = mapper.readValue(response.body().toString(), new TypeReference<User>() {});

            // Check user Exists by calling User auth api on specified component
            if (user != null) {
                String tokenSecretKey = this.parameterManager.get("token_secret_key");
                int tokenExpiredTime = Integer.parseInt(this.parameterManager.get("token_exp_minutes"));
                Calendar currentTime = Calendar.getInstance();
                currentTime.add(Calendar.MINUTE, tokenExpiredTime);
                Map<String, String> tokenPayload = new ManagedMap<>();
                tokenPayload.put("exp", String.valueOf(currentTime.getTime().getTime()));
                tokenPayload.put("sub", user.getEmail());
                tokenPayload.put("role", user.getRole());
                tokenPayload.put("login", user.getLogin());
                return TokenUtil.getToken(tokenSecretKey, tokenPayload);
            }else{
                throw new SecurityException(4030, "Bad credentials or unknown user");
            }
        }catch (Exception e){
            throw new SecurityException(4020, e.getMessage());
        }
    }

    public boolean verifyToken(String token) throws SecurityException {
        try{
            String tokenSecretKey = this.parameterManager.get("token_secret_key");
            DecodedJWT decodedJWT = TokenUtil.verifyToken(tokenSecretKey, token);
            String subject = decodedJWT.getSubject();
            Map<String, String> userDetailMap = new HashMap<>();
            userDetailMap.put("login", subject);

            String getUserApi = this.parameterManager.get("get_user_api");
            Response response = HttpUtil.sendPOST(getUserApi, userDetailMap);
            User user = mapper.readValue(response.body().toString(), new TypeReference<User>() {});
            return user != null;
        }catch (JWTVerificationException e){
            throw new SecurityException(4031, "Invalid token");
        }catch(Exception e){
            throw new SecurityException(4021, e.getMessage());
        }
    }
}
