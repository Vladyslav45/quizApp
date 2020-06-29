package com.example.quiz.service.impl;

import com.example.quiz.model.Subject;
import com.example.quiz.repository.SubjectRepository;
import com.example.quiz.service.IAdminPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AdminPanelServiceImpl implements IAdminPanelService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

//    @Override
//    public void addListSubjects(MultipartFile file) {
//    }
}
