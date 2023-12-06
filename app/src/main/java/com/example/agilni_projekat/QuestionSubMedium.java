package com.example.agilni_projekat;

import java.util.Random;

public class QuestionSubMedium {
    private int  firstNumber, secondNumber, answer;
    private int [] answerArray;
    private int answerPosition;
    private int upperLimit;
    private String questionPhrase;

    public QuestionSubMedium(int upperLimit){
        this.upperLimit=upperLimit;
        Random randomNumberMaker=new Random();

        this.firstNumber=randomNumberMaker.nextInt(upperLimit);
        this.secondNumber=randomNumberMaker.nextInt(upperLimit);
        if (this.firstNumber<this.secondNumber){
            int var=this.secondNumber;
            this.secondNumber=this.firstNumber;
            this.firstNumber=var;
        }
        while (this.firstNumber>6 || this.firstNumber==0){
            this.firstNumber=randomNumberMaker.nextInt(upperLimit);
        }
        while(this.secondNumber>6 || this.secondNumber==0){
            this.secondNumber=randomNumberMaker.nextInt(upperLimit);
        }
        this.answer=this.firstNumber-this.secondNumber;
        this.questionPhrase=firstNumber+" - "+secondNumber+" = ";

        this.answerPosition=randomNumberMaker.nextInt(6);
        this.answerArray=new int[] {0,1,2,3,4,5};

        this.answerArray[0]= answer+1;
        while(this.answerArray[0]>12 || this.answerArray[0]==0){
            this.answerArray[0]--;
        }
        this.answerArray[1]= answer+2;
        while(this.answerArray[1]>12 || this.answerArray[1]==0){
            this.answerArray[1]--;
        }
        this.answerArray[2]= answer-1;
        while(this.answerArray[2]>12 || this.answerArray[2]==0){
            this.answerArray[2]--;
        }
        this.answerArray[3]= answer-2;
        while(this.answerArray[3]>12 || this.answerArray[3]==0){
            this.answerArray[3]--;
        }
        this.answerArray[4]= answer-2;
        while(this.answerArray[4]>12 || this.answerArray[4]==0){
            this.answerArray[4]--;
        }
        this.answerArray[5]= answer-2;
        while(this.answerArray[5]>12 || this.answerArray[5]==0){
            this.answerArray[5]--;
        }

        this.answerArray=shuffleArray(this.answerArray);
        answerArray[answerPosition]=answer;
    }

    private int[] shuffleArray(int[] array){
        int index, temp;
        Random randomNumberGenerator=new Random();

        for (int i=array.length-1;i>0;i--){
            index=randomNumberGenerator.nextInt(i+1);
            temp=array[index];
            array[index]=array[i];
            array[i]=temp;
        }
        return array;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }
}
