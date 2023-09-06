package com.Hecht.ar.Helper;

import static com.Hecht.ar.UserFragment.anchorID;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.Hecht.ar.ModelClass.PostClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class FirebaseCallBacks {
    public static final StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    private static OnPostEventListener mPostEventListner;

    public static void writeNewPost(DatabaseReference mDatabase, Context con, String id, PostClass obj,String objectId) {

        mDatabase.child("user").child("object_labeled").child(objectId).child("posts").child(id).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(con,"Post successfully",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(con,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void writeNewPost(DatabaseReference mDatabase, Context con, String id,PostClass obj) {

        mDatabase.child("posts").child(id).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(con,"Post successfully",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(con,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void writeNewComment(DatabaseReference mDatabase, Context con, String id,String comentID,PostClass obj,String anchorid) {

        mDatabase.child("user").child("object_labeled").child(anchorid).child("posts").child(id).child("replies").child(comentID).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(con,"Post successfully",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(con,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    public static void  updateApikey(DatabaseReference mDatabase,String userID,String apiKey){
//        mDatabase.child("users").child(userID).child("api_key").setValue(apiKey);
//    }
//
//    public static void writeFile(DatabaseReference mDatabase,String userId, Context con,String fileId, String url,String name) {
//        FileModelClass file = new FileModelClass(url,fileId,name,SPmanager.getEnvironmentID(con));
//
//        mDatabase.child("users").child(userId).child("3d_models").child(fileId).setValue(file).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Utils.showCenteredToastmessage(con,"Successfully uploaded");
//                }
//            }
//        });
//    }
//
//    public static void writeAccessPoint(DatabaseReference mDatabase,String userId,AccessPointModelClass obj, Context con,String fileId,String apNumber) {
//        AccessPointModelClass ap = new AccessPointModelClass(obj.getId(),obj.getPosX(),obj.getPosY(),obj.getPosZ(),obj.getType(),0);
//
//        mDatabase.child("users").child(userId).child("3d_models").child(fileId).child("access_points").child(apNumber).setValue(ap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Utils.showCenteredToastmessage(con,"Successfully ap uploaded");
//                }
//            }
//        });
//    }
//
//    public static void writeOrigin(DatabaseReference mDatabase, OriginModelClass obj, Context con, String userId,String fileId) {
//        OriginModelClass origin = new OriginModelClass(obj.getPosX(),obj.getPosY(),obj.getPosZ());
//
//        mDatabase.child("users").child(userId).child("3d_models").child(fileId).child("origin").setValue(origin).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()){
//                    Utils.showCenteredToastmessage(con,"Successfully origin uploaded");
//                }
//            }
//        });
//    }
//
//    public static void readFiles(DatabaseReference mDatabase, String userID,Context context){
//
//
//
//        mDatabase = FirebaseDatabase.getInstance().getReference("users");
//
//        mDatabase.child(userID).child("3d_models").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                List<FileModelClass> fileList = new ArrayList<>();
//                if(!uploadAp) {
//                    for (DataSnapshot singleSnapshot : snapshot.getChildren()) {
//                        FileModelClass file = singleSnapshot.getValue(FileModelClass.class);
//                        fileList.add(file);
//                    }
//                    mOnEventListener.onSuccessListner(fileList);
//                }else {
//                    uploadAp = false;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        }
//
    public static void readPost(DatabaseReference mDatabase,String id){

        List<PostClass> postList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //.getReference("users");

        mDatabase.child("user").child("object_labeled").child(id).child("posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot singleSnapshot : snapshot.getChildren()){
                    List<PostClass> comentList = new ArrayList<>();
                    PostClass post = singleSnapshot.getValue(PostClass.class);
                    for(DataSnapshot snapShot : singleSnapshot.child("replies").getChildren()){
                        PostClass obj = snapShot.getValue(PostClass.class);
                        comentList.add(obj);
                    }
                    post.setRepliess(comentList);
                    postList.add(post);
                }
                mPostEventListner.onPostSuccessListner(postList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        mDatabase.child("posts").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for(DataSnapshot singleSnapshot : snapshot.getChildren()){
//                    List<PostClass> comentList = new ArrayList<>();
//                         PostClass post = singleSnapshot.getValue(PostClass.class);
//                    for(DataSnapshot snapShot : singleSnapshot.child("replies").getChildren()){
//                        PostClass obj = snapShot.getValue(PostClass.class);
//                        comentList.add(obj);
//                    }
//                    post.setRepliess(comentList);
//                    postList.add(post);
//                }
//                mPostEventListner.onPostSuccessListner(postList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
//
//    public static void deleteDataFromFirebase(DatabaseReference mDatabase, String  id, String userID,String modelID ){
//        mDatabase.child("users").child(userID).child("3d_models").child(modelID).child("access_point").child(id).removeValue(new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//
//            }
//        });
//    }
//    public static void setOnItemSuccessListener(OnEventListener listener) {
//        mOnEventListener = listener;
//    }
//
    public static void postSuccessListener(OnPostEventListener listener) {
        mPostEventListner = listener;
    }
//
//    public static void userRegisterSuccessListener(OnRegisterUSerEventListener listener) {
//        mUserRegisterEventListner = listener;
//    }
//
//    public interface OnEventListener {
//        void onSuccessListner(List<FileModelClass> fileList);
//    }
//
//    public interface OnRegisterUSerEventListener {
//        void onRegisterSuccessListner(boolean reg);
//    }
//
    public interface OnPostEventListener {
        void onPostSuccessListner(List<PostClass> postList);
    }
}
