import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
    // 解决多次点击左侧菜单报错问题
    const VueRouterPush = VueRouter.prototype.push
    VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
    }
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import beifen from '@/views/modules/databaseBackup/beifen'
import huanyuan from '@/views/modules/databaseBackup/huanyuan'

     import users from '@/views/modules/users/list'
    import chat from '@/views/modules/chat/list'
    import dictionary from '@/views/modules/dictionary/list'
    import forum from '@/views/modules/forum/list'
    import news from '@/views/modules/news/list'
    import yonghu from '@/views/modules/yonghu/list'
    import supplies from '@/views/modules/supplies/list'
    import suppliesCollection from '@/views/modules/suppliesCollection/list'
    import suppliesLiuyan from '@/views/modules/suppliesLiuyan/list'
    import suppliesOrder from '@/views/modules/suppliesOrder/list'
    import config from '@/views/modules/config/list'
    import dictionaryChat from '@/views/modules/dictionaryChat/list'
    import dictionaryForumState from '@/views/modules/dictionaryForumState/list'
    import dictionaryNews from '@/views/modules/dictionaryNews/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryZhuangtai from '@/views/modules/dictionaryZhuangtai/list'
    import dictionarySupplies from '@/views/modules/dictionarySupplies/list'
    import dictionarySuppliesCollection from '@/views/modules/dictionarySuppliesCollection/list'
    import dictionarySuppliesOrder from '@/views/modules/dictionarySuppliesOrder/list'
import meishi from '@/views/modules/meishi/list'
import meishiCollection from '@/views/modules/meishiCollection/list'
import yundong from '@/views/modules/yundong/list'
import yundongCollection from '@/views/modules/yundongCollection/list'
import dictionaryMeishi from '@/views/modules/dictionaryMeishi/list'
import dictionaryMeishiCollection from '@/views/modules/dictionaryMeishiCollection/list'
import dictionaryYundong from '@/views/modules/dictionaryYundong/list'
import dictionaryYundongCollection from '@/views/modules/dictionaryYundongCollection/list'




//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }, {
        path: '/huanyuan',
        name: '数据还原',
        component: huanyuan
    }, {
        path: '/beifen',
        name: '数据备份',
        component: beifen
    }, {
        path: '/users',
        name: '管理信息',
        component: users
    }
        ,{
            path: '/dictionaryMeishi',
            name: '膳食类型',
            component: dictionaryMeishi
        }
        ,{
            path: '/dictionaryMeishiCollection',
            name: '收藏表类型',
            component: dictionaryMeishiCollection
        }
    ,{
        path: '/dictionaryChat',
        name: '数据类型',
        component: dictionaryChat
    }
    ,{
        path: '/dictionaryForumState',
        name: '帖子状态',
        component: dictionaryForumState
    }
        ,{
            path: '/dictionaryYundongCollection',
            name: '收藏表类型',
            component: dictionaryYundongCollection
        }
    ,{
        path: '/dictionaryNews',
        name: '新闻资讯类型',
        component: dictionaryNews
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryZhuangtai',
        name: '状态',
        component: dictionaryZhuangtai
    }
    ,{
        path: '/dictionarySupplies',
        name: '用品类型',
        component: dictionarySupplies
    }
    ,{
        path: '/dictionarySuppliesCollection',
        name: '收藏表类型',
        component: dictionarySuppliesCollection
    }
        ,{
            path: '/dictionaryYundong',
            name: '运动类型',
            component: dictionaryYundong
        }
    ,{
        path: '/dictionarySuppliesOrder',
        name: '预约类型',
        component: dictionarySuppliesOrder
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/chat',
        name: '客服聊天',
        component: chat
      }
    ,{
        path: '/dictionary',
        name: '字典表',
        component: dictionary
      }
    ,{
        path: '/forum',
        name: '论坛',
        component: forum
      }
    ,{
        path: '/news',
        name: '新闻资讯',
        component: news
      }
    ,{
        path: '/yonghu',
        name: '用户',
        component: yonghu
      }
    ,{
        path: '/supplies',
        name: '用品管理',
        component: supplies
      }
    ,{
        path: '/suppliesCollection',
        name: '用品收藏',
        component: suppliesCollection
      }
        ,{
            path: '/yundong',
            name: '运动教程',
            component: yundong
        }
        ,{
            path: '/yundongCollection',
            name: '运动教程收藏',
            component: yundongCollection
        }
        ,{
            path: '/meishi',
            name: '膳食信息',
            component: meishi
        }
        ,{
            path: '/meishiCollection',
            name: '膳食信息收藏',
            component: meishiCollection
        }
    ,{
        path: '/suppliesLiuyan',
        name: '运动留言',
        component: suppliesLiuyan
      }
    ,{
        path: '/suppliesOrder',
        name: '订座预约',
        component: suppliesOrder
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
