package com.ammar.socialpocketa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ammar.socialpocketa.api.APIService;
import com.ammar.socialpocketa.api.APIUrl;
import com.ammar.socialpocketa.data.SharedPrefManager;
import com.ammar.socialpocketa.models.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    TextView linkSignin;
    EditText txtName, txtEmail, txtPassword, txtConPassword;

    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        linkSignin = findViewById(R.id.link_signin);
        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        txtConPassword = findViewById(R.id.txt_confirm_pwd);
        btnSignup = findViewById(R.id.btn_signup);

        linkSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtName.getText().toString().trim().isEmpty()) {
                    txtName.setError("This field is required");
                } else if (txtEmail.getText().toString().trim().isEmpty()) {
                    txtEmail.setError("This filed is required");
                } else if (!isValidEmail(txtEmail.getText().toString().trim())) {
                    txtEmail.setError("Not a valid email");
                } else if (txtPassword.getText().toString().trim().isEmpty()) {
                    txtPassword.setError("This field is required");
                } else if (txtConPassword.getText().toString().trim().isEmpty()) {
                    txtConPassword.setError("This field is required");
                } else if (txtPassword.getText().toString().trim().compareTo(txtConPassword.getText().toString().trim()) != 0) {
                    txtConPassword.setError("Not matched with the Password");
                } else if (txtPassword.getText().toString().trim().length() < 8) {
                    txtPassword.setError("Password must include 8 characters!");
                }

                else {
                    Snackbar.make(v, "No validation errors, continue signup", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    userSignUp();

                }
            }

        });

    }


    public final static boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }



    private void userSignUp() {

        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        String name = txtName.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        //String confirmPassword = txtConPassword.getText().toString().trim();

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the Register object as we need to pass it with the call
        Register user = new Register(name, email, password);

        //defining the call
        Call<Register> call = service.createUser(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPassword()
        );

        //calling the api
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                //hiding progress dialog
                progressDialog.dismiss();

                String resMsg = response.message();
                if(resMsg.equals("Bad Request")) {

                    Toast.makeText(getApplicationContext(), "This email has already been used in an existing account", Toast.LENGTH_LONG).show();

                }

                //if there is no error
                //displaying the message from the response as toast
                else if(resMsg.equals("OK")) {
                    Toast.makeText(getApplicationContext(), "An email verification has been sent to you. Please verify your Email First", Toast.LENGTH_LONG).show();

                    //starting profile activity
//                    finish();
                    SharedPrefManager.getInstance(getApplicationContext()).userRegister(response.body().getId(), response.body().getName(), response.body().getEmail());
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    finish();

                }

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }

        });

    }

}
