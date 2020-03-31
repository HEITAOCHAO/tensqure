package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 最新回答
     * @param id
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem right join tb_pl on id=problemid where labelid=? order by replytime desc",nativeQuery = true)
    Page<Problem> newList(String id, Pageable pageable);

    /**
     * 热门问题
     * @param id
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem right join tb_pl on id=problemid where labelid=? order by reply desc",nativeQuery = true)
    Page<Problem> hotList(String id, Pageable pageable);

    /**
     * 等待回复
     * @param id
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem right join tb_pl on id=problemid where labelid=? and reply=0 order by createtime desc",nativeQuery = true)
    Page<Problem> waitList(String id, Pageable pageable);

}
