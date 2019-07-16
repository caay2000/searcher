import com.github.caay2000.searcher.SearcherApplication;
import com.github.caay2000.searcher.io.SystemConsole;
import com.github.caay2000.searcher.io.SystemFileReader;

public class Searcher {

    public static void main(String[] args) {

        SearcherApplication searcherApplication = new SearcherApplication(new SystemFileReader(), new SystemConsole(System.out, System.in));

        searcherApplication.execute(args);
    }
}
