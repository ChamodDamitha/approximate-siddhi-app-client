import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EventInputHandler {
    private static String sCurrentLine = null;
    private static BufferedReader br = null;
    private static FileReader fr = null;
    private TCPClient tcpClient;

    private PerformanceTester performanceTester;

    public EventInputHandler(String FILENAME,int RECORD_WINDOW) {
        initFileRead(FILENAME);
        tcpClient = new TCPClient("localhost", 1234);
        performanceTester = new PerformanceTester("Client", RECORD_WINDOW);
    }


    //  file read for event generation
    private void initFileRead(String FILENAME) {
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getNextIPAddress() {
        try {
            if ((sCurrentLine = br.readLine()) != null) {
                String[] strArr = sCurrentLine.trim().split(",");
                return strArr[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sCurrentLine == null) {
                try {
                    if (br != null) {
                        br.close();
                    }
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public void sendEvents(int noOfEvents, int tps) throws ParameterException {

        int sleepRate = tps / 1000;
        if (sleepRate == 0) {
            throw new ParameterException("A TPS value greater than or equal 1000 expected but " + tps + " found.");
        }

        for (int i = 0; i < noOfEvents; i++) {
            long eventTimestamp = System.currentTimeMillis();
            tcpClient.sendMsg(getNextIPAddress() + "," + eventTimestamp);
            if (i % sleepRate == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            performanceTester.addEvent(eventTimestamp);
        }
    }

}
