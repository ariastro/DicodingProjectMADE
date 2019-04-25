package com.example.astronout.mysharedpreference;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_phone)
    TextView tvPhoneNo;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_is_love_mu)
    TextView tvIsLoveMU;
    @BindView(R.id.btn_save)
    Button btnSave;

    private UserPreference mUserPreference;

    private boolean isPreferenceEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnSave.setOnClickListener(this);

        mUserPreference = new UserPreference(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("My User Preference");
        }
        showExistingPreference();

    }

    private void showExistingPreference() {
        if (!TextUtils.isEmpty(mUserPreference.getName())) {
            tvName.setText(mUserPreference.getName());
            tvAge.setText(String.valueOf(mUserPreference.getAge()));
            tvIsLoveMU.setText(mUserPreference.isLoveMU() ? "Ya" : "Tidak");
            tvEmail.setText(mUserPreference.getEmail());
            tvPhoneNo.setText(mUserPreference.getPhoneNumber());
            btnSave.setText(getString(R.string.ubah));
        } else {
            final String TEXT_EMPTY = "Tidak Ada";
            tvName.setText(TEXT_EMPTY);
            tvAge.setText(TEXT_EMPTY);
            tvIsLoveMU.setText(TEXT_EMPTY);
            tvEmail.setText(TEXT_EMPTY);
            tvPhoneNo.setText(TEXT_EMPTY);
            btnSave.setText(getString(R.string.save));
            isPreferenceEmpty = true;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            Intent intent = new Intent(MainActivity.this, FormUserPreferenceActivity.class);
            if (isPreferenceEmpty) {
                intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_ADD);
            } else {
                intent.putExtra(FormUserPreferenceActivity.EXTRA_TYPE_FORM, FormUserPreferenceActivity.TYPE_EDIT);
            }
            startActivityForResult(intent, FormUserPreferenceActivity.REQUEST_CODE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FormUserPreferenceActivity.REQUEST_CODE) {
            showExistingPreference();
        }
    }
}