package visca;

import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by dawid on 08.11.16.
 */
public class ResponseReader {

    public static byte[] readResponse(SerialPort serialPort) throws SerialPortException, TimeoutException {
        ArrayList<Byte> data = new ArrayList<Byte>();
        long startTime = System.currentTimeMillis();
        byte b;
        do {
            while (serialPort.getInputBufferBytesCount() == 0) {
                long currenTime = System.currentTimeMillis();
                long timeDiff = currenTime - startTime;
                if (timeDiff > 5000) {
                    throw new TimeoutException();
                }
            }
            byte[] bs = serialPort.readBytes(1);
            b = bs[0];
            data.add(b);
        } while (b != -1);
        byte[] responseData = new byte[data.size()];
        byte idx = 0;
        for(Byte b1 : data) {
            responseData[idx++] = b1.byteValue();
        }

        return responseData;

//        System.out.println("Response");
//        return new byte[2];
    }

}
