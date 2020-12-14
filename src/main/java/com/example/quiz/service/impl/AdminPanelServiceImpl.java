package com.example.quiz.service.impl;

import com.example.quiz.model.Answer;
import com.example.quiz.model.Question;
import com.example.quiz.model.Subject;
import com.example.quiz.model.ThemeSubject;
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
    public void addAnswersForTHemeSubject(Question question) {
        questionRepository.save(question);
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
                Question question = new Question();
                Answer answer = new Answer();

                int cellIdx = 0;
                while (cellIterator.hasNext()){
                    Cell currentCell = cellIterator.next();


                    switch (cellIdx){
                        case 0:
                            question.setNameQuestion(currentCell.getStringCellValue());
                            break;
                        case 1:
                            answer.setCorrectAnswer(currentCell.getStringCellValue());
                            break;
                        case 2:
                            answer.setInCorrectAnswer1(currentCell.getStringCellValue());
                            break;
                        case 3:
                            answer.setInCorrectAnswer2(currentCell.getStringCellValue());
                            break;
                        case 4:
                            answer.setInCorrectAnswer3(currentCell.getStringCellValue());
                            break;
                        case 5:
                            answer.setDescribeAnswerIfChoiceWrong(currentCell.getStringCellValue());
                        case 6:
                            question.setThemeSubject(themeSubjectsRepository.findByName(currentCell.getStringCellValue()));
                            break;
                    }
                    cellIdx++;
                }
                question.setAnswer(answer);
                questionRepository.save(question);
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
