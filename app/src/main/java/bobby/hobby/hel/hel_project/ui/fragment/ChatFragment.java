package bobby.hobby.hel.hel_project.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseChatFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.BaseChatAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.BaseChatAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.ChatText;
import bobby.hobby.hel.hel_project.ui.model.ChatMessage;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

/**
 * Description: Implementation of chat functionality from base class
 * Features:
 * - Creates viewholders for sent and received messages
 * - Handles user input for chat messaging
 */
public class ChatFragment extends BaseChatFragment<FragmentViewModel> {

    private List<ChatText> messages;
    private Boolean scrollAtBottom = false;
    private EditText message_edittext;


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModel.typedText = message_edittext.getText();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        message_edittext.setText(mViewModel.typedText);
        changeEditTextSize();
    }

    private void editTextClearFocus() {
        message_edittext.clearFocus();
        Util.hideKeyboard(getActivity(), message_edittext);
    }

    ;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);


        message_edittext = view.findViewById(R.id.message_edittext);
        message_edittext.setImeOptions(EditorInfo.IME_ACTION_SEND);
        message_edittext.setRawInputType(InputType.TYPE_CLASS_TEXT);


        /**
         * Recognises soft keyboard ime type to send message directly
         */
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

        /**
         * dirty temporary fix for scroll position
         */
        message_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollToBottom();
                    }
                }, 300);
            }
        });

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextClearFocus();
            }
        });

        message_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeEditTextSize();
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

        /**
         * to make sure chat wont scroll to bottom if you're browsing older messages while a message is received
         */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == recyclerView.getLayoutManager().getItemCount() - 1) {
                    scrollAtBottom = true;
                } else {
                    scrollAtBottom = false;
                }
            }
        });
        //messages = new ArrayList<>();
        //messages.add(new ChatMessage("sdafd", "you", "xx:xx"));

        //mViewModel.chatMessageList.setValue(messages);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.attachSocketClientTo(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    private void sendMessage(String text) {
        if (text.length() > 0) {
            mViewModel.addMessage(new ChatMessage(text, false, Util.getTime(), "self"));
            mViewModel.emitMessage(text);
            scrollToBottom();
        }
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
                editTextClearFocus();
            }
        });

        mViewModel.chatMessageListUpdated.observe(getActivity(), b -> {
            scrollToBottom();
        });

        mViewModel.chatMessageList.observe(getActivity(), data -> {
            adap.refreshData(data);
            if (scrollAtBottom) {
                scrollToBottom();
            }
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

    private class ReceivedMessageViewHolder extends BaseChatAdapterViewHolder<ChatText> {
        public TextView msg, time, sender;

        public ReceivedMessageViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            msg = itemView.findViewById(R.id.chat_message_received);
            time = itemView.findViewById(R.id.chat_timestamp_received);
            sender = itemView.findViewById(R.id.chat_message_sender);
        }

        @Override
        public void bindData(ChatText data) {
            this.msg.setText(((ChatMessage) data).getMessage());
            this.time.setText(((ChatMessage) data).getTime());
            this.sender.setText(((ChatMessage) data).getSender());
        }

    }

    private class SentMessageViewHolder extends BaseChatAdapterViewHolder<ChatText> {
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

    private void scrollToBottom() {
        if (adapter != null) {
            if (adapter.getItemCount() > 0) {
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            }
        }
    }

    /**
     * dynamically change message box layout according to line count
     */
    // TODO: optimise
    private void changeEditTextSize() {
        if (message_edittext.getLineCount() == 2) {
            message_edittext.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.chat_edittext_multirow_bg));
        } else if (message_edittext.getLineCount() > 2) {
            message_edittext.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.chat_edittext_multirow_bg2));
        } else {
            message_edittext.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.chat_edittext_bg));
        }
    }
}
