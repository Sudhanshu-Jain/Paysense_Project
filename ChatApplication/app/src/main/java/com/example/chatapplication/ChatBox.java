package com.example.chatapplication;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatBox extends AppCompatActivity {

    private ListView list;
    // private ListView serverList;
    private EditText chatText;
    private Button send;
    private CharArrayAdapter adp;
    ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        chatText = (EditText)findViewById(R.id.chat);
        send = (Button) findViewById(R.id.btn);
        //send.setText(getResources().getText(R.string.a_send));
        list = (ListView) findViewById(R.id.listView1);
        adp = new CharArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, messages);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  isMe = true;
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Calendar calobj = Calendar.getInstance();
//            // System.out.println(df.format(calobj.getTime()));
                CharSequence text = df.format(calobj.getTime());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                Date convertedDate = new Date();
                try {
                    convertedDate = dateFormat.parse(text.toString());
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                  sendChatMessage(convertedDate);
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.execute(Utils.loadPrefs(getApplicationContext(),"username"),getMessage(),Utils.loadPrefs(getApplicationContext(),"recieverName"));

                //  new JSONTASK4().execute(url5);
// chatText.setText("");
            }
        });
        adp.notifyDataSetChanged();
        list.setAdapter(adp);

        adp.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                list.setSelection(adp.getCount() - 1);
            }
        });
    }

    public String getMessage(){
        String message = chatText.getText().toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatText.setText("");
            }
        });
        return message;

    }

    private boolean sendChatMessage(Date date) {

//

        Message message = new Message(Utils.loadPrefs(getApplicationContext(),"username"), chatText.getText().toString(), Utils.loadPrefs(getApplicationContext(),"recieverName"), date);
        adp.add(message);

//        TextView date = (TextView)findViewById(R.id.Date);
//        date.setText(message.getDateTime());
        // if(getRecieverName()==MainActivity1.bob)


        // chatText.setText("");
        // List<ChatMessage> chat = adp.getMessageList();


        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat_box, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
