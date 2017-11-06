public class Main {
    public static void main(String[] args) {
        final int noOfEvents = 100000;
        final int tps = 10000;

        EventInputHandler eventInputHandler = new EventInputHandler(
                ClassLoader.getSystemResource("log20161231/log20161231.csv").getPath(),
                noOfEvents / 10);

        try {
            eventInputHandler.sendEvents(noOfEvents, tps);
        } catch (ParameterException e) {
            e.printStackTrace();
        }
    }
}

