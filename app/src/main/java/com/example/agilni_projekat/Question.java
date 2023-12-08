package com.example.agilni_projekat;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Question {
    private int firstNumber, secondNumber, answer;
    private int[] answerArray;
    private int answerPosition;
    private String questionPhrase;
    private int i,upperLimit;

    public Question(String type, String difficulty) {
        Random randomNumberMaker = new Random();
        this.firstNumber = ThreadLocalRandom.current().nextInt(1, 6);
        this.secondNumber = ThreadLocalRandom.current().nextInt(1, 6);
        switch (type) {
            case "add":
                this.answer = this.firstNumber + this.secondNumber;
                this.questionPhrase = firstNumber + " + " + secondNumber + " = ";
                upperLimit=12;
                break;
            case "subtract":
                if (this.firstNumber < this.secondNumber) {
                    int temp = this.firstNumber;
                    this.secondNumber=this.firstNumber;
                    this.firstNumber=temp;
                }
                upperLimit=5;
                this.answer = this.firstNumber - this.secondNumber;
                this.questionPhrase = firstNumber + " - " + secondNumber + " = ";
                break;
            case "multiply":
                upperLimit=36;
                this.answer = this.firstNumber * this.secondNumber;
                this.questionPhrase = firstNumber + " * " + secondNumber + " = ";
                break;
            case "module":
                upperLimit=5;
                this.answer = this.firstNumber % this.secondNumber;
                this.questionPhrase = firstNumber + " % " + secondNumber + " = ";
                break;
        }
        switch (difficulty) {
            case "Easy":
                i = 4;
                break;
            case "Medium":
                i = 6;
                break;
            case "Hard":
                i = 8;
                break;
        }
        this.answerPosition = randomNumberMaker.nextInt(i);//poslati broj kombinacija zavisno od nivoa
        this.answerArray = new int[i];
        for (int j = i - 1; j >= 0; j--) {
            this.answerArray[j] = ThreadLocalRandom.current().nextInt(1, upperLimit);
        }
        answerArray[answerPosition] = answer;
    }

    private int[] shuffleArray(int[] array) {
        int index, temp;
        Random randomNumberGenerator = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            index = randomNumberGenerator.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
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

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }
}
