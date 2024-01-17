package com.example.agilni_projekat;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private List<Question> questionAdds;
    private int numberCorrect, numberIncorrect,totalQuestions, level_points,points=0;
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
        switch (difficulty){
            case "Easy":
                level_points=2;
                break;
            case "Medium":
                level_points=3;
                break;
            case "Hard":
                level_points=4;
                break;
        }
        questionAdds.add(currentQuestionAdd);
    }
    public boolean checkAnswer(int submittedAnswer){
        boolean isCorrect;
        if(currentQuestionAdd.getAnswer()==submittedAnswer){
            numberCorrect++;
            points+=level_points;
            isCorrect=true;
        }else {
            numberIncorrect++;
            if (points>0)
                points-=level_points;
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

    public int getPoints() {
        return points;
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
