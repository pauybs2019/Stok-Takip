package o.osman.stok_takip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Stok_liste extends ArrayAdapter<urunler> {
    public Stok_liste(Context context, List<urunler> urunlerList){
        super(context, 0, urunlerList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        urunler urun = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.liste, parent, false);
        }
        // Lookup view for data population
        TextView ad = (TextView) convertView.findViewById(R.id.ad);
        TextView kod = (TextView) convertView.findViewById(R.id.kod);
        TextView adet = (TextView) convertView.findViewById(R.id.adet);
        TextView aciklama = (TextView) convertView.findViewById(R.id.aciklama);

        // Populate the data into the template view using the data object
        ad.setText(urun.ad);
        kod.setText(urun.kod);
        adet.setText(urun.adet);
        aciklama.setText(urun.aciklama);

        // Return the completed view to render on screen
        return convertView;
    }
}
