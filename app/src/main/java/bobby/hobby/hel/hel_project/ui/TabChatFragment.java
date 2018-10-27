package bobby.hobby.hel.hel_project.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseTabChildFragment;
import bobby.hobby.hel.hel_project.ui.model.ChatMessage;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;


public class TabChatFragment extends BaseTabChildFragment<FragmentViewModel>{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //private RecyclerView recyclerView;
    private List<ChatMessage> messages;
    //private RecyclerView.Adapter adapter;
    //private RecyclerView.LayoutManager layoutManager;

    public TabChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabChatFragment newInstance(String param1, String param2) {
        TabChatFragment fragment = new TabChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<FragmentViewModel> returnFragmentsViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        //mFragmentsViewModel.attachSocketClientTo(getActivity());
        //mFragmentsViewModel.emitTest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ChatFragment chatFragment = new ChatFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.chat_fragment_container, chatFragment);
        transaction.commit();

        /*EditText message_edittext = view.findViewById(R.id.message_edittext);
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

        messages = new ArrayList<>();
        messages.add(new ChatMessage("hello hello nice nice", "you", "12:43"));
        messages.add(new ChatMessage("hello hello nice nicehello hello nice nicehello hello nice nicehello hello nice nicehello hello nice nicehello hello nice nicehello hello nice nicehello hello nice nice", "you", "12:43"));
        messages.add(new ChatMessage("hello hello nice nicehello hello nice nicehello hello nice nice", "you", "12:43"));
        messages.add(new ChatMessage("hello hello nice nicehello hello nice nicehello hello nice nicehello hello nice nicehello hello nice nice", "you", "12:43"));
        messages.add(new ChatMessage("hello hello nice nice", "you", "12:43"));
        messages.add(new ChatMessage("hello hello nice nicehello hello nice nicehello nice", "you", "12:43"));
        messages.add(new ChatMessage("hello hello nice nicehello hello nice nicehello hello nice nice", "you", "12:43"));
        messages.add(new ChatMessage("hello hello nice nicehello hello nice nicehello hello nice nicehello hello nice nicehello hello nice nice", "you", "12:43"));


        recyclerView = view.findViewById(R.id.chat_recyclerview);
        recyclerView.setHasFixedSize(true);

        adapter = new MessageListAdapter(getContext(), messages);
        mFragmentsViewModel.chatMessageList.observe(this, data ->{
            ((MessageListAdapter)adapter).refreshData(data);
            Log.d("asd","size"+ String.valueOf(mFragmentsViewModel.chatMessageList.getValue().size()));
        });
        recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        mFragmentsViewModel.chatMessageList.setValue(messages);
        */
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }




    /*
    private class MessageListAdapter extends RecyclerView.Adapter {
        private static final int MESSAGE_SENT = 0;
        private static final int MESSAGE_RECEIVED = 1;
        private Context context;
        private List<ChatMessage> messageList;

        public MessageListAdapter(Context context, List<ChatMessage> messageList) {
            this.context = context;
            this.messageList = messageList;
        }

        public void refreshData(List<ChatMessage> newData) {
            Log.d("asd", "chat refres");
            //jooh
            messageList = newData;
            notifyDataSetChanged();
        }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;

            if (viewType == MESSAGE_SENT) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_sent_item, parent, false);
                return new MessageSentViewHolder(view);
            }
            return null;
        }

        @Override
        public int getItemViewType(int position) {
            ChatMessage msg = messageList.get(position);

            if (msg.getUser() == "you") {
                return MESSAGE_SENT;
            } else {
                return MESSAGE_RECEIVED;
            }
        }

        public class MessageSentViewHolder extends RecyclerView.ViewHolder {
            public TextView message, timestamp;
            public RecyclerView.Adapter adapter;


            public MessageSentViewHolder(View view) {
                super(view);
                this.message = view.findViewById(R.id.chat_message);
                this.timestamp = view.findViewById(R.id.chat_timestamp);
            }

            void bind(ChatMessage message) {
                this.message.setText(message.getMessage());
                this.timestamp.setText(message.getTime());
            }


        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ChatMessage msg = messageList.get(position);
            if (holder.getItemViewType() == MESSAGE_SENT) {
                ((MessageSentViewHolder) holder).bind(msg);
            }
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }
    }*/
}
