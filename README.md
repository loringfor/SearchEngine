# SearchEngine

## 运行步骤
1. 导包，虽然为maven项目，但是有些本地包，在libs里面
2. 修改util包下LuceneUtils.java文件中directory的路径，这是索引文件存放的目录
3. 先运行HBaseDaoImpl下的main方法，可以操作Hbase数据库，插入数据
4. 运行IndexServiceImpl下的main方法，建立索引文件