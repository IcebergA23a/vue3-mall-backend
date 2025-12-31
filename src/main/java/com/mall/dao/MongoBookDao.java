package com.mall.dao;

import com.mall.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author bingshan
 * @since 2025/12/31 17:28
 */
@Repository
public class MongoBookDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /* 保存对象*/
    public String saveObj(Book book) {
        book.setUpdateTime(new Date());
        mongoTemplate.save(book);
        return "添加成功";
    }

    /* 查询所有*/
    public List<Book> findAll() {
        return mongoTemplate.findAll(Book.class);
    }

    /*根据名称查询*/
    public Book getBookByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Book.class);
    }

    /*更新对象*/
    public String updateBook(Book book) {
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        Update update = new Update().set("name", book.getName()).set("updateTime",
                new Date());
        // updateFirst 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, Book.class);
        // updateMulti 更新查询返回结果集的全部
        // mongoTemplate.updateMulti(query,update,Book.class);
        // upsert 更新对象不存在则去添加
        // mongoTemplate.upsert(query,update,Book.class);
        return "success";
    }

    /* 删除对象*/
    public String deleteBook(int id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,Book.class);
        return "success";
    }
}
