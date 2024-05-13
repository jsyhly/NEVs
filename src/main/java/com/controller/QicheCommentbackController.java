
package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.QicheCommentbackEntity;
import com.entity.QicheEntity;
import com.entity.YonghuEntity;
import com.entity.view.QicheCommentbackView;
import com.service.*;
import com.utils.PageUtils;
import com.utils.PoiUtil;
import com.utils.R;
import com.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 汽车评价
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/qicheCommentback")
public class QicheCommentbackController {
    private static final Logger logger = LoggerFactory.getLogger(QicheCommentbackController.class);

    @Autowired
    private QicheCommentbackService qicheCommentbackService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private QicheService qicheService;
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = qicheCommentbackService.queryPage(params);

        //字典表数据转换
        List<QicheCommentbackView> list =(List<QicheCommentbackView>)page.getList();
        for(QicheCommentbackView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        QicheCommentbackEntity qicheCommentback = qicheCommentbackService.selectById(id);
        if(qicheCommentback !=null){
            //entity转view
            QicheCommentbackView view = new QicheCommentbackView();
            BeanUtils.copyProperties( qicheCommentback , view );//把实体数据重构到view中

                //级联表
                QicheEntity qiche = qicheService.selectById(qicheCommentback.getQicheId());
                if(qiche != null){
                    BeanUtils.copyProperties( qiche , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setQicheId(qiche.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(qicheCommentback.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody QicheCommentbackEntity qicheCommentback, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,qicheCommentback:{}",this.getClass().getName(),qicheCommentback.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            qicheCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        qicheCommentback.setInsertTime(new Date());
        qicheCommentback.setCreateTime(new Date());
        qicheCommentbackService.insert(qicheCommentback);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody QicheCommentbackEntity qicheCommentback, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,qicheCommentback:{}",this.getClass().getName(),qicheCommentback.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            qicheCommentback.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<QicheCommentbackEntity> queryWrapper = new EntityWrapper<QicheCommentbackEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QicheCommentbackEntity qicheCommentbackEntity = qicheCommentbackService.selectOne(queryWrapper);
        qicheCommentback.setUpdateTime(new Date());
        if(qicheCommentbackEntity==null){
            qicheCommentbackService.updateById(qicheCommentback);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        qicheCommentbackService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<QicheCommentbackEntity> qicheCommentbackList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            QicheCommentbackEntity qicheCommentbackEntity = new QicheCommentbackEntity();
//                            qicheCommentbackEntity.setQicheId(Integer.valueOf(data.get(0)));   //汽车 要改的
//                            qicheCommentbackEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            qicheCommentbackEntity.setQicheCommentbackText(data.get(0));                    //评价内容 要改的
//                            qicheCommentbackEntity.setInsertTime(date);//时间
//                            qicheCommentbackEntity.setReplyText(data.get(0));                    //回复内容 要改的
//                            qicheCommentbackEntity.setUpdateTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            qicheCommentbackEntity.setCreateTime(date);//时间
                            qicheCommentbackList.add(qicheCommentbackEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        qicheCommentbackService.insertBatch(qicheCommentbackList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = qicheCommentbackService.queryPage(params);

        //字典表数据转换
        List<QicheCommentbackView> list =(List<QicheCommentbackView>)page.getList();
        for(QicheCommentbackView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        QicheCommentbackEntity qicheCommentback = qicheCommentbackService.selectById(id);
            if(qicheCommentback !=null){


                //entity转view
                QicheCommentbackView view = new QicheCommentbackView();
                BeanUtils.copyProperties( qicheCommentback , view );//把实体数据重构到view中

                //级联表
                    QicheEntity qiche = qicheService.selectById(qicheCommentback.getQicheId());
                if(qiche != null){
                    BeanUtils.copyProperties( qiche , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setQicheId(qiche.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(qicheCommentback.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody QicheCommentbackEntity qicheCommentback, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,qicheCommentback:{}",this.getClass().getName(),qicheCommentback.toString());
        qicheCommentback.setInsertTime(new Date());
        qicheCommentback.setCreateTime(new Date());
        qicheCommentbackService.insert(qicheCommentback);
        return R.ok();
        }


}

