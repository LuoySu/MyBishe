
package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.StoreupEntity;
import com.entity.YundongEntity;
import com.entity.view.StoreupView;
import com.entity.view.YundongCollectionView;
import com.entity.view.YundongView;
import com.service.*;
import com.utils.*;
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
 * 运动教程
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yundong")
public class YundongController {
    private static final Logger logger = LoggerFactory.getLogger(YundongController.class);

    private static final String TABLE_NAME = "yundong";

    @Autowired
    private YundongService yundongService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//反馈
    @Autowired
    private MeishiService meishiService;//膳食信息
    @Autowired
    private MeishiCollectionService meishiCollectionService;//膳食信息收藏
    @Autowired
    private NewsService newsService;//公告信息
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private YundongCollectionService yundongCollectionService;//运动教程收藏
    @Autowired
    private UsersService usersService;//管理员
    @Autowired
    private StoreupService storeupService;


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
        params.put("yundongDeleteStart",1);params.put("yundongDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = yundongService.queryPage(params);

        //字典表数据转换
        List<YundongView> list =(List<YundongView>)page.getList();
        for(YundongView c:list){
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
        YundongEntity yundong = yundongService.selectById(id);
        if(yundong !=null){
            //entity转view
            YundongView view = new YundongView();
            BeanUtils.copyProperties( yundong , view );//把实体数据重构到view中
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
    public R save(@RequestBody YundongEntity yundong, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yundong:{}",this.getClass().getName(),yundong.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YundongEntity> queryWrapper = new EntityWrapper<YundongEntity>()
            .eq("yundong_name", yundong.getYundongName())
            .eq("yundong_types", yundong.getYundongTypes())
            .eq("yundong_video", yundong.getYundongVideo())
            .eq("yundong_num", yundong.getYundongNum())
            .eq("zan_number", yundong.getZanNumber())
            .eq("cai_number", yundong.getCaiNumber())
            .eq("yundong_delete", yundong.getYundongDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YundongEntity yundongEntity = yundongService.selectOne(queryWrapper);
        if(yundongEntity==null){
            yundong.setYundongDelete(1);
            yundong.setInsertTime(new Date());
            yundong.setCreateTime(new Date());
            yundongService.insert(yundong);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YundongEntity yundong, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yundong:{}",this.getClass().getName(),yundong.toString());
        YundongEntity oldYundongEntity = yundongService.selectById(yundong.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(yundong.getYundongPhoto()) || "null".equals(yundong.getYundongPhoto())){
                yundong.setYundongPhoto(null);
        }
        if("".equals(yundong.getYundongVideo()) || "null".equals(yundong.getYundongVideo())){
                yundong.setYundongVideo(null);
        }

            yundongService.updateById(yundong);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YundongEntity> oldYundongList =yundongService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<YundongEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YundongEntity yundongEntity = new YundongEntity();
            yundongEntity.setId(id);
            yundongEntity.setYundongDelete(2);
            list.add(yundongEntity);
        }
        if(list != null && list.size() >0){
            yundongService.updateBatchById(list);
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
            List<YundongEntity> yundongList = new ArrayList<>();//上传的东西
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
                            YundongEntity yundongEntity = new YundongEntity();
                            yundongList.add(yundongEntity);
                        }

                        //查询是否重复
                        yundongService.insertBatch(yundongList);
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
     * 获取所有用户的收藏信息：通过 yundongCollectionService.queryPage 方法获取所有用户的收藏信息。
     * 构建用户 - 运动类型收藏映射：使用 userCollectionMap 存储每个用户收藏的运动类型。
     * 计算用户相似度：通过计算当前用户与其他用户收藏的运动类型的交集大小来衡量相似度。
     * 按相似度排序：找到与当前用户最相似的用户。
     * 获取推荐的运动类型：从最相似的用户的收藏中选取当前用户未收藏的运动类型。
     * 根据推荐的运动类型查询运动信息：使用 yundongService.queryPage 方法查询推荐的运动信息。
     * 获取浏览记录信息：调用 storeupService.queryPage(allUserParams) 方法获取所有用户的浏览记录信息。
     * 构建用户 - 运动类型映射：将浏览记录信息也纳入到 userCollectionMap 中，通过 type 方法从浏览记录中提取运动类型。
     * 补全推荐信息：如果推荐的运动信息数量不足，从正常查询结果中补全。
     */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}", this.getClass().getName(), JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<YundongView> returnYundongViewList = new ArrayList<>();

        // 获取当前用户ID
        Integer currentUserId = (Integer) request.getSession().getAttribute("userId");

        // 获取所有用户的收藏信息
        Map<String, Object> allUserParams = new HashMap<>(params);
        allUserParams.put("sort", "id");
        PageUtils allUserPageUtils = yundongCollectionService.queryPage(allUserParams);
        List<YundongCollectionView> allUserCollectionViewsList = (List<YundongCollectionView>) allUserPageUtils.getList();

        StoreupEntity storeup=new StoreupEntity();
        storeup.setTablename("yundong");
        // 获取所有用户的浏览记录信息
        EntityWrapper<StoreupEntity> ewss = new EntityWrapper<StoreupEntity>();
        ewss.allEq(MPUtil.allEQMapPre(storeup, "storeup"));
        List<StoreupView> allUserStoreupViewsList =storeupService.selectListView(ewss);

        // 构建用户 - 运动类型收藏和浏览记录映射
        Map<Integer, Set<Integer>> userCollectionMap = new HashMap<>();
        // 处理收藏信息
        for (YundongCollectionView collectionView : allUserCollectionViewsList) {
            Integer userId = collectionView.getYonghuId();
            Integer yundongTypes = collectionView.getYundongTypes();
            userCollectionMap.computeIfAbsent(userId, k -> new HashSet<>()).add(yundongTypes);
        }
        // 处理浏览记录信息
        for (StoreupView storeupView : allUserStoreupViewsList) {
            Integer userId = storeupView.getUserid().intValue();
            Integer yundongTypes = storeupView.getTypes(); // 假设可以从浏览记录中获取运动类型
            if (yundongTypes != null) {
                userCollectionMap.computeIfAbsent(userId, k -> new HashSet<>()).add(yundongTypes);
            }
        }

        // 计算当前用户与其他用户的相似度
        Map<Integer, Integer> similarityMap = new HashMap<>();
        Set<Integer> currentUserCollections = userCollectionMap.getOrDefault(currentUserId, new HashSet<>());
        for (Map.Entry<Integer, Set<Integer>> entry : userCollectionMap.entrySet()) {
            Integer otherUserId = entry.getKey();
            if (!otherUserId.equals(currentUserId)) {
                Set<Integer> otherUserCollections = entry.getValue();
                int intersectionSize = 0;
                for (Integer type : currentUserCollections) {
                    if (otherUserCollections.contains(type)) {
                        intersectionSize++;
                    }
                }
                similarityMap.put(otherUserId, intersectionSize);
            }
        }

        // 按相似度排序，找到最相似的用户
        List<Map.Entry<Integer, Integer>> sortedSimilarityList = new ArrayList<>(similarityMap.entrySet());
        sortedSimilarityList.sort((o1, o2) -> o2.getValue() - o1.getValue());

        // 获取推荐的运动类型
        Set<Integer> recommendedTypes = new HashSet<>();
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for (Map.Entry<Integer, Integer> entry : sortedSimilarityList) {
            Integer similarUserId = entry.getKey();
            Set<Integer> similarUserCollections = userCollectionMap.get(similarUserId);
            for (Integer type : similarUserCollections) {
                if (!currentUserCollections.contains(type)) {
                    recommendedTypes.add(type);
                }
                if (recommendedTypes.size() >= limit) {
                    break;
                }
            }
            if (recommendedTypes.size() >= limit) {
                break;
            }
        }

        // 根据推荐的运动类型查询运动信息
        for (Integer type : recommendedTypes) {
            Map<String, Object> params2 = new HashMap<>(params);
            params2.put("yundongTypes", type);
            PageUtils pageUtils1 = yundongService.queryPage(params2);
            List<YundongView> yundongViewList = (List<YundongView>) pageUtils1.getList();
            returnYundongViewList.addAll(yundongViewList);
            if (returnYundongViewList.size() >= limit) {
                break;
            }
        }

        // 正常查询出来商品，用于补全推荐缺少的数据
        PageUtils page = yundongService.queryPage(params);
        if (returnYundongViewList.size() < limit) {
            int toAddNum = limit - returnYundongViewList.size();
            List<YundongView> yundongViewList = (List<YundongView>) page.getList();
            for (YundongView yundongView : yundongViewList) {
                Boolean addFlag = true;
                for (YundongView returnYundongView : returnYundongViewList) {
                    if (returnYundongView.getId().intValue() == yundongView.getId().intValue()) {
                        addFlag = false;
                    }
                }
                if (addFlag) {
                    toAddNum = toAddNum - 1;
                    returnYundongViewList.add(yundongView);
                    if (toAddNum == 0) {
                        break;
                    }
                }
            }
        } else {
            returnYundongViewList = returnYundongViewList.subList(0, limit);
        }

        for (YundongView c : returnYundongViewList) {
            dictionaryService.dictionaryConvert(c, request);
        }
        page.setList(returnYundongViewList);
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
        PageUtils page = yundongService.queryPage(params);

        //字典表数据转换
        List<YundongView> list =(List<YundongView>)page.getList();
        for(YundongView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YundongEntity yundong = yundongService.selectById(id);
            if(yundong !=null){


                //entity转view
                YundongView view = new YundongView();
                BeanUtils.copyProperties( yundong , view );//把实体数据重构到view中

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
    public R add(@RequestBody YundongEntity yundong, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yundong:{}",this.getClass().getName(),yundong.toString());
        Wrapper<YundongEntity> queryWrapper = new EntityWrapper<YundongEntity>()
            .eq("yundong_name", yundong.getYundongName())
            .eq("yundong_types", yundong.getYundongTypes())
            .eq("yundong_video", yundong.getYundongVideo())
            .eq("yundong_num", yundong.getYundongNum())
            .eq("zan_number", yundong.getZanNumber())
            .eq("cai_number", yundong.getCaiNumber())
            .eq("yundong_delete", yundong.getYundongDelete())
//            .notIn("yundong_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YundongEntity yundongEntity = yundongService.selectOne(queryWrapper);
        if(yundongEntity==null){
                yundong.setZanNumber(1);
                yundong.setCaiNumber(1);
            yundong.setYundongDelete(1);
            yundong.setInsertTime(new Date());
            yundong.setCreateTime(new Date());
        yundongService.insert(yundong);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

