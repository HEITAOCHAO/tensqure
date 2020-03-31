package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void updata(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label){
        return labelDao.findAll(createSpecification(label));
    }

    public Page<Label> findSearch(Label label,int page,int size){
        PageRequest pageRequest=PageRequest.of(page-1,size);
        return labelDao.findAll(createSpecification(label), pageRequest);
    }

    public Specification  createSpecification(Label label){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> pr=new ArrayList<>();
                if(label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    pr.add(cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%"));
                }
                if(label.getState()!=null&&!"".equals(label.getState())){
                    pr.add(cb.equal(root.get("state").as(String.class),label.getState()));
                }
                if(label.getRecommend()!=null&&!"".equals(label.getRecommend())) {
                    pr.add(cb.equal(root.get("recommend").as(String.class), label.getRecommend()));
                }
                return cb.and(pr.toArray(new Predicate[pr.size()]));
            }
        };
    }


}
