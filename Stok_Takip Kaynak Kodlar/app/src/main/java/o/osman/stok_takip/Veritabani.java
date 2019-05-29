package o.osman.stok_takip;

import android.arch.lifecycle.ViewModelProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;
import java.sql.RowId;
import java.util.ArrayList;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "stok_takip";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "stok";
    public static final String ROW_ID = "id";
    public static final String ROW_NAME = "ad";
    public static final String ROW_KOD = "kod";
    public static final String ROW_ACİKLAMA = "aciklama";
    public static final String ROW_ADET = "adet";


    public Veritabani(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(" + ROW_ID + " INTEGER PRIMARY KEY, " + ROW_NAME + " TEXT NOT NULL, " + ROW_KOD + " TEXT NOT NULL, " + ROW_ADET + " TEXT NOT NULL, " + ROW_ACİKLAMA + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

        public void VeriEkle(String ad, String kod, String adet, String aciklama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_NAME, ad.trim());
        cv.put(ROW_KOD, kod.trim());
        cv.put(ROW_ADET, adet.trim());
        cv.put((ROW_ACİKLAMA), aciklama.trim());
        db.insert(DATABASE_TABLE, null, cv);
        db.close();
    }

    public List<urunler> VeriListele() {
        List<urunler> veriler = new ArrayList<urunler>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] sutunlar = {ROW_ID, ROW_NAME, ROW_KOD, ROW_ADET,ROW_ACİKLAMA};
        Cursor cursor = db.query(DATABASE_TABLE, sutunlar, null, null, null, null,null);
        while (cursor.moveToNext()) {
            urunler urun = new urunler(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3), cursor.getString(4));
            veriler.add(urun);
            //veriler.add(cursor.getInt(0) + "--" + cursor.getString(1) + "--" + cursor.getString(2) + "--" + cursor.getString(3)+ "--" + cursor.getString(4));
        }
        return veriler;
    }
    public boolean barkodsorgula(String qrkod){
        boolean durum=true;
        SQLiteDatabase db=this.getReadableDatabase();
        String[] sutunlar={ROW_KOD};
        Cursor cursor=db.query(DATABASE_TABLE,sutunlar,null,null,null,null,null);
        while (cursor.moveToNext())
            if(cursor.getString(0).toString().equals(qrkod))
            { durum=false; break;}
        return  durum;
    }
    public void VeriSil(String kod)

    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE,ROW_KOD + "=?",new String[]{String.valueOf(kod)});
        db.close();
}
    public void Vguncelle (String id,String ad, String kod, String adet,String aciklama)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_NAME, ad);
        cv.put(ROW_KOD, kod);
        cv.put(ROW_ADET, adet);
        cv.put(ROW_ACİKLAMA,aciklama );
        db.update(DATABASE_TABLE, cv, ROW_KOD + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
public String stokguncelle (String kod,String adet,int islem){
try {


    List<String> veriler = new ArrayList<String>();
    SQLiteDatabase gc = this.getWritableDatabase();
    String[] sutunlar = {ROW_KOD, ROW_ADET};
    Cursor cursor = gc.query(DATABASE_TABLE, sutunlar, null, null, null, null, null, null);
    while (cursor.moveToNext()) {
        if (cursor.getString(0).equals(kod))
            veriler.add(cursor.getString(1));

    }
    gc.close();

    if (islem == 1) {


        int adet1 = Integer.parseInt(adet) + Integer.parseInt(veriler.get(0).toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ROW_KOD, kod);
        cv.put(ROW_ADET, adet1);


        db.update(DATABASE_TABLE, cv, ROW_KOD + "=?", new String[]{String.valueOf(kod)});
        db.close();
        return "Stok Girişi Başarılı !!";

    } else if (islem == 0) {
        int adet1 = Integer.parseInt(veriler.get(0).toString()) - Integer.parseInt(adet);
        if (adet1 >= 0) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(ROW_KOD, kod);
            cv.put(ROW_ADET, adet1);


            db.update(DATABASE_TABLE, cv, ROW_KOD + "=?", new String[]{String.valueOf(kod)});
            db.close();
            return "Stok Çıkışı Başarılı";
        } else {
            return " Yeterli Stok Bulunamamaktadır !!";
        }
    }
}
catch (Exception e){}
    return "Böyle Bir Kayıt Bulunmamaktadır.";

}
}