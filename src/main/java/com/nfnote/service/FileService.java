package com.nfnote.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by codedrinker on 2020/08/16.
 */
@Service
@Slf4j
public class FileService {

    @Value("${file.path}")
    private String path;

    public boolean isExist(String fileName) {
        File file = getFile(fileName);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private boolean create(String fileName) throws IOException {
        File file = getFile(fileName);
        return file.createNewFile();
    }

    public void write(String fileName, String content) {
        File file = getFile(fileName);
        try {
            if (isExist(fileName) || create(fileName)) {
                FileUtils.write(file, content, Charset.forName("UTF-8"), false);
            }
        } catch (IOException e) {
            log.info("WRITE_FILE_ERROR:{}", fileName, e);
        }
    }

    public String read(String fileName) {
        File file = getFile(fileName);
        try {
            return isExist(fileName) ? FileUtils.readFileToString(file, Charset.forName("UTF-8")) : "";
        } catch (IOException e) {
            log.info("READ_FILE_ERROR:{}", fileName, e);
        }
        return "";
    }

    private File getFile(String fileName) {
        return new File(path + File.separator + fileName);
    }

    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        fileService.path = "/Users/codedrinker/Code/94note";
        String name = "19281";
        System.out.println(fileService.read("22323"));
        boolean exist = fileService.isExist(name);
        if (exist) {
            System.out.println("内容" + fileService.read(name));
        } else {
            fileService.create(name);
            fileService.write(name, "我就是内容");
        }
    }
}
