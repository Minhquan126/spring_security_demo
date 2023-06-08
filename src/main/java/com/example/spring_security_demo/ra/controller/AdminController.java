package com.example.spring_security_demo.ra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v4/test")
public class AdminController {
    @GetMapping("admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("admin");
    }
    @GetMapping("pm")
    @PreAuthorize("hasAnyAuthority('ADMIN','PM')")
    public ResponseEntity<String> pm(){
        return ResponseEntity.ok("pm");
    }
    @GetMapping("user")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','PM')")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok("user");
    }
}
