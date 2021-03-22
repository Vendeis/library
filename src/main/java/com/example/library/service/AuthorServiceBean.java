package com.example.library.service;

import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceBean {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceBean(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(Author author) {
        if (authorRepository.findByName(author.getName()).isPresent())
            return null;
        return authorRepository.save(author);
    }

    public Author getAuthorById(long id){
        if(findById(id) != null)
            return findById(id);
        return null;
    }

    public Author getAuthorByName(String name){
        if(findByName(name) != null)
            return findByName(name);
        return null;
    }

    public List<Author> getAllPublishers(){
        return authorRepository.findAll();
    }

    public Author updatePublisher(Long id, Author author) {
        Author authorToUpdate = findById(id);
        authorToUpdate.setName(author.getName());
        authorToUpdate.setSurname(author.getSurname());
        authorToUpdate.setNationality(author.getNationality());

        return authorRepository.save(authorToUpdate);
    }

    public void deletePublisher(Long id) {
        Author authorToDelete = findById(id);

        if(authorToDelete != null)
            authorRepository.deleteById(id);
    }

    private Author findById(Long id) {
        Optional<Author> authorById = authorRepository.findById(id);
        if (authorById.isEmpty()) {
            return null;
        }
        return authorById.get();
    }
    private Author findByName(String name) {
        Optional<Author> authorByName = authorRepository.findByName(name);
        if (authorByName.isEmpty()) {
            return null;
        }
        return authorByName.get();
    }
}
