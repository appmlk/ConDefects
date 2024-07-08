N = int(input())
A = list(map(int, input().split()))
# Aに0が入っている場合は、0の個数(n_0)*n - (n_0 + 1)*n_0/2
n_0 = A.count(0)

import math
import collections

# 素因数分解する
A = [a for a in A if a != 0] # 0を除外
B = [] # Aの各要素を平方数で割った残りを入れる
for i in range(len(A)):
    a = A[i]
    for j in range(2,int(math.sqrt(a))+1):
        while a%(j**2) == 0:
            a //= j**2
    B.append(a)

ans = 0
# Aに0が入っている場合は、0の個数(n_0)*n - (n_0 + 1)*n_0/2
ans += (n_0)*N - (n_0 + 1)*n_0//2

# あとは、Bの中に何個同じものがあるか
for i in collections.Counter(B).values():
    ans += i * (i-1)//2

print(ans)