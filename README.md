# MyNavigation
官方推荐架构组件Navigation 让单 Activity 应用成为首选架构，更好的管理Fragment框架

Navigation目前仅AndroidStudio 3.2以上版本支持，如果您的版本不足3.2，
下载AndroidStudio3.2以上版本请打开此地址：https://developer.android.google.cn/studio/

快速开发
组件可单独使用，也可以同时工作，当使用kotlin语言特性时，可以让你更有效率。
消除样板
代码Android Jetpack管理乏味的活动，例如后台任务、导航和生命周期管理，你可以专注于让你的app更棒的东西。
构建高质量、健壮的app
基于现代设计实践，Android Jetpack组件可以减少崩溃和内存泄漏，且向后兼容。
Navigation
本文今天主要讲述Navigation的使用以及如何管理多个Fragment等

项目builde.gradle文件需配置：
     implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
     implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
     
     
     

1.创建MainActivity和布局文件activity_navigation:
布局里配置:
 <fragment
        android:id="@+id/my_nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        app:defaultNavHost="true"
        app:navGraph="@navigation/mobile_navigation" />
        
    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_nav_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:labelVisibilityMode="labeled"
        app:menu="@menu/bottom_nav_menu" />
        
MainActivity里配置：

          val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return
          val navController = host.navController
          val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
          bottomNav?.setupWithNavController(navController)
          
          
2、新建HomeFragment、FlowStepFragment、FlowStepFragment、SettingsFragment、DeepLinkFragment  
在Fragment片段里跳片段方法

方法1：
       view.findViewById<View>(R.id.navigate_destination_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.next_action)
        )
 方法2：
 view.findViewById<Button>(R.id.navigate_destination_button)?.setOnClickListener {
            findNavController().navigate(R.id.flow_step_one_dest, null, null)
        }
        
        
3、在res里新建文件夹navigation 类型选择Navigation，然后在这个文件夹里创建mobile_navigation.xml
在mobile_navigation.xml里写入要跳转的各个片段Fragment及要传递的参数：
startDestination默认第一个跳的片段id
destination跳到另外一个片段id
action 隐式跳转 ，argType传递参数类型，defaultValue传递参数值

<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
    app:startDestination="@+id/home_dest">
    <fragment
        android:id="@+id/home_dest"
        android:name="com.my.navigation.HomeFragment"
        android:label="@string/home"
        tools:layout="@layout/home_fragment">

        <!--todo destination隐式跳转到Fragement id=flow_step_one_dest -->
        <action
            android:id="@+id/next_action"
            app:destination="@+id/flow_step_one_dest"
            app:enterAnim="@anim/slide_in_right"
            app:exitAnim="@anim/slide_out_left"
            app:popEnterAnim="@anim/slide_in_left"
            app:popExitAnim="@anim/slide_out_right" />

    </fragment>

    <fragment
        android:id="@+id/flow_step_one_dest"
        android:name="com.my.navigation.FlowStepFragment"
        tools:layout="@layout/flow_step_one_fragment">
        <argument
            android:name="flowStepNumber"
            app:argType="integer"
            android:defaultValue="1"/>

        <action
            android:id="@+id/next_action"
            app:destination="@+id/flow_step_two_dest">
        </action>
    </fragment>

    <fragment
        android:id="@+id/flow_step_two_dest"
        android:name="com.my.navigation.FlowStepFragment"
        tools:layout="@layout/flow_step_two_fragment">

        <argument
            android:name="flowStepNumber"
            app:argType="integer"
            android:defaultValue="2"/>

        <action
            android:id="@+id/next_action"
            app:destination="@+id/settings_dest">
        </action>
    </fragment>

    <fragment
        android:id="@+id/settings_dest"
        android:name="com.my.navigation.SettingsFragment"
        android:label="@string/settings"
        tools:layout="@layout/settings_fragment" >
        <action
            android:id="@+id/next_action"
            app:destination="@+id/deeplink_dest">
        </action>
    </fragment>

    <fragment
        android:id="@+id/deeplink_dest"
        android:name="com.my.navigation.DeepLinkFragment"
        android:label="@string/deeplink"
        tools:layout="@layout/deeplink_fragment">

        <argument
            android:name="myarg"
            android:defaultValue="Android!"/>
        <deepLink app:uri="www.baidu.com/{myarg}" />
    </fragment>
</navigation>


    
