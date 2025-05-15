package com.Book_Mart.OOP.Controller;

import com.Book_Mart.OOP.DTO.AdminDTO;
import com.Book_Mart.OOP.Model.Admin;
import com.Book_Mart.OOP.Services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v3")

public class AdminController {
    @Autowired
    private AdminServices adminServices;


    @PostMapping("/create")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody Admin admin) {
        AdminDTO adminDTO = adminServices.saveAdmin(admin);
        return new ResponseEntity<>(adminDTO, HttpStatus.CREATED);
    }


    @GetMapping("/displayById/{id}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable Long id) {
        AdminDTO adminDTO = adminServices.getAdminById(id);
        return ResponseEntity.ok(adminDTO);
    }


    @GetMapping("/display")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminServices.getAllAdmins();
        return ResponseEntity.ok(admins);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        admin.setId(id);
        AdminDTO adminDTO = adminServices.saveAdmin(admin);
        return ResponseEntity.ok(adminDTO);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminServices.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}