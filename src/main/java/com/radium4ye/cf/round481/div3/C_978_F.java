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

        long[] result = new long[n];

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
                result[y] --;
            }else if(skilly.value < skillx.value){
                result[x] --;
            }

        }

        list.sort(Comparator.comparing(o -> o.value));
        for (int i = 1; i < n; i++) {
            Skill skill = list.get(i);

            long count = list.subList(0,i)
                    .parallelStream()
                    .filter(skill1 -> skill1.value < skill.value)
                    .count();

            result[skill.position] += count;
        }




        //输出结果
        for (int i = 0; i < n; i++) {
            System.out.printf(result[i] + " ");
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
