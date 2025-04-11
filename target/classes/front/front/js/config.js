var projectName = '运动爱好和健康管理平台';
/**
 * 轮播图配置
 */
var swiper = {
    // 设定轮播容器宽度，支持像素和百分比
    width: '100%',
    height: '400px',
    // hover（悬停显示）
    // always（始终显示）
    // none（始终不显示）
    arrow: 'none',
    // default（左右切换）
    // updown（上下切换）
    // fade（渐隐渐显切换）
    anim: 'default',
    // 自动切换的时间间隔
    // 默认3000
    interval: 2000,
    // 指示器位置
    // inside（容器内部）
    // outside（容器外部）
    // none（不显示）
    indicator: 'outside'
}

/**
 * 个人中心菜单
 */
var centerMenu = [
    {
        name: '个人中心',
        url: '../' + localStorage.getItem('userTable') + '/center.html'
    }
    ,{
        name: '运动收藏',
        url: '../yundongCollection/list.html'
    }
    ,{
        name: '膳食收藏',
        url: '../meishiCollection/list.html'
    },{
        name: '运动用品收藏',
        url: '../suppliesCollection/list.html'
    }
    // ,{
    //     name: '运动留言',
    //     url: '../suppliesLiuyan/list1.html'
    // }
    ,
    {
        name: '运动浏览记录',
        url: '../storeup/list1.html'
    },
    {
        name: '膳食浏览记录',
        url: '../storeup/list2.html'
    },
    {
        name: '运动用品记录',
        url: '../storeup/list3.html'
    }

]


var indexNav = [

    // {
    //     name: '论坛',
    //     url: './pages/forum/list1.html'
    // },
    // {
    //     name: '通知公告',
    //     url: './pages/news/list1.html'
    // },
    // {
    //     name: '运动信息',
    //     url: './pages/supplies/list1.html'
    // },

]

// 已废弃,不用了
var adminurl = "http://localhost:8080/sportshealth/admin/dist/index.html#/login";


//
var menu = [
]


var isAuth = function (tableName, key) {
    let role = localStorage.getItem("userTable");
    let menus = menu;
    for (let i = 0; i < menus.length; i++) {
        if (menus[i].tableName == role) {
            for (let j = 0; j < menus[i].backMenu.length; j++) {
                for (let k = 0; k < menus[i].backMenu[j].child.length; k++) {
                    if (tableName == menus[i].backMenu[j].child[k].tableName) {
                        let buttons = menus[i].backMenu[j].child[k].buttons.join(',');
                        return buttons.indexOf(key) !== -1 || false
                    }
                }
            }
        }
    }
    return false;
}

var isFrontAuth = function (tableName, key) {
    let role = localStorage.getItem("userTable");
    let menus = menu;
    for (let i = 0; i < menus.length; i++) {
        if (menus[i].tableName == role) {
            for (let j = 0; j < menus[i].frontMenu.length; j++) {
                for (let k = 0; k < menus[i].frontMenu[j].child.length; k++) {
                    if (tableName == menus[i].frontMenu[j].child[k].tableName) {
                        let buttons = menus[i].frontMenu[j].child[k].buttons.join(',');
                        return buttons.indexOf(key) !== -1 || false
                    }
                }
            }
        }
    }
    return false;
}
