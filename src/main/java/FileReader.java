import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {
    private static String sCurrentLine = null;
    private static BufferedReader br = null;
    private static java.io.FileReader fr = null;
    private ArrayList<String> bufferedIPs;

    public FileReader(String FILENAME) {
        initFileRead(FILENAME);
        bufferedIPs = new ArrayList<>();

        System.out.println("Started reading from file : " + FILENAME);
        readFile();
        System.out.println("Stopped reading from file : " + FILENAME);
    }

    private void readFile() {
        String ip;
        while ((ip = getNextIPAddress()) != null) {
            bufferedIPs.add(ip);
        }
    }

    //  file read for event generation
    private void initFileRead(String FILENAME) {
        try {
            fr = new java.io.FileReader(FILENAME);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getNextIPAddress() {
        String ip = null;
        try {
            if ((sCurrentLine = br.readLine()) != null) {
                String[] strArr = sCurrentLine.trim().split(",");
                ip = strArr[0];
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
        return ip;
    }

    public ArrayList<String> getBufferedIPs() {
        return bufferedIPs;
    }
}
