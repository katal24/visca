package visca;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 * @throws SerialPortException 
	 */
    static String commName = "COM1";
    static SerialPort serialPort;// = new SerialPort(commName);
    static private byte speed1 = 3;
    static private byte speed2 = 3;
    static ArrayList<String> macros;
//    static final byte[] ptLeftCommandData;

    public static void main(String[] args) throws SerialPortException {

        Scanner in = new Scanner(System.in);

        serialPort = new SerialPort(commName);
        commName = "COM1";
        macros = new ArrayList<String>();

       // ***  serialPort.openPort();
       // ***  serialPort.setParams(9600, 8, 1, 0);

        while(in.hasNext()){
            for(String ss : macros){
                System.out.println(ss);
            }

            String s = in.nextLine();
            String[] words = s.split(" ");
            if(s.equals("exit")){
                break;
            } else{
                if(!words[0].equals("=") && !isInMacros(words[0])) {
                    switchWord(s);
                } else if(isInMacros(words[0])){ // wywolanie makra
                    callMacro(getMacroFromMacros(words[0]));
                } else {    // makro!!!
                    System.out.println("nowe makro " + s);
                    macros.add(s);
                   // callMacro(s);
                }
            }
        }
}

    public static void callMacro(String macro) throws SerialPortException {
        String[] reqs = macro.split(" ");
        reqs = Arrays.copyOfRange(reqs, 2, reqs.length);

        for(int i=0; i<reqs.length; i++){
            if(reqs[i].equals("speed")){
                switchWord(reqs[i] + " " + reqs[i+1] + " " + reqs[i+2]);
                i = i+2;
            } else if(reqs[i].equals("wait")){
                switchWord(reqs[i] + " " + reqs[i+1]);
                i++;
            } else {
                switchWord(reqs[i]);
            }
        }
    }

    public static boolean isInMacros(String maybeMacro){
        for(String macro : macros){
            if(maybeMacro.equals(macro.split(" ")[1])){
                return true;
            }
        }
        return false;
    }

    public static String getMacroFromMacros(String maybeMacro){
        for(String macro : macros) {
            if (maybeMacro.equals(macro.split(" ")[1])) {
                return macro;
            }
        }
        return null;
    }

    public byte getSpeed1() {
        return speed1;
    }

    public void setSpeed1(byte speed1) {
        this.speed1 = speed1;
    }

    public byte getSpeed2() {
        return speed2;
    }

    public void setSpeed2(byte speed2) {
        this.speed2 = speed2;
    }

    public static void switchWord(String oneReq) throws SerialPortException {
        String[] word = oneReq.split(" ");
        switch (word[0]) {
            case "home":
                Home.move(serialPort);
                readResponseHelp(serialPort);
                //waitaMoment(5);
                break;
            case "right":
                MoveRight.move(serialPort, speed1, speed2);
                readResponseHelp(serialPort);
                //waitaMoment(5);
                break;
            case "left":
                MoveLeft.move(serialPort, speed1, speed2);
                readResponseHelp(serialPort);
                //waitaMoment(5);
                break;
            case "up":
                MoveUp.move(serialPort, speed1, speed2);
                readResponseHelp(serialPort);
                //waitaMoment(5);
                break;
            case "down":
                MoveDown.move(serialPort, speed1, speed2);
                readResponseHelp(serialPort);
                //waitaMoment(5);
                break;
            case "tele":
                ZoomTele.move(serialPort);
                readResponseHelp(serialPort);
                //waitaMoment(5);
                break;
            case "wide":
                ZoomWide.move(serialPort);
                readResponseHelp(serialPort);
                //waitaMoment(5);
                break;
            case "speed":
                if (word.length == 3) {
                    System.out.println("Spped " + word[1] + " " + word[2]);
                    speed1 = Byte.parseByte(word[1]);
                    speed2 = Byte.parseByte(word[2]);
                } else {
                    System.out.println("No parameters");
                }
                //waitaMoment(5);
                break;
            case "wait":
                if(word.length == 2) {
                    System.out.println("Wait " + word[1]);
                    waitaMoment(Integer.parseInt(word[1]));
                } else {
                    System.out.println("Bad parameter");
                }
                break;
            default:
                System.out.println(word[0]);
                System.out.println("Bad request");
                break;
        }
    }

    private static void waitaMoment(int time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte[] arrby = bytes;
        int n = arrby.length;
        int n2 = 0;
        while (n2 < n) {
            byte b = arrby[n2];
          //  sb.append(String.format("%02X ", new Object[](Byte.valueOf(b))));
            ++n2;
        }
        return sb.toString();
    }

    public static void readResponseHelp(SerialPort serialPort){
        try {
            ResponseReader.readResponse(serialPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}