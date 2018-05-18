package com.radium4ye.cf.round481.div3;

import java.util.*;

/**
 * @author radium4ye
 */
public class C_978_A {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }


        Map<Integer, Record> map = new TreeMap<>();

        for (int i = 0; i < array.length; i++) {
            map.put(array[i], new Record(array[i], i));
        }

        int digitSize = map.size();
        System.out.println(digitSize);

        Set<Map.Entry<Integer, Record>> entries = map.entrySet();
        List<Map.Entry<Integer, Record>> list = new ArrayList<>(entries);
        list.sort(Comparator.comparing(o -> o.getValue().position));

        list.forEach(integerRecordEntry -> {
            System.out.print(integerRecordEntry.getValue().num + " ");
        });

    }


    static class Record {
        int num;
        Integer position;

        public Record(int num, int position) {
            this.num = num;
            this.position = position;
        }

        public Integer getPosition() {
            return position;
        }


    }
}
