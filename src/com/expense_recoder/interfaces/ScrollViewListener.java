package com.expense_recoder.interfaces;

import com.expense_recoder.scrollview.ObservableScrollView;

public interface ScrollViewListener {
	void onScrollChanged(ObservableScrollView scrollView, int x, int y,int oldX, int oldY);
}