public class PirateSite {

    private String url;
    private String parsedUrl;
    private String parsedName;

    public PirateSite(String url) {
        this.url = url;
        this.parsedUrl = parseUrl(url);
        this.parsedName = parseName(this.parsedUrl);
    }

    private String parseUrl(String url) {
        String s = url.substring(url.indexOf("/") + 2);
        if (url.contains("www")) {
            s = url.substring(url.indexOf("w") + 4);
        }
        String s1 = s.substring(0, s.indexOf("/"));
        String[] split = s1.split("\\.");
        String fin = "";
        for (String word : split) {
            fin += word + " ";
        }
        return fin;
    }

    private String parseName(String parsedUrl) {
        String[] splitParsedUrl = parsedUrl.split(" ");
        String parsedName = "";

        outer:
        for (int i = 0; i < splitParsedUrl.length; i++) {
            if (i == splitParsedUrl.length - 1) break;
            for (String word : Constants.WHITELISTED_WORDS) {
                if (splitParsedUrl[i].strip().equals(word)) continue outer;
            }
            parsedName += splitParsedUrl[i].strip();
        }

        return parsedName;
    }

    public String getUrl() {
        return this.url;
    }

    public String getParsedUrl() {
        return this.parsedUrl;
    }

    public String getParsedName() {
        return this.parsedName;
    }

}
