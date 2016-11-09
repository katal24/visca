package visca;

import jssc.SerialPort;
import jssc.SerialPortException;

public class ZoomWide extends Cmd  {

    static void move(SerialPort serialPort) throws SerialPortException{

        byte[] cmdData = new byte[4];
        cmdData[0] = 1;
        cmdData[1] = 4;
        cmdData[2] = 7;
        cmdData[3] = 3;


        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
//      System.out.println("@ " + Main.byteArrayToString(cmdData));
        System.out.println("ZoomWide");
        //    serialPort.writeBytes(cmdData);

    }

    public byte[] createCommandData() {
        // TODO Auto-generated method stub
        return null;
    }

}