N = int(input())
A = list(map(int,input().split()))
B=[]
for i in range(1000000):
  B.append(0)
for i in A:
  B[i]+=1
ans = N*(N-1)*(N-2)/6
for i in B:
  ans -= i*(i-1)/2*(N-i)
  ans -= i*(i-1)*(i-2)/6
print(int(ans))