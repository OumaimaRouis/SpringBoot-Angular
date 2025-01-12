package com.gestion3.service;

import com.gestion3.entities.Publisher;
import com.gestion3.repository.PublisherRepository;

import java.util.List;

public class ServicePublisher implements IServicePublisher{
    private PublisherRepository publisherRepository;
    @Override
    public void addPublisher(Publisher p) {
        publisherRepository.save(p);
    }

    @Override
    public List<Publisher> getAllPublishe() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisher(Long id) {
        return publisherRepository.findById(id).get();
    }

    @Override
    public void deletePublisher(Long id) {
publisherRepository.deleteById(id);
    }

    @Override
    public void updatePublisher(Publisher p) {
publisherRepository.save(p);
    }
}
