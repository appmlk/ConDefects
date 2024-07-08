import bisect
N=int(input())
eng="abcdefghijklmnopqrstuvwxyz"
engd=dict()
for i in range(26) : engd[eng[i]]=i

s=input()
S=[[]for _ in range(26)]
for i in range(len(s)) : S[engd[s[i]]].append(i)

t=input()
T=[]
for i in t : T.append(engd[i])
for i in T:
    if len(S[i])==0 : exit(print(0))

def can(n):
    n1,n2=0,-1
    for i in T:
        p=bisect.bisect_left(S[i],n2)
        p+=n-1
        n1+=p//len(S[i])
        n2=S[i][p%len(S[i])]
    return n1<N

left=-1
right=2**60
while left!=right:
    temp=(left+right+1)//2
    if can(temp): left=temp
    else: right=temp-1
print(max(0,left))