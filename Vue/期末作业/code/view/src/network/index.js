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
    get_category_books_WaterFall(id,start,size) {
        return request.get("/category/waterBooks",{
            params: { id,start,size }
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
            params: { id: id,token:localStorage.getItem("token") }
        })
    },
    deleteGoodItem(id) {
        return loginRequest({
            url:"/shoppingCart/delete",
            method:'post',
            params: { id: id,token:localStorage.getItem("token") }
        })
    },
    get_shoppingCart() {
        return loginRequest({
            url:"/shoppingCart",
            method:'post',
            params: { token:localStorage.getItem("token") }
        })
    },
    getBookByName(kw) {
        return loginRequest({
            url: "/findBookByName",
            method: "post",
            params: {name:kw}
        })
    },
    findIfCollected(bid) {
        return loginRequest({
            url: "/findBookIfCollected",
            method: "post",
            params: {token:localStorage.getItem("token"),bid:bid}
        })
    },
    toCancelCollect(bid) {
        return loginRequest({
            url: "/toCancelCollect",
            method: "post",
            params: {token:localStorage.getItem("token"),bid:bid}
        })
    },
    toCollect(bid,original) {
        return loginRequest({
            url: "/toCollect",
            method: "post",
            params: {token:localStorage.getItem("token"),bid:bid,original:original}
        })
    },
    getCollection() {
        return loginRequest({
            url: "/toGetCollection",
            method: "post",
            params: {token:localStorage.getItem("token")}
        })
    },
    addUserTrack(id) {
        return loginRequest({
            url: "/toAddUserTrack",
            method: "post",
            params: {token:localStorage.getItem("token"),bid:id}
        })
    },
    getUserTrack() {
        return loginRequest({
            url: "/toGetUserTrack",
            method: "post",
            params: {token:localStorage.getItem("token")}
        })
    }
}
