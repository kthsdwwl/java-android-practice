package com.mini2assignment3b.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mini2assignment3b.R;
import com.mini2assignment3b.exception.MyException;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class MailFragment extends Fragment {

    private Button sendBtn;
    private EditText textSubject;
    private EditText textContent;

    public MailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail, container, false);
        getElements(view);
        addListeners();
        return view;
    }

    /**
     * Get all the ui objects
     * @param view
     */
    private void getElements(View view) {
        sendBtn = (Button) view.findViewById(R.id.send_mail_btn);
        textSubject = (EditText) view.findViewById(R.id.edit_subject);
        textContent = (EditText) view.findViewById(R.id.edit_content);
    }

    /**
     * Add listeners to ui objects
     */
    private void addListeners() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    /**
     * Send mail to a specific address
     */
    private void sendMail() {
        String subject;
        String content;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        try {
            subject = textSubject.getText().toString();
            content = textContent.getText().toString();
            if (subject.equals("") || content.equals(""))
                throw new MyException();
        } catch (MyException e) {
            new MyException().handle();
            showError();
            return;
        }
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kthsdwwl@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    /**
     * Display error dialog
     */
    private void showError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Error");
        builder.setMessage("Subject and content should not be empty.");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
