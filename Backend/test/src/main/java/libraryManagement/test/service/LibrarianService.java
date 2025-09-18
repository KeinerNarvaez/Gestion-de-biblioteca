package libraryManagement.test.service;

import libraryManagement.test.dto.LibrarianDto;
import libraryManagement.test.model.Librarian;
import libraryManagement.test.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;

    public List<LibrarianDto> getAllLibrarians() {
        return librarianRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LibrarianDto getLibrarianById(Long id) {
        Librarian librarian = librarianRepository.findById(id).orElseThrow(() -> new RuntimeException("Librarian not found"));
        return convertToDto(librarian);
    }

    public LibrarianDto createLibrarian(LibrarianDto librarianDto) {
        Librarian librarian = convertToEntity(librarianDto);
        Librarian savedLibrarian = librarianRepository.save(librarian);
        return convertToDto(savedLibrarian);
    }

    public LibrarianDto updateLibrarian(Long id, LibrarianDto librarianDto) {
        Librarian librarian = librarianRepository.findById(id).orElseThrow(() -> new RuntimeException("Librarian not found"));
        librarian.setName(librarianDto.getName());
        librarian.setEmail(librarianDto.getEmail());
        librarian.setPhone(librarianDto.getPhone());
        librarian.setHireDate(librarianDto.getHireDate());
        Librarian updatedLibrarian = librarianRepository.save(librarian);
        return convertToDto(updatedLibrarian);
    }

    public void deleteLibrarian(Long id) {
        librarianRepository.deleteById(id);
    }

    private LibrarianDto convertToDto(Librarian librarian) {
        LibrarianDto dto = new LibrarianDto();
        dto.setLibrarianId(librarian.getLibrarianId());
        dto.setName(librarian.getName());
        dto.setEmail(librarian.getEmail());
        dto.setPhone(librarian.getPhone());
        dto.setHireDate(librarian.getHireDate());
        return dto;
    }

    private Librarian convertToEntity(LibrarianDto dto) {
        Librarian librarian = new Librarian();
        librarian.setName(dto.getName());
        librarian.setEmail(dto.getEmail());
        librarian.setPhone(dto.getPhone());
        librarian.setHireDate(dto.getHireDate());
        return librarian;
    }
}