#ExVolley

**先吐槽**：

Google的[Volley](https://android.googlesource.com/platform/frameworks/volley)很好很强大，但这货就是个sample，明明有大招的命却很难放出来。所以像Saber那样大喊一声`Excalibur`就能用才是王道

##Features
* Use [OkHttp](https://github.com/square/okhttp) as HttpStack,support SDPY
* Easy to use Fluent API designed for Android
* As powerful as Volley


##Samples

###Get JSON
	ExVolley.with(context).load("http://example.com/api/")
		.method(GET)
		.setResponseListener(listener. JsonObject.class)
		.setErrorListener(errorListener)
		.execute();
###Post JSON
	ExVolley.with(context).load("https://example.com/api/")
		.method(POST)
		.asJsonRequest()
		.setJsonBody(json)
		.setResponseListener(listener, JsonArray.class)
		.setErrorListner(errorListener)
		.execute();
###Set GET Params
	ExVolley.with(context).load("http:/example.com/api")
		.method(GET)
		.setParam("name", "jack")
		.setParams(hashmap)
		...
ExVolley parse params to url automatic.

###Set POST Params
	ExVolley.with(context).load("http:/example.com/api")
		.method(POST)
		.setParam("name", "jack")
		.setParams(hashmap)
		...

###cancel
	request = ExVolley.with(context).load("http:/example.com/api")
		.method(POST)
		.setTag("TAG")
		...
		.execute()
		
	ExVolley.cancel("TAG") or request.cancel()

You'd better put it in `onDestory()`
	
		
###More
	* setHeader(String key, String value)
	* shouldCache(Boolean should)
	* setRequestBody(String body)
	* setContentType(String type)
and so on~


##TODO
* session/cookie support
* make network imageload smoothly as Picasso

