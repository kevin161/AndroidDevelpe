package com.gyz.androiddevelope.util;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gyz.androiddevelope.engine.AppContants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.ResponseBody;

/**
 * @author: guoyazhou
 * @date: 2016-01-18 16:51
 */
public class FileUtil {
    private static final String TAG = "FileUtil";
    private static final String DefaultDirsFileName= AppContants.FILE_NAME;

    /**
     * 检查是否安装了sd卡
     *
     * @return false 未安装
     */
    public static boolean sdcardMounted() {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED) && !state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return true;
        }
        return false;
    }





    @NonNull
    public static File getDirsFile(){
        return getDirsFile(DefaultDirsFileName);
    }

    @NonNull
    public static File getDirsFile(String dirsFileName) {
        File file=  new File(Environment.getExternalStorageDirectory()+"/"+dirsFileName);
//        File file = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_DCIM), dirsFileName);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    /**
     * 获取SD卡剩余空间的大小
     *
     * @return long SD卡剩余空间的大小（单位：byte）
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getSDSize() {
        final String str = Environment.getExternalStorageDirectory().getPath();
        final StatFs localStatFs = new StatFs(str);
        final long blockSize = localStatFs.getBlockSize();
        return localStatFs.getAvailableBlocks() * blockSize;
    }

    public static String getMD5(String str) {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.trim().getBytes());
            byte[] messageDigest = digest.digest();
            return toHexString(messageDigest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;

    }

    // MD5相关函数
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 转换为十六进制字符串
     *
     * @param messageDigest byte数组
     * @return String byte数组处理后字符串
     */
    public static String toHexString(byte[] messageDigest) {

        StringBuffer sb = new StringBuffer(messageDigest.length * 2);
        for (final byte element : messageDigest) {
            sb.append(HEX_DIGITS[(element & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[element & 0x0f]);
        }
        return sb.toString();
    }

    public static void saveObject(String path, Object object) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        File file = new File(path);
        Log.d(TAG,"path=="+path);
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(object);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveMyBitmap(File file, Bitmap mBitmap){

        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * 从网络上获取图片，如果图片在本地存在的话就直接拿，如果不存在再去服务器上下载图片
    * 这里的path是图片的地址
    */
    public static Uri saveImgFromNet(String path, File file) throws Exception {
        // 如果图片存在本地缓存目录，则不去服务器下载
//        if (file.exists()) {
//            return Uri.fromFile(file);//Uri.fromFile(path)这个方法能得到文件的URI
//        } else {
            // 从网络上获取图片
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            if (conn.getResponseCode() == 200) {

                InputStream is = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                is.close();
                fos.close();
                // 返回一个URI对象
                return Uri.fromFile(file);
            }
//        }
        return null;
    }


    public static Object restoreObject(String path) {

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object object = null;

        File file = new File(path);

        if (!file.exists())
            return null;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            object = ois.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }


    public static String getPinsType(String type){
        if (type == null||type.isEmpty()) {
            return ".jpeg";
        }

        if (type.contains("jpeg")){
            return ".jpeg";
        }else if (type.contains("png")){
            return ".png";
        }else if (type.contains("gif")){
            return ".gif";
        }

        return ".jpeg";
    }



    public static File writeResponseBodyToDisk(File file, ResponseBody body, String name) {

        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(file.getPath(), name);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    if (fileSizeDownloaded == 0) {
                        LogUtils.d("file download: " + fileSizeDownloaded + " of " + fileSize);
                    }

                    if (fileSizeDownloaded == fileSize) {
                        LogUtils.d("file download: " + fileSizeDownloaded + " of " + fileSize);
                    }
                }
                outputStream.flush();
                //所有流程操作完成 返回true
                return futureStudioIconFile;
            } catch (IOException e) {
                //捕捉到写入异常 返回false
                return null;
            } finally {
                //finally修饰的代码块 一定执行 关闭流
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            //捕捉到 文件的异常 返回false
            return null;
        }

    }
}
