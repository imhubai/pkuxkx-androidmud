package hubai.pkuxkx.mud;

import static hubai.pkuxkx.mud.colorizer.*;
import static hubai.pkuxkx.mud.PreferencesUtil.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class mudMain extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Activity mudActivity;
    private mudSocket mudsocket;
    private String ip;
    private int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mudActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudmain);
        //设置状态栏黑色
        Window window = mudActivity.getWindow();
        window.setStatusBarColor(Color.BLACK);

        //获取ip port
        String ipALL = this.getIntent().getStringExtra("ip");
        ip = ipALL.substring(0, ipALL.indexOf(":"));
        String temp_port = ipALL.substring(ipALL.indexOf(":") + 1);
        port = Integer.parseInt(temp_port);

        //初始化连接
            mudSocket_Init();

        EditText outputedit = findViewById(R.id.outputEdit);
        outputedit.setHorizontallyScrolling(true);
        Button btn_sendcmd = findViewById(R.id.btn_sendcmd);
        Button btn_settings = findViewById(R.id.btn_settings);
        EditText et_command = findViewById(R.id.CommandEdit);

        //发送命令按钮
        btn_sendcmd.setOnClickListener(v -> send_Cmd(et_command.getText().toString())
        );
        //设置按钮
        btn_settings.setOnClickListener(v -> {
                 //   Intent intent = new Intent(this, mudSetting.class);
                   // startActivity(intent);
                }
        );
        //第一次启动时记录密码
        if (getPre(this, "isfirst", true)) {
            setPre(this, "isfirst", false);

        }
    }

    public void AddLine(String msg) {
        runOnUiThread(() -> {
            TextView output = findViewById(R.id.outputEdit);
            output.append(spantxt(msg));
            output.scrollTo(0, Math.max(output.getLayout().getLineTop(output.getLineCount()) - output.getHeight(), 0));
        });
    }

    public void mudSocket_Init() {
        mudsocket = new mudSocket();
        new Thread(() -> {
            if (mudsocket.connect(ip, port)) {
                rcvMsgHandler();
                if (mudsocket.serverStatus != -1) {
                    //成功连接后销毁登录页
                    pkuxkxLogin.LoginActivity.finish();
                    AddLine("\u001B[1;33m北大侠客行 - Pkuxkx欢迎游玩\n\u001B[30m");
                }
            }
        }).start();
    }

    public void rcvMsgHandler() {
        new Thread(() -> {
            while (true) {
                if (mudsocket.serverStatus != -1) {
                    String msg = mudsocket.rcvMsg();
                    if (msg != null) {
                        //操作显示
                        AddLine(msg);
                    }
                } else {
                    AddLine("\n\u001B[1;31m服务器连接断开");

                    break;


                }
            }
        }).start();
    }

    public void send_Cmd(String msg) {
        if (mudsocket.serverStatus != -1) {
            //替换多指令
            if (msg.contains(";")) {
                msg = msg.replaceAll(";", "\r\n");
            }
            String finalMsg = msg;
            new Thread(() -> mudsocket.sendMsg(finalMsg + "\r\n")).start();
        } else {
            //serverStatus -1
            AddLine("\n\u001B[1;31m连接已断开，命令发送失败");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //exit
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            builder.setMessage("确认退出游戏吗？");
            builder.setTitle("少侠！");
            builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
            builder.setPositiveButton("确认", (dialog, which) -> {
                dialog.dismiss();
                mudsocket.disconnect();
                finish();
            });
            builder.create().show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
