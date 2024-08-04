package com.menk.social_media_analytics.service;

import jakarta.annotation.PostConstruct;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class SentimentAnalysisService {

    private DoccatModel model;

    private SimpleTokenizer tokenizer;

    @PostConstruct
    public void initialize (){

        try (InputStream modelStream = getClass().getResourceAsStream("/models/en-doccat.bin")) {
            model = new DoccatModel(modelStream);
            tokenizer = SimpleTokenizer.INSTANCE;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String analyzeSertiment(String text){

        DocumentCategorizer categorizer = new DocumentCategorizerME(model);
        String[] tokens = tokenizer.tokenize(text);
        double[] outcomes = categorizer.categorize(tokens);
        return categorizer.getBestCategory(outcomes);
    }
}
