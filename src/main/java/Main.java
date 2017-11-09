public class Main {
    public static void main(String[] args) {
//        System.setProperty("noOfEvents","100000");
//        System.setProperty("ip","localhost");

        int noOfEvents = Integer.valueOf(System.getProperty("noOfEvents"));
        String ip = System.getProperty("ip");

//        EventInputHandler eventInputHandler = new EventInputHandler(
//                ClassLoader.getSystemResource("log20161231/log20161231.csv").getPath(),
//                noOfEvents / 10);

//        FileReader fileReader = new FileReader("log20161231/log20161231.csv");
        EventInputHandler eventInputHandler = new EventInputHandler(noOfEvents / 10,
                ip);

        try {
            eventInputHandler.sendEvents(noOfEvents);
        } catch (ParameterException e) {
            e.printStackTrace();
        }
    }
}

