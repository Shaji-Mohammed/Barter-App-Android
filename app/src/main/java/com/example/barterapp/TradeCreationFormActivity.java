package com.example.barterapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TradeCreationFormActivity extends AppCompatActivity {

    static final int IMAGE_SELECTED = 2;

    private FirebaseUser user;

    private EditText itemEditText;
    private EditText descEditText;
    private EditText estimatedPriceText;
    private Button imageButton;

    private Trade trade;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_creation_form);

        itemEditText = findViewById(R.id.trade_create_name);
        descEditText = findViewById(R.id.trade_create_desc);
        estimatedPriceText = findViewById(R.id.trade_create_estimate);
        imageButton = findViewById(R.id.trade_create_image);

        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_SELECTED);
        });

        Button createItemButton = findViewById(R.id.trade_create_submit);
        createItemButton.setOnClickListener(view -> createItem());

        Button backButton = findViewById(R.id.createTradeFormBack);
        backButton.setOnClickListener(view -> switchToItemFeedWindow());

        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        trade = new Trade(firestore);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_SELECTED && resultCode == RESULT_OK) {
            assert data != null;
            imageUri = data.getData();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage(Uri uri, String name, String desc, int estimatedPrice) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Uploading");
        progress.show();

        if (uri != null) {
            StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("trades").child(System.currentTimeMillis() + "." + getFileExtension(uri));
            fileRef.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            progress.dismiss();

                            trade.save(user.getUid(), name, desc, estimatedPrice, url)
                                    .addOnSuccessListener(documentReference -> {
                                        Toast.makeText(TradeCreationFormActivity.this, "Posted", Toast.LENGTH_SHORT).show();
                                        switchToItemFeedWindow();
                                    })
                                    .addOnFailureListener(e -> Toast.makeText(TradeCreationFormActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show());
                        }
                    });
                }
            });
        }
    }

    private void switchToItemFeedWindow() {
        Intent intent = new Intent(this, ItemFeedActivity.class);
        startActivity(intent);
    }

    private void createItem() {
        boolean isError = false;

        String name = itemEditText.getText().toString();
        String desc = descEditText.getText().toString();
        int estimatedPrice = 0;
        try {
            estimatedPrice = Integer.parseInt(estimatedPriceText.getText().toString());
        } catch (NumberFormatException e) {
            isError = true;
        }

        if (!FormValidator.isValidItemName(name)) {
            itemEditText.setError("Item name must be " + FormValidator.MIN_ITEM_NAME_LENGTH + "-" + FormValidator.MAX_ITEM_NAME_LENGTH + " characters.");
            isError = true;
        }

        if (!FormValidator.isValidItemDesc(desc)) {
            descEditText.setError("Item description must be " + FormValidator.MIN_ITEM_DESC_LENGTH + "-" + FormValidator.MAX_ITEM_DESC_LENGTH + " characters.");
            isError = true;
        }

        if (!isError) {
            uploadImage(imageUri, name, desc, estimatedPrice);
//            trade.save(user.getUid(), name, desc, estimatedPrice)
//                    .addOnSuccessListener(documentReference -> {
//                        Toast.makeText(TradeCreationFormActivity.this, "Posted", Toast.LENGTH_SHORT).show();
//                        switchToItemFeedWindow();
//                    })
//                    .addOnFailureListener(e -> Toast.makeText(TradeCreationFormActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show());
        }


    }
}