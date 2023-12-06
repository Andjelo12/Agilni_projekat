package com.example.agilni_projekat;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private List<QuestionAdd> questionAdds;
    private int numberCorrect, numberIncorrect,totalQuestions,score;
    private QuestionAdd currentQuestionAdd;

    public Game(){
        numberCorrect=0;
        numberIncorrect=0;
        totalQuestions=0;
        score=0;
        currentQuestionAdd =new QuestionAdd(10);
        questionAdds =new ArrayList<QuestionAdd>();
    }
    public void makeNewQuestion(){
        currentQuestionAdd =new QuestionAdd(totalQuestions*2+5);
        totalQuestions++;
        questionAdds.add(currentQuestionAdd);
    }
    public boolean checkAnswer(int submittedAnswer){
        boolean isCorrect;
        if(currentQuestionAdd.getAnswer()==submittedAnswer){
            numberCorrect++;
            isCorrect=true;
        }else {
            numberIncorrect++;
            isCorrect=false;
        }
        score=numberCorrect*10-numberIncorrect*30;
        return isCorrect;
    }
    public List<QuestionAdd> getQuestions() {
        return questionAdds;
    }

    public void setQuestions(List<QuestionAdd> questionAdds) {
        this.questionAdds = questionAdds;
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

    public QuestionAdd getCurrentQuestion() {
        return currentQuestionAdd;
    }

    public void setCurrentQuestion(QuestionAdd currentQuestionAdd) {
        this.currentQuestionAdd = currentQuestionAdd;
    }
}
