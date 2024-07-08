mod=998244353
mod_syou=119
mod_log=23
mod_kon=3
root=pow(mod_kon,mod_syou,mod)
rootinv=pow(root,mod-2,mod)
bbeki=[0]*(mod_log+1)
bbeki2=[0]*(mod_log+1)
beki_dic=dict()
this=1
for i in range(mod_log+1):
  beki_dic[this]=i
  this*=2
def beki_mae():
  x=root
  beki=1
  for i in range(mod_log,-1,-1):
    bbeki[i]=x
    x=x**2
    x%=mod
  x=rootinv
  beki=1
  for i in range(mod_log,-1,-1):
    bbeki2[i]=x
    x=x**2
    x%=mod
beki_mae()
def rev(i,k):
  ans=0
  s=bin(i)[2:]
  for i in range(len(s)):
    if s[len(s)-1-i]=="1":
      ans+=2**(k-1-i)
  return ans
def NTT_len(a):#a<=2^Nを満たす最小のNを返す
  k=1
  b=0
  for i in range(100):
    if k>=a:
      break
    else:
      k*=2
      b+=1
  return b
def NTT_change0(A,k):#長さ2^kにする
  N=len(A)
  A=A+[0]*(2**k-N)
  NTT_change(A)
  return A
def NTT_change(A):#Aをstart<=k<finまでNTT変換,f(x^i)(i=0~N-1)を求める
  N=len(A)
  le=N
  while le>1:
    x=bbeki[beki_dic[le]]
    for st in range(N//le):
      this=1
      for i in range(le//2):
        l=A[st*le+i]
        r=A[st*le+i+le//2]
        A[st*le+i]=(l+r)%mod
        A[st*le+i+le//2]=((l-r)*this)%mod
        this*=x
        this%=mod
    le//=2

def NTT_invchange0(A,k):#長さ2^kにする
  N=len(A)
  A=A+[0]*(2**k-N)
  NTT_invchange(A)
  return A
def NTT_invchange(A):#Aをstart<=k<finまでNTT変換,f(x^i)(i=0~N-1)を求める
  N=len(A)
  le=2
  while le<=N:
    x=bbeki2[beki_dic[le]]
    for st in range(N//le):
      this=1
      for i in range(le//2):
        l=A[st*le+i]
        r=A[st*le+i+le//2]*this
        r%=mod
        A[st*le+i]=(l+r)%mod
        A[st*le+i+(le//2)]=(l-r)%mod
        this*=x
        this%=mod
    le*=2
  invN=pow(N,mod-2,mod)
  for i in range(N):
    A[i]*=invN
    A[i]%=mod
def NTT_time(A,B):#A,Bの畳み込み
  n=len(A)
  m=len(B)
  k=NTT_len(n+m-1)
  A=NTT_change0(A,k)
  B=NTT_change0(B,k)
  c=list()
  for i in range(len(A)):
    c.append(A[i]*B[i]%mod)
  c=NTT_invchange0(c,k)
  return c[:n+m-1]

def NTTinv(f):
  """
  1/f=gをreturn
  """
  le=len(f)
  this_roop=NTT_len(len(f))
  c=pow(int(f[0]),mod-2,mod)
  g=[c]
  a=1
  for i in range(this_roop):
      a*=2
      S=NTT_time(g,f)[:a]
      S=[-i%mod for i in S]
      S[0]+=2
      g=NTT_time(g,S)[:a]
  return g[:le]
#f[0]==0の時バグりそう。

#f[0]==0の時バグりそう。

N,K=map(int,input().split())
c=list(map(int,input().split()))
mod=998244353
d=dict()
for i in c:
  if i in d:
    d[i]+=1
  else:
    d[i]=1
X=list()
for i in d:
  if d[i]==1:
    continue
  else:
    X.append(d[i])
X.sort()
T=[1]
#(1+x)...(1+(d[i]-1)x)を全体にかける
#print(X)
from collections import deque
T=deque()
for i in X:
  for j in range(1,i):
    T.append([1,j])
#a
if len(T)==0:
  if K%2==0:
    print(1)
  else:
    print(0)
  exit()
while len(T)>1:
  a=T.popleft()
  b=T.popleft()
  T.append(NTT_time(a,b))

#print(T)
ans=0
for i in range(len(T[0])):
  if i%2==K%2 and i<=K:
    ans+=T[0][i]
    ans%=mod
print(ans)