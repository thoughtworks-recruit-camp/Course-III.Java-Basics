package apps;

public class AppWechat extends AppWindows {
    public AppWechat() {
    }

    @Override
    public void handleF5() {
        super.handleF5();
        System.out.println("Wechat弹出英文输出框");
    }
}
