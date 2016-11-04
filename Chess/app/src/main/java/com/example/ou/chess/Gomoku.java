package com.example.ou.chess;

/**
 * Created by ou on 16-11-4.
 */
import android.util.Log;
import android.widget.Toast;

import java.util.Scanner;

public class Gomoku {
    //standard chessboard
    private static int n = 5;
    /*public static void set_chessboard_n(int s_n){
        try{
            if( s_n >= 5 )
                n = s_n;
        }catch(Exception e){
            System.out.println("chessboard is too small.");
        }
    }*/

    private static char piece_B = 'B';
    private static char piece_W = 'W';
    private static char piece_none = ' ';

    private static int [][] chessboard ;
    public static void init_chessboard(){
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                chessboard[i][j] = piece_none;
            }
        }
    }

    public static boolean can_set_piece(int pi, int pj, char piece){
        return pi>=0 && pi<n && pj>=0 && pj<n &&
                chessboard[pi][pj] == piece_none
                && (piece == piece_B || piece == piece_W);
    }

    public static void set_chessboard(int pi, int pj, char piece){
        chessboard[pi][pj] = piece;
    }

    public Gomoku(int rowNo){
        n=rowNo;
        chessboard= new int [n][n];
        //set_chessboard_n(n);
        init_chessboard();
    }
    public static boolean player_now_win(int pi, int pj, char piece){
        int count = 0;
        for(int i = pi-1; i>=0 && chessboard[i][pj] == piece ;i--){
            count++;
            Log.d("Gomoku","piece: "+ piece +" count = "+count);
        }
        for(int i = pi+1; i<n && chessboard[i][pj] == piece ; i++){
            count++;
            Log.d("Gomoku","piece: "+ piece +" count = "+count);
        }
        if(count==4){
            return true;
        }

        count = 0;
        for(int j = pj-1; j>=0 && chessboard[pi][j] == piece ; j--){
            count++;
            Log.d("Gomoku","piece: "+ piece +" count = "+count);
        }
        for(int j = pj+1; j<n && chessboard[pi][j] == piece ; j++){
            count++;
            Log.d("Gomoku","piece: "+ piece +" count = "+count);
        }
        if(count==4){
            return true;
        }

        count = 0;
        for(int i = pi-1, j = pj-1; i>=0 && j>=0 && chessboard[i][j] == piece ; i--, j--){
            count++;
            Log.d("Gomoku","piece: "+ piece +" count = "+count);
        }
        for(int i = pi+1, j = pj+1; i<n && j<n && chessboard[i][j] == piece ; i++, j++){
            count++;
            Log.d("Gomoku","piece: "+ piece +" count = "+count);
        }
        if(count==4){
            return true;
        }

        count = 0;
        for(int i = pi+1, j = pj-1; i<n && j>=0 && chessboard[i][j] == piece ; i++, j--){
            count++;
            Log.d("Gomoku","piece: "+ piece +" count = "+count);
        }
        for(int i = pi-1, j = pj+1; i>=0 && j<n && chessboard[i][j] == piece ; i--, j++){
            count++;
            Log.d("Gomoku","piece: "+ piece +" count = "+count);
        }
        if(count==4){
            return true;
        }
        Log.d("Gomoku ","Final piece: "+ piece +" count = "+count+ "chessboard["+pi+"]["+(pj+1)+"] : ");
        for(int dd=0; dd<n;dd++){
            for(int ee=0; ee<n;ee++) {
                Log.d("Gomoku","board["+dd+"]["+ee+"] :"+chessboard[dd][ee]);
            }

        }
        //Log.d("Gomoku",""+chessboard);
        return false;

    }

    public static boolean player_now_tie(){
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0 ; j < n ; j++){
                if(chessboard[i][j] == piece_none){
                    return false;
                }
            }
        }
        return true;
    }

    public static void print_chessboard(){
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0 ; j < n ; j++){
                System.out.printf("%c ", chessboard[i][j]);
            }
            System.out.printf(" i%d%n", i+1);
        }
        for(int j = 0 ; j < n ; j++){
            System.out.printf("%d ", j+1);
        }
        System.out.println();
    }

    public static void main(String[] args){
        init_chessboard();
        print_chessboard();
        Scanner in = new Scanner(System.in);
        while(!player_now_tie()){
            for(int p = 0 ; p < 2 ; p++){
                char piece_p;
                if(p==0){
                    System.out.println("player Black set piece i j");
                    piece_p = piece_B;
                }else{
                    System.out.println("player White set piece i j");
                    piece_p = piece_W;
                }
                int pi = in.nextInt();
                int pj = in.nextInt();

                while(!can_set_piece(pi, pj, piece_p)){
                    System.out.println("wrong input, please reset");
                    pi = in.nextInt();
                    pj = in.nextInt();
                }
                set_chessboard(pi, pj, piece_p);
                print_chessboard();
                if(player_now_win(pi, pj, piece_p)){
                    System.out.println("player win");
                    in.close();
                    return;
                }
            }

        }
        in.close();
        System.out.println("play tie");
    }
}
