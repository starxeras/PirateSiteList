import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> urls = new ArrayList<String>();
        try {
            File file = new File("resources/list.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                urls.add(data);
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        ArrayList<PirateSite> pirateSites = new ArrayList<PirateSite>();
        for(String url : urls) {
            pirateSites.add(new PirateSite(url));
        }

        try {
            FileWriter w = new FileWriter("resources/piratesitesReddit.txt");
            ArrayList<String> arr = new ArrayList<String>();
            for (PirateSite site : pirateSites) {
                if (site.getParsedName().isBlank()) {
                    arr.add(site.getUrl().strip());
                    arr.add(site.getParsedUrl().strip());
                    continue;
                }
                arr.add(site.getUrl().strip());
                arr.add(site.getParsedUrl().strip());
                arr.add(site.getParsedName().strip());
            }
            w.write("[");
            for (int i = 0; i < arr.size(); i++) {
                if (i == arr.size() - 1) {
                    w.write("\"" + arr.get(i) + "\"");
                    continue;
                }
                w.write("\"" + arr.get(i) + "\"" + ", ");
            }
            w.write("]");
            w.close(); // important...
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
