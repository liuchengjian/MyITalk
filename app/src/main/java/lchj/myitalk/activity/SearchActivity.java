package lchj.myitalk.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import butterknife.BindView;
import lchj.common.BaseActivty;
import lchj.common.BaseFragment;
import lchj.myitalk.R;
import lchj.myitalk.fragment.search.SearchGroupFragment;
import lchj.myitalk.fragment.search.SearchUserFragment;

public class SearchActivity extends BaseActivty {
    private static final String EXTRA_TYPE = "EXTRA_TYPE";
    public static final int TYPE_USER = 1; // 搜索人
    public static final int TYPE_GROUP = 2; // 搜索群
    // 具体需要显示的类型
    private int type;
    private SearchFragment mSearchFragment;

    @BindView(R.id.ll_search)
    LinearLayout llSearch;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_seach;
    }

    @Override
    protected boolean initArgs(Bundle bundle){
        type = getIntent().getIntExtra("0",0);
        return  type==TYPE_USER||type==TYPE_GROUP;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 显示对应的Fragment
        BaseFragment fragment = null;
        if (type == TYPE_USER) {
            SearchUserFragment searchUserFragment = new SearchUserFragment();
            fragment = searchUserFragment;
            mSearchFragment = (SearchFragment) searchUserFragment;
        } else {
            SearchGroupFragment searchGroupFragment = new SearchGroupFragment();
            fragment = searchGroupFragment;
            mSearchFragment = (SearchFragment) searchGroupFragment;
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.search_container, fragment)
                .commit();
    }

    @Override
    protected void initData() {
        super.initData();


        Glide.with(this)
                .load(R.drawable.bg_src_tianjin)
                .centerCrop()
                .into(new ViewTarget<View, GlideDrawable>(llSearch) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        this.view.setBackground(resource.getCurrent());
                    }
                });
    }

    /**
     * 搜索的Fragment必须继承的接口
     */
    public interface SearchFragment {
        void search(String content);
    }
}
