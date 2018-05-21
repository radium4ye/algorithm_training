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

        //技能
        List<Skill> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Skill skill = new Skill(s.nextInt(), i);
            list.add(skill);
        }

        //争吵
        for (int i = 0; i < k; i++) {
            int x = s.nextInt() - 1;
            int y = s.nextInt() - 1;
            recordQuarrel(x, y);
        }

        list.sort(Comparator.comparing(o -> o.value));

        //输出结果
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            Skill skill = list.get(i);

            int count = i;
            //遍历0到i之间的程序猿
            for (int j = i - 1; j >= 0; j--) {
                Skill skillJ = list.get(j);
                if (skillJ.value >= skill.value || quarrel(skill.position, skillJ.position)) {
                    count--;
                }
            }

            result[skill.position] = count;
        }

        for (int i = 0; i < n; i++) {
            System.out.printf(result[i] + " ");
        }
    }

    /**
     * 记录争吵
     *
     * @param x
     * @param y
     */
    public static void recordQuarrel(int x, int y) {
        if (y < x) {
            y = (x = y ^ x) ^ y;
            x = y ^ x;
        }

        Set<Integer> set = quarrel.getOrDefault(x, new HashSet<>());
        set.add(y);
        quarrel.putIfAbsent(x, set);
    }

    public static boolean quarrel(int x, int y) {
        if (y < x) {
            y = (x = y ^ x) ^ y;
            x = y ^ x;
        }
        Set<Integer> set = quarrel.getOrDefault(x, new HashSet<>());
        return set.contains(y);
    }

    static HashMap<Integer, Set<Integer>> quarrel = new HashMap<>();

    public static class Skill {
        Integer value;
        Integer position;

        public Skill(Integer value, Integer position) {
            this.value = value;
            this.position = position;
        }


    }
}
