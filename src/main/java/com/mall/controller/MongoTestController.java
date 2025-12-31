package com.mall.controller;

import com.mall.dao.MongoBookDao;
import com.mall.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
public class MongoTestController {
    @Autowired
    private MongoBookDao mongoBookDao;

    @PostMapping("/mongo/save")
    public String saveObj(@RequestBody Book book) {
        return mongoBookDao.saveObj(book);
    }

    @GetMapping("/mongo/findAll")
    public List<Book> findAll() {
        return mongoBookDao.findAll();
    }

    @GetMapping("/mongo/findOneByName")
    public Book findOneByName(@RequestParam String name) {
        return mongoBookDao.getBookByName(name);
    }

    @PostMapping("/mongo/update")
    public String update(@RequestBody Book book) {
        return mongoBookDao.updateBook(book);
    }

    @PostMapping("/mongo/delOne")
    public String delOne(@RequestBody Book book ) {
        int id = book.getId();
        return mongoBookDao.deleteBook(id);
    }
}
