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
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.thesaugat.studentfinance.HomeActivity;
import com.thesaugat.studentfinance.R;
import com.thesaugat.studentfinance.helper.Constants;
import com.thesaugat.studentfinance.server.ApiClient;
import com.thesaugat.studentfinance.server.ApiServices;
import com.thesaugat.studentfinance.server.ServerRequest;
import com.thesaugat.studentfinance.server.ServerResponse;
import com.thesaugat.studentfinance.server.UserPojo;
import com.thesaugat.studentfinance.utils.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {


    Validator validator;
    @NotEmpty
    @Email
    @BindView(R.id.input_email)
    EditText email;

    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    @BindView(R.id.input_password)
    EditText inputPassword;

    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;

    @BindView(R.id.link_signup)
    TextView linkSignup;
    @BindView(R.id.progress)
    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {

        final UserPojo user = new UserPojo();
        user.setEmail(email.getText().toString());
        user.setPassword(inputPassword.getText().toString());
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        ApiServices apiServices = ApiClient.getClient().create(ApiServices.class);
        Call<ServerResponse> call = apiServices.LoginSignUpRequest(request);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                progress.setVisibility(View.GONE);

                System.out.println("111111111111" + response.body());

                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if (response.body().getResult().equals(Constants.SUCCESS)) {
                    UserPojo userPojo = response.body().getUser();
                    SharedPreferencesUtils.setStringPreference(LoginActivity.this, Constants.NAME, userPojo.getName());
                    SharedPreferencesUtils.setStringPreference(LoginActivity.this, Constants.EMAIL, userPojo.getEmail());
                    SharedPreferencesUtils.setStringPreference(LoginActivity.this, Constants.SYMBOLNO, userPojo.getSymbolno());
                    SharedPreferencesUtils.setStringPreference(LoginActivity.this, Constants.UNIQUEID, userPojo.getUniqueid());
                    SharedPreferencesUtils.setBooleanPreference(LoginActivity.this, Constants.ISLOGGEDIN, true);
                    goToHome();

                } else {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);

            }
        });


    }

    private void goToHome() {

        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
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
                Toast.makeText(this, "Wrong Credinteials", Toast.LENGTH_LONG).show();
            }
        }
    }


    @OnClick({R.id.btn_login, R.id.link_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                try {
                    InputMethodManager imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
                progress.setVisibility(View.VISIBLE);
                validator.validate();
                break;
            case R.id.link_signup:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
