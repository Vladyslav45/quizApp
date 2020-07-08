package com.example.quiz.service.impl;

import com.example.quiz.model.Answers;
import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
import com.example.quiz.repository.AnswersRepository;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.SubjectRepository;
import com.example.quiz.repository.ThemeSubjectsRepository;
import com.example.quiz.service.IAdminPanelService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;


@Service
public class AdminPanelServiceImpl implements IAdminPanelService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ThemeSubjectsRepository themeSubjectsRepository;

    @Autowired
    private AnswersRepository answersRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void addThemeSubject(ThemeSubject themeSubject) {
        themeSubjectsRepository.save(themeSubject);
    }

    @Override
    public void addAnswersForTHemeSubject(Answers answers) {
        answersRepository.save(answers);
    }

    @Override
    public void addListAnswers(MultipartFile multipartFile) {

        try {
            Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            int row = 0;
            while(rowIterator.hasNext()){
                Row currentRow = rowIterator.next();

                if (row == 0){
                    row++;
                    continue;
                }

                Iterator<Cell> cellIterator = currentRow.iterator();
                Answers answers = new Answers();
                Question question = new Question();

                int cellIdx = 0;
                while (cellIterator.hasNext()){
                    Cell currentCell = cellIterator.next();


                    switch (cellIdx){
                        case 0:
                            question.setNameQuestion(currentCell.getStringCellValue());
                            break;
                        case 1:
                            answers.setCorrectAnswer(currentCell.getStringCellValue());
                            break;
                        case 2:
                            answers.setAnswers1(currentCell.getStringCellValue());
                            break;
                        case 3:
                            answers.setAnswer2(currentCell.getStringCellValue());
                            break;
                        case 4:
                            answers.setAnswer3(currentCell.getStringCellValue());
                            break;
                        case 5:
                            question.setThemeSubject(themeSubjectsRepository.findByName(currentCell.getStringCellValue()));
                            break;
                    }
                    cellIdx++;
                    answers.setQuestion(question);
                }
                questionRepository.save(question);
                answersRepository.save(answers);
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void addListSubjects(MultipartFile file) {
//    }
}
