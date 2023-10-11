# import系 ---
from math import gcd

# 入力用 ---
INT = lambda: int(input())
MI = lambda: map(int, input().split())
MI_DEC = lambda: map(lambda x: int(x) - 1, input().split())
LI = lambda: list(map(int, input().split()))
LS = lambda: input().split()

# リストの最大公約数 ---
def gcd_all(li):
    g = li[0]
    for c in li[1:]:
        g = gcd(g, c)
    return g

# コード ---
N = INT()
a_list = LI()
gcd_a = gcd_all(a_list)

cnt = 0
for a in a_list:
    a //= gcd_a
    
    while a % 2 == 0:
        cnt += 1
        a //= 2
    
    while a % 3 == 0:
        cnt += 1
        a //= 2
    
    if a > 1:
        print(-1)
        exit()

print(cnt)
