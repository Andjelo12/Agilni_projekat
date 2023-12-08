package com.example.agilni_projekat;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private List<Question> questionAdds;
    private int numberCorrect, numberIncorrect,totalQuestions;
    private Question currentQuestionAdd;

    public Game(String type, String difficulty){
        numberCorrect=0;
        numberIncorrect=0;
        totalQuestions=0;
        currentQuestionAdd = new Question(type, difficulty);
        questionAdds =new ArrayList<Question>();
    }
    public void makeNewQuestion(String type, String difficulty){
        currentQuestionAdd = new Question(type, difficulty);
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
        return isCorrect;
    }
    public List<Question> getQuestions() {
        return questionAdds;
    }

    public void setQuestions(List<Question> questionAdds) {
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

    public Question getCurrentQuestion() {
        return currentQuestionAdd;
    }

    public void setCurrentQuestion(Question currentQuestionAdd) {
        this.currentQuestionAdd = currentQuestionAdd;
    }
}
