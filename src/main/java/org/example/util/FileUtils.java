package org.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {
    public static void delete(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }

        if (dir.isFile()) {
            deleteFile(dir);
            return;
        }
        File[] files = dir.listFiles();
        for (File file : files) {
            delete(file);
        }
        deleteFile(dir);
    }

    private static void deleteFile(File file) {
        if (file == null) {
            return;
        }

        if (file.exists()) {
            if (!file.delete()) {
                file.deleteOnExit();
            }
        }
    }

    public static void createCompressedFile(ZipOutputStream out, File file, String dir) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            out.putNextEntry(new ZipEntry(dir + "/"));

            dir = dir.length() == 0 ? "" : dir + "/";

            for (int i = 0; i < files.length; i++) {
                createCompressedFile(out, files[i], dir + files[i].getName()); // 递归处理
            }
        } else {
            FileInputStream fis = new FileInputStream(file);

            out.putNextEntry(new ZipEntry(dir));
            int j = 0;
            byte[] buffer = new byte[1024];
            while ((j = fis.read(buffer)) > 0) {
                out.write(buffer, 0, j);
            }
            fis.close();
        }
    }


}
