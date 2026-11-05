# Java 程序设计实验项目（8 个）

依据《实验内容提要(1).docx》正文生成。原文目录称 7 个实验，但正文为 8 个；这里全部保留，实验四为智能图形设计师。

## 使用方式

1. 安装 JDK 8 或更新版本和 IntelliJ IDEA（支持 Java 的版本即可）。JRE 不能替代 JDK。
2. 完整解压压缩包。每个 exptXX 都是独立 IDEA 项目，请逐个打开相应目录，不要只打开单个 .java 文件。
3. File → Project Structure 设置本机 Project SDK，Language level 为 8。
4. 阅读该项目的《实验说明.md》，运行对应 main 类。源码为 UTF-8。

无需 Maven、Gradle、数据库或网络服务。代码均为控制台应用，不需要安装 GitHub Desktop。`.idea` 与 `.iml` 已包含源码目录、编码和运行配置，首次使用只需选择本机 JDK。

| 项目 | 实验 | 入口 |
| --- | --- | --- |
| expt01 | Java 编程环境与 HelloWorld | HelloWorld |
| expt02 | 狼人杀游戏 | WerewolfGame |
| expt03 | 汽车租赁系统 | RentalApp |
| expt04 | 智能图形设计师 | ShapeApp |
| expt05 | 美食烹饪大赛 | CookingApp |
| expt06 | 打卡异常提醒 | AttendanceApp |
| expt07 | 智慧农业月度实际预算统计 | Agricultural |
| expt08 | 模拟冰墩墩挂件预售 | Panda |

## 说明材料

每个项目包含实验步骤、代码知识、运行命令、预期结果及原文问题修正说明。另附《实验知识与步骤汇总.docx》，方便使用 Word 阅读和整理报告。演示数据不冒充实际运行截图。

源码兼容 Java 8；云端的编译与行为验证情况见《验证结果.md》。未在图形版 IDEA 中逐项点击验证，也未在真实 JDK 8 上运行；IDEA 元数据经过 XML 检查，Java 8 API/字节码兼容性由 --release 8 编译验证。
