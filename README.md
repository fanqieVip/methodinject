# MethodInject for Android 安卓AOP框架
## 使用编译期注入字节码技术，无性能瓶颈
## 一行注解实现拦截，没有比这更简单的集成方式了
## 全面支持外部类、内部类、匿名内部类、类无限嵌套的方法的拦截
## 支持无限个拦截处理器

## 前言
在相当长的时间内，安卓开发领域，没有一个真正方便自由的AOP框架。有的方案或依赖于宿主框架、或依赖于多态，实现过程繁杂，无法真正做到与业务完全解耦，直到MethodInject诞生！
MethodInject是完全基于APT+Gradle开发的框架，它在编译期自动向您的java文件对应的class注入字节码，您无需关注性能问题；
MethodInject的使用方式及其简单，你只需在你需要处理拦截的方法上加上注解就可以实现自动拦截；
MethodInject开启了使用java代码动态编程的新纪元，比如，一些老旧的运行时注解方案可基于MethodInject改造成性能更好的框架。
```
【啰嗦一句，有网友问我：为什么只用Gradle插件就可以实现，为什么还要用APT？原因是在Gradle插件中遍历所有目录的class，同时还要遍历class中的Method，性能开销太大，如果使用APT提前生成要处理的class表，那么在Gradle插件工作的时候就没那么费劲了。】
```
## 使用方式
### 外部类
```Java
@InjectClass //拦截方法所属的类要加上InjectClass注解
public class A {

    @Inject(TestInject.class) //加上Inject注解完成方法拦截 TestInject.class为你自定义的拦截处理器
    void goHome(){
        
    }
}
```

### 内部类
```Java
 public class A {
    @InjectClass //拦截方法所属的类要加上InjectClass注解
    class B{
        @Inject({TestInject.class, TestInject2.class}) 
        void hellow(){

        }
    }   
 }

```
### 匿名内部类及嵌套
```Java
@InjectClass //拦截方法所属的类要加上InjectClass注解
public class A {

    void innerClassM(){
        new Runnable(){
            @Override
            public void run() {
                new Runnable(){
                    @Inject(TestInject.class) //只要你喜欢，怎么嵌套都行
                    @Override
                    public void run() {

                    }
                };
            }
        };
    }
}
```
### InjectProcessor处理器
```Java
//必须实现InjectProcessor协议
public class TestInject implements InjectProcessor {
    
    //true:拦截  false:不拦截 （注意，如果你注入的方法有返回值的话，拦截会失效，但依然会执行这个方法）
    @Override
    public boolean intercept() {
        return true;
    }
}
```

### 集成方式

#### 在project的build.gradle中使用Gradle插件
```Xml
dependencies {
        classpath 'com.fanjun:injectplugin:1.0.0'
    }
```
#### 在Moudle的build.gradle中使用API及APT处理器插件
```Xml
dependencies {
    implementation 'com.fanjun:methodinject:1.0.0'
    annotationProcessor 'com.fanjun:methodinjectcompiler:1.0.0'
}
```

#### 联系我
```Xml
我的博客：https://blog.csdn.net/qwe112113215
```
```Xml
我的邮箱：810343451@qq.com
```
