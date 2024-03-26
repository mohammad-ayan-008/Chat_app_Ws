
package com.stream.wassup;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.input.InputManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.stream.wassup.databinding.ActivityMainBinding;
import com.stream.wassup.recycler_items.*;
import com.stream.wassup.recycler_items.recycler_adapter;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private RecyclerView rview;
    private List<ChatData> data= new ArrayList<>();
    private recycler_adapter adapter;
    private WebSocket socket;
    
    public WebSocketListener listen = new WebSocketListener(){
        
        @Override
        public void onOpen(WebSocket arg0, Response arg1) {
            super.onOpen(arg0, arg1);
            // TODO: Implement this method
             runOnUiThread(()->{
            Toast.makeText(getApplicationContext(),"Connected",0).show();
                    });
        }
        
        @Override
        public void onMessage(WebSocket arg0, String arg1) {
            super.onMessage(arg0, arg1);
            // TODO: Implement this method
            runOnUiThread(()->{
                data.add(new ChatData(arg1,1));
                adapter.notifyDataSetChanged();
            });
             
        }
        
        @Override
        public void onFailure(WebSocket arg0, Throwable arg1, Response arg2) {
            super.onFailure(arg0, arg1, arg2);
            // TODO: Implement this method
            runOnUiThread(()->{
              Toast.makeText(getApplicationContext(),arg1.getLocalizedMessage(),0).show();
            });
        }
        
        
        
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup_socket();
        data.add(new ChatData("hello",1));//sender
        data.add(new ChatData("heyy",2));//receiver
        
          
        data.add(new ChatData("whats your name  brooooo",1));//sender
        data.add(new ChatData("my name is Ayan brooooooo",2));//receiver
        
         
        // Inflate and get instance of binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        rview= binding.chatView;
        adapter = new recycler_adapter(data);
        rview.setLayoutManager(new LinearLayoutManager(this));
        rview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // set content view to binding's root
        setContentView(binding.getRoot());
        
        binding.send.setOnClickListener((V)->{
            String text = binding.textToSend.getText().toString();
           if(!text.isEmpty()){
             data.add(new ChatData(text,2));
             adapter.notifyDataSetChanged();       
             binding.textToSend.setText("");
             if(socket!=null) {    
                socket.send(text);
             }
           //  binding.textToSend.clearFocus();      
            InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                   
           }
        });
        
        
        
    }
    
    
    
    public void setup_socket(){
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url("ws:/100.94.187.227:3000").build();
        socket = client.newWebSocket(req,listen);
    }
    
    
    
    
    
    
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
