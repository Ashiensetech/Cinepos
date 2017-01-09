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

    public static List<ScreenSeat> generateSeats(int row,int col, SeatType defaultSeatType){
        if(row>702){
            out.println("Maximum 702 row allowed");
            return null;
        }
        String [] seatRowName = ScreenHelper.generateTable(row);
        List<ScreenSeat> screenSeats  = new ArrayList<>();
        int screenId = 1;
        for(int r=0;r<row;r++){
            for(int c=0;c<col;c++){

                ScreenSeat screenSeat = new ScreenSeat();
                screenSeat.setId(screenId++);
                screenSeat.setName(seatRowName[r] + "-" + (c + 1));
                screenSeat.setSeatType(defaultSeatType);

                screenSeats.add(screenSeat);
            }
        }


        return screenSeats;
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
    public static List<ScreenSeat> arrayToListAndSetIdZero(ScreenSeat[] screenSeats){
        List<ScreenSeat> screenSeatList = new ArrayList<>();

        for(ScreenSeat screenSeat : screenSeats){
            screenSeat.setId(0);
            screenSeatList.add(screenSeat);
        }

        out.println(screenSeatList);
        return screenSeatList;
    }

}
