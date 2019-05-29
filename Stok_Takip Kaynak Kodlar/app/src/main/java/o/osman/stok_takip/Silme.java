package o.osman.stok_takip;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;


public class Silme extends AppCompatActivity {
Button anasayfa;
ListView veri;
List<urunler> x = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silme);


        veri=(ListView) findViewById(R.id.sveriler);
        final ListView listView = (ListView) findViewById(R.id.sveriler);

        Veritabani veritabani = new Veritabani((Silme.this));
        List<urunler> vtx = veritabani.VeriListele();
        Stok_liste adapter = new Stok_liste(getApplicationContext(), vtx);

        //ArrayAdapter<String> adapterx = new ArrayAdapter<String>(Silme.this, android.R.layout.simple_list_item_1, android.R.id.text1, vtx);
        listView.setAdapter(adapter);

        veri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                if(id>-1){
                    AlertDialog.Builder builder=new AlertDialog.Builder(Silme.this);
                    builder.setTitle("Uyarı");
                    builder.setMessage("Silmek İstediğinizden Eminmisiniz?");
                    builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Veritabani veritabani = new Veritabani(getApplicationContext());
                            x = veritabani.VeriListele();
                            urunler urun = x.get(position);
                           // Toast.makeText(getApplicationContext(),urun.kod.trim(), Toast.LENGTH_LONG).show();
                             veritabani.VeriSil(String.valueOf(urun.kod));
                            Stok_liste adaptery = new Stok_liste(getApplicationContext(), x);
                            listView.setAdapter(adaptery);
                            List<urunler> vVeriler = veritabani.VeriListele();
                            Stok_liste adapter = new Stok_liste(getApplicationContext(), vVeriler);
                            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(Listele.this, android.R.layout.simple_list_item_1, android.R.id.text1, vVeriler);
                            listView.setAdapter(adapter);

                            Toast.makeText(getApplicationContext(),"Silme İşlemi Yapıldı", Toast.LENGTH_LONG).show();

                        }
                    });
                    builder.show();
                }
            }
        });


        anasayfa = (Button) findViewById(R.id.button5);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
