public class Main {
    public static void main(String[] args) {
        EventInputHandler eventInputHandler = new EventInputHandler(
                ClassLoader.getSystemResource("log20161231/log20161231.csv").getPath());

        try {
            eventInputHandler.sendEvents(10000,1000);
        } catch (ParameterException e) {
            e.printStackTrace();
        }
    }
}

