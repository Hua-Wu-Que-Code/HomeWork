//mock.js 导入依赖模块
const Mock = require('mockjs')
Mock.mock('/api/shoppingCart',{
    "books|1-5": [{
        "id|1": [
            "1","13","25","37","49"
        ],
        "num|1-10": 1
    }]
})