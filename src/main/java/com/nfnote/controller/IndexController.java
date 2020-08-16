package com.nfnote.controller;

import com.nfnote.dto.ResultDTO;
import com.nfnote.service.FileService;
import com.nfnote.service.UriService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by codedrinker on 2020/08/16.
 */
@Controller
@Slf4j
public class IndexController {

    @Value("${domain}")
    private String domain;

    @Autowired
    private UriService uriService;

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String index() {
        String uri = uriService.generateUri();
        if (uri != null) {
            return "redirect:/" + uri;
        }
        return "redirect:/";
    }

    @GetMapping("/{uri}")
    public String uri(@PathVariable(name = "uri") String uri, Model model) {
        log.info("URI_ACCESS:{}", uri);
        model.addAttribute("uri", domain + "/" + uri);
        model.addAttribute("content", fileService.read(uri));
        return "index";
    }

    @PostMapping("/{uri}")
    @ResponseBody
    public ResultDTO save(@PathVariable(name = "uri") String uri, @RequestBody String content) {
        log.info("URI_SAVE:{}", uri);
        if (StringUtils.isAlphanumeric(uri)) {
            fileService.write(uri, content);
        }
        return ResultDTO.okOf();
    }
}
