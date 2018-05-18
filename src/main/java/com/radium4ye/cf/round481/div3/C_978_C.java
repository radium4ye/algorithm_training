package com.radium4ye.cf.round481.div3;

import java.util.Scanner;

/**
 * C. Letters
 *
 * @author radium4ye
 */
public class C_978_C {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //宿舍数和信件数
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        //每个宿舍的 房间数
        long[] roomNum = new long[n];
        scanner.nextLine();
        String[] roomNumStr = scanner.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            roomNum[i] = Long.valueOf(roomNumStr[i]);
        }

        for (int i = 1; i < n; i++) {
            roomNum[i] += roomNum[i - 1];
        }

        //每封信
        for (int i = 0; i < m; i++) {
            long letter = scanner.nextLong();

//            int dormitory = 0;
            //采用二分查找 取代直接遍历
//            while (dormitory < n) {
//                if (roomNum[dormitory] >= letter) {
//                    break;
//                }
//                dormitory++;
//            }
            int dormitory = belongDormitory(roomNum, letter);

            long f = dormitory + 1;
            long decreaseNum;
            if (dormitory == 0) {
                decreaseNum = 0;
            } else {
                decreaseNum = roomNum[dormitory - 1];
            }

            long k = letter - decreaseNum;
            System.out.println(f + " " + k);
        }

    }

    /**
     * 查询属于哪个宿舍楼
     *
     * @return
     */
    public static int belongDormitory(long[] roomNum, long letter) {
        int dormitoryStart = 0;
        int dormitoryEnd = roomNum.length - 1;

        for (; ; ) {
            int dormitoryMid = (dormitoryStart + dormitoryEnd) / 2;

            if (letter < roomNum[dormitoryMid]) {
                dormitoryEnd = dormitoryMid;

            } else if (roomNum[dormitoryMid] < letter) {
                dormitoryStart = dormitoryMid;
            } else {
                return dormitoryMid;
            }

            int compare = dormitoryEnd - dormitoryStart;
            if (compare == 0) {
                return dormitoryEnd;
            } else if (compare == 1) {
                if(roomNum[dormitoryStart] < letter){
                    return dormitoryEnd;
                }else {
                    return dormitoryStart;
                }
            }
        }
    }

}
