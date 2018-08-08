package lchj.myitalk.fragment.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lchj.common.BaseFragment;
import lchj.myitalk.R;
import lchj.myitalk.activity.SearchActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchGroupFragment extends BaseFragment implements SearchActivity.SearchFragment {


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_group;
    }

    @Override
    public void search(String content) {

    }
}
