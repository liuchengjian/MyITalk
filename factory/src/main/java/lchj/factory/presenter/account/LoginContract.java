package lchj.factory.presenter.account;

import lchj.factory.presenter.BaseContract;

public interface LoginContract {
    interface LoginView extends BaseContract.View<LoginPresenter> {
        // 登录成功
        void loginSuccess();
    }


    interface LoginPresenter extends BaseContract.Presenter {
        // 发起一个登录
        void login(String phone, String password);
    }

}