package com.Hecht.ar;


import static com.Hecht.ar.UserFragment.anchorID;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Hecht.ar.Helper.FirebaseCallBacks;
import com.Hecht.ar.Helper.SPmanager;
import com.Hecht.ar.ModelClass.PostClass;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class GltfActivity extends AppCompatActivity implements View.OnClickListener, CommentClickListener {

    FrameLayout layout;
    private static final int SELECT_Image = 100;
    ImageView imgCreatePost;
    RecyclerView rvPost;

    ImageView viewImage;
    String imageUrl="";
    private static final double MIN_OPENGL_VERSION = 3.0;
    public List<PostClass> objlist = new ArrayList<>();
    private CommentAdapter adapterData;
    boolean isImageUpload, isCreatePost;
    String post = "https://qrco.de/bd3vMT";
    public static boolean isComment = false;
    private DatabaseReference mDatabase;

    Dialog dialogRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uxx);
        

        layout = findViewById(R.id.layout);
        rvPost = findViewById(R.id.rvPost);
        imgCreatePost = findViewById(R.id.imgCreatePost);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        imgCreatePost.setOnClickListener(this);

        anchorID = "ua-7795912198fca44b5c99fccc47ef6690";



        FirebaseCallBacks.readPost(mDatabase,anchorID);
        FirebaseCallBacks.postSuccessListener(new FirebaseCallBacks.OnPostEventListener() {
            @Override
            public void onPostSuccessListner(List<PostClass> postList) {
                objlist.clear();
                objlist.addAll(postList);
                if(objlist.size() > 0)
                    setDataInAdaptor(objlist);

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == imgCreatePost.getId()){
            qrPostDialog();
         //   FirebaseCallBacks.readPost(mDatabase);
        //    List<PostClass> replyList = new ArrayList<>();
//
//            String key = mDatabase.child("user").child("object_labeled").child(anchorID).child("posts").push().getKey();
//            PostClass obj = new PostClass(key,"testing", SPmanager.getFirstName(getActivity()),0, 0,isCreatePost,replyList);
//            objlist.add(obj);
//
//            FirebaseCallBacks.writeNewPost(mDatabase,this,key,obj,anchorID);
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //For android 11 and above

            if (resultCode == RESULT_OK) {
                if (requestCode == SELECT_Image) {
                    Toast.makeText(this, "Get Image", Toast.LENGTH_LONG)
                            .show();
                    Log.d("comeOn_comeOn", "image");
                    //handle image
                    Uri selectedImageUri = data.getData();
                    imageUrl=selectedImageUri.toString();
                    viewImage.setImageURI(selectedImageUri);

// start cropping activity for pre-acquired image saved on the device



                }



            }

        }
      /*  IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanningResult != null) {
            if (scanningResult.getContents() != null) {
                if(isCreatePost) {
                    Toast.makeText(this, "1", Toast.LENGTH_LONG)
                            .show();
                    String scanContent = scanningResult.getContents();
                    Log.i("scanning result",scanContent);
                    List<PostClass> replyList = new ArrayList<>();

                    String key = mDatabase.child("user").child("object_labeled").child(anchorID).child("posts").push().getKey();
                    PostClass obj = new PostClass(key,scanContent, SPmanager.getFirstName(this),0, 0,isCreatePost,replyList);
                    objlist.add(obj);

                    FirebaseCallBacks.writeNewPost(mDatabase,this,key,obj,anchorID);
                }else {
                    Toast.makeText(this, "2", Toast.LENGTH_LONG)
                            .show();
                    List<PostClass> replyList = new ArrayList<>();
                    String scanContent = scanningResult.getContents();
                    String key = mDatabase.child("user").child("object_labeled").child(anchorID).child("posts").push().getKey();
                    PostClass obj = new PostClass(key,scanContent, SPmanager.getFirstName(this),0, 0,false,replyList);
                    objlist.add(obj);
                    FirebaseCallBacks.writeNewPost(mDatabase,this,key,obj,anchorID);
                }

                if(objlist.size() == 1)
                setDataInAdaptor(objlist);
                else
                adapterData.notifyDataSetChanged();

            }

        } else {
            Toast.makeText(this, "3", Toast.LENGTH_LONG);
        }*/

    }

    public void qrCodeFunctionality() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.setPrompt("Scan");
        scanIntegrator.setBeepEnabled(false);
        scanIntegrator.setCameraId(0);
        scanIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        scanIntegrator.setCaptureActivity(CaptureActivity.class);
        scanIntegrator.setOrientationLocked(false);
        scanIntegrator.setBarcodeImageEnabled(true);
        scanIntegrator.initiateScan();
    }


    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }

    private void setDataInAdaptor(List<PostClass> fileList) {
        adapterData = new CommentAdapter(this,fileList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setReverseLayout(false);
        rvPost.setLayoutManager(gridLayoutManager);
        rvPost.setAdapter(adapterData);

//        adapterData.setOnItemClickListener(new ViewPostAdaptor.ClickListener() {
//            @Override
//            public void onArViewClick(int position, View v) {
//                Toast.makeText(this,"Comments and post saving will be appeared in next version",Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void qrPostDialog() {
        AlertDialog.Builder builderAddInfo = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogViewAddInfo = inflater.inflate(R.layout.create_post_dialog, null);
        builderAddInfo.setView(dialogViewAddInfo);
        AlertDialog dialogAddInfo = builderAddInfo.create();
        Objects.requireNonNull(dialogAddInfo.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogAddInfo.show();
        dialogAddInfo.setCancelable(true);


        RelativeLayout rlCreatePost = dialogViewAddInfo.findViewById(R.id.rlCreatePost);
        RelativeLayout rlUploadImage = dialogViewAddInfo.findViewById(R.id.rlUploadImage);


        rlUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowUploadImageDialog(true);
               // qrCodeFunctionality();

                dialogAddInfo.dismiss();
            }
        });

        rlCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowUploadImageDialog(false);


                dialogAddInfo.dismiss();

            }
        });
    }
    private void ShowUploadImageDialog(boolean withImage) {
        AlertDialog.Builder builderAddInfo = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogViewAddInfo = inflater.inflate(R.layout.dialog_upload_image, null);
        builderAddInfo.setView(dialogViewAddInfo);
        AlertDialog dialogAddInfo = builderAddInfo.create();
        Objects.requireNonNull(dialogAddInfo.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogAddInfo.show();
        dialogAddInfo.setCancelable(true);

        ImageView imgUpload = dialogViewAddInfo.findViewById(R.id.img_upload);
        if (!withImage)
        {
            imgUpload.setVisibility(View.GONE);
        }




        TextInputEditText tvMessage = dialogViewAddInfo.findViewById(R.id.ti_message);
        Button btnCreate = dialogViewAddInfo.findViewById(R.id.btn_create);


        imgUpload.setOnClickListener(v -> {
            viewImage = imgUpload;
            pickFromGallery();


        });

        btnCreate.setOnClickListener(v -> {

            if (tvMessage.getText().toString().equals(""))

                Toast.makeText(GltfActivity.this, "Please Enter title of Post ", Toast.LENGTH_LONG)
                        .show();

            else if (imageUrl.equals("")&& withImage )

                Toast.makeText(GltfActivity.this, "Please Upload Image of Post ", Toast.LENGTH_LONG)
                        .show();

            else {
                if (withImage)
                {
                    FirebaseCallBacks.storageRef.child("Pictures/" + tvMessage.getText().toString() + System.currentTimeMillis())
                            .putFile(Uri.parse(imageUrl)).addOnSuccessListener(taskSnapshot ->
                            {
                                Task<Uri> taskUp = Objects.requireNonNull(Objects.requireNonNull(taskSnapshot.getMetadata()).getReference()).getDownloadUrl();
                                taskUp.addOnSuccessListener(uri ->
                                {

                                    List<PostClass> replyList = new ArrayList<>();

                                    String key = mDatabase.child("user").child("object_labeled").child(anchorID).child("posts").push().getKey();
                                    PostClass obj = new PostClass(key, tvMessage.getText().toString(), SPmanager.getFirstName(GltfActivity.this), 0, 0, true, replyList, uri.toString());
                                    objlist.add(obj);

                                    FirebaseCallBacks.writeNewPost(mDatabase, GltfActivity.this, key, obj, anchorID);

                                    Toast.makeText(GltfActivity.this, "Create New Post with Image", Toast.LENGTH_LONG)
                                            .show();
                                    dialogAddInfo.dismiss();
                                }).addOnFailureListener(e -> {
                                    Toast.makeText(GltfActivity.this, "Failure: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    Log.e("Failure"," "+e.getMessage());

                                });


                            }).addOnFailureListener(e ->
                                    {
                                        Log.e("Failed"," "+e.getMessage() +"\n" +imageUrl);
                                        Toast.makeText(GltfActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_LONG).show();

                                    }
                            );
                }
                else
                {
                    List<PostClass> replyList = new ArrayList<>();
                    String key = mDatabase.child("user").child("object_labeled").child(anchorID).child("posts").push().getKey();
                    PostClass obj = new PostClass(key, tvMessage.getText().toString(), SPmanager.getFirstName(GltfActivity.this), 0, 0, true, replyList, "");
                    objlist.add(obj);

                    FirebaseCallBacks.writeNewPost(mDatabase, GltfActivity.this, key, obj, anchorID);

                    Toast.makeText(GltfActivity.this, "Create New Post", Toast.LENGTH_LONG)
                            .show();
                    dialogAddInfo.dismiss();
                }


                if(objlist.size() == 1)
                    setDataInAdaptor(objlist);
                else
                    adapterData.notifyDataSetChanged();
            }
        });
    }
    private void pickFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_Image);

    }
    @Override
    public void onSendComment(@NonNull String author, @NonNull String message, int level, @Nullable Integer parentId, int pos, boolean flag) {
        isComment = true;

        Random r = new Random();
        int i1 = r.nextInt(45 - 28) + 28;
        String key = mDatabase.child("user").child("object_labeled").child(anchorID).child("posts").child(objlist.get(pos).getId()).push().getKey();
        List<PostClass> nestedCommentList = new ArrayList<>();
        PostClass obj = new PostClass(key,message, author,level, parentId,true,nestedCommentList,"");
        objlist.get(pos).getRepliess().add(obj);

        FirebaseCallBacks.writeNewComment(mDatabase,GltfActivity.this,objlist.get(pos).getId(),key,obj,anchorID);
        adapterData.notifyItemChanged(pos);
    }
}

