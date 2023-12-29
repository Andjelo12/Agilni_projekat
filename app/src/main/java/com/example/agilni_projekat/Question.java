package com.example.agilni_projekat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Question {
    private int firstNumber, secondNumber, answer;
    private int[] answerArray;
    private int answerPosition, i, upperLimit;
    private String questionPhrase;

    public Question(String type, String difficulty) {
        Random randomNumberMaker = new Random();
        this.firstNumber = ThreadLocalRandom.current().nextInt(1, 6);
        this.secondNumber = ThreadLocalRandom.current().nextInt(1, 6);
        switch (type) {
            case "add":
                this.answer = this.firstNumber + this.secondNumber;
                this.questionPhrase =  " + " ;
                upperLimit = 12;
                break;
            case "subtract":
                if (this.firstNumber < this.secondNumber) {
                    int temp = this.firstNumber;
                    this.firstNumber = this.secondNumber;
                    this.secondNumber = temp;
                }
                this.answer = this.firstNumber - this.secondNumber;
                this.questionPhrase = " - ";
                upperLimit = 12;
                break;
            case "multiply":
                this.answer = this.firstNumber * this.secondNumber;
                this.questionPhrase = " * ";
                upperLimit = 36;
                break;
            case "module":
                this.answer = this.firstNumber % this.secondNumber;
                this.questionPhrase = " % ";
                upperLimit = 12;
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
        this.answerArray = generateRandomNumbersArray(i, 1, upperLimit);
//        for (int j = 0; j < i-1; j++) {
//            this.answerArray[j] = ThreadLocalRandom.current().nextInt(1, upperLimit);
//        }
        int flag=0;
        for (int i=0;i<answerArray.length;i++){
            if (answerArray[i]==answer) {
                flag = 1;
                break;
            }
        }
        if (flag==0)
            answerArray[answerPosition] = answer;
    }
    private static int[] generateRandomNumbersArray(int n, int i, int j) {
        if (n > (j - i + 1)) {
            throw new IllegalArgumentException("Cannot generate " + n + " unique random numbers in the specified range");
        }

        Random random = new Random();
        Set<Integer> uniqueRandomNumbers = new HashSet<>();

        while (uniqueRandomNumbers.size() < n) {
            int randomNumber = random.nextInt(j - i + 1) + i;
            uniqueRandomNumbers.add(randomNumber);
        }

        int[] randomNumbersArray = new int[n];
        int index = 0;
        for (int randomNumber : uniqueRandomNumbers) {
            randomNumbersArray[index++] = randomNumber;
        }

        return randomNumbersArray;
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
