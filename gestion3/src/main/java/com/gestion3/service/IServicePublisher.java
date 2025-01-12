package com.gestion3.service;

import com.gestion3.entities.Publisher;

import java.util.List;

public interface IServicePublisher {
    public void addPublisher(Publisher p);
    public List<Publisher> getAllPublishe();
    public Publisher getPublisher(Long id);
    public void deletePublisher(Long id);
    public void updatePublisher(Publisher p);
}
