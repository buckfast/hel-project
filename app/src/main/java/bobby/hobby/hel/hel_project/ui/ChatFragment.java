package bobby.hobby.hel.hel_project.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseChatFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseChatAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseChatAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.ChatText;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.ui.model.ChatMessage;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

public class ChatFragment extends BaseChatFragment<FragmentViewModel>{

    private List<ChatText> messages;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        EditText message_edittext = view.findViewById(R.id.message_edittext);
        message_edittext.setImeOptions(EditorInfo.IME_ACTION_SEND);
        message_edittext.setRawInputType(InputType.TYPE_CLASS_TEXT);
        message_edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    sendMessage(message_edittext.getText().toString());
                    message_edittext.setText("");
                    return true;
                }
                return false;
            }
        });
        message_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // TODO: hehe
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (message_edittext.getLineCount() == 2) {
                    message_edittext.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.chat_edittext_multirow_bg));
                } else if (message_edittext.getLineCount() > 2) {
                    message_edittext.setBackground(ContextCompat.getDrawable(getActivity(),R.drawable.chat_edittext_multirow_bg2));
                } else {
                    message_edittext.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.chat_edittext_bg));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ImageButton sendButton = view.findViewById(R.id.message_send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(message_edittext.getText().toString());
                message_edittext.setText("");
            }
        });

        //messages = new ArrayList<>();
        //messages.add(new ChatMessage("sdafd", "you", "xx:xx"));

        //mViewModel.chatMessageList.setValue(messages);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    private void sendMessage(String text) {
        mViewModel.addMessage(new ChatMessage(text, false, "xx:xx"));
        recyclerView.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    protected int returnRecyclerViewId() {
        return R.id.chat_recyclerview;
    }

    @Override
    protected BaseChatAdapter returnAdapter() {
        ChatAdapter adap = new ChatAdapter(new OnAdapterItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Log.d("asd", "kliketiklak");
            }
        });

        mViewModel.chatMessageList.observe(this, data ->{
            adap.refreshData(data);
        });

        return adap;
    }

    @Override
    protected Class<FragmentViewModel> returnViewModel() {
        return FragmentViewModel.class;
    }

    private class ChatAdapter extends BaseChatAdapter {

        public ChatAdapter(OnAdapterItemClickListener listener) {
            super(listener);
        }

        @Override
        public int returnCurrentUserMessageLayoutId() {
            return R.layout.chat_message_sent_item;
        }

        @Override
        public int returnOtherUserMessageLayoutId() {
            return R.layout.chat_message_received_item;
        }

        @Override
        public BaseChatAdapterViewHolder returnCurrentUserMessageViewHolderInstance(View view, OnAdapterItemClickListener listener) {
            return new SentMessageViewHolder(view, listener);
        }

        @Override
        public BaseChatAdapterViewHolder returnOtherUserMessageViewHolderInstance(View view, OnAdapterItemClickListener listener) {
            return new ReceivedMessageViewHolder(view, listener);
        }
    }

    private class ReceivedMessageViewHolder extends BaseChatAdapterViewHolder {
        public TextView msg, time;

        public ReceivedMessageViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            msg = itemView.findViewById(R.id.chat_message_received);
            time = itemView.findViewById(R.id.chat_timestamp_received);
        }

        @Override
        public void bindData(ChatText data) {
            this.msg.setText(((ChatMessage) data).getMessage());
            this.time.setText(((ChatMessage) data).getTime());
        }

    }

    private class SentMessageViewHolder extends BaseChatAdapterViewHolder {
        public TextView message, timestamp;

        public SentMessageViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            message = itemView.findViewById(R.id.chat_message);
            timestamp = itemView.findViewById(R.id.chat_timestamp);
        }

        @Override
        public void bindData(ChatText data) {
            this.message.setText(((ChatMessage) data).getMessage());
            this.timestamp.setText(((ChatMessage) data).getTime());
        }
    }
}
