package com.example.vrs.starwar.parent;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vrs.starwar.R;
import com.example.vrs.starwar.util.APPConstant;
import com.example.vrs.starwar.util.ScreenDimension;


@SuppressLint("NewApi")
public class StarwarParentDialogFragment extends DialogFragment implements OnKeyListener {
	protected View view;
	protected BaseActivity mActivity;
	protected FragmentManager mFragmentManager;
	protected Context context;
	private TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int style = DialogFragment.STYLE_NO_TITLE, theme = android.R.style.Theme_Holo_Light_Dialog_MinWidth;
		setStyle(style, theme);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setCancelable(false);
		getDialog().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		getDialog().setCanceledOnTouchOutside(false);
		getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
		initSuperViews();
		return null;
	}

	private void initSuperViews() {
		mActivity = (BaseActivity) getActivity();
		mFragmentManager = getFragmentManager();
		context = mActivity.getApplicationContext();
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		APPConstant.isDiaolgShownLevel = false;
		// deleteFile(tv.getText().toString());
		super.onCancel(dialog);
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		APPConstant.isDiaolgShownLevel = false;
		super.onDismiss(dialog);
	}

	protected void setWidth(Double size) {
		View layout = (View) view.findViewById(R.id.dialog_layout);
		layout.setMinimumWidth((int) (size * ScreenDimension.SCREEN_WIDTH));
	}

	protected void setHeight(Double size) {
		View layout = (View) view.findViewById(R.id.dialog_layout);
		layout.setMinimumHeight((int) (size * ScreenDimension.SCREEN_HEIGHT));
	}

	protected void disableBackPress() {
		getDialog().setOnKeyListener(this);
	}

	public void onLoginSucessful() {
	}

	protected void setTitleText(String title) {
		tv = (TextView) view.findViewById(R.id.title);
		tv.setText(title);
	}

	protected void enalbleHeaderCancelButton(final String imageName,boolean isActivityFinish) {
		ImageButton cancel = (ImageButton) view.findViewById(R.id.cancel);
		if (isActivityFinish)
			cancel.setVisibility(View.INVISIBLE);
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// deleteFile(imageName);
				dismiss();
			}

		});
	}


	protected void hideHeaderCancelButton() {
		ImageButton cancel = (ImageButton) view.findViewById(R.id.cancel);
		cancel.setVisibility(View.GONE);
	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return false;
	}

}
