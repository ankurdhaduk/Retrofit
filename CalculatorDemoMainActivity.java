package com.example.antop.calculatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bdot,badd,bsub,bmul,bdiv,beq,buttonC,back;
    EditText et,editText2;
    float val1,val2;
    boolean add,sub,div,mul;
    StringBuilder stringBuilder=new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button) findViewById(R.id.button1);
        b2=(Button) findViewById(R.id.button2);
        b3=(Button) findViewById(R.id.button3);
        b4=(Button) findViewById(R.id.button4);
        b5=(Button) findViewById(R.id.button5);
        b6=(Button) findViewById(R.id.button6);
        b7=(Button) findViewById(R.id.button7);
        b8=(Button) findViewById(R.id.button8);
        b9=(Button) findViewById(R.id.button9);
        b0=(Button) findViewById(R.id.button0);
        bdot=(Button) findViewById(R.id.dot);
        badd=(Button) findViewById(R.id.Button11);
        bsub=(Button) findViewById(R.id.Button12);
        bmul=(Button) findViewById(R.id.Button13);
        bdiv=(Button) findViewById(R.id.Button14);
        beq=(Button) findViewById(R.id.Button15);
        buttonC=(Button) findViewById(R.id.buttonC);
        back=(Button) findViewById(R.id.back);
        et=(EditText) findViewById(R.id.editText1);
        editText2=(EditText) findViewById(R.id.editText2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=et.getText().toString();
                if (str.length() >1 ) {
                    str = str.substring(0, str.length() - 1);
                    et.setText(str);
                }
                else if (str.length() <=1 ) {
                    et.setText("0");
                }
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText("");
                editText2.setText("");
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"1");
                editText2.setText(editText2.getText()+"1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"2");
                editText2.setText(editText2.getText()+"2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"3");
                editText2.setText(editText2.getText()+"3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"4");
                editText2.setText(editText2.getText()+"4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"5");
                editText2.setText(editText2.getText()+"5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"6");
                editText2.setText(editText2.getText()+"6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"7");
                editText2.setText(editText2.getText()+"7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"8");
                editText2.setText(editText2.getText()+"8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"9");
                editText2.setText(editText2.getText()+"9");
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                et.setText(et.getText()+"0");
                editText2.setText(editText2.getText()+"0");
            }
        });
        try {
            bdot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!et.getText().toString().trim().contains(".")) {
                        et.setText(et.getText() + ".");
                        editText2.setText(editText2.getText()+".");
                    }
                }
            });
if (add=true) {
    badd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                val1 = Float.parseFloat(et.getText() + "");
                add = true;
                et.setText(null);
                editText2.setText(editText2.getText() + "+");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}else if (sub =true) {
    bsub.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            try {
                val1 = Float.parseFloat(et.getText() + "");
                sub = true;
                et.setText(null);
                editText2.setText(editText2.getText() + "-");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}else if (div=true) {
    bdiv.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            try {
                val1 = Float.parseFloat(et.getText() + "");
                div = true;
                et.setText(null);
                editText2.setText(editText2.getText() + "/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}else if (mul=true) {
    bmul.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            try {
                val1 = Float.parseFloat(et.getText() + "");
                mul = true;
                et.setText(null);
                editText2.setText(editText2.getText() + "*");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}
            beq.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    val2 = Float.parseFloat(et.getText() + "");
                    if (add == true) {
                        et.setText(val1 + val2 + "");
                        add = false;
                    }
                    if (sub == true) {
                        et.setText(val1 - val2 + "");
                        sub = false;
                    }
                    if (mul == true) {
                        et.setText(val1 * val2 + "");
                        mul = false;
                    }
                    if (div == true) {
                        et.setText(val1 / val2 + "");
                        div = false;
                    }
                }

            });
        }catch (Exception e){e.printStackTrace();}

    }
/*
    @Override
    publicbooleanonCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gui, menu);
        return true;
    }

    @Override
    publicbooleanonOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        returnsuper.onOptionsItemSelected(item);

    }*/



}
