package com.nfnote.service;

import com.nfnote.util.UriUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by codedrinker on 2020/08/16.
 */
@Service
@Slf4j
public class UriService {

    @Autowired
    private FileService fileService;

    public String generateUri() {
        List<String> uris = UriUtil.randomUris();
        for (String uri : uris) {
            boolean exist = fileService.isExist(uri);
            if (!exist) {
                return uri;
            }
        }
        return null;
    }
}
