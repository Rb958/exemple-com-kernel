package com.afrikpay.security.webservice.config;

import com.afrikpay.security.entity.AppConfig;
import com.afrikpay.security.service.config.AppConfigService;
import com.afrikpay.security.webservice.FormatedResponse;
import dbgateway.exception.DBConnectionConfigException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security")
public class AppConfigController {

    private final AppConfigService appConfigService;

    public AppConfigController() throws DBConnectionConfigException {
        this.appConfigService = new AppConfigService();
    }

    @PostMapping("/appconfig")
    public ResponseEntity<FormatedResponse<?>> createAppConfig(@RequestBody AppConfig appConfig){
        try{
            return ResponseEntity.ok(new FormatedResponse<>(this.appConfigService.create(appConfig)));
        }catch (Exception e){
            return ResponseEntity.ok()
                    .body(FormatedResponse.hydrateWithException(e));
        }
    }

    @GetMapping("/appconfig/{appid}")
    public ResponseEntity<FormatedResponse<?>> getAppConfig(@PathVariable(name = "appid") String appid){
        try{
            return ResponseEntity.ok(new FormatedResponse<>(this.appConfigService.getAppConfig(appid)));
        }catch (Exception e){
            return ResponseEntity.ok()
                    .body(FormatedResponse.hydrateWithException(e));
        }
    }

    @GetMapping("/appconfig")
    public ResponseEntity<FormatedResponse<?>> getAll(){
        try{
            return ResponseEntity.ok(new FormatedResponse<>(this.appConfigService.getAll()));
        }catch (Exception e){
            return ResponseEntity.ok()
                    .body(FormatedResponse.hydrateWithException(e));
        }
    }

    @PatchMapping("/appconfig/{appid}")
    public ResponseEntity<FormatedResponse<?>> updateAppConfig(@RequestBody AppConfig appConfig, @PathVariable(name = "appid") String appid){
        try{
            return ResponseEntity.ok(new FormatedResponse<>(this.appConfigService.update(appConfig, appid)));
        }catch (Exception e){
            return ResponseEntity.ok()
                    .body(FormatedResponse.hydrateWithException(e));
        }
    }

    @DeleteMapping("/appconfig/{appid}")
    public ResponseEntity<FormatedResponse<?>> deleteAppConfig(@PathVariable(name = "appid") String appid){
        try{
            this.appConfigService.delete(appid);
            return ResponseEntity.ok(new FormatedResponse<>("Successfully deleted"));
        }catch (Exception e){
            return ResponseEntity.ok()
                    .body(FormatedResponse.hydrateWithException(e));
        }
    }
}
