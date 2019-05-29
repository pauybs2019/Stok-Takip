package o.osman.stok_takip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
Button ekleme,listele,guncel,giris,silme,hakkimizda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listele = (Button) findViewById(R.id.button2);
        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), Listele.class);
                startActivity(i);
            }
        });
        ekleme = (Button) findViewById(R.id.button14);
        ekleme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), Ekleme.class);
                startActivity(i);
            }
        });
        silme = (Button) findViewById(R.id.button);
        silme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), Silme.class);
                startActivity(i);
            }
        });
        guncel = (Button) findViewById(R.id.button4);
        guncel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), guncelle.class);
                startActivity(i);
            }
        });
        giris =(Button) findViewById(R.id.button20);
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), giris.class);
                startActivity(i);
            }
        });
        hakkimizda = (Button) findViewById(R.id.button3);
        hakkimizda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), hakkimizda.class);
                startActivity(i);
            }
        });
    }

}
