import math
from heapq import heapify, heappop, heappush
# import bisect
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)
def ipt(num=0):
    if num==1:return int(input())
    elif num==2:return map(int, input().split())
    elif num==3:return input().rstrip()
    return list(map(int, input().split()))
# mod = 998244353
# d = [[] for i in range(n)]
# for i in range(n):
n=ipt(1)
a=ipt()
class SegTree():
    def __init__(self,n) -> None:
        self.N0 = 2**(n-1).bit_length()
        self.INF = 2**60
        self.data = [self.INF]*(2*self.N0)
# a_k の値を x に更新
    def update(self, k, x):
        k += self.N0-1
        self.data[k] = x
        while k >= 0:
            k = (k - 1) // 2
            self.data[k] = min(self.data[2*k+1], self.data[2*k+2])
    # 区間[l, r)の最小値
    def query(self, l, r):
        L = l + self.N0; R = r + self.N0
        s = self.INF
        while L < R:
            if R & 1:
                R -= 1
                s = min(s, self.data[R-1])

            if L & 1:
                s = min(s, self.data[L-1])
                L += 1
            L >>= 1; R >>= 1
        return s
inf=1<<40
l=[inf]*n
st=SegTree(n+1);st2=SegTree(n+1)
st.update(a[0],-a[0]-1)
st2.update(a[0],a[0]-1)
for i in range(1,n):
    s=st.query(0,a[i])+a[i]+i+1
    ss=st2.query(a[i]+1,n+1)-a[i]+i+1
    l[i]=min(s,ss)
    st.update(a[i],-a[i]-i-1)
    st2.update(a[i],a[i]-i-1)
st=SegTree(n+1);st2=SegTree(n+1)
st.update(a[n-1],-a[n-1]+n)
st.update(a[n-1],a[n-1]+n)
for i in range(n-2,-1,-1):
    s=st.query(0,a[i])+a[i]-i-1
    ss=st2.query(a[i]+1,n+1)-a[i]-i-1
    s=min(s,ss);l[i]=min(s,l[i])
    st.update(a[i],-a[i]+i+1)
    st2.update(a[i],a[i]+i+1)
print(*l)
