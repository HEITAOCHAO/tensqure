package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取全部吐槽
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     * 根据id获取吐槽
     * @param spitid
     * @return
     */
    @RequestMapping(value = "/{spitid}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitid){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(spitid));
    }

    /**
     * 新增吐槽
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        System.out.println(spit.getContent());
        return new Result(true, StatusCode.OK,"查询成功",spitService.save(spit));
    }

    /**
     * 修改吐槽
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit){
        return new Result(true, StatusCode.OK,"查询成功",spitService.update(spit));
    }

    /**
     * 删除吐槽
     * @param spitid
     * @return
     */
    @RequestMapping(value = "/{spitid}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitid){
        spitService.delete(spitid);
        return new Result(true, StatusCode.OK,"查询成功");
    }

    /**
     * 根据父级id查询吐槽列表
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> pageList = spitService.findByParentid(parentid, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageList.getTotalElements(),pageList.getContent()));
    }

    /**
     * 吐槽点赞
     * @param spitid
     * @return
     */
    @RequestMapping(value = "/thumbup/{spitid}",method = RequestMethod.PUT)
    public Result addThumbup(@PathVariable String spitid){

        String userId="guoyeye";

        if(redisTemplate.opsForValue().get("thumbup_"+userId+"_"+spitid)!=null){
            return new Result(false, StatusCode.ERROR,"不能重复点赞");
        }
        redisTemplate.opsForValue().set("thumbup_"+userId+"_"+spitid,"1");

        spitService.addThumbup(spitid);
        return new Result(true, StatusCode.OK,"点赞成功");
    }
}
