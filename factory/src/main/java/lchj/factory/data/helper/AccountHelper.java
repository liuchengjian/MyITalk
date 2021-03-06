package lchj.factory.data.helper;

import lchj.common.data.DataSource;
import lchj.factory.Factory;
import lchj.factory.R;
import lchj.factory.RspModel;
import lchj.factory.data.bean.LoginBean;
import lchj.factory.data.bean.RegisterBean;
import lchj.factory.db.User;
import lchj.factory.net.Network;
import lchj.factory.net.RemoteService;
import lchj.factory.net.api.AccountRspModel;
import lchj.factory.utils.Account;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountHelper {

    /**
     * 注册的接口，异步的调用
     *
     * @param bean    传递一个注册的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void register(final RegisterBean bean, final DataSource.Callback<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountRegister(bean);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }

    /**
     * 登录的调用
     *
     * @param bean    登录的Model
     * @param callback 成功与失败的接口回送
     */
    public static void login(final LoginBean bean, final DataSource.Callback<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountLogin(bean);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }



    /**
     * 请求的回调部分封装
     */
    private static class AccountRspCallback implements Callback<RspModel<AccountRspModel>> {

        final DataSource.Callback<User> callback;

        AccountRspCallback(DataSource.Callback<User> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<RspModel<AccountRspModel>> call,
                               Response<RspModel<AccountRspModel>> response) {
            // 请求成功返回
            // 从返回中得到我们的全局Model，内部是使用的Gson进行解析
            RspModel<AccountRspModel> rspModel = response.body();
            if (rspModel.success()) {
                // 拿到实体
                AccountRspModel accountRspModel = rspModel.getResult();
                // 获取我的信息
                User user = accountRspModel.getUser();

                // 然后返回
                if (callback != null)
                    callback.onDataLoaded(user);
//                DbHelper.save(User.class, user);

                // 第一种，之间保存
//                 user.save();
                /*
                // 第二种通过ModelAdapter
                FlowManager.getModelAdapter(User.class)
                        .save(user);

                // 第三种，事务中
                DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
                definition.beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        FlowManager.getModelAdapter(User.class)
                                .save(user);
                    }
                }).build().execute();
                */
                // 同步到XML持久化中
                Account.login(accountRspModel);

                // 判断绑定状态，是否绑定设备
//                if (accountRspModel.isBind()) {
//                    // 设置绑定状态为True
//                    Account.setBind(true);
//                    // 然后返回
//                    if (callback != null)
//                        callback.onDataLoaded(user);
//                } else {
//                    // 进行绑定的唤起
//                    bindPush(callback);
//                }
            } else {
                // 错误解析
                Factory.decodeRspCode(rspModel, callback);
            }
        }

        @Override
        public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
            // 网络请求失败
            if (callback != null)
                callback.onDataNotAvailable(R.string.data_network_error);
        }
    }


}
