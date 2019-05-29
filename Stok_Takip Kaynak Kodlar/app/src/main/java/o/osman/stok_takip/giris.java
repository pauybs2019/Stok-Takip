package o.osman.stok_takip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class giris extends AppCompatActivity {
Button tara,anasayfa,giris,cikis;
    EditText kod,aciklama,adet;
    String tutulanindex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        anasayfa = (Button) findViewById(R.id.button21);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });



            giris = (Button) findViewById(R.id.button16);
            kod = (EditText) findViewById(R.id.editText8);
            adet = (EditText) findViewById(R.id.editText6);



                giris.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (kod.getText().toString().trim().length() > 0 && adet.getText().toString().trim().length() > 0) {
                            Veritabani veritabani = new Veritabani(giris.this);

                            String hg = veritabani.stokguncelle(kod.getText().toString(), adet.getText().toString(), 1);
                            Toast.makeText(getApplicationContext(), hg, Toast.LENGTH_LONG).show();
                        }
                        else{Toast.makeText(getApplicationContext(), "Alanlar Boş Geçilemez", Toast.LENGTH_LONG).show();}
                    }

                });


        cikis=(Button) findViewById(R.id.button17);
        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kod.getText().toString().trim().length() > 0 && adet.getText().toString().trim().length() > 0) {
                    Veritabani veritabani = new Veritabani(giris.this);
                    String hg = veritabani.stokguncelle(kod.getText().toString(), adet.getText().toString(), 0);
                    Toast.makeText(getApplicationContext(), hg, Toast.LENGTH_LONG).show();
                }
                else{Toast.makeText(getApplicationContext(), "Alanlar Boş Geçilemez", Toast.LENGTH_LONG).show();}
            }
        });

        tara = (Button) findViewById(R.id.button18);


try {
    tara.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);    //Barcode Scanner to scan for us
        }
    });
}
catch (Exception exe){Toast.makeText(getApplicationContext(), "Karekod Tarayıcısını Yükleyiniz!!.", Toast.LENGTH_LONG).show();}
    }
    public void Tarama_tiklama (View view)
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            TextView tvlink =(TextView) findViewById(R.id.editText8);
            //TextView tvlink=(TextView)findViewById(R.id.editText);
            if (resultCode == RESULT_OK) {
                tvlink.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                tvlink.setText("Barkod Okunmadı.");
            }
        }


    }
}
