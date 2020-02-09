import apps.AppChrome;
import apps.AppIdea;
import apps.AppWechat;
import apps.AppWindows;

public class Application {
    public static void main(String[] args) {
        AppWindows appChrome = new AppChrome();
        AppWindows appIdea = new AppIdea();
        AppWindows appWechat = new AppWechat();

        appIdea.handleF5();
        appChrome.handleF5();
        appWechat.handleF5();
    }
}
