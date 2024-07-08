# import sys
# sys.setrecursionlimit(10**6)
# import pypyjit
# pypyjit.set_param("max_unroll_recursion=-1")
# sys.set_int_max_str_digits(10**6)

# mod = 998244353
# ds = [(-1,0),(0,1),(1,0),(0,-1)]
# inf = float('inf')
# ni,nj=i+di,j+dj
# 0<=ni<H and 0<=nj<W
# alph = 'abcdefghijklmnopqrstuvwxyz'
def rint(offset=0,base=10): return list(map(lambda x: int(x, base)+offset, input().split())) 
def full(s, f=int, *args): return [full(s[1:], f) if len(s) > 1 else f(*args) for _ in range(s[0])]
def shift(*args,offset=-1): return (a+offset for a in args)

B, = rint()

ans =-1
for A in range(1,20):
    if A**A == B :
        ans = A
        break
print(ans)