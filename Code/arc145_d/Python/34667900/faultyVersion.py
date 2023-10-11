from functools import lru_cache

@lru_cache(maxsize=None)
def a(x):
    if x==0:return 1
    if x&1:return 3*a(x//2)-1
    return 3*a(x//2)-2

n,m=map(int,input().split())
if n==1:
    print(m)
    exit(0)

ANS=[a(x) for x in range(n-1)]
s=sum(ANS)
# print(ANS,s)

target=abs(m)+2*10**6
plus=(target-s)//(n-1)+1
ANS=[num+plus for num in ANS]
s=sum(ANS)
# print(ANS,s)

ANS.append(abs(m)-s)
# print(ANS,sum(ANS))

if m<0:
    ANS=[-num for num in ANS]

print(*ANS)
# print(sum(ANS))