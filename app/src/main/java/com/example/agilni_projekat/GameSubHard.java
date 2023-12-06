package com.example.agilni_projekat;

import java.util.ArrayList;
import java.util.List;

public class GameSubHard {


    private List<QuestionSubHard> questions;
    private int numberCorrect, numberIncorrect,totalQuestions,score;
    private QuestionSubHard currentQuestion;

    public GameSubHard(){
        numberCorrect=0;
        numberIncorrect=0;
        totalQuestions=0;
        score=0;
        currentQuestion=new QuestionSubHard(10);
        questions=new ArrayList<QuestionSubHard>();
    }
    public void makeNewQuestion(){
        currentQuestion=new QuestionSubHard(totalQuestions*2+5);
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
    public List<QuestionSubHard> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionSubHard> questions) {
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

    public QuestionSubHard getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(QuestionSubHard currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}
