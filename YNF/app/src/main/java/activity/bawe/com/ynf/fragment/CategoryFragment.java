package activity.bawe.com.ynf.fragment;

import android.view.View;

import activity.bawe.com.ynf.bese.BaseFragment;
import activity.bawe.com.ynf.view.ShowingPage;


/**
 * Created by zhiyuan on 16/9/28.
 */
public class CategoryFragment extends BaseFragment {
    @Override
    protected void onLoad() {
        CategoryFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
    }
    @Override
    public View createSuccessView() {
        return null;
    }
}
