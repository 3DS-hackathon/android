package com.github.dan4ik95dv.app.util;

import android.content.Context;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Utils {


    private static final String TAG = Utils.class.getName();

    public static Object loadObject(Context context, String fileName) {
        File file = new File(context.getCacheDir(), fileName);
        return loadObject(file);
    }

    public static <T> T loadObject(File file) {
        Object instance = null;
        if (file.exists()) {
            ObjectInputStream ois = null;
            try {
                try {
                    ois = new ObjectInputStream(new FileInputStream(file));
                    instance = ois.readObject();
                } finally {
                    if (ois != null)
                        ois.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "loadObject from " + file + " failed", e);
            }
        }
        try {
            return (T) instance;
        } catch (ClassCastException e) {
            return null;
        }
    }

    public static void saveObject(Context context, String fileName, Object data) {
        saveObject(new File(context.getCacheDir(), fileName), data);
    }

    public static void saveObject(File file, Object data) {
        ObjectOutputStream oos = null;
        try {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(data);
            } finally {
                if (oos != null)
                    oos.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "saveObject to " + file + " failed", e);
        }
    }

    public static void deleteFile(Context context, String fileName) {
        new File(context.getCacheDir(), fileName).delete();
    }

    public static String readStringFromStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (is != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } finally {
                is.close();
            }
        }

        return sb.toString();
    }


    public static void copyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception e) {
            Log.e(TAG, "copyStream", e);
        }
    }

    public static void createDir(final File dir) throws IOException {
        if (!dir.exists())
            if (!dir.mkdirs())
                throw new IOException("Making dirs failed: " + dir.getAbsolutePath());
    }

    public static void deleteDir(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteDir(child);

        fileOrDirectory.delete();
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encrypt(String clearText, byte[] keyBytes)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] cipherText = cipher.doFinal(clearText.getBytes());
        return Base64.encodeToString(cipherText, Base64.DEFAULT);
    }

    public static String decrypt(String cipherText, byte[] keyBytes) throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] clearText = cipher.doFinal(Base64.decode(cipherText.getBytes(), Base64.DEFAULT));

        return new String(clearText);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e(TAG, "Unable to close quietly", e);
            }
    }

    public static void deleteQuietly(final File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory())
                for (final File f : file.listFiles())
                    deleteQuietly(f);

            if (!file.delete())
                file.deleteOnExit();
        }
    }

    public static void deleteSurely(final File file) throws IOException {
        if (file.isDirectory())
            for (final File f : file.listFiles())
                deleteSurely(f);

        if (!file.delete())
            throw new IOException("File " + file.getPath() + " failed");
    }

    public static boolean isUiThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static void assertUiThread() {
        if (!isUiThread())
            throw new RuntimeException("It should be UI thread");
    }

    public static void assertBackgroundThread() {
        if (isUiThread())
            throw new RuntimeException("It should be background thread");
    }

    public static String packArrayWithDelimiter(String[] array, String delimiter, String lastDelimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i != array.length - 1) {
                builder.append(delimiter);
            } else {
                builder.append(lastDelimiter);
            }
        }
        return builder.toString();
    }

    public static String packArrayWithDelimiter(long[] array, String delimiter, String lastDelimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i != array.length - 1) {
                builder.append(delimiter);
            } else {
                builder.append(lastDelimiter);
            }
        }
        return builder.toString();
    }

    public static String packArrayWithDelimiter(int[] array, String delimiter, String lastDelimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i != array.length - 1) {
                builder.append(delimiter);
            } else {
                builder.append(lastDelimiter);
            }
        }
        return builder.toString();
    }

    public static String packArrayWithDelimiter(List<Integer> array, String delimiter, String lastDelimiter) {
        StringBuilder builder = new StringBuilder();
        int size = array.size();
        for (int i = 0; i < size; i++) {
            builder.append(array.get(i));
            if (i != size - 1) {
                builder.append(delimiter);
            } else {
                builder.append(lastDelimiter);
            }
        }
        return builder.toString();
    }

    public static int daysBetween(long from, long to) {
        return (int) ((to - from) / (1000 * 60 * 60 * 24));
    }


    public static int randInt(int min, int max) {
        Random rand = new Random();
        return (rand.nextInt((max - min) + 1) + min);
    }

    public static double y2lat(double aY) {
        return Math.toDegrees(2 * Math.atan(Math.exp(Math.toRadians(aY))) - Math.PI / 2);
    }

    public static double lat2y(double aLat) {
        return Math.toDegrees(Math.log(Math.tan(Math.PI / 4 + Math.toRadians(aLat) / 2)));
    }


    public static String randomString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }


    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
