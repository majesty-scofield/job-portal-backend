package com.backend.job_portal.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sequence")
public class Sequence {
    private String id;
    private Long seq;
}
