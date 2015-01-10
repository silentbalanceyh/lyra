# com.lyra.util.sys

## 系统内部工具包

### 基本规则

1. 这个工具包中的Pre Condition检查使用Java中的assert，不使用AOP切面；
2. 这个工具包中对应的TestCase和包同步；

### 基本功能

1. 做基本检查，包括NULL校验、EMPTY校验、数组长度ZERO校验
2. 对象相等性判断
3. 对象hash值计算（Java默认算法）