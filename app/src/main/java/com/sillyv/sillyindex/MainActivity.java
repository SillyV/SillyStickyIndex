package com.sillyv.sillyindex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sillyv.stickyindex.StickyScrollListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter recyclerAdapter;
    private IndexAdapter stickyRecyclerAdapter;
    private TextView textView;
    private StickyScrollListener<MyInitial> myScrollListener;
    private RecyclerView sticky_recycler;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.index_textview);
        EditText editText = (EditText) findViewById(R.id.edit_text_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String newString = charSequence.toString();
                List<String> newStrings = generateNames(newString);
                textView.setVisibility(View.INVISIBLE);
                myScrollListener.removeTouchListeners(recyclerView,sticky_recycler);
                recyclerAdapter.setStrings(newStrings);
                stickyRecyclerAdapter.setInitials(getInitials(newStrings));
                recyclerAdapter.notifyDataSetChanged();
                stickyRecyclerAdapter.notifyDataSetChanged();
                sticky_recycler.removeOnScrollListener(myScrollListener);
                myScrollListener = new StickyScrollListener<>(sticky_recycler,recyclerView, textView, R.id.index_textview);
                sticky_recycler.addOnScrollListener(myScrollListener);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        List<String> strings = generateNames();
        List<String> initials = getInitials(strings);


        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        sticky_recycler = (RecyclerView) findViewById(R.id.index_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sticky_recycler.setLayoutManager(new LinearLayoutManager(this));

        recyclerAdapter = new RecyclerAdapter(this, strings, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stickyRecyclerAdapter = new IndexAdapter(this, initials, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        recyclerView.setAdapter(recyclerAdapter);
        sticky_recycler.setAdapter(stickyRecyclerAdapter);

        myScrollListener = new StickyScrollListener<>(sticky_recycler,recyclerView, textView, R.id.index_textview);
        sticky_recycler.addOnScrollListener(myScrollListener);

    }

    @NonNull
    private List<String> getInitials(List<String> strings) {
        List<String> initials = new ArrayList<>();
        for (String str :
                strings) {
            initials.add(str.substring(0, 1));
        }
        return initials;
    }


    @NonNull
    private List<String> generateNames() {
        final List<String> strings = new ArrayList<>();
        strings.add("Jeni Querry");
        strings.add("Russell Eng");
        strings.add("Loma Serio");
        strings.add("Dorris Sprung");
        strings.add("Lesli Piro");
        strings.add("Dylan Yagi");
        strings.add("Virgil Mulvaney");
        strings.add("Peggy Sobus");
        strings.add("Lovella Rehkop");
        strings.add("Reynaldo Veal");
        strings.add("Alfonso Stiver");
        strings.add("Hector Gosselin");
        strings.add("Adriene Hazelton");
        strings.add("Arleen Law");
        strings.add("Marjorie Croteau");
        strings.add("Vern Mantz");
        strings.add("Teressa Berglund");
        strings.add("Clarisa Ries");
        strings.add("Nicol Odaniel");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Hung Stryker");
        strings.add("Vasia");
        strings.add("Vaska Fedodik");
        strings.add("Vasilisa Prekrastnaya");
        strings.add("Vasili Fedotov");
        strings.add("Maximina Pfarr");

        strings.add("Aleshia Etter");
        strings.add("Julianne Priester");
        strings.add("Jose Keaney");
        strings.add("Sid Hornick");
        strings.add("Andera Olague");
        strings.add("Palmira Sabb");
        strings.add("Abigail Green");
        strings.add("Del Champlin");
        strings.add("Jaqueline Harned");
        strings.add("Nga Merrihew");
        strings.add("Tosha Rosendahl");
        strings.add("Guy Hersom");
        strings.add("Bo Jesse");
        strings.add("Gayla Hargreaves");
        strings.add("Beau Kentner");
        strings.add("Lettie Mcewan");
        strings.add("Wilda Ordaz");
        strings.add("Dione Schroer");
        strings.add("Edelmira Yocom");
        strings.add("Candie Kangas");
        strings.add("Kimberly Barnhill");
        strings.add("Fidelia Kroner");
        strings.add("Necole Hamburger");
        strings.add("Silvia Dileo");
        strings.add("Allison Compton");
        strings.add("Reanna Mears");
        strings.add("Sima Wingate");
        strings.add("Jona Neuendorf");
        strings.add("Raven Mccampbell");
        strings.add("Jeni Querry");
        strings.add("Russell Eng");
        strings.add("Loma Serio");
        strings.add("Dorris Sprung");
        strings.add("Lesli Piro");
        strings.add("Dylan Yagi");
        strings.add("Virgil Mulvaney");
        strings.add("Peggy Sobus");
        strings.add("Lovella Rehkop");
        strings.add("Reynaldo Veal");
        strings.add("Alfonso Stiver");
        strings.add("Hector Gosselin");
        strings.add("Adriene Hazelton");
        strings.add("Arleen Law");
        strings.add("Marjorie Croteau");
        strings.add("Vern Mantz");
        strings.add("Teressa Berglund");
        strings.add("Clarisa Ries");
        strings.add("Nicol Odaniel");
        strings.add("Hung Stryker");
        strings.add("Maximina Pfarr");
        strings.add("Aleshia Etter");
        strings.add("Julianne Priester");
        strings.add("Jose Keaney");
        strings.add("Sid Hornick");
        strings.add("Andera Olague");
        strings.add("Palmira Sabb");
        strings.add("Abigail Green");
        strings.add("Del Champlin");
        strings.add("Jaqueline Harned");
        strings.add("Nga Merrihew");
        strings.add("Tosha Rosendahl");
        strings.add("Guy Hersom");
        strings.add("Bo Jesse");
        strings.add("Gayla Hargreaves");
        strings.add("Beau Kentner");
        strings.add("Lettie Mcewan");
        strings.add("Wilda Ordaz");
        strings.add("Dione Schroer");
        strings.add("Edelmira Yocom");
        strings.add("Candie Kangas");
        strings.add("Zimberly Barnhill");
        strings.add("Zidelia Kroner");
        strings.add("Yecole Hamburger");
        strings.add("Yilvia Dileo");
        strings.add("Xllison Compton");
        strings.add("Xeanna Mears");
        strings.add("Xima Wingate");
        strings.add("Wona Neuendorf");
        strings.add("Waven Mccampbell");
        strings.add("Weni Querry");
        strings.add("Vussell Eng");
        strings.add("Voma Serio");
        strings.add("Uorris Sprung");
        strings.add("Uesli Piro");
        strings.add("Tylan Yagi");
        strings.add("Tirgil Mulvaney");
        strings.add("Seggy Sobus");
        strings.add("Sovella Rehkop");
        strings.add("Reynaldo Veal");
        strings.add("Rlfonso Stiver");
        strings.add("Rector Gosselin");
        strings.add("Qdriene Hazelton");
        strings.add("Prleen Law");
        strings.add("Qarjorie Croteau");
        strings.add("Pern Mantz");
        strings.add("Oeressa Berglund");
        strings.add("Olarisa Ries");
        strings.add("Nicol Odaniel");
        strings.add("Nung Stryker");
        strings.add("Maximina Pfarr");
        strings.add("Mleshia Etter");
        strings.add("Lulianne Priester");
        strings.add("Lose Keaney");
        strings.add("Lid Hornick");
        strings.add("Kndera Olague");
        strings.add("Kalmira Sabb");
        strings.add("Jbigail Green");
        strings.add("Jel Champlin");
        strings.add("Iaqueline Harned");
        strings.add("Hosha Rosendahl");
        strings.add("Huy Hersom");
        strings.add("Ho Jesse");
        strings.add("Gayla Hargreaves");
        strings.add("Geau Kentner");
        strings.add("Gettie Mcewan");
        strings.add("Filda Ordaz");
        strings.add("Fione Schroer");
        strings.add("Edelmira Yocom");
        strings.add("Eandie Kangas");
        strings.add("Eimberly Barnhill");
        strings.add("Ddelia Kroner");
        strings.add("Decole Hamburger");
        strings.add("Cilvia Dileo");
        strings.add("Cllison Compton");
        strings.add("Beanna Mears");
        strings.add("Bima Wingate");
        strings.add("Aona Neuendorf");
        strings.add("Aaven Mccampbell");
        java.util.Collections.sort(strings);
        return strings;
    }


    private List<String> generateNames(String query) {
        final List<String> strings = generateNames();
        final List<String> newStrings = new ArrayList<>();
        for (String str :
                strings) {
            if (str.toLowerCase().contains(query.toLowerCase())) {
                newStrings.add(str);
            }

        }
        return newStrings;
    }
}

