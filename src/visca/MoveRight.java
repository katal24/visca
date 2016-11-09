package visca;

import jssc.SerialPort;
import jssc.SerialPortException;


public class MoveRight extends Cmd  {

	static void move(SerialPort serialPort, byte speed1, byte speed2) throws SerialPortException{
		
        byte[] cmdData = new byte[7];
        cmdData[0] = 1;
        cmdData[1] = 6;
        cmdData[2] = 1;
        cmdData[5] = 2;
        cmdData[6] = 3;

        cmdData[3] = speed1;
        cmdData[4] = speed2;

      ViscaCommand vCmd = new ViscaCommand();
      vCmd.commandData = cmdData;
      vCmd.sourceAdr = 0;
      vCmd.destinationAdr = 1;
      cmdData = vCmd.getCommandData();
//      System.out.println("@ " + Main.byteArrayToString(cmdData));
        System.out.println("MoveRight");

  //***      serialPort.writeBytes(cmdData);
      
	}

	public byte[] createCommandData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}