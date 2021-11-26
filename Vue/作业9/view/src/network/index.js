import axios from "axios"
import request from "@/network/request";
export default{
    get_category(){
        return request.get("/category");
    },
    login(name,passwd) {
        return request.post('/login',{
            name,
            passwd
        })
    }

}
