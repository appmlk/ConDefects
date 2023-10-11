# https://atcoder.jp/contests/arc158/tasks/arc158_c
n = int(input())
a = list(map(int,input().split()))
ans = 0

# 思考按数位进行枚举？进位再进位何解？
# 单独去掉进位的部分
for i in a:
    while i:
        ans += i%10 *2*n
        i //= 10

import bisect
for i in range(1,14):
    b = sorted([x%(10**i) for x in a])
    for x in b:
        ans -= 9*(n - bisect.bisect_left(b,10**i - x))
print(ans)