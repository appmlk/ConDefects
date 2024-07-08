from bisect import*
B=bisect_left
N,*A=map(int,open(0).read().split())
o=[1]*N
F=1<<60
A=A+[-F]
for k in[-1,1]:
 d=[F]*N
 for i in range(N):o[i]+=B(d,A[i+k]-k);d[B(d,A[i])]=A[i]
 o=o[::-1];A=[-e for e in A][::-1][1:];A+=F,
print(max(o))