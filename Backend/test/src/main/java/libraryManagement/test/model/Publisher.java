package libraryManagement.test.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "publishers")
@Data
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;

    private String name;
    private String country;
}