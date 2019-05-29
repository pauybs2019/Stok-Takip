package o.osman.stok_takip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class Listele extends AppCompatActivity {
Button anasayfa,listele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listele);
        final ListView listView = (ListView) findViewById(R.id.sveriler);
        Veritabani veritabani = new Veritabani((Listele.this));
        List<urunler> vVeriler = veritabani.VeriListele();
        Stok_liste adapter = new Stok_liste(getApplicationContext(), vVeriler);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(Listele.this, android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
        listView.setAdapter(adapter);

        Toast.makeText(getApplicationContext(),"Listeleme Yapıldı", Toast.LENGTH_LONG).show();


        anasayfa = (Button) findViewById(R.id.button8);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
