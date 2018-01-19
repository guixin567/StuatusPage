### 好处
    封装前：
        状态布局文件写在当前Activity的布局中
        在Activity中需要相应的点击事件和部分控件的隐藏和显示
    封装后：
        不需要在Activity中写布局
        只需要当成一个组件，差不多10行代码搞写
        逻辑很清晰，适合用来做组件化
### 使用
  #### 继承BaseStatusUtil
    1,实现getLayout方法，根据自己的状态类别进行视图的赋值
    2,重写getClickId，指定每个控件可以点击控件的ID，每个布局文件这个ID必须一致
  ####  Activiti中的使用
    1，初始化状态页
        1，设置状态要替换的视图（当显示状态页时，要隐藏的视图）
        2，添加状态事件
            根据相应的控件类型，完成相应的点击事件
    2，在相应的地方设置相应的状态页类型，并展示
         mStatusDialog.setType(StatusDialog.TYPE_ERROR)
        .show(getSupportFragmentManager());
    
### 原理
    使用DialogFragment，将每个状态页当成一个Diaog这样就不会跟Activity的逻辑混 在一起，再提供相应的接口用于处理事件
    优点：
        组件化
        侵入性低
        使用简单，逻辑清晰
