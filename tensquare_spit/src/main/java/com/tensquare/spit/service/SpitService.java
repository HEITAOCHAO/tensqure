package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit save(Spit spit){
        spit.set_id(new IdWorker(0,0).nextId()+"");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态

        if(spit.getParentid()!=null&&!"".equals(spit.getParentid())){
            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update=new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }

        return spitDao.save(spit);
    }

    public Spit update(Spit spit){
        return spitDao.save(spit);
    }

    public void delete(String id){
        spitDao.deleteById(id);
    }

    /**
     * 据父级ID查询吐槽列表
     * @param id
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String id, int page,int size){
        Pageable pageable1 = PageRequest.of(page-1,size);
        return spitDao.findByParentid(id,pageable1);
    }

    /**
     * 吐槽点赞
     * @param spitid
     */
    public void addThumbup(String spitid){
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(spitid));
        Update update=new Update();
        update.inc("visits",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }


}
