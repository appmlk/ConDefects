#import
import sys
import string
import collections
import itertools
import math

#constant
alphabet=string.ascii_lowercase
ALPHABET=string.ascii_uppercase
inf=float("inf")
mod=998244353
sift4=((-1,0),(0,-1),(1,0),(0,1))
sift6=((-1,0,0),(0,-1,0),(0,0,-1),(1,0,0),(0,1,0),(0,0,1))
sift8=((-1,0),(-1,-1),(0,-1),(1,-1),(1,0),(1,1),(0,1),(-1,1))

#input
def inputs():return input().split()
def iinput():return int(input())
def sinput():return set(input())
def linput():return list(input())
def linputs():return list(inputs())
def iinputs():return map(int,inputs())
def liinputs():return list(iinputs())
def tiinputs():return tuple(iinputs())
def sinputr(i):return set(input()for _ in range(i))
def linputr(i):return list(input()for _ in range(i))
def llinputr(i):return list(linput()for _ in range(i))
def llinputsr(i):return list(linputs()for _ in range(i))
def lliinputsr(i):return list(liinputs()for _ in range(i))
def stiinputsr(i):return set(tiinputs()for _ in range(i))

#conversion
def A_to_a(c):return chr(ord(c)+32)
def a_to_A(c):return chr(ord(c)-32)
def f_to_i(f):
    f_i,f_f = str(f).split(".")
    return int(f_i) if int(f_f[0]) < 5 else int(f_i)+1

#judge
def palindrome(a,b):return True if a == b[::-1] else False

#patterns
def distinct_strings(s):
    st = set()
    for S in itertools.permutations(s):st.add("".join(S))
    return list(st)
def manhattan(i):
    ans = set()
    for I in range(i+1):
        ans.add((i-I,I))
        ans.add((I-i,I))
        ans.add((i-I,-I))
        ans.add((I-i,-I))
    return ans

#math
def modulo(i):return i % mod
def round_my(i_or_f,i=0):None
def sumr(l):return sum(map(sum,l))

#class
class UnionFind():
    def __init__(self,n):
        self.n=n
        self.parents=[-1]*n
    def find(self,x):
        if self.parents[x]<0:return x
        else:
            self.parents[x]=self.find(self.parents[x])
            return self.parents[x]
    def union(self,x,y):
        x=self.find(x)
        y=self.find(y)
        if x==y:return
        if self.parents[x]>self.parents[y]:x,y=y,x
        self.parents[x]+=self.parents[y]
        self.parents[y]=x
    def size(self,x):return -self.parents[self.find(x)]
    def same(self,x,y):return self.find(x)==self.find(y)
    def members(self,x):
        root=self.find(x)
        return [i for i in range(self.n)if self.find(i)==root]
    def roots(self):return [i for i,x in enumerate(self.parents)if x<0]
    def group_count(self):return len(self.roots())
    def all_group_members(self):
        group_members = collections.defaultdict(list)
        for member in range(self.n):group_members[self.find(member)].append(member)
        return group_members
    def __str__(self):return '\n'.join(f'{r}:{m}'for r,m in self.all_group_members().items())

#others
def minreverse(a):return min(a,a[::-1])
def dif_chr(sa,sb):
    ans=0
    for SA,SB in zip(sa,sb):
        if SA!=SB:ans += 1
    return ans
def mkl(n,*a):return list(mkl(n,*a[:-1])for A in range(a[-1]))if a else n
def mkt(i,l):
    ans=[[]for I in range(i)]
    for L1,L2 in l:
        ans[L1-1].append(L2-1)
        ans[L2-1].append(L1-1)
    return ans

#output
def Y_or_N(o):print("Yes" if o else "No")
def a_or_b(a,o,b):print(a if o else b)
def printr(a):
    for A in a:print(A)
def lprintr(a):
    for A in a:print(*A)

#----------------------------------------------------------------

n,m = iinputs()
b = lliinputsr(n)

ans = True

for M in range(m-1):
    b1,b2 = b[0][M:M+2]
    if b1+1 != b2 or b1%7+1 != b2%7:ans = False

for N in range(n-1):
    for M in range(m):
        if b[N][M]+7 != b[N+1][M]:ans = False

Y_or_N(ans)