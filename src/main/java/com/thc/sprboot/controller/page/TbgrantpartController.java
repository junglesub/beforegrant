package com.thc.sprboot.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbgrantpart")
@Controller
public class TbgrantpartController {
    @GetMapping("/{page}")
    public String page(@PathVariable String page){
        return "tbgrantpart/" + page;
    }


    @GetMapping("/{page}/{id}")
    public String page(@PathVariable String page, @PathVariable String id){
        return "tbgrantpart/" + page;
    }
}
