package com.appinion.utils;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SetListViewHeightBasedOnChildren {

    public static void getListViewSize(ListView myListView) {

        ListAdapter listAdapter = myListView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int desiredWidth = MeasureSpec.makeMeasureSpec(myListView.getWidth(), MeasureSpec.AT_MOST);
        int totalHeight = 0;
        View view = null;
        if (listAdapter.getCount() > 0) {
            for (int i = 0; i < listAdapter.getCount(); i++) {
                view = listAdapter.getView(i, view, myListView);
                if (i == 0) {
                    view.setLayoutParams(new AbsListView.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));
                }
                view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
                totalHeight += view.getMeasuredHeight();
            }
        }
        LayoutParams params = myListView.getLayoutParams();
        params.height = totalHeight + 100 + (myListView.getDividerHeight() * (listAdapter.getCount() - 1));
        myListView.setLayoutParams(params);
        myListView.requestLayout();
    }
}
