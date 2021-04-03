package com.example.todo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class TodoDetailActivity extends AppCompatActivity {

    private int mTodoIndex;
    private String[] todoDetails;


    public static Intent newIntent(Context packageContext, int todoIndex){
        Intent intent = new Intent(packageContext, TodoDetailActivity.class);
        intent.putExtra(TODO_INDEX,todoIndex);
        return intent;
    }


    private static final String IS_TODO_COMPLETE = "com.example.isTodoComplete";


    private static final String TODO_INDEX = "com.example.todoIndex";


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, mTodoIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        if (savedInstanceState != null){
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
        }

        /* TODO: refactor to a data layer */
        Resources res = getResources();
        todoDetails = res.getStringArray(R.array.todo_detail);


        int mTodoIndex = getIntent().getIntExtra(TODO_INDEX, 0);
        updateTextViewTodoDetail(mTodoIndex);

        CheckBox checkboxIsComplete = (CheckBox)findViewById(R.id.checkBoxIsComplete);

        checkboxIsComplete.setOnClickListener(mTodoListener);

    }

    private void updateTextViewTodoDetail(int todoIndex) {

        final TextView textViewTodoDetail;
        textViewTodoDetail = (TextView) findViewById(R.id.textViewTodoDetail);


        textViewTodoDetail.setText(todoDetails[todoIndex]);

    }


    private OnClickListener mTodoListener = new OnClickListener() {
        public void onClick(View v) {

            switch (v.getId() ) {
                case R.id.checkBoxIsComplete:
                    CheckBox checkboxIsComplete = (CheckBox)findViewById(R.id.checkBoxIsComplete);
                    setIsComplete(checkboxIsComplete.isChecked());
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    private void setIsComplete(boolean isChecked) {


        if(isChecked){
            Toast.makeText(TodoDetailActivity.this,
                    "Hurray, it's done!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(TodoDetailActivity.this,
                    "There is always tomorrow!", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent();
        intent.putExtra(IS_TODO_COMPLETE, isChecked);
        setResult(RESULT_OK, intent);
    }

}

