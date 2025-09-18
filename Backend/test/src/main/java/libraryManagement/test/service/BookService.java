package libraryManagement.test.service;

import libraryManagement.test.dto.BookDto;
import libraryManagement.test.model.Author;
import libraryManagement.test.model.Book;
import libraryManagement.test.model.Category;
import libraryManagement.test.model.Publisher;
import libraryManagement.test.repository.AuthorRepository;
import libraryManagement.test.repository.BookRepository;
import libraryManagement.test.repository.CategoryRepository;
import libraryManagement.test.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return convertToDto(book);
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found")));
        book.setPublisher(publisherRepository.findById(bookDto.getPublisherId()).orElseThrow(() -> new RuntimeException("Publisher not found")));
        book.setCategory(categoryRepository.findById(bookDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found")));
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setTotalQuantity(bookDto.getTotalQuantity());
        book.setAvailableQuantity(bookDto.getAvailableQuantity());
        Book updatedBook = bookRepository.save(book);
        return convertToDto(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDto> getAvailableBooks() {
        return bookRepository.findAvailableBooks().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BookDto convertToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getAuthorId());
        dto.setPublisherId(book.getPublisher().getPublisherId());
        dto.setCategoryId(book.getCategory().getCategoryId());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setTotalQuantity(book.getTotalQuantity());
        dto.setAvailableQuantity(book.getAvailableQuantity());
        return dto;
    }

    private Book convertToEntity(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(authorRepository.findById(dto.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found")));
        book.setPublisher(publisherRepository.findById(dto.getPublisherId()).orElseThrow(() -> new RuntimeException("Publisher not found")));
        book.setCategory(categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found")));
        book.setPublicationYear(dto.getPublicationYear());
        book.setTotalQuantity(dto.getTotalQuantity());
        book.setAvailableQuantity(dto.getAvailableQuantity());
        return book;
    }
}