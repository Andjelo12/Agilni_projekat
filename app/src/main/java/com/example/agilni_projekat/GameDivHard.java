package com.example.agilni_projekat;

import java.util.ArrayList;
import java.util.List;

public class GameDivHard {


    private List<QuestionDivHard> questions;
    private int numberCorrect, numberIncorrect,totalQuestions,score;
    private QuestionDivHard currentQuestion;

    public GameDivHard(){
        numberCorrect=0;
        numberIncorrect=0;
        totalQuestions=0;
        score=0;
        currentQuestion=new QuestionDivHard(10);
        questions=new ArrayList<QuestionDivHard>();
    }
    public void makeNewQuestion(){
        currentQuestion=new QuestionDivHard(totalQuestions*2+5);
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
    public List<QuestionDivHard> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDivHard> questions) {
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

    public QuestionDivHard getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(QuestionDivHard currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}
