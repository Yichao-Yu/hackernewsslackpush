package com.yichao.hackernews.service;

import com.yichao.hackernews.persistence.FirebaseClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    private final static Logger logger = LoggerFactory.getLogger(FirebaseService.class);

    @Autowired
    private FirebaseClient client;
}
