import axios from "axios"
import request from "@/network/request";
import loginRequest from "@/network/loginRequest";
export default{
    get_category(){
        return request.get("/category");
    },
    login(name,passwd) {
        return request.post('/login',{
            name,
            passwd
        })
    },
    get_category_books(id) {
        return request.get("/category/books",{
            params: { id }
        });
    },
    get_book_detailed(id) {
        return request.get("/category/books/details",{
            params:{id}
        })
    },
    addGoodItem(id) {
        return loginRequest({
            url:"/shoppingCart/add",
            method:'post',
            params: { id: this.key }
        })
    }
}
