package com.example.blocnoteapp;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.res.Resources;


import com.example.blocnoteapp.MainActivity7;
import com.example.blocnoteapp.R;

public class MainActivity6 extends AppCompatActivity {
///base de donnees


    private LinearLayout cardContainer;
    private ImageButton addImageButton;
    private CardView card1;
    private CardView card2;
    private CardView card3;
    private CardView card4;
    private TextView textViewSavedShopping;
    private TextView textViewSavedFamily;
    private TextView textViewSavedHobbies;
    private static final int REQUEST_CODE_SHOPPING = 1;
    private static final int REQUEST_CODE_FAMILY = 2;
    private static final int REQUEST_CODE_HOBBIES = 3;
    private static final int REQUEST_CODE_card4 = 4;
    private static final int REQUEST_CODE = 1;
    private int[] iconList = {
            R.drawable.baseline_fastfood_24,
            R.drawable.baseline_laptop_mac_24,
            R.drawable.baseline_sports_football_24,
            // Ajoutez les références de vos icônes ici
    };
    TextView[] textViews = new TextView[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        getSupportActionBar().hide();

        card1= findViewById(R.id.cardView1);
        setCardClickListener(card1,REQUEST_CODE_SHOPPING);
        setCardLongClickListener(card1);
        card2= findViewById(R.id.cardView2);
        setCardClickListener(card2,REQUEST_CODE_FAMILY);
        setCardLongClickListener(card2);
        card3= findViewById(R.id.cardView3);
        setCardClickListener(card3,REQUEST_CODE_HOBBIES);
        setCardLongClickListener(card3);

        // Appel pour récupérer les données de la carte 1


        cardContainer = findViewById(R.id.cardContainer);
        addImageButton = findViewById(R.id.addImageButton);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIconSelectionDialog();
            }
        });

    }
    // Méthode pour gérer le clic sur les cartes
    private void setCardClickListener(CardView cardView,final int requestCode) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity6.this, MainActivity7.class);
                intent.putExtra("requestCode", requestCode); // Envoyer le texte à l'activité suivante
                startActivityForResult(intent, requestCode);// Utiliser cet ID comme requestCode

            }
        });
    }

    private CardView createNewCardView(String titleText, int selectedIcon) {
        ImageView iconImageView = new ImageView(this);//creer un objet de image_icon

        LinearLayout.LayoutParams iconLayoutParams = new LinearLayout.LayoutParams(
                getResources().getDimensionPixelSize(R.dimen.icon_width),
                getResources().getDimensionPixelSize(R.dimen.icon_height)
        );
        iconLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        iconLayoutParams.setMargins(
                getResources().getDimensionPixelSize(R.dimen.icon_margin_start),
                getResources().getDimensionPixelSize(R.dimen.icon_margin_top),
                getResources().getDimensionPixelSize(R.dimen.icon_margin_end),
                getResources().getDimensionPixelSize(R.dimen.icon_margin_bottom)
        );
        iconImageView.setLayoutParams(iconLayoutParams);
        iconImageView.setColorFilter(getResources().getColor(R.color.vert)); // Appliquer la couleur sur l'icône
        CardView cardView = new CardView(this); ///ici il cree une cardview
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelSize(R.dimen.card_height)
        );
        layoutParams.setMargins(
                getResources().getDimensionPixelSize(R.dimen.card_margin),
                getResources().getDimensionPixelSize(R.dimen.card_margin),
                getResources().getDimensionPixelSize(R.dimen.card_margin),
                getResources().getDimensionPixelSize(R.dimen.card_margin)
        );
        EditText titleTextView = new  EditText(this);
//        generate un id pour la view nouvellement cree


        setCardLongClickListener(cardView);
        cardView.setLayoutParams(layoutParams);

        cardView.setRadius(getResources().getDimension(R.dimen.card_corner_radius));
        cardView.setCardElevation(getResources().getDimension(R.dimen.card_elevation));

        titleTextView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        LinearLayout.LayoutParams titleLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        titleLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        titleTextView.setLayoutParams(titleLayoutParams);

        titleTextView.setText(titleText);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 27);
        titleTextView.setTypeface(null, Typeface.BOLD);
        titleTextView.setPadding(50, 25, 50, 25);

        cardView.addView(titleTextView);
        iconImageView.setImageResource(selectedIcon);//set src de image est source de image selecter
        cardView.addView(iconImageView);//add it t cardview

        return cardView;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        textViews[0] = card1.findViewById(R.id.textViewSavedShopping);
        textViews[1] = card2.findViewById(R.id.textViewSavedFamily);
        textViews[2] = card3.findViewById(R.id.textViewSavedHobbies);


        if (resultCode == RESULT_OK && data != null) {
            String textFromNotes = data.getStringExtra("textFromNotes");
            int returnedRequestCode = data.getIntExtra("requestCode", 0);

            // Update the appropriate card based on the requestCode
            switch (returnedRequestCode) {
                case REQUEST_CODE_SHOPPING:
                    textViews[0].setText(textFromNotes);
                    textViews[0].setVisibility(View.VISIBLE);
                    break;
                case REQUEST_CODE_FAMILY :
                    textViews[1].setText(textFromNotes);
                    textViews[1].setVisibility(View.VISIBLE);
                    break;
                case REQUEST_CODE_HOBBIES:
                    textViews[2].setText(textFromNotes);
                    textViews[2].setVisibility(View.VISIBLE);
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


    private void showIconSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity6.this);
        builder.setTitle("Sélectionnez une icône");

        final int[] iconResources = {
                R.drawable.baseline_fastfood_24,
                R.drawable.baseline_laptop_mac_24,
                R.drawable.baseline_sports_football_24
                // Ajoutez les références de vos icônes ici
        };

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 20, 20, 20);
        layout.setGravity(Gravity.CENTER);
        int iconColor = getResources().getColor(R.color.vert);

        for (int iconResource : iconResources) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(10, 10, 10, 10);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(iconResource);
            imageView.setColorFilter(iconColor); // Appliquer la couleur sur l'icône
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedIconIndex = layout.indexOfChild(v);
                    int selectedIcon = iconResources[selectedIconIndex];
                    CardView newCard = createNewCardView("", selectedIcon); // Titre vide
                    cardContainer.addView(newCard);
                }
            });
            layout.addView(imageView);
        }

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(layout);
        builder.setView(scrollView);

        builder.show();
    }
    private void setCardLongClickListener(CardView cardView) {
        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity6.this);
                alertDialogBuilder.setTitle("Supprimer la carte ?");
                alertDialogBuilder.setMessage("Êtes-vous sûr de vouloir supprimer cette carte ?");
                alertDialogBuilder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Supprimer la carte
                        cardContainer.removeView(cardView);
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.create().show();
                return true;
            }
        });
    }

}









