package com.example.v_vend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public String mParam1;
    public String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }
    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;

    public ImageButton closeButton;
    private Button signUpButton;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String emailPattern = "^[a-zA-Z0-9._]+[@][a-zA-Z0-9]+[.][a-zA-Z]{2,3}$";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        alreadyHaveAnAccount=view.findViewById(R.id.AlreadyHaveAccount_signin);
        parentFrameLayout=view.findViewById(R.id.register_FrameLayout);
        email=view.findViewById(R.id.Sign_Up_Email);
        fullName=view.findViewById(R.id.Sign_Up_FullName);
        password=view.findViewById(R.id.Sign_Up_Password);
        confirmPassword=view.findViewById(R.id.Sign_Up_ConfirmPassword);
        closeButton=view.findViewById(R.id.Sign_Up_CloseButton);
        signUpButton=view.findViewById(R.id.SignUp_Button);
        progressBar=view.findViewById(R.id.SignUp_Progressbar);
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SigninFragment());
            }
        });


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: send data to firebase
                checkEmailAndPassword();


            }
        });
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs(){
        Boolean emailEmpty=email.getText().toString().isEmpty();
        if(emailEmpty)
        {
            Toast.makeText(getActivity(),"Email is empty",Toast.LENGTH_LONG).show();

        }
//        else
//        {
//            Toast.makeText(getActivity(),"Email is not empty"+email.getText().toString(),Toast.LENGTH_LONG).show();
//
//        }

//        if(!TextUtils.isEmpty(email.getText()))
//        {
//            Toast.makeText(getActivity(), "INSIDE condtion",Toast.LENGTH_LONG).show();
//
//            signUpButton.setEnabled(true);
//            signUpButton.setTextColor(Color.rgb(255, 255, 255));
//        }
       /* if(!TextUtils.isEmpty(email.getText())) {
            Toast.makeText(getActivity(), email.getText(), Toast.LENGTH_SHORT).show();
            if (TextUtils.isEmpty(fullName.getText())) {
                if (TextUtils.isEmpty(password.getText()) && password.length() >= 8) {
                    if (TextUtils.isEmpty(confirmPassword.getText())) {
                        signUpButton.setEnabled(true);
                        signUpButton.setTextColor(Color.rgb(255, 255, 255));
                    } else {
                        signUpButton.setEnabled(false);
                        signUpButton.setTextColor(Color.argb(50, 255, 255,255));
                    }

                } else {
                    signUpButton.setEnabled(false);
                    signUpButton.setTextColor(Color.argb(50, 255, 255,255));
                }
            } else {
                signUpButton.setEnabled(false);
                signUpButton.setTextColor(Color.argb(50, 255, 255,255));
            }
        }
        else{

                signUpButton.setEnabled(false);
                signUpButton.setTextColor(Color.argb(50, 255, 255,255));
            }*/

        }
    private void checkEmailAndPassword() {

        @SuppressLint("UseCompatLoadingForDrawables")

        Drawable customErrorIcon= getResources().getDrawable(R.mipmap.error_icon);
        customErrorIcon.setBounds(0,0,customErrorIcon.getIntrinsicWidth(),customErrorIcon.getIntrinsicHeight());
        if (email.getText().toString().matches(emailPattern)) {
            if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                progressBar.setVisibility(View.VISIBLE);
                signUpButton.setEnabled(true);
                signUpButton.setTextColor(Color.argb(50, 225, 255,255));
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Map<Object,String> userdata=new HashMap<>();
                                    userdata.put("Fullname",fullName.getText().toString());

                                    firebaseFirestore.collection("USERS")
                                            .add(userdata)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                                    if(task.isSuccessful()){
                                                        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                                                        startActivity(mainIntent);
                                                        getActivity().finish();
                                                    }else{
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        signUpButton.setEnabled(true);
                                                        signUpButton.setTextColor(Color.rgb( 255, 255,255));
                                                        String error = task.getException().getMessage();
                                                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });


                                } else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    signUpButton.setEnabled(true);
                                    signUpButton.setTextColor(Color.rgb( 255, 255,255));
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                confirmPassword.setError("Password Doesn't Matched",customErrorIcon);
            }
        } else {
            email.setError("Invalid Email ",customErrorIcon);
        }


    }
}