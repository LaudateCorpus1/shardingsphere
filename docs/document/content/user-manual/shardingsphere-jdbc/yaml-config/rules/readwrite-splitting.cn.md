+++
title = "读写分离"
weight = 2
+++

## 配置项说明

```yaml
rules:
- !READWRITE_SPLITTING
  dataSources:
    <data-source-name> (+): # 读写分离逻辑数据源名称
      writeDataSourceName: # 写库数据源名称
      readDataSourceNames: 
        - <read-data_source-name> (+) # 读库数据源名称
      loadBalancerName: # 负载均衡算法名称
  
  # 负载均衡算法配置
  loadBalancers:
    <load-balancer-name> (+): # 负载均衡算法名称
      type: # 负载均衡算法类型
      props: # 负载均衡算法属性配置
        # ...
```

算法类型的详情，请参见[内置负载均衡算法列表](/cn/user-manual/shardingsphere-jdbc/builtin-algorithm/load-balance)。
查询一致性路由的详情，请参见[使用规范](/cn/features/readwrite-splitting/use-norms)。
