package com.example.agilni_projekat;

import java.util.ArrayList;
import java.util.List;

public class GameAddMedium {


    private List<QuestionAddMedium> questions;
    private int numberCorrect, numberIncorrect,totalQuestions,score;
    private QuestionAddMedium currentQuestion;

    public GameAddMedium(){
        numberCorrect=0;
        numberIncorrect=0;
        totalQuestions=0;
        score=0;
        currentQuestion=new QuestionAddMedium(10);
        questions=new ArrayList<QuestionAddMedium>();
    }
    public void makeNewQuestion(){
        currentQuestion=new QuestionAddMedium(totalQuestions*2+5);
        totalQuestions++;
        questions.add(currentQuestion);
    }
    public boolean checkAnswer(int submittedAnswer){
        boolean isCorrect;
        if(currentQuestion.getAnswer()==submittedAnswer){
            numberCorrect++;
            isCorrect=true;
        }else {
            numberIncorrect++;
            isCorrect=false;
        }
        score=numberCorrect*10-numberIncorrect*30;
        return isCorrect;
    }
    public List<QuestionAddMedium> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionAddMedium> questions) {
        this.questions = questions;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public QuestionAddMedium getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(QuestionAddMedium currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}
