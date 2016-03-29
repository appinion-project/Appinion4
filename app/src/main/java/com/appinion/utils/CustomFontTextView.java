 package com.appinion.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomFontTextView extends TextView {

	public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (!isInEditMode()) {
			init();
		}
	}

	public CustomFontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode()) {
			init();
		}
	}

	public CustomFontTextView(Context context) {
		super(context);
		if (!isInEditMode()) {
			init();
		}
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"helveticaneue.ttf");
		setTypeface(tf);
	}

}
