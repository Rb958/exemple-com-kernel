package com.afrikpay.security.webservice.config;

import com.afrikpay.security.entity.Parameters;
import com.afrikpay.security.kernel.SecurityKernel;
import com.afrikpay.security.service.ParametersService;
import com.afrikpay.security.webservice.FormatedResponse;
import dbgateway.exception.DBConnectionConfigException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rkernel.KernelFactory;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/api/parameters")
public class ParametersController {
    private ParametersService parametersService;

    public ParametersController() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        try {
            this.parametersService = new ParametersService();
        } catch (DBConnectionConfigException e) {
            KernelFactory.getInstance(SecurityKernel.class).dispatchLogException(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<FormatedResponse<?>> createParameters(@RequestBody Parameters parameters){
        try{
            return ResponseEntity.ok(
                    new FormatedResponse<>(this.parametersService.create(parameters))
            );
        }catch (Exception e){
            return ResponseEntity.ok(FormatedResponse.hydrateWithException(e));
        }
    }

    @PatchMapping("/{kxey}")
    public ResponseEntity<FormatedResponse<?>> updateParameters(@RequestBody Parameters parameters, @PathVariable(name = "key") String key){
        try{
            return ResponseEntity.ok(
                    new FormatedResponse<>(this.parametersService.update(parameters, key))
            );
        }catch (Exception e){
            return ResponseEntity.ok(FormatedResponse.hydrateWithException(e));
        }
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<FormatedResponse<?>> deleteParameters(@PathVariable(name = "key") String key){
        try{
            this.parametersService.delete(key);
            return ResponseEntity.ok(
                    new FormatedResponse<>("Successfully deleted")
            );
        }catch (Exception e){
            return ResponseEntity.ok(FormatedResponse.hydrateWithException(e));
        }
    }

    @GetMapping("/{key}")
    public ResponseEntity<FormatedResponse<?>> getParameters(){
        try{
            return ResponseEntity.ok(
                    new FormatedResponse<>(this.parametersService.getAll())
            );
        }catch (Exception e){
            return ResponseEntity.ok(FormatedResponse.hydrateWithException(e));
        }
    }
}
