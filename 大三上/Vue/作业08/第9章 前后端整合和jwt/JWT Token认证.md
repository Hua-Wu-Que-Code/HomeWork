# JWT Token
## 认证登录之session为什么不行？
session是同域的，发起请求的是前端，接收在后端，对浏览器来说，这是两个不同的地址，cookie是不同的。session无法透传（还是有的，百度**session共享**）

除此之外，session有很多限制，最大的问题在于session是有状态的，无法很好支持微服务，分布式部署也存在很多问题。

当前的认证机制都转向token方式，token是无状态的，他可以很好地支持跨域和多服务模式，此外开销也比session小。

## Token 是什么
以JWT为例。
```java
		Map map = new HashMap<String, String>();
		map.put("name", "tom");
		map.put("age", "18");
		String s = Jwts.builder().setHeaderParam("typ", "JWT")
		.signWith(SignatureAlgorithm.HS512, "asecretkey")
		.setClaims(map)
		.setSubject("tom1234") 
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+3600*1000))
		.compact();
		System.out.println(s);
```
运行后生成下token（我运行的当时，这里因为包含时间，所以可能不同）：
```
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b20xMjM0IiwibmFtZSI6InRvbSIsImV4cCI6MTYwNzMwODA3NCwiaWF0IjoxNjA3MzA0NDc0LCJhZ2UiOiIxOCJ9.6OHnC6gJEE1XiO9ANIeFJstouExNtMCaoYuqMRVNRVKK9w3BBHFzgPkc5fGZ3qP1TRUeDrTBYHIjixiKN296tA
```
他被```.```分成三块。
放到这里解码：
http://jwt.calebb.net/

```json
{
    typ: "JWT",
    alg: "HS512"
}.
{
    sub: "tom1234",
    name: "tom",
    exp: 1607308074,
    iat: 1607304474,
    age: "18"
}.
[signature]
```
一共有三块，前两块是BASE64编码的，可以直接解码，你没看错！**JWT数据是明文传输的，不要存放敏感信息**！最后一块由服务器的私钥进行hash，是加密的，无法解码。
第一个块是头部，这块申明了这个JWT的类型和加密算法，比如这里是```HS512```加密算法。
第二个块是负载(payload)，这个块存放具体信息，比如对于登录，你可以简单存放一个用户的id，**再次重申！JWT的payload是明文传输，不要存放敏感信息**。
第三个块是加密签名，这个块将保证信息不被伪造。他把前两个块的内容拼接后附加上一个附加密码然后进行hash操作。本例中就是：

```java
.signWith(SignatureAlgorithm.HS512, "asecretkey")
```
这个部分，也就是附加的密钥是```ascreatkey```了。

### Header头部信息：指定类型和算法

- typ：用来标识整个token字符串是一个JWT字符串
- alg：用来说明这个JWT签发的时候所使用的签名和摘要算法
- 一般签发JWT的时候，header对应的json结构只需要typ和alg属性就够了。JWT的header部分是把前面的json结构，经过Base64Url编码之后生成出来的

### Payload 荷载信息

**存放Claims声明信息既主体信息组成。用来存储JWT基本信息，或者是我们的信息。**

payload用来承载要传递的数据，它的json结构实际上是对JWT要传递的数据的一组声明，这些声明被JWT标准称为claims。当后面对JWT进行验证的时候，这些claim都能发挥特定的作用。



**根据JWT的标准，这些claims可以分为以下三种类型：**

- Reserved claims

保留的claims都是可选的，JWT标准里面针对它自己规定的claim都提供了有详细的验证规则描述，每个实现库都会参照这个描述来提供JWT的验证实现。

- Public calims

不重要

- Private claims

自定义的claim不会验证，除非明确告诉接收方要对这些claim进行验证以及规则才行；标准的claim知道如何进行验证。



### Signature

把前两者对应的Json结构进行base64url编码之后的字符串拼接起来和密钥放一起加密后的签名，验证是否是我们服务器发起的Token，secret是我们的密钥。使用base64拼接很容易破解，所以建议在传输过程中采用ssl加密是最稳妥的。

**组成方式：**header+playload+signature



## JWT如何运作
看图：
![jwt](images/jwt.png)
登录后，服务器生成一个JWT发送到浏览器。
浏览器需要把这个JWT记录下来。
以后的每次请求，浏览器都需要携带这个JWT。
服务端检查JWT是否合法（加密对不对，有没有超时）。合法则从中取出对应的字段进行处理。
我们看如何处理。

## 后端处理
### 工具类
先将JWT相关代码提炼成工具类：
```java
public class JwtUtil {
	final static String key = "testjwtsaltkey";
	final static Integer ttl = 24*60*60*1000;
	static public String generateToken( Integer id ) {
		String s = Jwts.builder()
			.setHeaderParam("typ", "JWT")
            //jwt的id
			.setId(id.toString())
			.setIssuedAt(new Date())
            //有效时间：当前系统时间+一整天
			.setExpiration(new Date(System.currentTimeMillis()+ttl)) 
            //签名算法  key就是自定义的签名
            .signWith(SignatureAlgorithm.HS512, key)
            //p
			.compact();
		return s;
	}
	
	static public Integer parseToken( String token ) {
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return Integer.parseInt(claims.getId());
	}
}
```
简单的两个函数，假定token里只放user的id，因此我们只需要setId即可，返回的值也只用Integer就行。

