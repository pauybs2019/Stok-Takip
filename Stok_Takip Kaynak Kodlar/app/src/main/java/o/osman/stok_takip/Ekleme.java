package o.osman.stok_takip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Ekleme extends AppCompatActivity {
    Button anasayfa;
    Button tara;
    Button listele;
    Button silme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekleme);
        final Veritabani veritabani = new Veritabani(Ekleme.this);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText11 = (EditText) findViewById(R.id.editText11);

        Button button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("") || editText2.getText().toString().trim().equals("") ||  editText4.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Bu Alanlar Boş Geçilemez !!", Toast.LENGTH_LONG).show();
                }

                else {

                    Veritabani veritabani = new Veritabani(Ekleme.this);
                    if(veritabani.barkodsorgula(editText2.getText().toString().trim())==true) {
                        veritabani.VeriEkle(editText.getText().toString(), editText2.getText().toString(), editText4.getText().toString(), editText11.getText().toString());
                        Toast.makeText(getApplicationContext(), "KAYIT BAŞARILI", Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(getApplicationContext(), "AYNI QR KODDAN BİRDEN FAZLA GİRİLMEZ.", Toast.LENGTH_LONG).show();
                }
            }
        });
        tara = (Button) findViewById(R.id.button11);
        listele = (Button) findViewById(R.id.button10);
        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Listele.class);
                startActivity(i);
            }
        });
        anasayfa = (Button) findViewById(R.id.button6);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
        tara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);	//Barcode Scanner to scan for us
            }
        });

    }
    public void Tarama_tiklama (View view)
    {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            TextView tvlink =(TextView) findViewById(R.id.editText2);
            //TextView tvlink=(TextView)findViewById(R.id.editText);
            if (resultCode == RESULT_OK) {
                tvlink.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                tvlink.setText("Barkod Okunmadı.");
            }
        }
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText4 = (EditText) findViewById(R.id.editText4);
        final EditText editText11 = (EditText) findViewById(R.id.editText11);
        Button button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals("") || editText2.getText().toString().equals("") || editText4.getText().toString().equals("")|| editText11.getText().toString().equals("")
                        )
                {
                    Toast.makeText(getApplicationContext(),"Bu Alanlar Boş Geçilemez !!", Toast.LENGTH_LONG).show();
                }

                else {

                    Veritabani veritabani = new Veritabani(Ekleme.this);
                    veritabani.VeriEkle(editText.getText().toString(), editText2.getText().toString(), editText4.getText().toString(),editText11.getText().toString());
                    Toast.makeText(getApplicationContext(),"KAYIT BAŞARILI", Toast.LENGTH_LONG).show();
                }
            }
        });



        listele = (Button) findViewById(R.id.button10);
        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Listele.class);
                startActivity(i);
            }
        });
        anasayfa = (Button) findViewById(R.id.button6);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

}

