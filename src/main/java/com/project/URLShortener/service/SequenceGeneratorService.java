package com.project.URLShortener.service;

import com.project.URLShortener.entity.Sequence;
import com.project.URLShortener.repository.SequenceGeneratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    @Autowired
    private SequenceGeneratorRepository sequenceGeneratorRepository;
    public long getNextSequence() {
        Sequence currentSequence = sequenceGeneratorRepository.fetchCurrentSequence();
        sequenceGeneratorRepository.update(currentSequence);
        return currentSequence.getSequenceId();
    }

    public void initiateSequence() {
        Sequence sequence = new Sequence(Sequence.OFFSET);
        sequenceGeneratorRepository.save(sequence);
    }
}
