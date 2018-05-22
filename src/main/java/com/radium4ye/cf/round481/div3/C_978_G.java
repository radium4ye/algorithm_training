package com.radium4ye.cf.round481.div3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author radium4ye
 */
public class C_978_G {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();

        //每日的安排
        int[] day = new int[n + 1];

        List<Exam> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            Exam exam = new Exam(i + 1, s.nextInt(), s.nextInt(), s.nextInt());
            list.add(exam);
            day[exam.examTime] = m + 1;
        }

        list.sort(Comparator.comparing(Exam::getExamTime));


        //考试最早的优先
        for (int i = 0; i < m; i++) {
            Exam exam = list.get(i);

            int prepareDay = exam.prepareDay;
            for (int j = exam.startTime; j < exam.examTime; j++) {
                //准备完毕
                if (prepareDay == 0) {
                    break;
                }

                //第j天休息
                if (day[j] == 0) {
                    prepareDay--;
                    day[j] = exam.num;
                }
            }

            //没时间准备
            if (prepareDay > 0) {
                System.out.println("-1");
                return;
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.printf(day[i] + " ");
        }

    }

    public static class Exam {
        int num;
        int startTime;
        int examTime;
        int prepareDay;

        public Exam(int num, int startTime, int examTime, int prepareDay) {
            this.num = num;
            this.startTime = startTime;
            this.examTime = examTime;
            this.prepareDay = prepareDay;
        }

        public int getExamTime() {
            return examTime;
        }
    }
}
