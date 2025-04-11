
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
 * 运动信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/supplies")
public class SuppliesController {
    private static final Logger logger = LoggerFactory.getLogger(SuppliesController.class);

    private static final String TABLE_NAME = "supplies";

    @Autowired
    private SuppliesService suppliesService;


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
    private SuppliesCollectionService suppliesCollectionService;//运动收藏
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
        params.put("suppliesDeleteStart",1);params.put("suppliesDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = suppliesService.queryPage(params);

        //字典表数据转换
        List<SuppliesView> list =(List<SuppliesView>)page.getList();
        for(SuppliesView c:list){
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
        SuppliesEntity supplies = suppliesService.selectById(id);
        if(supplies !=null){
            //entity转view
            SuppliesView view = new SuppliesView();
            BeanUtils.copyProperties( supplies , view );//把实体数据重构到view中
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
    public R save(@RequestBody SuppliesEntity supplies, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,supplies:{}",this.getClass().getName(),supplies.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<SuppliesEntity> queryWrapper = new EntityWrapper<SuppliesEntity>()
            .eq("supplies_name", supplies.getSuppliesName())
            .eq("supplies_types", supplies.getSuppliesTypes())
            .eq("zuowei_number", supplies.getZuoweiNumber())
            .eq("zan_number", supplies.getZanNumber())
            .eq("cai_number", supplies.getCaiNumber())
            .eq("supplies_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SuppliesEntity suppliesEntity = suppliesService.selectOne(queryWrapper);
        if(suppliesEntity==null){
            supplies.setSuppliesDelete(1);
            supplies.setCreateTime(new Date());
            suppliesService.insert(supplies);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SuppliesEntity supplies, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,supplies:{}",this.getClass().getName(),supplies.toString());
        SuppliesEntity oldSuppliesEntity = suppliesService.selectById(supplies.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(supplies.getSuppliesPhoto()) || "null".equals(supplies.getSuppliesPhoto())){
                supplies.setSuppliesPhoto(null);
        }

            suppliesService.updateById(supplies);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SuppliesEntity> oldSuppliesList =suppliesService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<SuppliesEntity> list = new ArrayList<>();
        for(Integer id:ids){
            SuppliesEntity suppliesEntity = new SuppliesEntity();
            suppliesEntity.setId(id);
            suppliesEntity.setSuppliesDelete(2);
            list.add(suppliesEntity);
        }
        if(list != null && list.size() >0){
            suppliesService.updateBatchById(list);
        }

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
            List<SuppliesEntity> suppliesList = new ArrayList<>();//上传的东西
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
                            SuppliesEntity suppliesEntity = new SuppliesEntity();
//                            suppliesEntity.setSuppliesName(data.get(0));                    //运动标题 要改的
//                            suppliesEntity.setSuppliesPhoto("");//详情和图片
//                            suppliesEntity.setSuppliesTypes(Integer.valueOf(data.get(0)));   //运动类型 要改的
//                            suppliesEntity.setZuoweiNumber(Integer.valueOf(data.get(0)));   //消耗 要改的
//                            suppliesEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            suppliesEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            suppliesEntity.setSuppliesDelete(1);//逻辑删除字段
//                            suppliesEntity.setSuppliesContent("");//详情和图片
//                            suppliesEntity.setCreateTime(date);//时间
                            suppliesList.add(suppliesEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        suppliesService.insertBatch(suppliesList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<SuppliesView> returnSuppliesViewList = new ArrayList<>();

        //查询收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = suppliesCollectionService.queryPage(params1);
        List<SuppliesCollectionView> collectionViewsList =(List<SuppliesCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(SuppliesCollectionView orderView:collectionViewsList){
            Integer suppliesTypes = orderView.getSuppliesTypes();
            if(typeMap.containsKey(suppliesTypes)){
                typeMap.put(suppliesTypes,typeMap.get(suppliesTypes)+1);
            }else{
                typeMap.put(suppliesTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("suppliesTypes",type);
            PageUtils pageUtils1 = suppliesService.queryPage(params2);
            List<SuppliesView> suppliesViewList =(List<SuppliesView>)pageUtils1.getList();
            returnSuppliesViewList.addAll(suppliesViewList);
            if(returnSuppliesViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = suppliesService.queryPage(params);
        if(returnSuppliesViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnSuppliesViewList.size();//要添加的数量
            List<SuppliesView> suppliesViewList =(List<SuppliesView>)page.getList();
            for(SuppliesView suppliesView:suppliesViewList){
                Boolean addFlag = true;
                for(SuppliesView returnSuppliesView:returnSuppliesViewList){
                    if(returnSuppliesView.getId().intValue() ==suppliesView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnSuppliesViewList.add(suppliesView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnSuppliesViewList = returnSuppliesViewList.subList(0, limit);
        }

        for(SuppliesView c:returnSuppliesViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnSuppliesViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = suppliesService.queryPage(params);

        //字典表数据转换
        List<SuppliesView> list =(List<SuppliesView>)page.getList();
        for(SuppliesView c:list) {
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        }
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SuppliesEntity supplies = suppliesService.selectById(id);
            if(supplies !=null){


                //entity转view
                SuppliesView view = new SuppliesView();
                BeanUtils.copyProperties( supplies , view );//把实体数据重构到view中

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
    public R add(@RequestBody SuppliesEntity supplies, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,supplies:{}",this.getClass().getName(),supplies.toString());
        Wrapper<SuppliesEntity> queryWrapper = new EntityWrapper<SuppliesEntity>()
            .eq("supplies_name", supplies.getSuppliesName())
            .eq("supplies_types", supplies.getSuppliesTypes())
            .eq("zuowei_number", supplies.getZuoweiNumber())
            .eq("zan_number", supplies.getZanNumber())
            .eq("cai_number", supplies.getCaiNumber())
            .eq("supplies_delete", supplies.getSuppliesDelete())
//            .notIn("supplies_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SuppliesEntity suppliesEntity = suppliesService.selectOne(queryWrapper);
        if(suppliesEntity==null){
                supplies.setZanNumber(1);
                supplies.setCaiNumber(1);
            supplies.setSuppliesDelete(1);
            supplies.setCreateTime(new Date());
        suppliesService.insert(supplies);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

