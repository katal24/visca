package visca;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Home extends Cmd  {

    static void move(SerialPort serialPort) throws SerialPortException{

        byte[] cmdData = new byte[3];
        cmdData[0] = 1;
        cmdData[1] = 6;
        cmdData[2] = 4;

        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
//      System.out.println("@ " + Main.byteArrayToString(cmdData));
        System.out.println("Home");
        //    serialPort.writeBytes(cmdData);

    }

    public byte[] createCommandData() {
        // TODO Auto-generated method stub
        return null;
    }

}