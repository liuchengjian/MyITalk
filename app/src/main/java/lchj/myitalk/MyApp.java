package lchj.myitalk;

import lchj.common.Application;
import lchj.factory.Factory;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 调用Factory进行初始化
        Factory.setup();
    }
}
