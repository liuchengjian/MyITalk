package lchj.factory.net;

import lchj.factory.RspModel;
import lchj.factory.data.bean.LoginBean;
import lchj.factory.data.bean.RegisterBean;
import lchj.factory.net.api.AccountRspModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 网络请求的所有的接口
 */
public interface RemoteService {
    /**
     * 注册接口
     *
     * @param bean 传入的是RegisterBean
     * @return 返回的是RspModel<AccountRspModel>
     */
    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterBean bean);

    /**
     * 登录接口
     *
     * @param bean LoginBean
     * @return RspModel<AccountRspModel>
     */
    @POST("account/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginBean bean);
}
