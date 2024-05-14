
package com.controller;


import com.alibaba.fastjson.JSONObject;
import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.QicheEntity;
import com.entity.QicheShijiaEntity;
import com.entity.YonghuEntity;
import com.entity.view.QicheShijiaView;
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
 * 汽车试驾预定
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/qicheShijia")
public class QicheShijiaController {
    private static final Logger logger = LoggerFactory.getLogger(QicheShijiaController.class);

    @Autowired
    private QicheShijiaService qicheShijiaService;


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
        PageUtils page = qicheShijiaService.queryPage(params);

        //字典表数据转换
        List<QicheShijiaView> list =(List<QicheShijiaView>)page.getList();
        for(QicheShijiaView c:list){
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
        QicheShijiaEntity qicheShijia = qicheShijiaService.selectById(id);
        if(qicheShijia !=null){
            //entity转view
            QicheShijiaView view = new QicheShijiaView();
            BeanUtils.copyProperties( qicheShijia , view );//把实体数据重构到view中

                //级联表
                QicheEntity qiche = qicheService.selectById(qicheShijia.getQicheId());
                if(qiche != null){
                    BeanUtils.copyProperties( qiche , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setQicheId(qiche.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(qicheShijia.getYonghuId());
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
    public R save(@RequestBody QicheShijiaEntity qicheShijia, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,qicheShijia:{}",this.getClass().getName(),qicheShijia.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            qicheShijia.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<QicheShijiaEntity> queryWrapper = new EntityWrapper<QicheShijiaEntity>()
            .eq("qiche_shijia_order_uuid_number", qicheShijia.getQicheShijiaOrderUuidNumber())
            .eq("qiche_id", qicheShijia.getQicheId())
            .eq("yonghu_id", qicheShijia.getYonghuId())
            .eq("qiche_shijia_order_yesno_types", qicheShijia.getQicheShijiaOrderYesnoTypes())
            .eq("qiche_shijia_order_yesno_text", qicheShijia.getQicheShijiaOrderYesnoText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QicheShijiaEntity qicheShijiaEntity = qicheShijiaService.selectOne(queryWrapper);
        if(qicheShijiaEntity==null){
            qicheShijia.setInsertTime(new Date());
            qicheShijia.setCreateTime(new Date());
            qicheShijiaService.insert(qicheShijia);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody QicheShijiaEntity qicheShijia, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,qicheShijia:{}",this.getClass().getName(),qicheShijia.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            qicheShijia.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<QicheShijiaEntity> queryWrapper = new EntityWrapper<QicheShijiaEntity>()
            .notIn("id",qicheShijia.getId())
            .andNew()
            .eq("qiche_shijia_order_uuid_number", qicheShijia.getQicheShijiaOrderUuidNumber())
            .eq("qiche_id", qicheShijia.getQicheId())
            .eq("yonghu_id", qicheShijia.getYonghuId())
            .eq("qiche_shijia_order_yesno_types", qicheShijia.getQicheShijiaOrderYesnoTypes())
            .eq("qiche_shijia_order_yesno_text", qicheShijia.getQicheShijiaOrderYesnoText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QicheShijiaEntity qicheShijiaEntity = qicheShijiaService.selectOne(queryWrapper);
        if(qicheShijiaEntity==null){
            qicheShijiaService.updateById(qicheShijia);//根据id更新
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
        qicheShijiaService.deleteBatchIds(Arrays.asList(ids));
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
            List<QicheShijiaEntity> qicheShijiaList = new ArrayList<>();//上传的东西
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
                            QicheShijiaEntity qicheShijiaEntity = new QicheShijiaEntity();
//                            qicheShijiaEntity.setQicheShijiaOrderUuidNumber(data.get(0));                    //编号 要改的
//                            qicheShijiaEntity.setQicheId(Integer.valueOf(data.get(0)));   //汽车 要改的
//                            qicheShijiaEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            qicheShijiaEntity.setQicheShijiaOrderTime(sdf.parse(data.get(0)));          //预定时间 要改的
//                            qicheShijiaEntity.setQicheShijiaOrderYesnoTypes(Integer.valueOf(data.get(0)));   //预定审核 要改的
//                            qicheShijiaEntity.setQicheShijiaOrderYesnoText(data.get(0));                    //审核结果 要改的
//                            qicheShijiaEntity.setInsertTime(date);//时间
//                            qicheShijiaEntity.setCreateTime(date);//时间
                            qicheShijiaList.add(qicheShijiaEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        qicheShijiaService.insertBatch(qicheShijiaList);
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
        PageUtils page = qicheShijiaService.queryPage(params);

        //字典表数据转换
        List<QicheShijiaView> list =(List<QicheShijiaView>)page.getList();
        for(QicheShijiaView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        QicheShijiaEntity qicheShijia = qicheShijiaService.selectById(id);
            if(qicheShijia !=null){


                //entity转view
                QicheShijiaView view = new QicheShijiaView();
                BeanUtils.copyProperties( qicheShijia , view );//把实体数据重构到view中

                //级联表
                    QicheEntity qiche = qicheService.selectById(qicheShijia.getQicheId());
                if(qiche != null){
                    BeanUtils.copyProperties( qiche , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setQicheId(qiche.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(qicheShijia.getYonghuId());
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
    public R add(@RequestBody QicheShijiaEntity qicheShijia, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,qicheShijia:{}",this.getClass().getName(),qicheShijia.toString());
        Wrapper<QicheShijiaEntity> queryWrapper = new EntityWrapper<QicheShijiaEntity>()
            .eq("qiche_shijia_order_uuid_number", qicheShijia.getQicheShijiaOrderUuidNumber())
            .eq("qiche_id", qicheShijia.getQicheId())
            .eq("yonghu_id", qicheShijia.getYonghuId())
            .eq("qiche_shijia_order_yesno_types", qicheShijia.getQicheShijiaOrderYesnoTypes())
            .eq("qiche_shijia_order_yesno_text", qicheShijia.getQicheShijiaOrderYesnoText())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QicheShijiaEntity qicheShijiaEntity = qicheShijiaService.selectOne(queryWrapper);
        if(qicheShijiaEntity==null){
            qicheShijia.setInsertTime(new Date());
            qicheShijia.setCreateTime(new Date());
        qicheShijiaService.insert(qicheShijia);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
