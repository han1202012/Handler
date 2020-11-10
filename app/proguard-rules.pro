# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 保留 MainActivity 类名
#-keep public class kim.hsl.handler.MainActivity

# 保留 MainActivity 类名,成员变量名称
#-keep public class kim.hsl.handler.MainActivity{
#    *;
#}

# 保留 Keep 注解
#-keep public class kim.hsl.handler.Keep

# 保留被 Keep 注解修饰的类
#-keep @kim.hsl.handler.Keep class * {*;}

# 指定 kim.hsl.handler.Main 类所有成员不被混淆
#-keepclassmembers class kim.hsl.handler.Handler

# 指定 kim.hsl.handler.Main 类 public void *(*) 成员不被混淆
#-keepclassmembers class kim.hsl.handler.Handler{
#    public void *(*);
#}

# 保留 kim.hsl.handler.Handler 类名 , 成员名会被混淆
#-keepclasseswithmembernames class kim.hsl.handler.Handler

# 保留 kim.hsl.handler.Handler 类名 , 成员名称
#-keepclasseswithmembernames class kim.hsl.handler.Handler{
#    *;
#}

# 保留 kim.hsl.handler.Handler 类名 , 成员名称
#-keepclasseswithmembernames class kim.hsl.handler.Handler{
#    *;
#}


# 保留 kim.hsl.handler.Handler native 方法名称
#-keepclasseswithmembernames class kim.hsl.handler.Handler{
#    native <methods>;
#}

# 保留行数
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute H


