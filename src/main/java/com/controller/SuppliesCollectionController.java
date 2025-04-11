
package com.controller;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;

import com.service.TokenService;
import com.utils.*;

import com.service.DictionaryService;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 运动收藏
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/suppliesCollection")
public class SuppliesCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(SuppliesCollectionController.class);

    private static final String TABLE_NAME = "suppliesCollection";

    @Autowired
    private SuppliesCollectionService suppliesCollectionService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChatService chatService;//客服聊天
    @Autowired
    private DictionaryService dictionaryService;//字典表
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//通知公告
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private SuppliesService suppliesService;//运动信息
    @Autowired
    private SuppliesLiuyanService suppliesLiuyanService;//运动留言
    @Autowired
    private UsersService usersService;//管理员


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
        CommonUtil.checkMap(params);
        PageUtils page = suppliesCollectionService.queryPage(params);

        //字典表数据转换
        List<SuppliesCollectionView> list =(List<SuppliesCollectionView>)page.getList();
        for(SuppliesCollectionView c:list){
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
        SuppliesCollectionEntity suppliesCollection = suppliesCollectionService.selectById(id);
        if(suppliesCollection !=null){
            //entity转view
            SuppliesCollectionView view = new SuppliesCollectionView();
            BeanUtils.copyProperties( suppliesCollection , view );//把实体数据重构到view中
            //级联表 运动信息
            //级联表
            SuppliesEntity supplies = suppliesService.selectById(suppliesCollection.getSuppliesId());
            if(supplies != null){
            BeanUtils.copyProperties( supplies , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setSuppliesId(supplies.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(suppliesCollection.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody SuppliesCollectionEntity suppliesCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,suppliesCollection:{}",this.getClass().getName(),suppliesCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            suppliesCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<SuppliesCollectionEntity> queryWrapper = new EntityWrapper<SuppliesCollectionEntity>()
            .eq("supplies_id", suppliesCollection.getSuppliesId())
            .eq("yonghu_id", suppliesCollection.getYonghuId())
            .eq("supplies_collection_types", suppliesCollection.getSuppliesCollectionTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SuppliesCollectionEntity suppliesCollectionEntity = suppliesCollectionService.selectOne(queryWrapper);
        if(suppliesCollectionEntity==null){
            suppliesCollection.setInsertTime(new Date());
            suppliesCollection.setCreateTime(new Date());
            suppliesCollectionService.insert(suppliesCollection);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SuppliesCollectionEntity suppliesCollection, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,suppliesCollection:{}",this.getClass().getName(),suppliesCollection.toString());
        SuppliesCollectionEntity oldSuppliesCollectionEntity = suppliesCollectionService.selectById(suppliesCollection.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            suppliesCollection.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            suppliesCollectionService.updateById(suppliesCollection);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SuppliesCollectionEntity> oldSuppliesCollectionList =suppliesCollectionService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        suppliesCollectionService.deleteBatchIds(Arrays.asList(ids));

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
            List<SuppliesCollectionEntity> suppliesCollectionList = new ArrayList<>();//上传的东西
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
                            SuppliesCollectionEntity suppliesCollectionEntity = new SuppliesCollectionEntity();
//                            suppliesCollectionEntity.setSuppliesId(Integer.valueOf(data.get(0)));   //运动 要改的
//                            suppliesCollectionEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            suppliesCollectionEntity.setSuppliesCollectionTypes(Integer.valueOf(data.get(0)));   //类型 要改的
//                            suppliesCollectionEntity.setInsertTime(date);//时间
//                            suppliesCollectionEntity.setCreateTime(date);//时间
                            suppliesCollectionList.add(suppliesCollectionEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        suppliesCollectionService.insertBatch(suppliesCollectionList);
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

        CommonUtil.checkMap(params);
        PageUtils page = suppliesCollectionService.queryPage(params);

        //字典表数据转换
        List<SuppliesCollectionView> list =(List<SuppliesCollectionView>)page.getList();
        for(SuppliesCollectionView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SuppliesCollectionEntity suppliesCollection = suppliesCollectionService.selectById(id);
            if(suppliesCollection !=null){


                //entity转view
                SuppliesCollectionView view = new SuppliesCollectionView();
                BeanUtils.copyProperties( suppliesCollection , view );//把实体数据重构到view中

                //级联表
                    SuppliesEntity supplies = suppliesService.selectById(suppliesCollection.getSuppliesId());
                if(supplies != null){
                    BeanUtils.copyProperties( supplies , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSuppliesId(supplies.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(suppliesCollection.getYonghuId());
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
    public R add(@RequestBody SuppliesCollectionEntity suppliesCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,suppliesCollection:{}",this.getClass().getName(),suppliesCollection.toString());
        Wrapper<SuppliesCollectionEntity> queryWrapper = new EntityWrapper<SuppliesCollectionEntity>()
            .eq("supplies_id", suppliesCollection.getSuppliesId())
            .eq("yonghu_id", suppliesCollection.getYonghuId())
            .eq("supplies_collection_types", suppliesCollection.getSuppliesCollectionTypes())
//            .notIn("supplies_collection_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SuppliesCollectionEntity suppliesCollectionEntity = suppliesCollectionService.selectOne(queryWrapper);
        if(suppliesCollectionEntity==null){
            suppliesCollection.setInsertTime(new Date());
            suppliesCollection.setCreateTime(new Date());
        suppliesCollectionService.insert(suppliesCollection);

            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }

}

