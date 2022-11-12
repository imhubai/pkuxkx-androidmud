package hubai.pkuxkx.mud;

import static hubai.pkuxkx.mud.PreferencesUtil.*;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class pkuxkxLogin extends AppCompatActivity {

    Spinner spinnerItems;
    EditText ipedit;

    @SuppressLint("StaticFieldLeak")
    public static Activity LoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoginActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkuxkx_login);

        //test
        Window window = LoginActivity.getWindow();
        window.setStatusBarColor(Color.BLACK);
        //ServerList-下拉框 ipedit-输入框
        spinnerItems = findViewById(R.id.ServerList);
        ipedit = findViewById(R.id.ipEditText);
        //初始化下拉框 确保选择第一位
        spinnerItems.setSelection(0, true);

        //读取是否有自定义IP 读取上一次选择位置
        if (!getPre(LoginActivity, "DiyIP", "").equals("")) {
            ipedit.setText(getPre(LoginActivity, "DiyIP", "mud.pkuxkx.net:8081"));
            ipedit.setVisibility(View.VISIBLE);
            spinnerItems.setSelection(4, true);
        } else if (getPre(LoginActivity, "LastLogin", 0) == 0) {
            spinnerItems.setSelection(0, true);
            ipedit.setText(getResources().getString(R.string.cnipUTF));
        } else if (getPre(LoginActivity, "LastLogin", 0) == 1) {
            spinnerItems.setSelection(1, true);
            ipedit.setText(getResources().getString(R.string.cnipGBK));
        } else if (getPre(LoginActivity, "LastLogin", 0) == 2) {
            spinnerItems.setSelection(2, true);
            ipedit.setText(getResources().getString(R.string.ipUTF));
        } else if (getPre(LoginActivity, "LastLogin", 0) == 3) {
            spinnerItems.setSelection(3, true);
            ipedit.setText(getResources().getString(R.string.ipGBK));
        }

        //事件 当下拉框被选择
        spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinnerItems.getSelectedItem().toString()){
                    case "国内UTF - mud.pkuxkx.net:8081":
                        ipedit.setText(getResources().getString(R.string.cnipUTF));
                        setPre(LoginActivity, "LastLogin", 0);
                        break;
                    case "国内GBK - mud.pkuxkx.net:8080":
                        ipedit.setText(getResources().getString(R.string.cnipGBK));
                        setPre(LoginActivity, "LastLogin", 1);
                        break;
                    case "国外UTF - pkuxkx.net:8081":
                        ipedit.setText(getResources().getString(R.string.ipUTF));
                        setPre(LoginActivity, "LastLogin", 2);
                        break;
                    case "国外GBK - pkuxkx.net:8080":
                        ipedit.setText(getResources().getString(R.string.ipGBK));
                        setPre(LoginActivity, "LastLogin", 3);
                        break;
                    case "自定义IP":
                        ipedit.setVisibility(View.VISIBLE);
                        setPre(LoginActivity, "LastLogin", 4);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Button button = findViewById(R.id.btn_enterServer);
        TextView hintText = findViewById(R.id.Hint_SelectServer);
        //进入游戏按钮点击事件
        button.setOnClickListener(view -> {
            String serverip = String.valueOf(ipedit.getText());
            //检测ip是否正确
            if (!serverip.contains(":")) {
                hintText.setText(R.string.errorip);
                hintText.setTextColor(ContextCompat.getColor(this,R.color.red));
            } else {
                hintText.setText(R.string.hint_selectserver);
                hintText.setTextColor(ContextCompat.getColor(this,R.color.black));
                //写入diyIP
                if (getPre(LoginActivity, "LastLogin", 0) == 4) {
                    setPre(LoginActivity, "DiyIP", serverip);
                } else {
                    setPre(LoginActivity, "DiyIP", "");
                }
                //ip正确 传参进入主页
                Intent intent = new Intent(pkuxkxLogin.this, mudMain.class);
                Bundle bundle = new Bundle();
                bundle.putString("ip", serverip);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //logo点击后打开浏览器主页
        ImageView logoview = findViewById(R.id.pkuxkxLOGO);
        logoview.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.pkuxkx.net/#/");
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(uri);
            startActivity(intent);
        });
        //关于按钮
        ImageButton view = findViewById(R.id.btn_about);
        view.setOnClickListener(v -> {

        });
        //语言按钮
        ImageButton view1 = findViewById(R.id.btn_language);
        view1.setOnClickListener(v -> {

        });
    }

}

