package libraryManagement.test.service;

import libraryManagement.test.dto.AuthorDto;
import libraryManagement.test.model.Author;
import libraryManagement.test.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        return convertToDto(author);
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        Author savedAuthor = authorRepository.save(author);
        return convertToDto(savedAuthor);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        author.setName(authorDto.getName());
        author.setNationality(authorDto.getNationality());
        Author updatedAuthor = authorRepository.save(author);
        return convertToDto(updatedAuthor);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    private AuthorDto convertToDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setAuthorId(author.getAuthorId());
        dto.setName(author.getName());
        dto.setNationality(author.getNationality());
        return dto;
    }

    private Author convertToEntity(AuthorDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setNationality(dto.getNationality());
        return author;
    }
}