spider-huaban
=============

#English
Simple java crawler for huaban

#Chinese
一个简单的Java爬虫程序，用来抓取并下载huaban.com中指定用户采集的图片。

##安装使用
###环境准备
- JDK，需要安装JDK1.6及其以上版本，确保在命令行模式下能正确执行 `java -version`；
- 安装最新版本的chrome浏览器；
###源码打包
如果您已下载release的文件请跳过；

- 确保已安装Maven；
- 在项目根目录下执行`mvn -package`，将会在target目录生成Huban-Spider.zip
###使用
- 解压Huban-Spider.zip
- 修改配置（具体参考“配置”章节）
- 执行run.bat
###配置
程序的配置文件为config.properties，配置项说明如下：

- huaban.userName：所要抓取的huaban.com网站的用户的用户名；
- huaban.downloadPath：图片下载的目录
- huaban.threadNum：启用多少线程下载图片
