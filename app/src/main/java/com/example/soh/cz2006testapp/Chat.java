/**
 * Chat screen between buyer and seller.
 */

package com.example.soh.cz2006testapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity {

    private FirebaseListAdapter<Chat_Message> adapter;
    RelativeLayout activity_chat;
    FloatingActionButton send;
    private DatabaseReference mDatabase,mDatabaseUser,chatRef;
    private FirebaseUser mUser;
    private FirebaseAuth ref;

    String chatID=null;
    String targetUid = null;

    /**
     * Creates the view using the activity_chat xml.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        activity_chat = (RelativeLayout) findViewById(R.id.activity_chat);
        send = (FloatingActionButton) findViewById(R.id.fab_chat);
        ref = FirebaseAuth.getInstance();
        mUser = ref.getCurrentUser();
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid()).child("Chats");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Chats");
        targetUid = getIntent().getExtras().getString("targetUid");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.input);
                if (chatID==null){
                    final String targetDisplayName = getIntent().getExtras().getString("targetDisplayName");

                    chatRef = mDatabase.push();
                    chatID = chatRef.getKey();
                    mDatabaseUser.child(targetUid).child("ChatID").setValue(chatID);
                    mDatabaseUser.child(targetUid).child("DisplayName").setValue(targetDisplayName);

                    DatabaseReference targetUser = FirebaseDatabase.getInstance().getReference().child("Users").child(targetUid).child("Chats");
                    targetUser.child(mUser.getUid()).child("ChatID").setValue(chatID);
                    targetUser.child(mUser.getUid()).child("DisplayName").setValue(mUser.getDisplayName());

                    chatRef.push().setValue(new Chat_Message(input.getText().toString().trim(),
                            FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
                    input.setText("");
                    displayChatMessage();
                }
                else {
                    mDatabase.child(chatID).push().setValue(new Chat_Message(input.getText().toString().trim(),
                            FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
                    input.setText("");
                    displayChatMessage();
                }
            }
        });

        mDatabaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(targetUid!=null){
                    if (dataSnapshot.child(targetUid).exists()){
                        chatID = dataSnapshot.child(targetUid).child("ChatID").getValue().toString();
                        chatRef = mDatabase.child(chatID);
                        displayChatMessage();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Display chat messages between buyer and seller.
     */

    private void displayChatMessage() {
        ListView messageList = (ListView) findViewById(R.id.list_view_message);
        adapter = new FirebaseListAdapter<Chat_Message>(this,Chat_Message.class,R.layout.chat_list_item,chatRef) {
            @Override
            protected void populateView(View v, Chat_Message model, int position) {
                TextView messageText,messageUser,messageTime;
                messageText = (TextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",model.getMessageTime()));
            }
        };
        messageList.setAdapter(adapter);
    }

}
