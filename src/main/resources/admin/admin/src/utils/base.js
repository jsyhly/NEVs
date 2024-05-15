const base = {
    get() {
        return {
            url : "http://localhost:8080/qichexiaoshouc/",
            name: "qichexiaoshouc",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/qichexiaoshouc/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "新能源汽车预约系统"
        } 
    }
}
export default base
