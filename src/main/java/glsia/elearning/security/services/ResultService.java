package glsia.elearning.security.services;

import glsia.elearning.models.Result;
import glsia.elearning.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Optional<Result> getResultById(int id) {
        return resultRepository.findById(id);
    }

    public List<Result> getResultsByExamenId(int examenId) {
        return resultRepository.findByExamenId(examenId);
    }

    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    public void deleteResult(int id) {
        resultRepository.deleteById(id);
    }


}