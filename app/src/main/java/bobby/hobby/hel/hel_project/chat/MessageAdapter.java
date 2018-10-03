package bobby.hobby.hel.hel_project.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bobby.hobby.hel.hel_project.R;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> messages;

    public MessageAdapter(Context context, List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = -1;
        switch (viewType) {
            case 0: //message
                layout = R.layout.item_message;
                break;
            case 1: //info
                layout = R.layout.item_info;
                break;
            case 2: //typing
                layout = R.layout.item_typing;
                break;
            case 3: //message self
                layout = R.layout.item_message_self;
                break;
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Message message = messages.get(position);
        viewHolder.setMessage(message.getMessage());
        viewHolder.setUsername(message.getUsername());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getType().ordinal();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView usernameView;
        private TextView messageView;

        public ViewHolder(View itemView) {
            super(itemView);

            usernameView = itemView.findViewById(R.id.username);
            messageView = itemView.findViewById(R.id.message);
        }

        public void setUsername(String username) {
            if (usernameView == null) return;
            usernameView.setText(username);
            //color maybe
        }

        public void setMessage(String message) {
            if (messageView == null) return;
            messageView.setText(message);
        }
    }
}
