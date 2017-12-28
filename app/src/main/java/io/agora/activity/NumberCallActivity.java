package io.agora.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import io.agora.AgoraAPI;
import io.agora.AgoraAPIOnlySignal;
import io.agora.IAgoraAPI;
import io.agora.openduo.AGApplication;
import io.agora.openduo.R;
import io.agora.utils.Constant;

/**
 * "*" and "#" is useless
 */

public class NumberCallActivity extends AppCompatActivity {
    private  final String TAG = NumberCallActivity.class.getSimpleName();


    private String accountLocal ;
    private String mBeCallaccount ;
    private TextView mCallTitle;
    private EditText mEdittextNumber;
    private StringBuffer mCallNumberText = new StringBuffer("");

    private String channelName = "channelid";


    private AgoraAPIOnlySignal mAgoraAPI;
    private final int REQUEST_CODE = 0x01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        initAgoraEngineAndJoinChannel();

       initUI();
    }

    private void initUI() {

        Intent intent = getIntent();
        accountLocal = intent.getStringExtra("account");

        mCallTitle = (TextView) findViewById(R.id.meet_title);
        mCallTitle.setText("Your account ID is "+accountLocal);
        mEdittextNumber = (EditText) findViewById(R.id.call_number_edit);


    }



    public void CallClickInit(View v){
        switch (v.getId()){
            case R.id.call_number_delete:
                if (mCallNumberText.length() > 0) {
                    mCallNumberText.delete(mCallNumberText.length() - 1, mCallNumberText.length());
                    mEdittextNumber.setText(mCallNumberText);
                    mEdittextNumber.setSelection(mEdittextNumber.getText().toString().length());
                }
                break;

            case R.id.call_number_call: //number layout call out button

                if (!Constant.isFastlyClick()){
                    if (mEdittextNumber.getText().toString().equals(accountLocal)){
                        Toast.makeText(this,"could not call yourself" ,Toast.LENGTH_SHORT).show();
                    }else {

                        mBeCallaccount = mEdittextNumber.getText().toString();
                        //call out
                        mAgoraAPI.queryUserStatus(mEdittextNumber.getText().toString());
                    }
                }else {
                    Toast.makeText(this,"fast click" ,Toast.LENGTH_SHORT).show();
                }

                Log.i(TAG, " call number call init");

                break;
        }
    }


    /**
     * number click button
     * @param v
     */
    public void numberClick(View v){
        mCallNumberText.append(((Button)v).getText().toString());
        mEdittextNumber.setText(mCallNumberText);
        mEdittextNumber.setSelection(mEdittextNumber.getText().toString().length());
    }


    private void addCallback() {
        if (mAgoraAPI == null){
            return;
        }

        mAgoraAPI.callbackSet(new AgoraAPI.CallBack() {

            @Override
            public void onLogout(final int i) {
                Log.i(TAG ,"onLogout  i = " + i);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (i == IAgoraAPI.ECODE_LOGOUT_E_KICKED){ //other login the account
                            Toast.makeText(NumberCallActivity.this ,"Other login account ,you are logout." ,Toast.LENGTH_SHORT).show();

                        }else if (i == IAgoraAPI.ECODE_LOGOUT_E_NET){ //net
                            Toast.makeText(NumberCallActivity.this ,"Logout for Network can not be." ,Toast.LENGTH_SHORT).show();

                        }
                        finish();

                    }
                });

            }

            @Override
            public void onLoginFailed(int i) {
                Log.i(TAG ,"onLoginFailed  i = " + i);
            }

            @Override
            public void onInviteReceived(final String channelID, final String account,int uid, String s2) { //call out other remote receiver
                Log.i(TAG ,"onInviteReceived  channelID = " + channelID + "  account = " + account);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(NumberCallActivity.this ,CallActivity.class);
                        intent.putExtra("account" ,accountLocal);
                        intent.putExtra("channelName" ,channelID);
                        intent.putExtra("mBeCallaccount" ,account);
                        intent.putExtra("type" ,Constant.CALL_IN);
                        startActivityForResult(intent ,REQUEST_CODE);

                    }
                });
            }

            @Override
            public void onInviteReceivedByPeer(final String channelID, final String account, int uid) {//call out other local receiver
                Log.i(TAG ,"onInviteReceivedByPeer  channelID = " + channelID + "  account = " + account);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(NumberCallActivity.this ,CallActivity.class);
                        intent.putExtra("account" ,accountLocal);
                        intent.putExtra("channelName" ,channelID);
                        intent.putExtra("mBeCallaccount" ,account);
                        intent.putExtra("type" , Constant.CALL_OUT);
                        startActivityForResult(intent ,REQUEST_CODE);
                    }
                });

            }

            @Override
            public void onInviteFailed(String channelID, String account, int uid, int i1, String s2) {
                Log.i(TAG ,"onInviteFailed  channelID = " + channelID + "  account = " + account +" s2 :" + s2 +" i1:" + i1);

            }

            @Override
            public void onError(final String s, int i,final String s1) {

                Log.i(TAG ,"onError  s = " + s + " i = " + i + "  s1 = " + s1);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (s.equals("query_user_status") ){
                            Toast.makeText(NumberCallActivity.this , s1 ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onQueryUserStatusResult(final String name, final String status) {
                Log.i(TAG ,"onQueryUserStatusResult  name = " + name + "  status = " + status);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (status.equals("1")){
                            channelName = accountLocal + mBeCallaccount;
                            mAgoraAPI.channelInviteUser(channelName, mEdittextNumber.getText().toString(), 0);
                        }else if (status.equals("0")){
                            Toast.makeText(NumberCallActivity.this , name +" is offline "  ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        addCallback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG ,"onDestroy");
        mAgoraAPI.logout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG ,"onActivityResult requestCode:" + requestCode + "  resultCode:" + resultCode);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null && data.getStringExtra("result").equals("finish")){
                finish();
            }

        }
    }

    private void initAgoraEngineAndJoinChannel() {
        mAgoraAPI = AGApplication.the().getmAgoraAPI();
    }

}
