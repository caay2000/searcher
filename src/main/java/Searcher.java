import caay2000.github.com.searcher.SearcherApplication;
import caay2000.github.com.searcher.io.SystemConsole;
import caay2000.github.com.searcher.io.SystemFileReader;

public class Searcher {

    public static void main(String[] args) {

        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), new SystemConsole(System.out));

        searcherApplication.execute(args);
    }
}
