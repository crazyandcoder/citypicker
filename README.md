### **CityPicker 城市选择器**

 
**在实际的项目中一般情况下都需要使用到省市区三级联动地址选择的功能，有的公司是提供接口获取，有的公司则不是，需要自己实现。一开始，我也深受其扰，每次都是要复制一遍，就想能不能打个包出来，供大伙使用。所以自己就封装了一个，不需要自己添加数据源，直接引用即可。这就是CityPicker城市选择器的由来！**

### **功能点**

 1. 支持仿iOS滚轮实现、仿京东样式、一级城市列表、三级城市列表
 2. 支持多种自定义属性来满足你的需求
 3. 简单的几行代码即可实现城市选择器功能
 4. 支持使用自己的数据来实现城市选择器

#### **gradle引用**

1. 在 Project 的 build.gradle 中增加 jitpack 的 maven 地址

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. 在 app 的 build.gradle 中增加我们开源库的引用

```
	dependencies {
	        implementation 'com.github.crazyandcoder:citypicker:6.0.2'
	}
```


### **更新说明**
#### 最新版本

**V6.0.2版本更新内容（2021.07.09）**
  1. 更新数据源，使用最新省市区行政数据
  2. 优化汉字转拼音逻辑，提高解析精度


#### 历史更新记录

 [历史更新说明](https://github.com/crazyandcoder/citypicker/wiki/%E5%8E%86%E5%8F%B2%E6%9B%B4%E6%96%B0%E8%AE%B0%E5%BD%95)



### **数据来源**

[2018年12月中华人民共和国县以上行政区划代码](http://www.mca.gov.cn/article/sj/xzqh/2018/201804-12/20181201301111.html)



[台湾数据（维基百科）需翻墙](https://zh.wikipedia.org/wiki/%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E8%A1%8C%E6%94%BF%E5%8C%BA%E5%88%92%E4%BB%A3%E7%A0%81_(7%E5%8C%BA))


[港澳数据（维基百科）需翻墙](https://zh.wikipedia.org/wiki/%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E8%A1%8C%E6%94%BF%E5%8C%BA%E5%88%92%E4%BB%A3%E7%A0%81_(8%E5%8C%BA))





### **效果展示**



| 样式说明 | 图片效果    |
|:--------:| :--------:|
| 样式选择 | ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191029194110335.jpg)|
| 仿iOS滚轮实现|![8.11x16.19-1080x2050](https://img-blog.csdnimg.cn/20190123144159879.png) ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190123144219666.png)|
| 自定义数据源 | ![在这里插入图片描述](https://img-blog.csdnimg.cn/20191029194154486.jpg)|
|仿京东样式| ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190123144403951.png) ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190123144413329.png)|
| 一级城市列表| ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190123144423843.png) ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190123144432482.png)|
| 三级城市列表| ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190123144441529.png) ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190123144449550.png)![在这里插入图片描述](https://img-blog.csdnimg.cn/20190123144507182.png)|

 
 
### **CityPicker使用方法**


#### **一. 仿iOS样式使用方法**

 1. [样式一链接-（仿iOS滚轮实现)](https://github.com/crazyandcoder/citypicker/wiki/%E6%A0%B7%E5%BC%8F%E4%B8%80%EF%BC%88%E4%BB%BFiOS%E6%BB%9A%E8%BD%AE%E5%AE%9E%E7%8E%B0%EF%BC%89)
 2. [样式一链接-（自定义数据源)](https://github.com/crazyandcoder/citypicker/wiki/%E6%A0%B7%E5%BC%8F%E4%B8%80%EF%BC%88%E4%BB%BFiOS%E6%BB%9A%E8%BD%AE%E5%AE%9E%E7%8E%B0%EF%BC%89%E8%87%AA%E5%AE%9A%E4%B9%89%E6%95%B0%E6%8D%AE%E6%BA%90)

#### **二. 仿京东样式使用方法**
[样式二链接-（仿京东样式)](https://github.com/crazyandcoder/citypicker/wiki/%E6%A0%B7%E5%BC%8F%E5%9B%9B%EF%BC%88%E4%BB%BF%E4%BA%AC%E4%B8%9C%E6%A0%B7%E5%BC%8F%EF%BC%89)

#### **三. 一级城市列表使用方法**
[样式三链接-一级城市列表使用方法](https://github.com/crazyandcoder/citypicker/wiki/%E6%A0%B7%E5%BC%8F%E4%BA%8C%EF%BC%88%E5%9F%8E%E5%B8%82%E4%B8%80%E7%BA%A7%E5%88%97%E8%A1%A8%E5%B1%95%E7%A4%BA%EF%BC%89)

#### **四. 三级城市列表使用方法**
[样式四链接-三级城市列表使用方法](https://github.com/crazyandcoder/citypicker/wiki/%E6%A0%B7%E5%BC%8F%E4%B8%89%EF%BC%88%E7%9C%81%E5%B8%82%E5%8C%BA%E4%B8%89%E7%BA%A7%E5%88%97%E8%A1%A8%EF%BC%89)




#### **代码混淆**

[代码混淆解决方案](https://github.com/crazyandcoder/citypicker/wiki/%E4%BB%A3%E7%A0%81%E6%B7%B7%E6%B7%86)



### **专属QQ群，欢迎加入！**

QQ群：417846442


### **其他问题wiki**

https://github.com/crazyandcoder/citypicker/wiki

### **License**
```
Copyright 2016 crazyandcoder

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


### 码农的小世界
欢迎关注本人公众号，学习更多知识，每天定时更新干货。

<p align="center">
  <a href="javascript:;" rel="noopener" target="_blank"><img width="70%" src="https://img-blog.csdnimg.cn/20191029194659502.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xqMTg4MjY2,size_16,color_FFFFFF,t_70" alt="citypicker logo"></a></p>
</p>
 
 


 
