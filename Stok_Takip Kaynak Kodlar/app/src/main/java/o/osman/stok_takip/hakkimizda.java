package o.osman.stok_takip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hakkimizda extends AppCompatActivity {
Button hakkimizda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkimizda);
        hakkimizda = (Button) findViewById(R.id.button13);
        hakkimizda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