### JWT的签发
当用户登陆时，如果验证成功，就应该将token作为返回值返回。
```java
public class UserVO implements Serializable {

    private Integer id;
	private String username;
    private Integer privilege;
    private String token;
}
```
增加token字段。
```java
	public Result user( String username, String password) {
		User user = userMapper.login2(username, password);
		if(user == null || user.getId() == null) {
			return Result.fail("登陆失败");
		}
		UserVO vo = new UserVO(user);
		vo.setToken(JwtUtil.generateToken(user.getId()));
		return Result.succeed(vo);
	}
```
发送一下请求，返回值里就包含token了：
```json
{"code":100,"message":"OK","data":{"id":2,"username":"tom","privilege":0,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIyIiwiaWF0IjoxNjA3Mzk5ODM3LCJleHAiOjE2MDczOTk4NDd9.Z57AqT1AMDLrkmBaC1vqixWfs1mqcbYlQgypWoRinI2Gd3ieeekdBxw5-jcIP3tx3czWeHZ-VCbJzfdiXTlAGA"}}
```
此时前端应该将token记录下来。

### JWT的验证
用户做需要认证的访问时，将token同步带给后端，由后端来验证token的合法性并取出相关的内容。
一般我们将token放在header的Authorization字段里。
例如：
```java
	@RequestMapping("checklog")
	public Result userCheck( HttpServletRequest req ) {
		try {
			String token = req.getHeader("Authorization");
			Integer id = JwtUtil.parseToken(token);
			System.out.println(id);
		}catch(ExpiredJwtException e) {
			return Result.fail("token超时");
		}catch(Exception e) {
			return Result.fail(e.toString());
		}
		return Result.succeed();
	}
```
在PostMan或者其他发包工具里发送请求，得到结果：
```json
{"code":100,"message":"OK","data":null}
```
如果将token稍作修改，则后端验证会失败，返回值就变成：
```json
{"code":101,"message":"io.jsonwebtoken.SignatureException: JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.","data":null}
```
告诉你验证失败了。

通过这个方法我们可以很方便地实现token信息的验证。

## 统一认证接口
JWT认证和错误处理有相似的问题，他们的代码都很相似，如果每个控制器都做处理，就非常多余。。一般可以配置SpringMVC的拦截器来统一处理。

### SpringMVC拦截器
SpringMVC的拦截器都要实现```HandlerInterceptor```接口。看实现：
```java
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception{
		String token = req.getHeader("Authorization");
		System.out.println(token);
		Integer id = JwtUtil.parseToken(token);
		req.setAttribute("UserID", id);
		req.setAttribute("UserToken", token);
		return true;
	}
}
```
接口```HandlerInterceptor```有三个函数可以实现：
- ```preHandle```：访问Controller之前调用，如果返回true则放行，false就中断。
- ```postHandle```：Controller处理之后调用，但此时还没有dispatcher到视图。
- ```afterCompletion```：在视图渲染后调用，这个对我们返回json的情况没有用。

我们实例化第一个，在这里获取token并解析，将id和token放入request attribute中。
如果解析错误，抛出异常，则会由统一的处理接口处理。

申明拦截器之后，还需要将其加入拦截器链中才能进行：
```java
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(new AuthInterceptor())
		   .addPathPatterns("/**")
		   .excludePathPatterns("/user/login");
	}
}
```
申明```WebMvcConfigurationSupport```，这个跟```MybatisPlusConfig```的作用一样。用来进行SpringMVC的基础配置。这个配置用来取代原有的xml配置。

类```InterceptorRegistry```用来添加拦截器并配置。```addInterceptors```添加拦截器。后面的```addPathPatterns```设置拦截器的拦截模式，跟servlet之类的配置类似，这里的```/**```表示适配所有url地址。```excludePathPatterns```用来设置排除模式。这里排除```/user/login```（想想为什么）。

比较好的做法是做好配置，```excludePathPatterns```接受```List<String>```，所以可以这样做：
```java
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(new AuthInterceptor())
		   .addPathPatterns("/**")
		   .excludePathPatterns(excludePattern());
	}
	
	public List<String> excludePattern(){
		List<String> ret = new ArrayList<String>();
		ret.add("/user/login");
		ret.add("/book/*");
		return ret;
	}
}
```
需要时再改写excludePattern函数即可。
测试一下看看行不行。

## 前端
前端需要保存token并在下次请求时带上token。前者可以存放在localStorage里，后者还是用axios的拦截器来处理更方便。

### token的保存
创建一个info.vue页面来处理登陆的内容。
```js
      onQuit(){
         window.localStorage.removeItem("token");
         window.localStorage.removeItem("username");
         this.nologin = true;
         this.loginname = "未登录";
      },
      onSubmit(){
         self = this;
         network.login(this.username, this.password)
         .then(function(res){
            window.localStorage.setItem("token",res.data.token)
            window.localStorage.setItem("username",res.data.username);
            self.nologin = false;
            self.loginname = res.data.username;
         })
      }
```
这里```onSubmit```调用之后，