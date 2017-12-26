# algorithm_training
自己的算法总结，顺便重新造个轮子


## 项目结构
包名 | 相关内容
--- | ---
com.radium4ye.algorithm.graph |  图相关算法
com.radium4ye.algorithm.string |  字符串相关算法


###字符串

类名 | 名称
--- | ---
Quick3String | 高位优先三向快排
BoyerMoore | BM 字符匹配算法
RabinKarp  | Rabin-Karp 指纹字符串查找
KMPByDFA | 通过确认有限状态机模拟 KMP 算法
KMP | KMP 算法


###有向加权图 - 计算最短路径

类名 | 适用面 |优势
--- | --- | ---
TopologicalSP | 无环 | 能在线性时间处理解决最短路径问题，比 Dijkstra 算法更快、更简单，无环图中最优算法
Dijkstra | 无负权重边 | 最坏的情况下也有较好的性能
BellmanFord  | 无负权重环 | 适用面广

