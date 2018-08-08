package lchj.myitalk.fragment;


import android.content.Intent;

import butterknife.BindView;
import butterknife.OnClick;
import lchj.common.BaseFragment;
import lchj.common.widget.PortraitView;
import lchj.myitalk.R;
import lchj.myitalk.UserActivity;

/**
 *我的
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.mPortraitView)
    PortraitView mPortraitView;


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_my;
    }

    @OnClick(R.id.mPortraitView)
    void PortraitClick(){
        Intent intent = new Intent(getContext(), UserActivity.class);
        getContext().startActivity(intent);

    }

    @Override
    protected void initData() {
        super.initData();

    }


}
