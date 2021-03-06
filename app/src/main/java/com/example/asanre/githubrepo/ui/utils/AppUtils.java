package com.example.asanre.githubrepo.ui.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class AppUtils {

    /**
     * detect if we are almost at the end of the page.
     *
     * @param recyclerView recycler with linear layout manager
     * @return true
     */
    public static boolean pageEndlessDetect(RecyclerView recyclerView) {

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleItemCount = layoutManager.getChildCount() / 2;
            int totalItemCount = layoutManager.getItemCount();
            int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
            return pastVisibleItems + visibleItemCount >= totalItemCount - visibleItemCount;
        } else {
            return false;
        }
    }
}
