package com.thesaugat.studentfinance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.thesaugat.studentfinance.R;
import com.thesaugat.studentfinance.helper.Constants;
import com.thesaugat.studentfinance.server.ApiClient;
import com.thesaugat.studentfinance.server.ApiServices;
import com.thesaugat.studentfinance.server.ServerRequest;
import com.thesaugat.studentfinance.server.ServerResponse;
import com.thesaugat.studentfinance.server.UserPojo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener {


    Validator validator;
    @NotEmpty
    @BindView(R.id.input_name)
    EditText inputName;

    @NotEmpty
    @BindView(R.id.input_symbol_no)
    EditText inputSymbolNo;


    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    @BindView(R.id.input_password)
    EditText inputPassword;

    @ConfirmPassword
    @BindView(R.id.input_reEnterPassword)
    EditText inputReEnterPassword;

    @BindView(R.id.btn_signup)
    AppCompatButton btnSignup;

    @BindView(R.id.link_login)
    TextView linkLogin;

    @Email
    @NotEmpty
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.progress)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }


    @Override
    public void onValidationSucceeded() {
        final UserPojo user = new UserPojo();
        user.setEmail(inputEmail.getText().toString());
        user.setPassword(inputPassword.getText().toString());
        user.setName(inputName.getText().toString());
        user.setSymbolno(inputSymbolNo.getText().toString());
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
        request.setUser(user);
        ApiServices apiServices = ApiClient.getClient().create(ApiServices.class);
        Call<ServerResponse> call = apiServices.LoginSignUpRequest(request);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                progress.setVisibility(View.GONE);

                if (response.body().getResult().equals(Constants.SUCCESS)) {
                    Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    goToLogin();

                } else {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });

    }

    private void goToLogin() {
        finish();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, "Check Your details", Toast.LENGTH_LONG).show();
            }
        }

    }

    @OnClick({R.id.btn_signup, R.id.link_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                try {
                    InputMethodManager imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
                progress.setVisibility(View.VISIBLE);
                validator.validate();
                break;
            case R.id.link_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


}
