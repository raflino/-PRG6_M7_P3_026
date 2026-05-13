package com.presensi.presensi_api.controller;

import com.presensi.presensi_api.entity.Mahasiswa;
import com.presensi.presensi_api.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/mahasiswa")
@CrossOrigin(origins = "*")
public class MahasiswaController {
    
    @Autowired
    private MahasiswaRepository mahasiswaRepository;
    
    @GetMapping("/{nim}")
    public ResponseEntity<?> getMahasiswa(@PathVariable String nim) {
        Optional<Mahasiswa> mahasiswa = mahasiswaRepository.findById(nim);
        if (mahasiswa.isPresent()) {
            return ResponseEntity.ok(mahasiswa.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Mahasiswa dengan NIM " + nim + " tidak ditemukan");
        }
    }
    
    @PostMapping("/upload")
    public ResponseEntity<?> uploadProfile(
            @RequestParam("nim") String nim,
            @RequestParam("nama") String nama,
            @RequestParam("foto") MultipartFile file) {
        
        try {
            Mahasiswa mhs = mahasiswaRepository.findById(nim)
                .orElse(new Mahasiswa());
            mhs.setNimMhs(nim);
            mhs.setNamaMhs(nama);
            mhs.setFotoMhs(file.getBytes());
            
            mahasiswaRepository.save(mhs);
            
            return ResponseEntity.ok("Foto profil berhasil diupload");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Gagal membaca file foto");
        }
    }
}