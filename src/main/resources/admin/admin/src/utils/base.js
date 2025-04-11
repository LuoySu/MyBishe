const base = {
    get() {
        return {
            url : "http://localhost:8080/sportshealth/",
            name: "sportshealth",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/sportshealth/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "运动爱好和健康管理平台"
        } 
    }
}
export default base
