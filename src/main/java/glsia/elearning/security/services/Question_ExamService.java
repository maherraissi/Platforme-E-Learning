package glsia.elearning.security.services;

import glsia.elearning.models.Question_Exam;
import glsia.elearning.repository.Question_ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Question_ExamService {

    private final Question_ExamRepository questionExamRepository;

    @Autowired
    public Question_ExamService(Question_ExamRepository questionExamRepository) {
        this.questionExamRepository = questionExamRepository;
    }

    public List<Question_Exam> getAllQuestions() {
        return questionExamRepository.findAll();
    }

    public Question_Exam getQuestionById(int id) {
        Optional<Question_Exam> question = questionExamRepository.findById(id);
        return question.orElse(null);
    }

    public void saveQuestion(Question_Exam questionExam) {
        questionExamRepository.save(questionExam);
    }

    public void deleteQuestion(int id) {
        questionExamRepository.deleteById(id);
    }
}
