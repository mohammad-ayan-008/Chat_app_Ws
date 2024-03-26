package com.stream.wassup.recycler_items;

import android.widget.TextView;
import com.stream.wassup.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.stream.wassup.databinding.LeftChatBinding;
import com.stream.wassup.databinding.RightChatBinding;
import com.stream.wassup.recycler_items.recycler_adapter;
import java.util.List;

public class recycler_adapter extends RecyclerView.Adapter {
    List<ChatData> data;

    public recycler_adapter(List<ChatData> data) {
        this.data = data;
    }

    class Holder_sender extends RecyclerView.ViewHolder {
        TextView text_s;

        public Holder_sender(LeftChatBinding v) {
            super(v.getRoot());
            text_s = v.TextOther;
        }
    }

    class Holder_recieve extends RecyclerView.ViewHolder {
        TextView text_r;

        public Holder_recieve(RightChatBinding v) {
            super(v.getRoot());
            text_r = v.TextMe;
        }
    }
    
    
     @Override
     public int getItemViewType(int arg0) {
         // TODO: Implement this method
          switch(data.get(arg0).getTYPE()){
              case 1:
                return 1;
                
              case 2:
                 return 2;
               
          }
         return super.getItemViewType(arg0);
     }
     

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        if (arg1== 1) {
            // View view =
            // LayoutInflater.from(arg0.getContext()).inflate(R.layout.left_chat,arg0,false);
            return new recycler_adapter.Holder_sender(
                    LeftChatBinding.inflate(LayoutInflater.from(arg0.getContext())));
        } else {
            // View view =
            // LayoutInflater.from(arg0.getContext()).inflate(R.layout.right_chat,arg0,false);
            return new recycler_adapter.Holder_recieve(
                    RightChatBinding.inflate(LayoutInflater.from(arg0.getContext())));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder arg0, int arg1) {
        ChatData chats = data.get(arg1);
        if (chats.getTYPE() == 1) {
            ((recycler_adapter.Holder_sender) arg0).text_s.setText(chats.getData());
        } else {
           ((recycler_adapter.Holder_recieve) arg0).text_r.setText(chats.getData());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
