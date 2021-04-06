package com.example.library.publisher;

import com.example.library.publisher.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher createPublisher(Publisher publisher);
    Publisher getPublisherById(long id);
    Publisher getPublisherByNameAndLanguage(String name, String language);
    List<Publisher> getAllPublishers();
    Publisher updatePublisher(Long id, Publisher publisher);
    void deletePublisher(Long id);

}
