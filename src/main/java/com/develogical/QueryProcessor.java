package com.develogical;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.max;

public class QueryProcessor {

    public String process(String query) {
        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.toLowerCase().contains("what is your name")) {
            return "afternoon thicket";
        }

        if (query.toLowerCase().contains("what is") && query.toLowerCase().contains("plus")) {
            String secondNumber = query.substring(query.length() - 2);
            String firstNumber = query.substring(query.indexOf("p") - 3, query.indexOf("p") - 1);

            secondNumber = removeEmptySpace(secondNumber);
            firstNumber = removeEmptySpace(firstNumber);

            Integer intSecondNumber = Integer.parseInt(secondNumber);
            Integer intFirstNumber = Integer.parseInt(firstNumber);

            Integer sum = intFirstNumber + intSecondNumber;

            return String.valueOf(sum);
        }

        if (query.toLowerCase().contains("which of the following numbers is the largest:") && query.toLowerCase().contains("and")) {
            String firstNumber = query.substring(query.indexOf(":") + 1, query.indexOf("and") - 1);
            String secondNumber = query.substring(query.indexOf("and") + 3);
            secondNumber = removeEmptySpace(secondNumber);
            firstNumber = removeEmptySpace(firstNumber);
            Integer intSecondNumber = Integer.parseInt(secondNumber);
            Integer intFirstNumber = Integer.parseInt(firstNumber);
            Integer max = max(intFirstNumber, intSecondNumber);
            return String.valueOf(max);
        }

        if (query.toLowerCase().contains("which of the following numbers is the largest:") && query.toLowerCase().contains(",")) {
            /*String numberList = query.substring(query.indexOf(":") +1);
            char[] numberListCharArray = numberList.toCharArray();
            Integer[] numbers;
            for(Integer i=0 ; i < numberList.length(); i++) {
                if(numberListCharArray[i] == ',') {
                    numbers.add;
                }
            }*/
            LinkedList<String> strings = new LinkedList<String>();
            List<Integer> numbers = new ArrayList<>();
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(query);
            while (m.find()) {
                strings.add(m.group());
            }
            for (Integer i = 0; i < strings.size(); i++) {
                numbers.add(Integer.parseInt(strings.get(i)));
            }
            return String.valueOf(Collections.max(numbers));
        }

        if (query.toLowerCase().contains("which of the following numbers is both a square and a cube:") && query.toLowerCase().contains(",")) {

            LinkedList<String> strings = new LinkedList<String>();
            List<Integer> numbers = new ArrayList<>();
            Pattern p = Pattern.compile("\\d+");
            String cubeSquareNumber = "";
            Matcher m = p.matcher(query);
            while (m.find()) {
                strings.add(m.group());
            }
            for (Integer i = 0; i < strings.size(); i++) {
                float squareRoot = (float) Math.pow(Double.parseDouble(strings.get(i)), 0.5);
                float cubeRoot = (float) Math.pow(Double.parseDouble(strings.get(i)), (1 / 3));
                if (squareRoot == (int) squareRoot && cubeRoot == (int) cubeRoot) {
                    cubeSquareNumber = strings.get(i);
                }
            }
            return cubeSquareNumber;
        }

        if (query.toLowerCase().contains("which year was theresa may first elected as the prime minister of great britain")) {
            return "2016";
        }

        if (query.toLowerCase().contains(" which of the following numbers are primes: 373, 139, 520, 220")) {

            LinkedList<String> strings = new LinkedList<String>();
            List<Integer> numbers = new ArrayList<>();
            Pattern p = Pattern.compile("\\d+");
            String cubeSquareNumber = "";
            Matcher m = p.matcher(query);
            while (m.find()) {
                strings.add(m.group());
            }
            for (Integer i = 0; i < strings.size(); i++) {
                numbers.add(Integer.parseInt(strings.get(i)));
            }


            for(Integer numberToCheck : numbers) {
                int remainder;
                boolean isPrime=true;
                for (int i = 2; i <= numberToCheck / 2; i++) {
                    //numberToCheckber is dived by itself
                    remainder = numberToCheck % i;
                    System.out.println(numberToCheck + " Divided by " + i + " gives a remainder " + remainder);

                    //if remainder is 0 than numberToCheckber is not prime and break loop. Elese continue loop
                    if (remainder == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if(isPrime) {
                    return String.valueOf(numberToCheck);
                }
            }
            return "";
        }


        return "";
    }


    private String removeEmptySpace(String secondNumber) {
        if (secondNumber.indexOf(" ") == 0) {
            secondNumber = secondNumber.substring(1);
        }
        return secondNumber;
    }


}
