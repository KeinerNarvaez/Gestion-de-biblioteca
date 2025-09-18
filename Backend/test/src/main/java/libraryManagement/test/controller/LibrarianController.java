package libraryManagement.test.controller;

import libraryManagement.test.dto.LibrarianDto;
import libraryManagement.test.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @GetMapping
    public List<LibrarianDto> getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrarianDto> getLibrarianById(@PathVariable Long id) {
        LibrarianDto librarian = librarianService.getLibrarianById(id);
        return ResponseEntity.ok(librarian);
    }

    @PostMapping
    public ResponseEntity<LibrarianDto> createLibrarian(@Valid @RequestBody LibrarianDto librarianDto) {
        LibrarianDto createdLibrarian = librarianService.createLibrarian(librarianDto);
        return ResponseEntity.ok(createdLibrarian);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibrarianDto> updateLibrarian(@PathVariable Long id, @Valid @RequestBody LibrarianDto librarianDto) {
        LibrarianDto updatedLibrarian = librarianService.updateLibrarian(id, librarianDto);
        return ResponseEntity.ok(updatedLibrarian);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrarian(@PathVariable Long id) {
        librarianService.deleteLibrarian(id);
        return ResponseEntity.noContent().build();
    }
}