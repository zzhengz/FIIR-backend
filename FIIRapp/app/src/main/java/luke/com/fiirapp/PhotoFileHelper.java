package luke.com.fiirapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.graphics.BitmapFactory;

/**
 * Created by luke on 10/5/16.
 *
 * Helper class to execute filesystem operations
 */

public class PhotoFileHelper {
/*
    private static final String CAMERA_DIR = "/dcim/";
    private static Activity activity;
    private static String currentPhotoPath;

    public PhotoFileHelper(Activity activity){
        this.activity = activity;
    }
    private static File getAlbumStorageDir(String albumName) {
        return new File (
                Environment.getExternalStorageDirectory()
                        + CAMERA_DIR
                        + albumName
        );
    }

    private static File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(getAlbumName());

            if (storageDir != null) {
                if (! storageDir.mkdirs()) {
                    if (! storageDir.exists()){
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            Log.v(activity.getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }
    private static String getAlbumName() {
        return activity.getString(R.string.album_name);
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, ".jpg", albumF);
        return imageF;
    }

    private File setUpPhotoFile() throws IOException {

        File f = createImageFile();
        currentPhotoPath = f.getAbsolutePath();

        return f;
    }

    private static void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        activity.sendBroadcast(mediaScanIntent);
    }
*/

    public static String getRandomString(int size){
        String s="";
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        int length = characters.length();
        for(int i=0; i<size; i++){
            s += characters.charAt((int)(Math.random()*length));
        }
        return s;
    }

    public static void publishPhoto(String bigFile, String tempFile){
        //resize photo
        File file = resizeImage(new File(bigFile), new File(tempFile), 1370, 1080);
        String filepath = file.getAbsolutePath();


        // publish resized photo
        Log.i("publishPhoto", "publishing photo at " + filepath);
        ApiRequest request = new ApiRequest("http://fiirapp.ddns.net:9096");
        request.setCredentials("DX0SRPYUYZQ4ZQXYSRNWOGZZCPCMIWQS","1");
        request.pics_create(filepath, "0", "");
        String response = request.getResponse();
        Log.i("api response", response);
    }

    public static File resizeImage(File file, File temp, int width, int height){
        String filepath = file.getAbsolutePath();
        Bitmap bitmap = BitmapFactory.decodeFile(filepath);
        Bitmap out = Bitmap.createScaledBitmap(bitmap, width, height, false);
        FileOutputStream fOut;

        try {
            fOut = new FileOutputStream(temp);
            out.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
            fOut.flush();
            fOut.close();
            bitmap.recycle();
            out.recycle();
        } catch (Exception e) {
            e.printStackTrace();
            return file;
        }

        file.delete();
        return temp;
    }

}
