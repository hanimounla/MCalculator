package com.mounla.hani.mcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnLongClickListener
{
    public static final String MAIN_ACTIVITY = "MainActivity";

    EditText editText ;
    String inputExpression;
    int pos = 0 ;
    View ans = null , longBackspace = null;
    public void temp_click(View view)  {
        TextView t = (TextView) findViewById(R.id.temp);
        editText.setText(t.getText());

    }

    //region DEFAULTS
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(MAIN_ACTIVITY, "Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        ans = findViewById(R.id.equal);
        ans.setOnLongClickListener(this);
        longBackspace = findViewById(R.id.backspace);
        longBackspace.setOnLongClickListener(longBackspace(savedInstanceState));
        editText.setSelection(pos);

        try {
            Button about = (Button)findViewById(R.id.aboutBTN);
            about.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),About.class));
                }
            });
        } catch (Exception e) {

        }
    }
    public View.OnLongClickListener longBackspace(Bundle v)
    {
        editText.setText("");
        return null;
    }
    @Override
    public boolean onLongClick(View v)
    {

        inputExpression = editText.getText().toString();
        correctPosition("ans");
        return true;
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();  // Always call the superclass
        Log.d(MAIN_ACTIVITY,"Destroy");
        android.os.Debug.stopMethodTracing();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle presses on the action bar items
        switch (item.getItemId())
        {
            case R.id.about : startActivity(new Intent(this, About.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(MAIN_ACTIVITY, "Pause");
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(MAIN_ACTIVITY, "Resume");
    }
    //endregion

    //region NUMBERPANEL
    public void n0(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("0");
    }

    public void n1(View view){

        inputExpression = editText.getText().toString();
        correctPosition("1");
    }

    public void n2(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("2");
    }

    public void n3(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("3");
    }

    public void n4(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("4");
    }

    public void n5(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("5");
    }

    public void n6(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("6");
    }

    public void n7(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("7");
    }

    public void n8(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("8");

    }

    public void n9(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("9");
    }

    public void dot(View view) {

        inputExpression = editText.getText().toString();
        correctPosition(".");
    }
    //endregion

    //region OPERATIONS
    public void plus(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("+");
    }

    public void mines(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("-");
    }

    public void mul(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("×");
    }

    public void div(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("÷");
    }
    //endregion

    private void correctPosition(String s) {
        pos = editText.getSelectionStart();
        char [] insert = inputExpression.toCharArray();
        String toEnd = "" ,toPos = "";
        if(pos != 0)
            for (int i = 0; i < pos; i++)
                toPos += insert[i];
        for (int i = pos ; i <insert.length ; i++)
            toEnd += insert[i];
        toPos += s;
        pos += s.length();
        editText.setText(toPos + toEnd);
        editText.setSelection(pos);
        inputExpression = editText.getText().toString();
    }

    //region SCIENTIFIC
    public void sin(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("sin(");
    }

    public void cos(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("cos(");
    }

    public void tan(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("tan(");
    }

    public void log(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("log(");
    }


    public void ln(View view) {

        inputExpression = editText.getText().toString();
        char [] insert = inputExpression.toCharArray();
        String toEnd = "" ,toPos = "";
        if(pos != 0)
            for (int i = 0; i < pos; i++)
                toPos += insert[i];
        for (int i = pos ; i <insert.length ; i++)
            toEnd += insert[i];
        toPos += "ln(";
        pos += 3;
        editText.setText(toPos + toEnd);
        editText.setSelection(pos);
    }

    public void sqrt(View view) {

        inputExpression = editText.getText().toString();
        char [] insert = inputExpression.toCharArray();
        String toEnd = "" ,toPos = "";
        if(pos != 0)
            for (int i = 0; i < pos; i++)
                toPos += insert[i];
        for (int i = pos ; i <insert.length ; i++)
            toEnd += insert[i];
        toPos += "√(";
        pos += 2;
        editText.setText(toPos + toEnd);
        editText.setSelection(pos);
    }

    public void pow(View view) {

        inputExpression = editText.getText().toString();
        char [] insert = inputExpression.toCharArray();
        String toEnd = "" ,toPos = "";
        if(pos != 0)
            for (int i = 0; i < pos; i++)
                toPos += insert[i];
        for (int i = pos ; i <insert.length ; i++)
            toEnd += insert[i];
        toPos += "^(";
        pos += 2;
        editText.setText(toPos + toEnd);
        editText.setSelection(pos);
    }

    public void fact(View view) {

        inputExpression = editText.getText().toString();
        char [] insert = inputExpression.toCharArray();
        String toPos = "" , toEnd = "";
        if(pos != 0)
            for (int i = 0; i < pos ; i++)
                toPos += insert[i];
        for (int i = pos ; i <insert.length ; i++)
            toEnd +=insert[i];
        toPos += "!";
        pos++;
        editText.setText(toPos + toEnd);
        editText.setSelection(pos);
    }

    public void e(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("e");
    }

    public void percent(View view) {

        inputExpression = editText.getText().toString();
        correctPosition("%");
    }
    //endregion

    //region MAIN BUTTONS
    public void left(View view) {
        if(pos != 0)
        {
            pos = editText.getSelectionStart();
            pos--;
            editText.setSelection(pos);
        }
    }

    public void right(View view) {
        int lenght = editText.length();
        if(editText != null && pos < lenght )
        {
            pos = editText.getSelectionStart();
            pos++;
            editText.setSelection(pos);
        }
    }

    public void allClear(View view) {

        editText.setText("");
        TextView t = (TextView) findViewById(R.id.temp);
        t.setText("");
        pos = 0;
        //lynda();
    }

    public void brackets(View view) {

        inputExpression = editText.getText().toString();
        char [] equation = inputExpression.toCharArray();
        int explenght = equation.length;
        char correctbrackert = '(';
        int openbrkt = 0, closebrkt = 0, i = 0;
        while (i < explenght)
        {
            if (equation[i] == '(')
                openbrkt = i;
            if (equation[i] == ')')
                closebrkt = i;
            i++;
        }
        if (openbrkt > closebrkt)
            correctbrackert = ')';
        String toEnd = "" ,toPos = "";
        if(pos != 0)
            for (int j = 0; j < pos; j++)
                toPos += equation[j];
        for (int j = pos ; j <equation.length ; j++)
            toEnd += equation[j];
        toPos += correctbrackert;
        pos++;
        editText.setText(toPos + toEnd);
        editText.setSelection(pos);
    }

    public void backspace(View view) {

        inputExpression = editText.getText().toString();
        char [] insert = inputExpression.toCharArray();
        if(pos != 0)
        {
            String toPos = "", toEnd = "";
            for (int i = 0; i < pos; i++)
                toPos += insert[i];
            for (int i = pos; i < insert.length; i++)
                toEnd += insert[i];
            char delete[] = toPos.toCharArray();
            toPos = "";
            for (int i = 0; i < pos-1; i++)
                toPos += delete[i];
            pos--;
            editText.setText(toPos + toEnd);
            editText.setSelection(pos);
        }
    }

    public void equal(View view) {
        try
        {
            TextView temp = (TextView) findViewById(R.id.temp);

            inputExpression = editText.getText().toString();
            MathHelper m = new MathHelper();
            double num = m.evaluate(inputExpression);
            temp.setText(inputExpression + " =");
            String done;
            done = num + "";
            if (done.contains(".0") && done.lastIndexOf("0") == done.length() - 1)
                done =(long)num + "";
            editText.setText(done + "");
            pos = editText.length();
            editText.setSelection(pos);
        }
        catch (Exception e)
        {
            Toast t = Toast.makeText(this,"Syntax Error !", Toast.LENGTH_SHORT );
            t.show();
        } catch (Throwable throwable) {
            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void plot(View view) {
        inputExpression = editText.getText().toString();
        startActivity(new Intent(getApplication(),Plotting.class).putExtra("equation" , inputExpression));
    }

    public void xClick(View view) {
        inputExpression = editText.getText().toString();
        correctPosition("X");
    }

    //endregion




    //region Lynda
    /*public void lynda()
    {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification(android.R.drawable.stat_notify_more,
                "Clear Button was pressed !",System.currentTimeMillis());
        Context context = MainActivity.this;
        CharSequence title = "You Have Been Notified";
        CharSequence details = "Continue what you where doing";
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pending = PendingIntent.getActivity(context,0,intent,0);
        notify.setLatestEventInfo(context, title, details, pending);
        notify.sound = Uri.parse("android.resource://com.mounla.hani.mcalculator/" + R.raw.beep);
        nm.notify(0, notify);
    }*/


    //endregion
}
