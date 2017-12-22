package com.radium4ye.algorithm;


/**
 * @author radium4ye
 */
public class HanoiMove {
    long count = 0;

    public static void main(String[] args) {
        HanoiMove hanoiMove = new HanoiMove();
        hanoiMove.move(0, 2, 30, 1);
    }


    public void move(int from, int to, int bottom, int top) {
        String msg = "将" + bottom + " from " + from + " to " + to + "。 ";
        if (bottom == top) {
            System.out.println(msg);
//            System.out.println(count++);
            return;
        }
        int other = 0;
        for (int i = 0; i <= 2; i++) {
            if (i != from && i != to) {
                other = i;
            }
        }

        move(from, other, bottom - 1, top);
        System.out.println(msg);
//        System.out.println(count++);
        move(other, to, bottom - 1, top);

    }
}
