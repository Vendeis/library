package com.example.library.service;

import com.example.library.model.Publisher;
import com.example.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceBean implements PublisherService{

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceBean(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }

    public Publisher createPublisher(Publisher publisher) {
        if (publisherRepository.findByNameAndLanguage(publisher.getName(), publisher.getLanguage()).isPresent())
            return null;
        return publisherRepository.save(publisher);
    }

    public Publisher getPublisherById(long id){
        if(findById(id) != null)
            return findById(id);
        return null;
    }

    public Publisher getPublisherByNameAndLanguage(String name, String language){
        if(findByNameAndLanguage(name,language) != null)
            return findByNameAndLanguage(name,language);
        return null;
    }

    public List<Publisher> getAllPublishers(){
        return publisherRepository.findAll();
    }

    public Publisher updatePublisher(Long id, Publisher publisher) {
        Publisher publisherToUpdate = findById(id);
        publisherToUpdate.setName(publisher.getName());
        publisherToUpdate.setCity(publisher.getCity());
        publisherToUpdate.setLanguage(publisher.getLanguage());
        publisherToUpdate.setPublications(publisher.getPublications());

        return publisherRepository.save(publisherToUpdate);
    }

    public void deletePublisher(Long id) {
        Publisher publisherToDelete = findById(id);

        if(publisherToDelete != null)
        publisherRepository.deleteById(id);
    }

    private Publisher findById(Long id) {
        Optional<Publisher> publisherById = publisherRepository.findById(id);
        if (publisherById.isEmpty()) {
            return null;
        }
        return publisherById.get();
    }
    private Publisher findByNameAndLanguage(String name, String language) {
        Optional<Publisher> publisherByNameAndLanguage = publisherRepository.findByNameAndLanguage(name,language);
        if (publisherByNameAndLanguage.isEmpty()) {
            return null;
        }
        return publisherByNameAndLanguage.get();
    }
}
