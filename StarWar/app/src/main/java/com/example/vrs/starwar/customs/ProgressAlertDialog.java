package com.example.vrs.starwar.customs;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

import com.example.vrs.starwar.R;


public class ProgressAlertDialog extends ProgressDialog {

	private TextView tvMsg;
	private TextView tvTitle;
	private String message;

	public ProgressAlertDialog(Context context, String message) {
		super(context);
		this.message = message;
	}

	@Override
	public void show() {
		super.show();
		setContentView(R.layout.dialog_progress);
		init();
	}

	private void init() {
		initView();
		initData();
	}

	@Override
	public void setMessage(CharSequence message) {
		// super.setMessage(message);
		tvMsg.setText(message.toString());
	}

	private void initView() {
		tvMsg = (TextView) findViewById(R.id.tv_msg);
		tvTitle = (TextView) findViewById(R.id.tv_title);
	}

	private void initData() {
		tvMsg.setText(message);
	}

}
