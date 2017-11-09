import java.util.Random;

public class EventInputHandler {
    private TCPClient tcpClient;

    private PerformanceTester performanceTester;

    public EventInputHandler(int RECORD_WINDOW, String ip) {
        tcpClient = new TCPClient(ip, 1234);
        performanceTester = new PerformanceTester("Client", RECORD_WINDOW);
    }


    public void sendEvents(int noOfEvents) throws ParameterException {
        Random random = new Random(123);
        int sleepRate = 2000;

        for (int i = 0; i < noOfEvents; i++) {
            long eventTimestamp = System.currentTimeMillis();
            tcpClient.sendMsg(random.nextInt() + "," + eventTimestamp);
            if (i % sleepRate == 0) {
                try {
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            performanceTester.addEvent(eventTimestamp);
        }
    }

}
