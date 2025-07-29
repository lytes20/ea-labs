package bank.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TraceRecord {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime timestamp;

    private String message;

    public TraceRecord() {}

    public TraceRecord(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }



    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

