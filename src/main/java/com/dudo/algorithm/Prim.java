package com.dudo.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Prim 最小生成树算法
 * 0              p1
 * 0         /     |     \
 * 0    6  /       | 1     \ 5
 * 0     /    5    |    5    \
 * 0  p2 -- -- -- - p3 -- -- -- p4
 * 0    \                      /
 * 0   3  \                  /  2
 * 0       p5 -- -- -- -- p6
 * 0             6
 *
 *
 * <p>
 *     时间复杂度： O(n^3)
 *     1*6+
 2*6+
 3*6+
 4*6+
 5*6
 =  (6+30)*5/2
 = (n+n*(n-1))*(n-1)/2
 = (n(n-1)+n*(n-1)^2)   /2
 = n(n-1)(1+n-1) /2
 = n^2(n-1)/2
 * =  O(n^3)
 * Created by zkai on 2015/2/3.
 */
public class Prim {
    private int[][] graph = new int[7][7];

    private void init() {
        graph[1][2] = graph[2][1] = 6;
        graph[1][3] = graph[3][1] = 1;
        graph[1][4] = graph[4][1] = 5;
        graph[2][3] = graph[3][2] = 5;
        graph[2][5] = graph[5][2] = 3;
        graph[3][4] = graph[4][3] = 5;
        graph[3][5] = graph[5][3] = 6;
        graph[3][6] = graph[6][3] = 4;
        graph[4][6] = graph[6][4] = 2;
        graph[5][6] = graph[6][5] = 6;
    }

    private void compute() {
        int pointA = 1;
        Set<Integer> ownPoints = new HashSet<>();
        ownPoints.add(pointA);

        int sum = 0;
        int loop = 1;
        /////////////
        while (ownPoints.size() < graph.length-1) {
            int currMinVal = -1;
            int pointB = -1;
            for (Integer ownPoint : ownPoints) {
                for (int i = 1; i < graph[ownPoint].length; i++) {
//                    System.out.format(loop++ +"curr edge:(%d,%d),val:%d",ownPoint,i,graph[ownPoint][i]);
//                    System.out.println();
                    if ((currMinVal < 0 || graph[ownPoint][i] < currMinVal)
                            && graph[ownPoint][i] > 0
                            && !ownPoints.contains(i)) {
                        currMinVal = graph[ownPoint][i];
                        pointA = ownPoint;
                        pointB = i;
                    }
                }
            }
            sum += currMinVal;
            System.out.format("curr edge:(%d,%d),val:%d,sum:%d",
                    pointA, pointB, currMinVal, sum);
            System.out.println();
            ownPoints.add(pointB);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Prim prim = new Prim();
        prim.init();
        prim.compute();

    }
}
