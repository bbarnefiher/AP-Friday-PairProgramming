package com.company;
import java.util.*;
import java.io.*;

public class Main {
    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) throws IOException, NullPointerException {
        Scanner sf = new Scanner(new File("scores2.txt"));
        List<Integer> scores = new ArrayList<>();
        List<String> Schools = new ArrayList<>();
        double average;
        int math;
        int reading;
        int writing;
        int averages;

        int skippedSchools = 0;

        sf.nextLine();
        while (sf.hasNextLine()) {
            String SATScore = sf.nextLine();

            String[] temp = SATScore.split("\\t");




            if (temp.length > 19) {

                if (tryParseInt(temp[18]) && tryParseInt(temp[19]) && tryParseInt(temp[20])){
                    math = Integer.parseInt(temp[18]);
                    reading = Integer.parseInt(temp[19]);
                    writing = Integer.parseInt(temp[20]);
                    averages = math + reading + writing;

                    scores.add(averages);
                    Schools.add(temp[1]);
                }
                else
                    skippedSchools++;
            }
        }

        System.out.println("Average SAT Scores:");
        for (int i = 0; i < scores.size(); i++)
        {
            System.out.println(Schools.get(i) + ": " + scores.get(i));
        }

        int top1 = 0, top1Tie = 0, top2 = 0, top2Tie = 0, top3Tie = 0, top3 = 0;
        int top1index = 0, top1TieIndex = 0, top2index = 0, top2TieIndex = 0, top3index = 0,
        top3TieIndex = 0;
        for (int i = 0; i < scores.size(); i++)
        {
            int a = scores.get(i);
            if (a > top1)
            {
                top3 = top2;
                top2 = top1;
                top1 = a;
                top1index = i;

            }
            else if (top1 == a){
                top1Tie = a;
                top1TieIndex = i;

            }
            else if (a > top2)
            {
                top3 = top2;
                top2 = a;
                top2index = i;
            }
            else if (top2 == a){
                top2Tie = a;
                top2TieIndex = i;
            }
            else if (a > top3)
            {
                top3 = a;
                top3index = i;
            }
            else if (top3 == a){
                top3Tie = a;
                top3TieIndex = i;
            }

        }

        System.out.println();
        System.out.println("Top three schools:");
        if (top1 == top1Tie){
            System.out.println("#1 - " + Schools.get(top1index) + ": " + scores.get(top1index) + " & "
                + Schools.get(top1TieIndex) + ": " + scores.get(top1TieIndex));}
        else {
            System.out.println("#1 - " + Schools.get(top1index) + ": " + scores.get(top1index));
        }
        if (top2 == top2Tie){
        System.out.println("#2 - " + Schools.get(top2index) + ": " + scores.get(top2index)
        + " & " + Schools.get(top2TieIndex) + ": " + scores.get(top2TieIndex));}
        else {
            System.out.println("#2 - " + Schools.get(top2index) + ": " + scores.get(top2index));
        }
        if (top3 == top3Tie){
        System.out.println("#3 - " + Schools.get(top3index) + ": " + scores.get(top3index)
                + " & " + Schools.get(top3TieIndex) + ": " + scores.get(top3TieIndex));}
        else {
            System.out.println("#3 - " + Schools.get(top3index) + ": " + scores.get(top3index));
        }


        double total = 0;
        for (int i = 0; i < scores.size(); i++)
        {
            total += scores.get(i);
        }
        System.out.println();
        average = total / (scores.size() );
        System.out.println("State average: " + average);
    }
}