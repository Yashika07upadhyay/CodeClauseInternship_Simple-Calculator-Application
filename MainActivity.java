package com.example.mycalci;

import androidx.appcompat.app.AppCompatActivity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView res_tv,sol_tv;
            MaterialButton btc,btOpen,btClose;
            MaterialButton btDivide,btMultiply,btSub,btPlus;
            MaterialButton btAC,btDot,btEqual;
            MaterialButton bt0,bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res_tv=findViewById(R.id.res_tv);
        sol_tv=findViewById(R.id.sol_tv);
        assign(btc,R.id.BTC);
        assign(btOpen,R.id.BTv);
        assign(btClose,R.id.BTb);
        assign(btDivide,R.id.BTo);
        assign(btMultiply,R.id.BTO);
        assign(btSub,R.id.BTm);
        assign(btPlus,R.id.BTp);
        assign(btAC,R.id.BTAC);
        assign(btDot,R.id.BTM);
        assign(bt0,R.id.BTZ);
        assign(bt1,R.id.BT1);
        assign(bt2,R.id.BT2);
        assign(bt3,R.id.BT3);
        assign(bt4,R.id.BTf);
        assign(bt5,R.id.BTfi);
        assign(bt6,R.id.BTsi);
        assign(bt7,R.id.BT7);
        assign(bt8,R.id.BT8);
        assign(bt9,R.id.BT9);
        assign(btEqual,R.id.BTe);

    }
    void assign(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
    MaterialButton bt=(MaterialButton) view;
    String btText=bt.getText().toString();
    String Calc=sol_tv.getText().toString();
    if(btText.equals("AC")){
        sol_tv.setText("");
        res_tv.setText("0");
        return;
    }
    if(btText.equals("=")){
        sol_tv.setText(res_tv.getText());
        return;
    }
    if(btText.equals("C")){
        Calc=Calc.substring(0,Calc.length()-1);
    }else{
        Calc=Calc+btText;
    }
        sol_tv.setText(Calc);
    String finalResult=getResult((Calc));
    if(!finalResult.equals("Err")){
        res_tv.setText(finalResult);
    }
    }
    String getResult(String data){
       try{
           Context context=Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable=context.initStandardObjects();
           String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResult.endsWith(".0")){
               finalResult.replace(".0"," ");
           }
           return finalResult;
       }catch (Exception e){
           return "Err";
       }
    }
}