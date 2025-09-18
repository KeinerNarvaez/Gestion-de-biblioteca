package libraryManagement.test.service;

import libraryManagement.test.dto.PublisherDto;
import libraryManagement.test.model.Publisher;
import libraryManagement.test.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<PublisherDto> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PublisherDto getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
        return convertToDto(publisher);
    }

    public PublisherDto createPublisher(PublisherDto publisherDto) {
        Publisher publisher = convertToEntity(publisherDto);
        Publisher savedPublisher = publisherRepository.save(publisher);
        return convertToDto(savedPublisher);
    }

    public PublisherDto updatePublisher(Long id, PublisherDto publisherDto) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
        publisher.setName(publisherDto.getName());
        publisher.setCountry(publisherDto.getCountry());
        Publisher updatedPublisher = publisherRepository.save(publisher);
        return convertToDto(updatedPublisher);
    }

    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }

    private PublisherDto convertToDto(Publisher publisher) {
        PublisherDto dto = new PublisherDto();
        dto.setPublisherId(publisher.getPublisherId());
        dto.setName(publisher.getName());
        dto.setCountry(publisher.getCountry());
        return dto;
    }

    private Publisher convertToEntity(PublisherDto dto) {
        Publisher publisher = new Publisher();
        publisher.setName(dto.getName());
        publisher.setCountry(dto.getCountry());
        return publisher;
    }
}