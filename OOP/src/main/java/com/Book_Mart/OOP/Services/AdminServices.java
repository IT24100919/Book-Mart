package com.Book_Mart.OOP.Services;

import com.Book_Mart.OOP.DTO.AdminDTO;
import com.Book_Mart.OOP.Model.Admin;
import com.Book_Mart.OOP.Repo.AdminRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AdminServices {

    @Autowired
    private AdminRepo adminRepo;


    public AdminDTO saveAdmin(Admin admin) {
        Admin savedAdmin = adminRepo.save(admin);
        return convertToDTO(savedAdmin);
    }


    public AdminDTO getAdminById(Long id) {
        Admin admin = adminRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return convertToDTO(admin);
    }


    public List<AdminDTO> getAllAdmins() {
        return adminRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public void deleteAdmin(Long id) {
        if (!adminRepo.existsById(id)) {
            throw new RuntimeException("Admin not found");
        }
        adminRepo.deleteById(id);
    }


    private AdminDTO convertToDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setUsername(admin.getUsername());
        dto.setEmail(admin.getEmail());
        dto.setRole(admin.getRole());
        dto.setActive(admin.isActive());
        return dto;
    }

}