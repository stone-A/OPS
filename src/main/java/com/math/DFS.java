package com.math;

import java.util.*;

/**
 * 深度优先遍历：顾名思义，就是一条路走到黑，走到最深的地方。当无路可走时，就返回上一步向其他路走，若没有其他路，则在返回一层，本方法可以通过递归来实现，
 * 但这次我是使用栈来实现的，把每一层的数据存储在栈中，用boolean型的vis[MAXN][MAXN]数组记录是否访问过，其中通过使用栈来实现记录访问路径过程，以便于回溯：
 */
public class DFS {

    /**
     * @param args
     */
    final static int MAXN = 100;
    static Scanner scan = new Scanner(System.in);
    public static class Stack    {
        int Depth;
        int Dot;
        Stack()    {
            Depth = -1;
            Dot = -1;
        }
        public int getTopDot()    {
            return Dot;
        }
        public int getDepth()    {
            return Depth;
        }
        public void PushDate(int dep,int dot)    {
            Depth = dep;
            Dot = dot;
            System.out.println(dot+" The depth is:"+dep);
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] Graph = new int[MAXN][MAXN];
        boolean[] vis = new boolean[MAXN];
        for(int i=0;i<MAXN;i++)    {
            Arrays.fill(Graph[i], 0);
        }
        Arrays.fill(vis, false);
        int base = 0;int top = 0;
        Stack[] stack = new Stack[MAXN];
        int n,m;//n为点数，m为边数
        n = scan.nextInt();
        m = scan.nextInt();
        for(int i=0;i<2*n;i++)    {
            stack[i]=new Stack();
        }
        for(int i=0;i<m;i++)    {
            int a,b;
            a = scan.nextInt();
            b = scan.nextInt();
            Graph[a][b] = Graph[b][a] = 1;
        }
        for(int i=0;i<n;i++)    {
            if(vis[i] == false)    {
                int dep = -1;
                int dot = -1;
                stack[top].PushDate(1, i);
                top++;
                vis[i] = true;
                while(base != top)    {
                    dot = stack[top-1].getTopDot();
                    for(int j=0;j<n;j++)    {
                        if(Graph[dot][j] == 1 && vis[j] == false)    {
                            dep = stack[top-1].getDepth()+1;
                            stack[top].PushDate(dep, j);
                            top++;
                            vis[j] = true;
                            break;
                        }
                        if(j == n-1)//如果无路可走，出栈
                            top--;
                    }
                    
                }
            }
        }
    }
}