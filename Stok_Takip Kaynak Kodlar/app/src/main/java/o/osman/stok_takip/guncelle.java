package o.osman.stok_takip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class guncelle extends AppCompatActivity {

    Button anasayfa,tara,guncelle;

    ListView gnc;
    EditText ad,kod,adet,aciklama;
    String tutulanindex;
    List<urunler> vVeriler = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guncelle);

        guncelle=(Button) findViewById(R.id.button12);
        gnc =(ListView) findViewById(R.id.sveriler);
        ad = (EditText) findViewById(R.id.editText3);
        kod = (EditText) findViewById(R.id.editText5);
        adet = (EditText) findViewById(R.id.editText7);
        aciklama = (EditText) findViewById(R.id.editText12);


            Veritabani veritabani = new Veritabani((guncelle.this));
            vVeriler = veritabani.VeriListele();
            Stok_liste adapter = new Stok_liste(getApplicationContext(), vVeriler);
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(guncelle.this, android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
            gnc.setAdapter(adapter);

            gnc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    urunler urun = vVeriler.get(position);
                    /*String secilen= parent.getItemAtPosition(position).toString();
                    String[] dizi = secilen.split("--");
                    tutulanindex = dizi[0].toString();*/
                    ad.setText(urun.ad);
                    kod.setText(urun.kod);
                    adet.setText(urun.adet);
                    aciklama.setText(urun.aciklama);

                }
            });
            guncelle.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (ad.getText().toString().equals("") || kod.getText().toString().equals("") || adet.getText().toString().equals("") || aciklama.getText().toString().equals("")
                            ) {
                        Toast.makeText(getApplicationContext(), "Güncellemek İstediğiniz Kolonu Seçiniz !!", Toast.LENGTH_LONG).show();
                    } else {
                        Veritabani vr = new Veritabani(getApplicationContext());

                        vr.Vguncelle(kod.getText().toString(), ad.getText().toString(), kod.getText().toString(), adet.getText().toString(), aciklama.getText().toString());

                        Veritabani veritabani2 = new Veritabani((guncelle.this));
                        Toast.makeText(getApplicationContext(), "GÜNCELLEME BAŞARILI", Toast.LENGTH_LONG).show();
                        List<urunler> vtguncelle = veritabani2.VeriListele();
                        Stok_liste adapter = new Stok_liste(getApplicationContext(), vtguncelle);
                        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(guncelle.this, android.R.layout.simple_list_item_1, android.R.id.text1, vtguncelle);
                        gnc.setAdapter(adapter);
                    }
                }
            });


        anasayfa = (Button) findViewById(R.id.button7);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });

        tara = (Button) findViewById(R.id.button15);

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
            TextView tvlink =(TextView) findViewById(R.id.editText5);
            //TextView tvlink=(TextView)findViewById(R.id.editText);
            if (resultCode == RESULT_OK) {
                tvlink.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                tvlink.setText("Barkod Okunmadı.");
            }
        }




    }
}
