package apps;

public class AppChrome extends AppWindows {
    public AppChrome() {
    }

    @Override
    public void handleF5() {
        System.out.println("Chrome刷新网页");
    }
}
