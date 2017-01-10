package helper;

import entity.ScreenSeat;
import entity.SeatType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
/**
 * Created by mi on 1/9/17.
 */


public class ScreenHelper {

    /*public static List<ScreenSeat> generateSeats(int row,int col, SeatType defaultSeatType){
        if(row>702){
            out.println("Maximum 702 row allowed");
            return null;
        }
        String [] seatRowName = ScreenHelper.generateTable(row);
        List<ScreenSeat> screenSeats  = new ArrayList<>();
        //int screenId = 1;
        for(int r=0;r<row;r++){
            for(int c=0;c<col;c++){

                ScreenSeat screenSeat = new ScreenSeat();
                screenSeat.setId(0);
                screenSeat.setName(seatRowName[r] + "-" + (c + 1));
                screenSeat.setSeatType(defaultSeatType);

                screenSeats.add(screenSeat);
            }
        }


        return screenSeats;
    }*/
    public static List<List<ScreenSeat>> generateSeats(int row,int col, SeatType defaultSeatType){
        if(row>702){
            out.println("Maximum 702 row allowed");
            return null;
        }
        String [] seatRowName = ScreenHelper.generateTable(row);
        List<List<ScreenSeat>> screenSeats  = new ArrayList<>();
        //int screenId = 1;
        for(int r=0;r<row;r++){
            List<ScreenSeat> screenSeatsCol = new ArrayList<>();
            for(int c=0;c<col;c++){

                ScreenSeat screenSeat = new ScreenSeat();
                screenSeat.setId(0);
                screenSeat.setName(seatRowName[r] + "-Old-" + (c + 1));
                screenSeat.setSeatType(defaultSeatType);

                screenSeatsCol.add(screenSeat);
            }
            screenSeats.add(screenSeatsCol);
        }


        return screenSeats;
    }
    public  static List<List<ScreenSeat>> singleDimensionToTwoDimensionList(List<ScreenSeat> singleDimensionList,int rows,int cols){
        List<List<ScreenSeat>> twoDimensionList  = new ArrayList<>();



        List<ScreenSeat> colList = new ArrayList<>();
        for(int  i=0;i<singleDimensionList.size();i++){
            colList.add(singleDimensionList.get(i));

            if((i+1)%cols == 0){
                twoDimensionList.add(colList);
                colList = new ArrayList<>();
            }
        }
        return twoDimensionList;
    }
    public static List<List<ScreenSeat>> mergeSeats(List<List<ScreenSeat>> oldSeats,int newRows,int newCols, SeatType defaultSeatType){
        if(newRows>702){
            out.println("Maximum 702 row allowed");
            return null;
        }
        int oldRows = oldSeats.size();
        int oldCOls = oldSeats.get(0).size();

        String [] seatRowName = ScreenHelper.generateTable(newRows);
        List<List<ScreenSeat>> screenSeats  = new ArrayList<>();
        //int screenId = 1;
        for(int r=0;r<newRows;r++){
            List<ScreenSeat> screenSeatsCol = new ArrayList<>();
            for(int c=0;c<newCols;c++){
                ScreenSeat screenSeat = null;
                if(r<oldRows && c<oldCOls){
                    screenSeat = oldSeats.get(r).get(c);
                    screenSeatsCol.add(screenSeat);
                    continue;
                }else{
                    screenSeat = new ScreenSeat();
                }


                screenSeat.setId(0);
                screenSeat.setName(seatRowName[r] + "-" + (c + 1));
                screenSeat.setSeatType(defaultSeatType);

                screenSeatsCol.add(screenSeat);
            }
            screenSeats.add(screenSeatsCol);
        }


        return screenSeats;
    }
    public static void main(String[] args) {
        List<List<ScreenSeat>> screenSeats =  ScreenHelper.generateSeats(5, 5, new SeatType());
        ScreenHelper.printSears(screenSeats);
        List<List<ScreenSeat>> newScreenSeats =ScreenHelper.mergeSeats(screenSeats,3,3, new SeatType());
        ScreenHelper.printSears(newScreenSeats);


    }
    private static void init1Dto2D(){
        List<ScreenSeat> l = new ArrayList<ScreenSeat>(){
            {
                ScreenSeat s1 = new ScreenSeat();
                ScreenSeat s2 = new ScreenSeat();
                ScreenSeat s3 = new ScreenSeat();
                ScreenSeat s4 = new ScreenSeat();
                ScreenSeat s5 = new ScreenSeat();
                ScreenSeat s6 = new ScreenSeat();
                ScreenSeat s7 = new ScreenSeat();
                ScreenSeat s8 = new ScreenSeat();
                ScreenSeat s9 = new ScreenSeat();


                s1.setName("1");
                s2.setName("2");
                s3.setName("3");
                s4.setName("4");
                s5.setName("5");
                s6.setName("6");
                s7.setName("7");
                s8.setName("8");
                s9.setName("9");

                add(s1);
                add(s2);
                add(s3);
                add(s4);
                add(s5);
                add(s6);
                add(s7);
                add(s8);
                add(s9);

            }
        };
        printSears(ScreenHelper.singleDimensionToTwoDimensionList(l, 3, 3));
    }
    private static void printSears( List<List<ScreenSeat>> screenSeats){
        for(List<ScreenSeat> screenSeatsRow : screenSeats){
            for(ScreenSeat seat : screenSeatsRow){
                out.print(seat.getName()+" ");
            }
            out.println("");
        }
    }
    private static  String [] generateTable(int totalRow){

        String [] seatRowName =new  String[totalRow];
        int roundLength = 26; // A - Z total 26 Alphabet
        int roundCount = 0;
        int charCode= 65;
        int c =1;
        for(int i=0;i<totalRow;i++,c++){
           if(seatRowName[i]==null){
               seatRowName[i] = "";
            }
            if(roundCount>0){
               seatRowName[i] +=(char)(roundCount+64);
            }
            seatRowName[i] +=((char)charCode);


            if(( c%roundLength )==0){
                charCode = 65;
                roundCount++;
            }else{
                charCode++;
            }




        }
        return seatRowName;
    }
    public static List<ScreenSeat> arrayToListAndSetIdZero(ScreenSeat[] screenSeats,boolean setIdToZero){
        List<ScreenSeat> screenSeatList = new ArrayList<>();

        for(ScreenSeat screenSeat : screenSeats){
            if(setIdToZero){
                screenSeat.setId(0);
            }
            screenSeatList.add(screenSeat);
        }

        return screenSeatList;
    }

}
