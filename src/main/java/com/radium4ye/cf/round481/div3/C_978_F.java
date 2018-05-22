package com.radium4ye.cf.round481.div3;

import java.util.*;

/**
 * @author radium4ye
 */
public class C_978_F {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();

        int[] result = new int[n];
        int[] quarrelResult = new int[n];

        //技能
        List<Skill> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Skill skill = new Skill(s.nextInt(), i);
            list.add(skill);
        }

        //争吵
        for (int i = 0; i < k; i++) {
            int x = s.nextInt() - 1;
            int y = s.nextInt() - 1;
            Skill skillx = list.get(x);
            Skill skilly = list.get(y);

            if (skillx.value < skilly.value) {
                quarrelResult[y]--;
            } else if (skilly.value < skillx.value) {
                quarrelResult[x]--;
            }

        }

        list.sort(Comparator.comparing(o -> o.value));

        int preValue = list.get(0).value;
        int preResult = 0;
        for (int i = 1; i < n; i++) {
            Skill skill = list.get(i);

            if (preValue == skill.value) {

            } else {
                preValue = skill.value;
                preResult = i;
            }

            result[skill.position] = preResult;
        }

        //输出结果
        for (int i = 0; i < n; i++) {
            System.out.printf((result[i] + quarrelResult[i]) + " ");
        }
    }

    public static class Skill {
        Integer value;
        Integer position;

        public Skill(Integer value, Integer position) {
            this.value = value;
            this.position = position;
        }
    }
}
